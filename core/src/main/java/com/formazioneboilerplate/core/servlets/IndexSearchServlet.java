package com.formazioneboilerplate.core.servlets;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.formazioneboilerplate.core.service.EndPointConfigurationsService;
import com.formazioneboilerplate.core.servlets.connector.Connector;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(name="GET Index Search Servlet",
        service=Servlet.class,
        property={"service.description=Index Search Servlet Description",
                "sling.servlet.paths=/bin/IndexSearchServlet",
                "sling.servlet.methods=GET"}
)


public class IndexSearchServlet extends SlingSafeMethodsServlet {

    @Reference
    private QueryBuilder queryBuilder;

    private static final String propertyParentPagePath = "/content/formazioneboilerplate/language-masters/en/selled-properties";

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {

        JsonArray jsonArray = new JsonArray();
        String searchTerm = request.getParameter("fulltext");

        if(StringUtils.isNotBlank(searchTerm)) {

            Map<String, String> queryMap = new HashMap<>();

            ResourceResolver resourceResolver = request.getResource().getResourceResolver();
            queryMap.put("path", propertyParentPagePath);
            queryMap.put("type", "cq:Page");
            queryMap.put("group.p.and", "true");
            queryMap.put("group.1_group.fulltext", searchTerm);
            queryMap.put("orderby", "jcr:content/cq:lastModified");
            queryMap.put("orderby.sort", "desc");


            Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap), resourceResolver.adaptTo(Session.class));
            SearchResult result = query.getResult();

            Iterator<Resource> resources = result.getResources();
            while(resources.hasNext()){
                JsonObject jsonObject = new JsonObject();
                Resource resource = resources.next();
                Resource title = resource.getChild("jcr:content/root/container/title");
                if(title != null){
                    ValueMap titleProperties = title.getValueMap();
                    String s = titleProperties.get("jcr:title", String.class);
                    jsonObject.addProperty("title", s);
                }
                Resource text = resource.getChild("jcr:content/root/container/text");
                if(text != null){
                    ValueMap textProperties = text.getValueMap();
                    String t = textProperties.get("text", String.class);
                    jsonObject.addProperty("text", t);
                }
                Resource image = resource.getChild("jcr:content/root/container/image");
                if(image != null){
                    ValueMap imageProperties = image.getValueMap();
                    String i = imageProperties.get("fileReference", String.class);
                    jsonObject.addProperty("imagePath", i);
                }

                if(jsonObject.size()>0){
                    jsonArray.add(jsonObject);
                }
            }
        }
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonArray.toString());

    }
}

