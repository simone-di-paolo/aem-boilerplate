package com.formazioneboilerplate.core.servlets.connector;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang.CharEncoding;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

@Component(immediate = true, service = Connector.class)
public class ConnectorImpl implements Connector {

@Override
public JsonObject executeGet(String endPoint, Map<String, String> headers, Map<String, String> params){
    JsonObject jsonResponse = new JsonObject();
    CloseableHttpClient httpClient = HttpClients.createDefault();
    try{
        HttpGet httpGet = new HttpGet(endPoint);
        if(headers != null){
            for (Map.Entry<String, String> entry : headers.entrySet()){
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String stringResponse = EntityUtils.toString(entity, CharEncoding.UTF_8);
        JsonParser jsonParser = new JsonParser();

        jsonResponse = jsonParser.parse(stringResponse).getAsJsonObject();
    }
    catch (ClientProtocolException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return jsonResponse;
    }
}
