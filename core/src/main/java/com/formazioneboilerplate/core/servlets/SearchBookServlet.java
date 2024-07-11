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
        JsonObject bodyParameter;
        PrintWriter out = response.getWriter();

        bodyParameter=bodyRequest(request);
        //se il bodyParameter ha almeno un elemento al suo interno
        getSearch(bodyParameter, request, responseResult);

        out.write(responseResult.toString());
        out.close();
    }

    /**
     *
     * @param request richiesta fatta alla servlet
     * @return quelo che troviamo nel body della request
     */
    private JsonObject bodyRequest ( SlingHttpServletRequest request){
        JsonObject bodyParameter = new JsonObject();
        try{
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                jsonString.append(line);
            }
            JsonParser jsonParser = new JsonParser();
            //inseriamo quello che abbiamo nel body della request in un JsonObject
            bodyParameter = jsonParser.parse(jsonString.toString()).getAsJsonObject();

        } catch (IOException e) {
            LOG.error("## SearchBookServlet - doPost failed IOException launched ###", e);
        }
        return bodyParameter;
    }

    /**
     *
     * @param bodyParameter body della request
     * @param request richiesta fatta alla servlet
     * @param responseResult json in response
     */
    private void getSearch(JsonObject bodyParameter, SlingHttpServletRequest request, JsonObject responseResult){
        // json array che conterrà json di risposta alla ricerca
        JsonArray results = new JsonArray();
        if(bodyParameter.size()>0 && bodyParameter.has("searchBy")){
            JsonElement searchByJsonElement = bodyParameter.get("searchBy");
            if(!searchByJsonElement.isJsonNull()){
                String searchBy = searchByJsonElement.getAsString();
                if(StringUtils.isNotBlank(searchBy)){
                    //si separa il body della request-> tipo di ricerca - valore di ricerca
                    String[] split = searchBy.split("#");

                    if(split[0].equals("authorName")|| split[0].equals("category") || split[0].equals("datePublish"))
                    {
                        responseResult.addProperty("searchBy", "{"+ split[0] + "}"+" + "+"{" + split[1]+"}");
                        //metodo per attivare e la risposta della servlet
                        getResearch(request, responseResult, results, split[0], split[1]);
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
    }

    private void getResearch(SlingHttpServletRequest request, JsonObject responseResult, JsonArray results, String split0, String split1){
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

                    String searchProperty = properties.get(split0, String.class);
                    if(searchProperty != null && !searchProperty.isEmpty()){
                        JsonObject jsonPageResult = new JsonObject();
                        switch(split0){
                            case "authorName":
                            case "category":
                                if(searchProperty.equals(split1)) {
                                    jsonPageResult = checkParameters(resourceResolver, bookCurrentPagePath);
                                    if (jsonPageResult.size() > 0) {
                                        results.add(jsonPageResult);
                                    }
                                }
                                break;
                            case "datePublish":
                                LocalDate dateRequest = LocalDate.parse(split1);
                                LocalDate dateBookPublish = LocalDate.parse(searchProperty.split("T")[0]);
                                if(dateBookPublish.isAfter(dateRequest)){
                                    jsonPageResult = checkParameters(resourceResolver, bookCurrentPagePath);
                                    if(jsonPageResult.size()>0){
                                        results.add(jsonPageResult);
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
                responseResult.add("results", results);
            }
        }
    }

    private JsonObject checkParameters(ResourceResolver resourceResolver, String bookCurrentPagePath) {
        JsonObject json = new JsonObject();
        Resource resource = resourceResolver.getResource(bookCurrentPagePath + bookContaneirPath);
        if(resource != null){

            //ottengo le informazioni che voglio vedere nel json delle response
            getProperty(json,resource, "title", "jcr:title", "bookName");
            getProperty(json, resource, "text", "text", "bookDescription");
            String bookPagePath = resource.getPath();
            json.addProperty("bookPagePath", bookPagePath);
        }
        return json;
    }

    private void getProperty(JsonObject json, Resource resource, String searchValue, String nameProperty, String bookInfomation) {
        Resource propertyPage = resource.getChild(searchValue);
        if(propertyPage != null){
            String bookInfo = propertyPage.getValueMap().get(nameProperty, String.class);
            if(bookInfo != null){
                json.addProperty(bookInfomation, bookInfo);
            }
        }
    }
}
