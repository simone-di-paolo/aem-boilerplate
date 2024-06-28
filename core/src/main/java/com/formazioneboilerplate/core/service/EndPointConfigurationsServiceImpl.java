package com.formazioneboilerplate.core.service;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service=EndPointConfigurationsService.class, immediate=true)
@Designate(ocd = EndPointConfigurations.class)
public class EndPointConfigurationsServiceImpl implements EndPointConfigurationsService {

    private String endPoint;

    @Activate
    public void activate(EndPointConfigurations configuration){
        endPoint = configuration.getEndPoint();
    }

    @Override
    public String getEndPoint() {
        return endPoint;
    }
}
