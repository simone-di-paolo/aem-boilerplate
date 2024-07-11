package com.formazioneboilerplate.core.servlets;

import com.formazioneboilerplate.core.service.EndPointConfigurationsService;
import com.formazioneboilerplate.core.servlets.connector.Connector;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(name="Training Servlet",
           service=Servlet.class,
           property={"service.description=Training Servlet Description",
                     "sling.servlet.paths=/bin/TrainingServlet",
                     "sling.servlet.methods=GET"}
)


public class TrainingServlet extends SlingSafeMethodsServlet {
    @Reference
    private Connector connector;

    @Reference
    private EndPointConfigurationsService endPointConfigurationsService;

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonObject = connector.executeGet(endPointConfigurationsService.getEndPoint(), null, null);
        response.getWriter().write(jsonObject.toString());
    }

}
