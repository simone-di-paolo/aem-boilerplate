package com.formazioneboilerplate.core.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "type",
        "ratings",
        "number_of_reviews",
        "address",
        "price_range",
        "availability"
})
@Generated("jsonschema2pojo")

public class Activity {
    //struttura della singola card
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("ratings")
    private Double ratings;
    @JsonProperty("number_of_reviews")
    private Integer number_of_reviews;
    @JsonProperty("address")
    private String address;
    @JsonProperty("price_range")
    private String price_range;
    @JsonProperty("availability")
    private String availability;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getRatings() {
        return ratings;
    }

    public Integer getNumber_of_reviews() {
        return number_of_reviews;
    }

    public String getAddress() {
        return address;
    }

    public String getPrice_range() {
        return price_range;
    }

    public String getAvailability() {
        return availability;
    }
}
