package com.formazioneboilerplate.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FeedbackModel {


    //parte nuova
    @Inject
    private List<MultifieldModel> multifield;

    public List<MultifieldModel> getMultifield() {
        return new ArrayList<>(multifield);
    }
}


