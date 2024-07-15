package com.formazioneboilerplate.core.bean;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Activities {

    @JsonProperty("activities")
    private List<Activity> activities = new ArrayList<>();

    @JsonAnyGetter
    public List<Activity> getActivities() {
        Collections.shuffle(activities);
        return activities;
    }

}
