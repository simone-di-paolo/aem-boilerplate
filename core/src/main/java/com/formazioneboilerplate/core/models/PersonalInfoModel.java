package com.formazioneboilerplate.core.models;

import com.formazioneboilerplate.core.services.PersonalInfo;
import com.formazioneboilerplate.core.services.PersonalInfoConfiguration;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PersonalInfoModel {

    @OSGiService
    PersonalInfo personalInfoService;

    private String[] names;
    private String[] surnames;
    private String[] nationalities;
    private String[] ages;
    private String[] genders;

    @PostConstruct
    protected void init() {
        names = personalInfoService.getName();
        surnames = personalInfoService.getSurname();
        ages= personalInfoService.getAge();
        nationalities = personalInfoService.getNationality();
        genders = personalInfoService.getGender();
    }
    public String[] getName() {
        return names;
    }

    public String[] getSurname() {
        return surnames;
    }


    public String[] getNationality() {
        return nationalities;
    }

    public String[] getAge() {
        return ages;
    }

    public String[] getGender() {
        return genders;
    }




}
