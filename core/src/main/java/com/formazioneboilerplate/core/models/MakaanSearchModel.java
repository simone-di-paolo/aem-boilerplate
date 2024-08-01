package com.formazioneboilerplate.core.models;

import com.formazioneboilerplate.core.bean.Cities;
import com.formazioneboilerplate.core.bean.City;
import com.formazioneboilerplate.core.bean.Properties;
import com.formazioneboilerplate.core.bean.Property;
import com.formazioneboilerplate.core.service.ActivitiesSearchFieldsConfigService;
import com.formazioneboilerplate.core.service.EndPointConfigurationsService;
import com.formazioneboilerplate.core.service.PropertiesSearchFieldsConfig;
import com.formazioneboilerplate.core.service.PropertiesSearchFieldsConfigService;
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
public class MakaanSearchModel {

    private static final Logger LOG = LoggerFactory.getLogger(MakaanSearchModel.class);

    @Inject
    private Connector connector;

    @Inject
    private PropertiesSearchFieldsConfigService configModel;

    @Inject
    private EndPointConfigurationsService endPointConfigurationsService;

    private List<String> typeProperty;
    private List<String> properties;

    @PostConstruct
    protected void init() {
        LOG.info("## Hero Banner Search Model - init started ###");
        typeProperty = configModel.getSearchTypeProperties();
        properties=getPropertiesList();
        //searchCity
        LOG.info("## Hero Banner Search Model - init finished ###");
    }

    public List<String> getSearchProperties() {
        return typeProperty;
    }

    public List<String> getProperties() {
        return properties;
    }

    private List<String> getPropertiesList(){
        String endPointSearch = endPointConfigurationsService.getEndPointSearch();
        JsonObject response = connector.executeGet(endPointSearch, null, null);
        Gson gson = new Gson();
        Properties properties = gson.fromJson(response.toString(), Properties.class);
        List<String> propertiesList =new ArrayList<>();
        List<Property> properties1 = properties.getProperties();
        for(Property property : properties1){
            if(propertiesList.size()<=3){
                propertiesList.add(property.getLocation());
            }

        }
        return propertiesList;
    }

}
