//Interfaccia di configurazione OSGi

package com.formazioneboilerplate.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

//Definisce una configurazione OSGi: crea un pannello configurabile in Felix Console (/system/console/configMgr) e leggibile nel codice
@ObjectClassDefinition(name="Student Details", description="Enter your Students details here")

public @interface StudentConfiguration {

    //Tutti i field e i campi
    @AttributeDefinition(name = "Student Name", description = "Enter Student Name here", type= AttributeType.STRING)
    public String getStudentName() default "Satyam";
    @AttributeDefinition(name="Roll Number", description="Enter Roll Number here", type=AttributeType.INTEGER)
    public int getRollNumber() default 3;

    @AttributeDefinition(name =  "Regular", type = AttributeType.BOOLEAN,
            description = "Is student regular")
    public boolean getRegular() default true;

    @AttributeDefinition(name =  "Subjects", type = AttributeType.STRING,
            description = "See Your Subjects")
    public String[] getSubjects() default {"maths,english,sanskrit"};

    @AttributeDefinition(name =  "Countries", type = AttributeType.STRING,
            description = "Select your Countries",
            options = {
                    @Option(label = "India", value = "india"),
                    @Option(label = "Russia", value = "russia"),
                    @Option(label = "France", value = "france"),
                    @Option(label = "America", value = "america")
            })
    public String getCountries() default "India";
}






