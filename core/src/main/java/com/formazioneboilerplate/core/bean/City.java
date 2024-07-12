package com.formazioneboilerplate.core.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "restaurants",
        "pubs",
        "bars"
})
@Generated("jsonschema2pojo")

public class City {
    //struttura della singola card
    @JsonProperty("name")
    private String name;
    @JsonProperty("restaurants")
    private List<Local> restaurants = new ArrayList<>();
    @JsonProperty("pubs")
    private List<Local> pubs = new ArrayList<>();;
    @JsonProperty("bars")
    private List<Local> bars = new ArrayList<>();;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Local> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Local> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Local> getPubs() {
        return pubs;
    }

    public void setPubs(List<Local> pubs) {
        this.pubs = pubs;
    }

    public List<Local> getBars() {
        return bars;
    }

    public void setBars(List<Local> bars) {
        this.bars = bars;
    }
}

