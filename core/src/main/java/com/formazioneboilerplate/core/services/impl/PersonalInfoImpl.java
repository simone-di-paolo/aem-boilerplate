//Implementazione del servizio

package com.formazioneboilerplate.core.services.impl;

import com.formazioneboilerplate.core.services.PersonalInfo;
import com.formazioneboilerplate.core.services.PersonalInfoConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = PersonalInfo.class)
@Designate(ocd= PersonalInfoConfiguration.class)

public class PersonalInfoImpl implements PersonalInfo {

    private String[] names;
    private String[] surnames;
    private String[] nationalities;
    private String[] ages;
    private String[] genders;

    @Activate()
    protected void activate(PersonalInfoConfiguration config) {
        names = config.getName();
        surnames = config.getSurname();
        nationalities = config.getNationality();
        ages= config.getAge();
        genders = config.getGender();
    }

            @Override
        public String[] getName() {
            return names;
        }
        @Override
        public String[] getSurname() {
            return surnames;
        }
        @Override
        public String[] getNationality() {
            return nationalities;
        }
        @Override
        public String[] getAge() {
            return ages;
        }
        @Override
        public String[] getGender() {
            return genders;
        }

}


