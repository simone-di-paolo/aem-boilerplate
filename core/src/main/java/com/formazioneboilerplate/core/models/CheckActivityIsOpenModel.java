
package com.formazioneboilerplate.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalTime;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CheckActivityIsOpenModel {

    @Inject
    @Optional
    private String openingHours;
    private Boolean isOpen;

    @PostConstruct
    protected void init() {
        isOpen = false;
        if(openingHours != null && !openingHours.equals("")) {
            String[] split = openingHours.split("-");
            split[0]=split[0].trim();
            split[1]=split[1].trim();//toglie spazi
            LocalTime opening = LocalTime.parse(split[0]);
            LocalTime closed = LocalTime.parse(split[1]);
            if(LocalTime.now().isAfter(opening) && LocalTime.now().isBefore(closed)){
                isOpen = true;
            }
        }

    }

    public Boolean getOpen() {
        return isOpen;
    }
}