/* Ho seguito questo video
https://www.youtube.com/watch?v=PnhqPic4Rc4
 */
package com.formazioneboilerplate.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = {Servlet.class}, //componente OSGI
            property = {
                    "sling.servlet.paths=/bin/news",})  //Per collegare servlet. Vedere i path nella configurazione OSGI nella console manager
/* Per collegare la servlet posso anche usare
@SlingServletResourceTypes()
@SlingServletPaths() */
public class ServletExample extends SlingSafeMethodsServlet {

    public void doGet (SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {   //Creare oggetti per prendere richiesta dal client e poi mandare risposta
        JsonObjectBuilder object = Json.createObjectBuilder();   //La risposta la voglia in formato json quindi ora creo oggetto json
        object.add("name", "surge Software SOlutions");     //Nelle parentesi scrivo prima la chiave e poi il valore
        object.add("site", "www.surge.com");
        response.getWriter().write(object.build().toString());    //Voglio scrivere nella pagina client i dati in Stringa (serve importare errore perché se non trova niente da scrivere manda quella)
    }

}
