package com.formazioneboilerplate.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;


@Component(service = { Servlet.class }, property = {
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=/bin/myservletChiara",
            })

public class FormServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(FormServlet.class);

    @Override
    public void doPost (SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {
        String birthdate = request.getParameter("datadinascita");
        String nationality = request.getParameter("nazionalita");
        String address = request.getParameter("indirizzo");

        LOG.debug("Datadinascita = {}", birthdate);
        LOG.debug("Nazionalita = {}", nationality);
        LOG.debug("Indirizzo = {}", address);

        JsonObjectBuilder object = Json.createObjectBuilder();
        object.add("statoOperazione", "operazione avvenuta con successo");
        object.add("datadinascita", birthdate);
        object.add("nazionalita", nationality);
        object.add("indirizzo", address);


        response.setContentType("application/json"); //dice al client che la risposta della servlet è in formato json
        response.getWriter().write(object.build().toString());
    }
}
