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

@Component(name="Get EndPoint Example Componet Servlet",
        service=Servlet.class,
        property={"service.description=Get EndPoint Example Componet Servlet Description",
                "sling.servlet.paths=/bin/GetEndPointExampleComponetServlet",
                "sling.servlet.methods=GET"}
)


public class GetEndPointExampleComponentServlet extends SlingSafeMethodsServlet {
    @Reference
    private Connector connector;

    @Reference
    private EndPointConfigurationsService endPointConfigurationsService;

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {

        JsonArray result = new JsonArray();

        JsonObject jsonObject = connector.executeGet(endPointConfigurationsService.getEndPointExample(), null, null);

        JsonElement films = jsonObject.get("films");
        JsonArray asJsonArray = films.getAsJsonArray();

        for (JsonElement c : asJsonArray) {
            JsonObject cObj = c.getAsJsonObject();
            result.add(cObj);
        }

        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result.toString());
    }
}

