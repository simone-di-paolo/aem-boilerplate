package com.formazioneboilerplate.core.service;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Formazione Boilerplate - Configuration Service",
        description = "Inside this OSGi configuration, we will store different kinds of website configs"
)

public @interface WebsiteConfigurations {

    @AttributeDefinition(
            name = "Configurations Path Code",
            description = "This field will contains the Conf page path",
            type = AttributeType.STRING
            //,cardinality = Integer.MAX_VALUE definiamo il numero massimo di interi rappresentabili, nel caso di array
    )
    String getConfigPagePath();

}
