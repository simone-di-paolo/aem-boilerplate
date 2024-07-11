package com.formazioneboilerplate.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;


@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SpaPageModel {

    private static final String CONGIF_PAGE_PTH = "/content/formazioneboilerplate/language-masters/en/spa-home";

    @Inject
    private Page currentPage;

    private String logoImage;

    @PostConstruct
    protected void init() {
        //Resource currentePageResource = currentPage.adaptTo(Resource.class);
        PageManager pageManager = currentPage.getPageManager();
        Page spaPage = pageManager.getPage(CONGIF_PAGE_PTH);

        ValueMap properties = spaPage.getProperties();
        logoImage = properties.get("logoImage", String.class);

    }

    public String getLogoImage() {
        return logoImage;
    }
}
