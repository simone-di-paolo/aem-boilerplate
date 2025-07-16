package com.formazioneboilerplate.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterStartMultifieldModel {

  @Inject
    private List<FooterStartModel> footermenu;

    public List<FooterStartModel> getFootermenu() {
        return new ArrayList<>(footermenu);
    }


}
