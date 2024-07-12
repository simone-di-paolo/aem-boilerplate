package com.formazioneboilerplate.core.service;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import java.util.Arrays;
import java.util.List;


@Component(service = ActivitiesSearchFieldsConfigService.class, immediate = true)
@Designate(ocd = ActivitiesSearchFieldsConfig.class)
public class ActivitiesSearchFieldsConfigServiceImpl implements ActivitiesSearchFieldsConfigService {

    private List<String> listaSearchFields;

    @Activate
    public void activate(ActivitiesSearchFieldsConfig configuration) {
        String[] searchFields = configuration.getSearchFields();
        listaSearchFields = Arrays.asList(searchFields);
    }

    @Override
    public List<String> getSearchFields() {
        return listaSearchFields;
    }
}
