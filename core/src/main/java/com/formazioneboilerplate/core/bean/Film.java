package com.formazioneboilerplate.core.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name_film",
        "category",
        "year",
        "director",
        "nation"
})
@Generated("jsonschema2pojo")

public class Film {
    //struttura della singola card
    @JsonProperty("name_film")
    private String name_film;
    @JsonProperty("category")
    private String category;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("director")
    private String director;
    @JsonProperty("nation")
    private String nation;

    public String getName_film() {
        return name_film;
    }

    public String getCategory() {
        return category;
    }

    public Integer getYear() { return year; }

    public String getDirector() {
        return director;
    }

    public String getNation() {
        return nation;
    }
}

