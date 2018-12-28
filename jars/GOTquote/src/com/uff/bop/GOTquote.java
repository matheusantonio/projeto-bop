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

public class GOTquote implements APILoader{


    public String doRequest() throws IOException, MalformedURLException, ProtocolException{
        String requesturl = "https://got-quotes.herokuapp.com/quotes";

        URL url = new URL(requesturl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream inputStream = connection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

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

        String quote = jobj.get("quote").toString();

        return quote;
    }
}