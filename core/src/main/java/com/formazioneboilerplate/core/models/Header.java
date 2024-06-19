package com.formazioneboilerplate.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


import javax.inject.Inject;
import java.util.List;

@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface Header {

    @Inject
    List<MenuItem> getMenuItems();

    @Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    interface MenuItem {
        @Inject
        String getNavLabel();

        @Inject
        List<SubMenuItem> getSubMenuItems();

        @Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
        interface SubMenuItem {
            @Inject
            String getSubLabel();

            @Inject
            String getSubLink();
        }
    }
}
