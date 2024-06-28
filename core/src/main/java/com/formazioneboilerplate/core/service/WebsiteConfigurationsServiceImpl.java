package com.formazioneboilerplate.core.service;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;


@Component(service = WebsiteConfigurationsService.class, immediate = true)
@Designate(ocd = WebsiteConfigurations.class)
public class WebsiteConfigurationsServiceImpl implements WebsiteConfigurationsService {

    private String configPagePath;

    @Activate
    public void activate(WebsiteConfigurations configuration) {
        configPagePath = configuration.getConfigPagePath();
    }

    @Override
    public String getConfigPagePath() {
        return configPagePath;
    }
}
