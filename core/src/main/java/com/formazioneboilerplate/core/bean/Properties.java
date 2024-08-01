package com.formazioneboilerplate.core.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Properties {
    @JsonProperty("properties")
    private List<Property> properties = new ArrayList<>();

    public List<Property> getProperties() {
        return properties;
    }
}
