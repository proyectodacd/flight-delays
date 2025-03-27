package com.duodinamico.model.schema;

import com.google.gson.annotations.SerializedName;

public class Snow {

    @SerializedName("1h")
    private final float snowOneHour;

    public Snow(float snowOneHour) {
        this.snowOneHour = snowOneHour;
    }

    public float getSnowOneHour() { return snowOneHour;}

}
