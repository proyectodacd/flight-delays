package com.duodinamico.controller.apiconsumer.schema;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    private final int cloudiness;

    public Clouds(int cloudiness) {
        this.cloudiness = cloudiness;
    }

    public int getCloudiness() {
        return cloudiness;
    }

}
