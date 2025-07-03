
package com.formazioneboilerplate.core.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import javax.inject.Inject;

@Getter
@Setter
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CensusParameters {

    @Inject
    private String contactName;

    @Inject
    private String contactSurname;

    @Inject
    private String contactEmail;

    @Inject
    private String contactPhone;

}