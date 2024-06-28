package com.formazioneboilerplate.core.servlets.connector;

import com.google.gson.JsonObject;
import netscape.javascript.JSObject;

import java.util.Map;

public interface Connector {
    JsonObject executeGet (String endPoint, Map<String,String> headers, Map<String,String> params);
}
