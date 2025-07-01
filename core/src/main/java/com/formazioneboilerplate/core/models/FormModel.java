package com.formazioneboilerplate.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Model(adaptables = Resource.class)
public class FormModel {

    @ValueMapValue
    Date datadinascita;
    @ValueMapValue
    String nazionalita;
    @ValueMapValue
    String indirizzo;
    @ValueMapValue
    boolean checkbox;
    @ValueMapValue
    boolean newsletter;
    @ValueMapValue
    String labelbottone;
    @ValueMapValue
    String linkbottone;

    public Date getDatadinascita() {
        return datadinascita;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public String getLabelbottone() {
        return labelbottone;
    }

    public String getLinkbottone() {
        return linkbottone;
    }
}
