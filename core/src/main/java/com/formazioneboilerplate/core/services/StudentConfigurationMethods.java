//Interfaccia del servizio
//Questa è un'interfaccia usata per esporre i metodi pubblici del servizio.
// Serve per: astrarre l’implementazione e permettere l’injection del servizio in altri componenti

package com.formazioneboilerplate.core.services;

public interface StudentConfigurationMethods {
    String getStudentName();
    int getRollNumber();
    boolean getRegular();
    String[] getSubjects();
    String getCountries();
}
