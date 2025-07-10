package com.formazioneboilerplate.core.models;

import com.formazioneboilerplate.core.service.PhoneNumberConfigurationMethods;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class PhoneNumberModel {

    @OSGiService
    private PhoneNumberConfigurationMethods phoneConfigService;

    public String[] getMobile() {
        return phoneConfigService.getMobile();

    }

    public String[] getOffice (){
        return phoneConfigService.getOffice();

    }

    public String[] getHome(){
    return phoneConfigService.getHome();

    }
}
