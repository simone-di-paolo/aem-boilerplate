package com.formazioneboilerplate.core.bean;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "age",
        "biography",
        "image",
        "job_role",
        "name",
        "social_url"
})
@Generated("jsonschema2pojo")

public class Card {
    //struttura della singola card
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("biography")
    private String biography;
    @JsonProperty("image")
    private String image;
    @JsonProperty("job_role")
    private String job_role;
    @JsonProperty("name")
    private String name;
    @JsonProperty("social_url")
    private String social_url;



    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty("biography")
    public String getBiography() {
        return biography;
    }

    @JsonProperty("biography")
    public void setBiography(String biography) {
        this.biography = biography;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("job_role")
    public String getJob_role() {
        return job_role;
    }

    @JsonProperty("job_role")
    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("social_url")
    public String getSocial_url() {
        return social_url;
    }

    @JsonProperty("social_url")
    public void setSocial_url(String social_url) {
        this.social_url = social_url;
    }


}
