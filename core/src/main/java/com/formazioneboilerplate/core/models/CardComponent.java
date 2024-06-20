package com.formazioneboilerplate.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface CardComponent {

    @Inject
    List<ElementsCard> getElementsCards();

    @Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    interface ElementsCard {

        @Inject
        String getImage();

        @Inject
        String getCardTitle();

        @Inject
        String getCardDescription();

        @Inject
        String getButtonLabel();

        @Inject
        String getButtonLink();
    }
}
