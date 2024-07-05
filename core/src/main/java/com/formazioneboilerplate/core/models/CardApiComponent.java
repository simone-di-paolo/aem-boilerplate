package com.formazioneboilerplate.core.models;

import com.formazioneboilerplate.core.bean.Cards;
import com.formazioneboilerplate.core.service.EndPointConfigurationsService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;



@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardApiComponent {

    private static final Logger LOG = LoggerFactory.getLogger(CardApiComponent.class);

    @Inject
    private EndPointConfigurationsService endPointConfigurations;

    private Cards cards;


    @PostConstruct
    protected void init() {
        LOG.info("## Card API Component Model - init started ###");
        // prendiamo attraverso una OSGI il file json sul server MAMPP
        JsonObject response = endPointConfigurations.getResponseFromEndPoint(endPointConfigurations.getEndPoint());
        //utilizziamo l'oggetto gson perchè ci permette di prendere info dal json e convertirle
        Gson gson = new Gson();
        cards = gson.fromJson(response.toString(), Cards.class);
        LOG.info("## Card API Component Model - init finished ###");

    }

    public Cards getCards(){
        return cards;
    }
}
