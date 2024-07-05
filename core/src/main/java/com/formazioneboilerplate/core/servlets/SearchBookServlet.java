package com.formazioneboilerplate.core.servlets;


import com.day.cq.wcm.api.PageManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Iterator;

import static com.formazioneboilerplate.core.servlets.SearchBookServlet.DEFAULT_SELECTOR;


@Component(name="Search Book Servlet",
        service= Servlet.class,
        property={"service.description=Search Book Servlet Description",
                "sling.servlet.selectors=" + DEFAULT_SELECTOR,
                "sling.servlet.resourceTypes=cq/Page",
                "sling.servlet.extensions=html",
                "sling.servlet.methods=" + HttpConstants.METHOD_POST}
)

public class SearchBookServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SearchBookServlet.class);

    protected static final String DEFAULT_SELECTOR = "bookFinder";

    private static final String bookParentPagePath = "/content/formazioneboilerplate/language-masters/en/books";

    private static final String bookContaneirPath = "/jcr:content/root/container/container";

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("## SearchBookServlet - doPost started ###");
        response.setContentType("application/json");
        JsonObject responseResult = new JsonObject();
        // json array che conterrà json di risposta alla ricerca
        JsonArray results = new JsonArray();
        // json che mandiamo in request per la ricerca
        JsonObject bodyParameter = new JsonObject();
        PrintWriter out = response.getWriter();

        try {
            //leggiamo carattere per carattere quello che abbiamo nel body della request
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                jsonString.append(line);
            }
            JsonParser jsonParser = new JsonParser();
            //inseriamo quello che abbiamo nel body della request in un JsonObject
            bodyParameter = jsonParser.parse(jsonString.toString()).getAsJsonObject();

            //se il bodyParameter ha almeno un elemento al suo interno
            if(bodyParameter.size()>0 && bodyParameter.has("searchBy")){
                JsonElement searchByJsonElement = bodyParameter.get("searchBy");
                if(!searchByJsonElement.isJsonNull()){
                    String searchBy = searchByJsonElement.getAsString();
                    if(StringUtils.isNotBlank(searchBy)){
                        if(searchBy.equals("authorName")|| searchBy.equals("category") || searchBy.equals("datePublish"))
                        {
                            //si separa il body della request-> tipo di ricerca - valore di ricerca
                            String[] split = searchBy.split("#");
                            responseResult.addProperty("searchBy", "{"+ split[0] + "}"+" + "+"{" + split[1]+"}");

                                //qui io sto ricavando attraverso la risorsa la pagina in cui la servlet è stata chiamata
                                Resource resource = request.getResource();
                                Page currentPage = resource.adaptTo(Page.class);
                                // il resourceResolver mi permette di capire se il persorso che io mando alla risolsa esiste o meno
                                ResourceResolver resourceResolver = resource.getResourceResolver();

                                if(currentPage!= null){
                                    // adatto la currentPage ad essere di tipo PageManager così da poter utilizzare i suoi metodi
                                    PageManager pageManager = currentPage.getPageManager();
                                    if(pageManager != null){
                                        //il pageManager mi permette di richiamare le pagine a cui sono interessata attraverso il metodo getPage
                                        Page booksParentPage = pageManager.getPage(bookParentPagePath);
                                        Iterator<Page> booksChildren = booksParentPage.listChildren();
                                        // se l'itiratore ha figli entra nel ciclo
                                        while (booksChildren.hasNext()) {
                                            // prendi il prossimo, se 0 allora prendi il primo
                                            Page bookPage = booksChildren.next();
                                            String bookCurrentPagePath = bookPage.getPath();
                                            ValueMap properties = bookPage.getProperties();

                                            String searchProperty = properties.get(split[0], String.class);
                                            if(searchProperty != null && !searchProperty.isEmpty()){
                                                JsonObject jsonPageResult = new JsonObject();
                                                switch(split[0]){
                                                    case "authorName":
                                                    case "category":
                                                        if(searchProperty.equals(split[1])) {
                                                            jsonPageResult = checkParameters(resourceResolver, bookCurrentPagePath, searchProperty, split[1]);
                                                            if (jsonPageResult.size() > 0) {
                                                                results.add(jsonPageResult);
                                                            }
                                                        }
                                                        break;
                                                    case "datePublish":
                                                        LocalDate dateRequest = LocalDate.parse(split[1]);
                                                        LocalDate dateBookPublish = LocalDate.parse(searchProperty.split("T")[0]);
                                                        if(dateBookPublish.isAfter(dateRequest)){
                                                            jsonPageResult = checkParameters(resourceResolver, bookCurrentPagePath, dateBookPublish.toString(), split[1]);
                                                            if(jsonPageResult.size()>0){
                                                                results.add(jsonPageResult);
                                                            }
                                                        }
                                                        LOG.info("LocalDate.parse(searchProperty.split(\"T\")[0]).isBefore(LocalDate.parse(split[1]))");
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                        responseResult.add("results", results);
                                    }
                                }
                        } else {
                            responseResult.addProperty("{error", "the value of searchBy parameter in request body is not valid}");
                            LOG.info("## SearchBookServlet -  the value of searchBy parameter in request body is not valid ###");
                        }
                    } else {
                        responseResult.addProperty("{error", "the value of searchBy parameter in request body is empty}");
                        LOG.info("## SearchBookServlet -  the value of searchBy parameter in request body is empty ###");
                    }
                } else {
                    responseResult.addProperty("{error", "the value of searchBy parameter in request body is null}");
                    LOG.info("## SearchBookServlet -  the value of searchBy parameter in request body is null ###");
                }
            } else {
                responseResult.addProperty("{error", "missing searchBy parameter in request body}");
                LOG.info("## SearchBookServlet -  missing searchBy parameter in request body ###");
            }
            out.write(responseResult.toString());
        } catch (IOException e) {
            LOG.error("## SearchBookServlet - doPost failed IOException launched ###", e);
        } finally{
            out.close();
        }

    }


    private JsonObject checkParameters(ResourceResolver resourceResolver, String bookCurrentPagePath, String propertyPage, String valueSearch) {
        JsonObject json = new JsonObject();
        Resource resource = resourceResolver.getResource(bookCurrentPagePath + bookContaneirPath);
        if(resource != null){

            // cerco il nodo title, figlio del nodo container, e vedo a cosa corrisponde nella valueMap e lo salvo nella property nel json
            Resource title = resource.getChild("title");
            if(title != null){
                String bookTitle = title.getValueMap().get("jcr:title", String.class);
                json.addProperty("bookName", bookTitle);
            }

            // cerco il resourceType di tipo text e vedo a cosa corrisponde nella valueMap e lo salvo nella property nel json
            Resource text = resource.getChild("text");
            if(text != null){
                String bookDescription = text.getValueMap().get("text", String.class);
                json.addProperty("bookDescription", bookDescription);
            }

            // prendo il path del resourse della pagina e lo salvo come property nel json
            String bookPagePath = resource.getPath();
            json.addProperty("bookPagePath", bookPagePath);
        }
        return json;
    }

}
