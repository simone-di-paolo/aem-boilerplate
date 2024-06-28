package com.formazioneboilerplate.core.service;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import java.util.Arrays;
import java.util.List;


@Component(service = TemplateConfigurationsService.class, immediate = true)
@Designate(ocd = TemplateConfigurations.class)
public class TemplateConfigurationsServiceImpl implements TemplateConfigurationsService {

    private List<String> lista;

    @Activate
    public void activate(TemplateConfigurations configuration) {
        String[] templateConfigPaths = configuration.getTemplateConfigPaths();
        lista = Arrays.asList(templateConfigPaths);
    }

    @Override
    public List<String> getTemplateConfigPaths() {
        return lista;
    }
}
