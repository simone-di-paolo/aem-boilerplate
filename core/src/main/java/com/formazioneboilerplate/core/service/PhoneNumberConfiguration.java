
package com.formazioneboilerplate.core.service;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition( name = "Phone Numbers Configuration", description = "Insert your phone numbers")
public @interface PhoneNumberConfiguration {

    @AttributeDefinition(name =  "Mobile", type = AttributeType.STRING,
            description = "Insert your personal phone number")
    public String[] getMobile () default {"+39 3271851235","+33 238830173","+66 7383782749"};

    @AttributeDefinition(name =  "Office", type = AttributeType.STRING,
            description = "Insert your office phone number")
    public String[] getOffice() default {"+39 0836804625","+32 0832567809","+22 0987615432"};

    @AttributeDefinition(name =  "Home", type = AttributeType.STRING,
            description = "Insert your home phone number")
    public String[] getHome() default {"+39 3271851235","+33 238830173","+66 7383782749"};

}