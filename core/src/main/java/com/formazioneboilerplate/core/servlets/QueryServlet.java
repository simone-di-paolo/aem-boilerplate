package com.formazioneboilerplate.core.servlets;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(name="Query Servlet",
        service= Servlet.class,
        property={"service.description=Query Servlet Description",
                "sling.servlet.paths=/bin/QueryServlet",
                "sling.servlet.methods=GET"}
)
public class QueryServlet extends SlingSafeMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(SearchBookServlet.class);

    @Reference
    private QueryBuilder queryBuilder;

    private static final String bookParentPagePath = "/content/formazioneboilerplate/language-masters/en/books";

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {
        LOG.info("## SearchQueryServlet - doGet started ###");

        String searchTerm_1 = request.getParameter("searchTerm_1");
        String valueTerm_1 = request.getParameter("valueTerm_1");
        String searchTerm_2 = request.getParameter("searchTerm_2");
        String valueTerm_2 = request.getParameter("valueTerm_2");
        Map<String, String> queryMap = new HashMap<>();

        ResourceResolver resourceResolver = request.getResource().getResourceResolver();
        queryMap.put("path", bookParentPagePath);
        queryMap.put("type", "cq:Page");
        queryMap.put("group.p.and", "true");
        queryMap.put("group.1_property", searchTerm_1);
        queryMap.put("group.1_property.value", valueTerm_1);
        queryMap.put("group.1_property.operation", "equals");
        queryMap.put("group.2_property", searchTerm_2);
        queryMap.put("group.2_property.value", valueTerm_2);
        queryMap.put("group.2_property.operation", "equals");
        queryMap.put("p.and", "true");
        queryMap.put("p.offset", "0");
        queryMap.put("p.limit", "10");
        queryMap.put("orderby", "jcr:content/cq:lastModified");
        queryMap.put("orderby.sort", "desc");

        Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap), resourceResolver.adaptTo(Session.class));
        SearchResult result = query.getResult();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int i = result.getQueryStatement().indexOf("[");
        int f = result.getQueryStatement().indexOf("]");
        String startSubstring = result.getQueryStatement().substring(0, i);
        String endSubstring1= result.getQueryStatement().substring(i + 1, f);
        response.getWriter().write("{ \"results\": "
                + startSubstring + "\n [\n"
                + endSubstring1 + "\n]" + "\n}");

        resourceResolver.close();

        LOG.info("## SearchQueryServlet - doGet finished ###");
    }

}
