package com.formazioneboilerplate.core.models;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import java.io.IOException;

// path to register the servlet
@Component(service = Servlet.class, property = {
        "sling.servlet.methods=POST",
        "sling.servlet.paths=/bin/formhandlerbenedetta"

}
)

public class SurveyServlet extends SlingAllMethodsServlet{

    public void doPost (SlingHttpServletRequest req, SlingHttpServletResponse res)
            throws IOException {

        // getParameter function to permit the servlet to read data
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthdate = req.getParameter("birthdate");
        String birthplace = req.getParameter("birthplace");


        // JSON answer of the received fields
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add ("status", "success");
        builder.add("name", name);
        builder.add("surname", surname);
        builder.add("birthdate", birthdate);
        builder.add("birthplace", birthplace);

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(builder.build().toString());

    }

}
