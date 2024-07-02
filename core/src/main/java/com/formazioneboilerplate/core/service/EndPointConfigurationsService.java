package com.formazioneboilerplate.core.service;

import com.google.gson.JsonObject;

public interface EndPointConfigurationsService {
    String getEndPoint();
    JsonObject getResponseFromEndPoint(String endPoint);
}
