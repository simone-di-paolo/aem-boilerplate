/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.formazioneboilerplate.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterStartModel {

    @ValueMapValue
    private String label;

    @ValueMapValue
    private String link;


    @SlingObject
    private ResourceResolver resourceResolver; // serve per accedere a JCR e verificare se link esiste

    public String getLabel() {
        return label;
    }

    public String getLink() {
        if (link != null && !link.isEmpty() && isValidPagePath(link)) {
            return link;
        }
        return null;
    }


    private boolean isValidPagePath(String path) {
        if (path.endsWith(".html")) {
            path = path.replace(".html", ""); //Togliere estensione html perché sennò ResourceResolver/PageManager non funzionano
        }
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class); //ResourceResolver per adattarsi a PageManager (permette di recuperare una pagina a partire da un path)
        if (pageManager != null) {
            Page page = pageManager.getPage(path);  //cerca se pagina esiste o meno
            return page != null;
        }
        return false;
    }
}
