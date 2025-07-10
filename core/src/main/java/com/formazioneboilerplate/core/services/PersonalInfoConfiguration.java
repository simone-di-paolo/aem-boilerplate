package com.formazioneboilerplate.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Personal Information Configuration")

public @interface PersonalInfoConfiguration {

    @AttributeDefinition(name = "Name", description = "Enter Your Name here", type= AttributeType.STRING)
    String[] getName() default {"Chiara", "Giosuè", "Miley"};

    @AttributeDefinition(name = "Surname", description = "Enter Your Surname here", type= AttributeType.STRING)
    String[] getSurname() default {"Giosi", "Carducci", "Cyrus"};

    @AttributeDefinition(name = "Nationality", description = "Enter Your Nationality here", type= AttributeType.STRING)
    String[] getNationality() default {"Italy", "Italy", "Usa"};

    @AttributeDefinition(name = "Age", description = "Enter Your Age here", type= AttributeType.STRING)
    String[] getAge() default {"25", "106", "32"};

    @AttributeDefinition(name = "Gender", description = "Enter Your Gender here", type= AttributeType.STRING)
    String[] getGender() default {"Female", "Male", "Female"};

}
