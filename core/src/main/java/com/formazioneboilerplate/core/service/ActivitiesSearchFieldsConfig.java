package com.formazioneboilerplate.core.service;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Formazione Boilerplate - Activities Search Fields Configuration Service",
        description = "Inside this OSGi configuration, we will store different kinds of field"
)

public @interface ActivitiesSearchFieldsConfig {

    @AttributeDefinition(
            name = "Configurations Search Fields",
            description = "This field will contains a possible actvity",
            type = AttributeType.STRING,
            cardinality = Integer.MAX_VALUE
    )
    String[] getSearchFields();

}
