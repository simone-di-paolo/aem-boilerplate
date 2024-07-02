package com.formazioneboilerplate.core.bean;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    @JsonProperty("cards")
    private List<Card> cards = new ArrayList<>();

    @JsonAnyGetter
    public List<Card> getCards() {
        return cards;
    }
    @JsonAnySetter
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
