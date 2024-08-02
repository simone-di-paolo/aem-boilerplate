package com.formazioneboilerplate.core.service;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import java.util.Arrays;
import java.util.List;


@Component(service = PropertiesSearchFieldsConfigService.class, immediate = true)
@Designate(ocd = PropertiesSearchFieldsConfig.class)
public class PropertiesSearchFieldsConfigServiceImpl implements PropertiesSearchFieldsConfigService {

    private List<String> listaSearchTypeProperties;

    @Activate
    public void activate(PropertiesSearchFieldsConfig configuration) {
        String[] searchTypeProperties = configuration.getSearchTypeProperties();
        listaSearchTypeProperties = Arrays.asList(searchTypeProperties);
    }

    @Override
    public List<String> getSearchTypeProperties() {
        return listaSearchTypeProperties;
    }
}
