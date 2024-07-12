package com.formazioneboilerplate.core.models;

import com.formazioneboilerplate.core.bean.Cities;
import com.formazioneboilerplate.core.bean.City;
import com.formazioneboilerplate.core.service.ActivitiesSearchFieldsConfigService;
import com.formazioneboilerplate.core.service.EndPointConfigurationsService;
import com.formazioneboilerplate.core.servlets.connector.Connector;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroBannerSearchModel {

    private static final Logger LOG = LoggerFactory.getLogger(HeroBannerSearchModel.class);

    @Inject
    private Connector connector;

    @Inject
    private ActivitiesSearchFieldsConfigService configModel;

    @Inject
    private EndPointConfigurationsService endPointConfigurationsService;

    private List<String> searchFields;
    private List<String> cities;

    @PostConstruct
    protected void init() {
        LOG.info("## Hero Banner Search Model - init started ###");
        searchFields = configModel.getSearchFields();
        cities=getCitiesList();
        //searchCity
        LOG.info("## Hero Banner Search Model - init finished ###");
    }

    public List<String> getSearchFields() {
        return searchFields;
    }

    public List<String> getCities() {
        return cities;
    }

    private List<String> getCitiesList(){
        String endPoint = endPointConfigurationsService.getEndPoint();
        JsonObject response = connector.executeGet(endPoint, null, null);
        Gson gson = new Gson();
        Cities cities = gson.fromJson(response.toString(), Cities.class);
        List<String> citiesList =new ArrayList<>();
        List<City> cities1 = cities.getCities();
        for(City city : cities1){
            citiesList.add(city.getName());
        }
        return citiesList;
    }

/*
    private List<String> getCityLocals(){
        String endPoint = endPointConfigurationsService.getEndPoint();

        JsonObject response = connector.executeGet(endPoint, null, null);
        Gson gson = new Gson();
        Locals locals = gson.fromJson(response.toString(), Local.class);
        List<String> localList =new ArrayList<>();

    }*/

}
