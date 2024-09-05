package com.formazioneboilerplate.core.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Films {
    @JsonProperty("films")
    private List<Film> films = new ArrayList<>();

    public List<Film> getFilms() {
        return films;
    }
}
