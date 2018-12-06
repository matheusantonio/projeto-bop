package com.uff.bop;

import java.lang.StringBuilder;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import java.io.StringReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.net.ssl.HttpsURLConnection;

import com.util.APILoader;

public class Coinrequest implements APILoader {

    private static String updatedTime;

    private static Double rate;

    public String doRequest() throws IOException, MalformedURLException, ProtocolException {
        String requestURL = "http://api.coindesk.com/v1/bpi/currentprice/BRL.json";

        URL url = new URL(requestURL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream inputstream = connection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));

        String currentLine;
        String jsonString = "";

        try {
            while ((currentLine = reader.readLine()) != null) {
                jsonString += currentLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObject jobj = new Gson().fromJson(jsonString, JsonObject.class);

        JsonObject time = jobj.getAsJsonObject("time");
        JsonObject bpi = jobj.getAsJsonObject("bpi");

        bpi = bpi.getAsJsonObject("BRL");

        rate = bpi.get("rate_float").getAsDouble();
        updatedTime = time.get("updated").toString();

        return updatedTime + " - " + rate;
    }

}
