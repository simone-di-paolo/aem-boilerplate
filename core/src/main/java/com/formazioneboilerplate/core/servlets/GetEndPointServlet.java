package com.formazioneboilerplate.core.servlets;

import com.formazioneboilerplate.core.bean.Cities;
import com.formazioneboilerplate.core.bean.City;
import com.formazioneboilerplate.core.bean.Local;
import com.formazioneboilerplate.core.service.EndPointConfigurationsService;
import com.formazioneboilerplate.core.servlets.connector.Connector;
import com.google.gson.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameterMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component(name="GET EndPoint Servlet",
        service=Servlet.class,
        property={"service.description=Get EndPoint Servlet Description",
                "sling.servlet.paths=/bin/GetEndPointServlet",
                "sling.servlet.methods=GET"}
)


public class GetEndPointServlet extends SlingSafeMethodsServlet {
    @Reference
    private Connector connector;

    @Reference
    private EndPointConfigurationsService endPointConfigurationsService;

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {

        JsonArray result = new JsonArray();

        String type = request.getParameter("type");
        String cityParam = request.getParameter("city");

        JsonObject jsonObject = connector.executeGet(endPointConfigurationsService.getEndPoint(), null, null);

        JsonElement cities = jsonObject.get("cities");
        JsonArray asJsonArray = cities.getAsJsonArray();
        for (JsonElement c : asJsonArray) {
            JsonObject cObj = c.getAsJsonObject();
            if(cObj.get("name").getAsString().equals(cityParam)){
                switch (type) {
                    case "restaurants":
                    case "bars":
                    case "pubs": JsonElement jsonElement = cObj.get(type);
                        if (jsonElement != null) {
                            result = jsonElement.getAsJsonArray();
                        }
                }
            }
        }
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result.toString());
    }
}

