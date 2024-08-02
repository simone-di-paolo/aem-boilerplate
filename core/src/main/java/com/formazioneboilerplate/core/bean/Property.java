package com.formazioneboilerplate.core.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "property_name",
        "property_type",
        "location",
        "address",
        "property_size",
        "bed_quantity",
        "bath_quantity",
        "type"
})
@Generated("jsonschema2pojo")

public class Property {
    //struttura della singola card
    @JsonProperty("property_name")
    private String property_name;
    @JsonProperty("property_type")
    private String property_type;
    @JsonProperty("location")
    private String location;
    @JsonProperty("address")
    private String address;
    @JsonProperty("property_size")
    private String property_size;
    @JsonProperty("bed_quantity")
    private String bed_quantity;
    @JsonProperty("bath_quantity")
    private String bath_quantity;
    @JsonProperty("type")
    private String type;

    public String getProperty_name() {
        return property_name;
    }

    public String getProperty_type() {
        return property_type;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public String getProperty_size() {
        return property_size;
    }

    public String getBed_quantity() {
        return bed_quantity;
    }

    public String getBath_quantity() {
        return bath_quantity;
    }

    public String getType() {
        return type;
    }
}

