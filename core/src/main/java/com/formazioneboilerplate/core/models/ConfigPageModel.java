
package com.formazioneboilerplate.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.formazioneboilerplate.core.service.WebsiteConfigurationsService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ConfigPageModel {

    /* voglio che questa diventi dinamica tramite un servizio
     private static final String CONGIF_PAGE_PTH = "/content/formazioneboilerplate/configuration-page";
     */

    @Inject
    private Page currentPage;

    @Inject
    private WebsiteConfigurationsService websiteConfigurationsService;


    private String projectHeader;
    private String projectDescription;

    private String templateTitle;
    private String templateTitleCurrentPage;


    @PostConstruct
    protected void init() {
        //Resource currentePageResource = currentPage.adaptTo(Resource.class);
        PageManager pageManager = currentPage.getPageManager();
        Page configPage = pageManager.getPage(websiteConfigurationsService.getConfigPagePath());
        ValueMap properties = configPage.getProperties();
        projectHeader = properties.get("projectHeader", String.class);
        projectDescription = properties.get("projectDescription", String.class);

        // ottengo in nome del tamplate della pagina corrente
        templateTitleCurrentPage = currentPage.getTemplate().getProperties().get("jcr:title",String.class);
        templateTitle = properties.get("jcr:title", String.class);
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectHeader() {
        return projectHeader;
    }
    public String getTemplateTitle() {
        return templateTitle;
    }
    public String getTemplateTitleCurrentPage() {
        return templateTitleCurrentPage;
    }
}