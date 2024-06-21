package com.formazioneboilerplate.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface BannerComponent {

    @Inject
    List<ElementsBanner> getElementsBanners();

    @Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    interface ElementsBanner{

        @Inject
        String getImage();

        @Inject
        String getTitle();

        @Inject
        String getSubtitle();

        @Inject
        String getText();

        @Inject
        String getSelectTheme();
    }
}
