package com.formazioneboilerplate.core.service;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = PhoneNumberConfigurationMethods.class)
@Designate(ocd = PhoneNumberConfiguration.class)
public class PhoneNumberConfigurationMethodsImpl implements PhoneNumberConfigurationMethods {

    private String[] mobile;
    private String[] office;
    private String[] home;

    @Activate()
    protected void start(PhoneNumberConfiguration config){
        mobile = config.getMobile();
        office = config.getOffice();
        home = config.getHome();
    }

    @Override
    public String[] getMobile() {
        return mobile;
    }

    @Override
    public String[] getOffice() {
        return office;
    }

    @Override
    public String[] getHome() {
        return home;
    }

}

