package com.formazioneboilerplate.core.servlets;

import com.formazioneboilerplate.core.service.EndPointConfigurationsService;
import com.formazioneboilerplate.core.servlets.connector.Connector;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(name="GET EndPoint Search Servlet",
        service=Servlet.class,
        property={"service.description=Get EndPoint Search Servlet Description",
                "sling.servlet.paths=/bin/GetEndPointSearchServlet",
                "sling.servlet.methods=GET"}
)


public class GetEndPointSearchServlet extends SlingSafeMethodsServlet {
    @Reference
    private Connector connector;

    @Reference
    private EndPointConfigurationsService endPointConfigurationsService;

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {

        JsonArray result = new JsonArray();

        String type = request.getParameter("type");
        String locationParam = request.getParameter("location");

        JsonObject jsonObject = connector.executeGet(endPointConfigurationsService.getEndPointSearch(), null, null);

        JsonElement properties = jsonObject.get("properties");
        JsonArray asJsonArray = properties.getAsJsonArray();
        for (JsonElement c : asJsonArray) {
            JsonObject cObj = c.getAsJsonObject();
            if(cObj.get("location").getAsString().equals(locationParam)){
                switch (type) {
                    case "For Rent":
                    case "For Sell": //JsonElement jsonElement = cObj;
                        /*if (jsonElement != null) {
                            result = cObj.getAsJsonArray();
                        }*/
                        result.add(cObj);
                }
            }
        }
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result.toString());
    }
}

