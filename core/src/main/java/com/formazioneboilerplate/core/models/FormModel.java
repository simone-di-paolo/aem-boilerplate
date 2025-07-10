package com.formazioneboilerplate.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.Date;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FormModel {

    @ValueMapValue
    private Date datadinascita;
    @ValueMapValue
    private String nazionalita;
    @ValueMapValue
    private String indirizzo;
    @ValueMapValue
    private String checkbox;
    @ValueMapValue
    private String newsletter;
    @ValueMapValue
    private String labelbottone;
    @ValueMapValue
    private String linkbottone;

    public Date getDatadinascita() {

        return datadinascita;
    }

    public String getNazionalita() {

        return nazionalita;
    }

    public String getIndirizzo() {

        return indirizzo;
    }

    public String isCheckbox() {

        return checkbox;
    }

    public String isNewsletter() {

        return newsletter;
    }

    public String getLabelbottone() {

        return labelbottone;
    }

    public String getLinkbottone() {

        return linkbottone;
    }
}
