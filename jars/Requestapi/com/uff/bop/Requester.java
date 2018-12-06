package com.uff.bop;

import java.lang.StringBuilder;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import java.io.StringReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import javax.net.ssl.HttpsURLConnection;

import com.util.APILoader;

public class Requester implements APILoader {

    private static String requestURL;

    private static String searchAnswer;

    private static String buildURL(String url, String username, String searchWord) {
        return url + "/code?q=" + searchWord + "+user:" + username;
    }

    private static InputStream makeRequisition(URL url) throws IOException {
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        return urlConnection.getInputStream();
    }

    private static String buildJSONResponse(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String currentLine;
        String jsonString = "";

        try {
            while ((currentLine = bufferedReader.readLine()) != null) {
                jsonString += currentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    private static void searchJson(String jsonString) throws IOException {

        JsonObject jobj = new Gson().fromJson(jsonString, JsonObject.class);

        searchAnswer = jobj.get("total_count").toString();

        // JsonReader jsonReader = new JsonReader(new StringReader(jsonString));

        // handleObject(jsonReader);
    }

    private static void handleObject(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            JsonToken jsonToken = jsonReader.peek();
            if (jsonToken.equals(JsonToken.BEGIN_ARRAY)) {
                handleArray(jsonReader);
            } else if (jsonToken.equals(JsonToken.END_OBJECT)) {
                jsonReader.endObject();
                return;
            } else {
                handleNonArrayToken(jsonReader, jsonToken);
            }
        }
    }

    private static void handleArray(JsonReader jsonReader) throws IOException {
        jsonReader.beginArray();
        while (true) {
            JsonToken jsonToken = jsonReader.peek();
            if (jsonToken.equals(JsonToken.END_ARRAY)) {
                jsonReader.endArray();
                return;
            } else if (jsonToken.equals(JsonToken.BEGIN_OBJECT)) {
                handleObject(jsonReader);
            } else if (jsonToken.equals(JsonToken.END_OBJECT)) {
                jsonReader.endObject();
            } else {
                handleNonArrayToken(jsonReader, jsonToken);
            }
        }
    }

    private static void handleNonArrayToken(JsonReader jsonReader, JsonToken jsonToken) throws IOException {
        String searchAux;
        if (jsonToken.equals(JsonToken.NAME)) {
            searchAux = jsonReader.nextName();
            if (searchAux.equals("total_count")) {
                jsonReader.hasNext();
                jsonToken = jsonReader.peek();
                if (jsonToken.equals(JsonToken.NUMBER)) {
                    searchAnswer = "";
                    searchAnswer += jsonReader.nextInt();
                }
            }
        } else {
            jsonReader.skipValue();
        }
    }

    public String doRequest() throws IOException, MalformedURLException, ProtocolException {

        String username = "carlosbazilio";
        String searchWord = "java";
        requestURL = "https://api.github.com/search";

        URL url = new URL(buildURL(requestURL, username, searchWord));

        InputStream inputStream = makeRequisition(url);

        String jsonString = buildJSONResponse(inputStream);

        searchJson(jsonString);

        return searchAnswer;
    }
}
