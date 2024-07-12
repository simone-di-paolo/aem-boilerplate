package com.formazioneboilerplate.core.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cities {
    @JsonProperty("cities")
    private List<City> cities = new ArrayList<>();

    public List<City> getCities() {
        return cities;
    }
}
