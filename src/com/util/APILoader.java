package com.util;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.io.IOException;

public interface APILoader {
    public String doRequest() throws IOException, MalformedURLException, ProtocolException;
}