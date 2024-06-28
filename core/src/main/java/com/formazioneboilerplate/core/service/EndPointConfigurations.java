package com.formazioneboilerplate.core.service;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name="Formazione Boilerplate - Configuration EndPoint Service",
        description = "Inside this OSGi configuration, we will store different kinds of website configs"
)

public @interface EndPointConfigurations {

    @AttributeDefinition(
            name = "Configuration URL endPoint",
            description = "This field will contains the endPoint",
            type= AttributeType.STRING
    )
    String getEndPoint();
}
