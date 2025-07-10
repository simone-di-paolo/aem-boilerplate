//Implementazione del servizio

package com.formazioneboilerplate.core.services.impl;

import com.formazioneboilerplate.core.services.StudentConfiguration;
import com.formazioneboilerplate.core.services.StudentConfigurationMethods;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

//registra la classe come servizio OSGi
@Component(service = StudentConfigurationMethods.class)
//collega questo servizio alla configurazione OSGi
@Designate(ocd=StudentConfiguration.class)
public class StudentConfigurationMethodsImpl implements StudentConfigurationMethods {

    private String studentName;
    private int rollNumber;
    private boolean regular;
    private String[] subjects;
    private String countries;

    //Quando il bundle viene attivato, AEM chiama il metodo start().
    // I valori della configurazione vengono letti e memorizzati in variabili private
                @Activate()
                protected void start(StudentConfiguration config){
                    studentName = config.getStudentName();
                    rollNumber = config.getRollNumber();
                    regular = config.getRegular();
                    subjects = config.getSubjects();
                    countries = config.getCountries();
                }
//Ogni metodo dell’interfaccia restituisce il valore configurato
                @Override
                public String getStudentName() {
                    return studentName;
                }

                @Override
                public int getRollNumber() {
                    return rollNumber;
                }

                @Override
                public String[] getSubjects() {
                    return subjects;
                }

                @Override
                public String getCountries() {
                    return countries;
                }

                @Override
                public boolean getRegular() {
                    return regular;
                }


}
