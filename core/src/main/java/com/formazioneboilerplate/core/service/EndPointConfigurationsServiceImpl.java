package com.formazioneboilerplate.core.service;

import com.formazioneboilerplate.core.servlets.connector.Connector;
import com.google.gson.JsonObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import java.util.HashMap;
import java.util.Map;

@Component(service=EndPointConfigurationsService.class, immediate=true)
@Designate(ocd = EndPointConfigurations.class)
public class EndPointConfigurationsServiceImpl implements EndPointConfigurationsService {

    @Reference
    protected Connector connector;
    private String endPoint;
    private String endPointExplore;
    private String endPointSearch;

    @Activate
    public void activate(EndPointConfigurations configuration){
        endPoint = configuration.getEndPoint();
        endPointExplore=configuration.getEndPointExplore();
        endPointSearch=configuration.getEndPointSearch();
    }

    @Override
    public String getEndPoint() {
        return endPoint;
    }

    @Override
    public String getEndPointExplore() {
        return endPointExplore;
    }

    @Override
    public String getEndPointSearch() {
        return endPointSearch;
    }

    @Override
    public JsonObject getResponseFromEndPoint(String endPoint) {
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return connector.executeGet(endPoint, headers, null);
    }

}
