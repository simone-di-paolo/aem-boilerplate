package com.formazioneboilerplate.core.models;

import com.formazioneboilerplate.core.bean.Activities;
import com.formazioneboilerplate.core.bean.Activity;
import com.formazioneboilerplate.core.bean.Cards;
import com.formazioneboilerplate.core.bean.City;
import com.formazioneboilerplate.core.service.EndPointConfigurationsService;
import com.formazioneboilerplate.core.servlets.connector.Connector;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SpaExploreModel {

    private static final Logger LOG = LoggerFactory.getLogger(SpaExploreModel.class);

    @Inject
    private Connector connector;

    @Inject
    private EndPointConfigurationsService endPointConfigurations;

    private Activities activities;

    @PostConstruct
    protected void init() throws IOException {
        LOG.info("## Card API Component Model - init started ###");
        String endPoint = endPointConfigurations.getEndPointExplore();
        Gson gson = new Gson();
        JsonObject response = connector.executeGet(endPoint, null, null);
        activities= gson.fromJson(response, Activities.class);
        LOG.info("## Card API Component Model - init finished ###");

    }

    public Activities getInformation(){
        return activities;
    }

}

