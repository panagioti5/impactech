package com.exercise.core.utils;

import com.exercise.core.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

@Service
public class CoreUtils {

    @Autowired
    private Environment env;

    public String callMicroserviceAPI(String microserviceName, String path, RequestMethod requestMethod){
        HttpURLConnection conn = null;
        StringBuilder apiUri = new StringBuilder();
        if (!isValueEmptyOrUnknownOrNull(microserviceName)){
            apiUri.append(env.getProperty(microserviceName));
        }
        apiUri.append(path);
        StringBuilder restOutput = new StringBuilder();
        try {
            URL url = new URL(apiUri.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethod.toString());
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != HttpStatus.OK.value()) {
                return String.valueOf(conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String lineOut;
            while ((lineOut = br.readLine()) != null) {
                restOutput.append(lineOut);
            }
            return restOutput.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            conn.disconnect();
        }
    }

    public String retrieveDeviceName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isValueEmptyOrUnknownOrNull(String val){
        return null == val
                || Constants.EMPTY_STRING.equals(val.trim())
                    || Constants.UNKNOWN.equalsIgnoreCase(val);
    }
}
