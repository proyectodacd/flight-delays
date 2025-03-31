
package com.duodinamico.controller.apiconsumer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class AviationStackProcessor {

    private int currentKey;

    public AviationStackProcessor() {
        this.currentKey = 3;
    }

    public int getCurrentKey() {
        return currentKey;
    }

    public void setCurrentKey(int currentKey) {
        this.currentKey = currentKey;
    }

    public Connection.Response flightsPetition(String[] args) {
        Connection.Response response;
        try {
            String endpoint = "https://api.aviationstack.com/v1/flights";
            Connection connection = Jsoup.connect(endpoint);
            connection.ignoreContentType(true);
            connection.data("access_key", args[getCurrentKey()]);
            connection.data("flight_status", "active");

            response = connection.method(Connection.Method.GET).execute();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String petitionValidator(Connection.Response response, String[] args) {

        if (response.statusCode() != 200) {
            if (getCurrentKey() < args.length - 1) {
                setCurrentKey(getCurrentKey()+1);
                return petitionValidator(flightsPetition(args), args);
            } else {
                setCurrentKey(3);
                return petitionValidator(flightsPetition(args), args);
            }
        } else {
            return response.body();
        }
    }
}
