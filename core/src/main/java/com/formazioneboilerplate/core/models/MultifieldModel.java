package com.formazioneboilerplate.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;



@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class MultifieldModel {
    @ValueMapValue
    private String name;
    @ValueMapValue
    private String feedback;
    @ValueMapValue
    private String rating;

    public String getName() {
        return name;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getRating() {
        return rating;
    }

}