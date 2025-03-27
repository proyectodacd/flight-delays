package com.duodinamico.model.schema;

import com.google.gson.annotations.SerializedName;

public class Rain {

    @SerializedName("1h")
    private final float rainOneHour;

    public Rain(float rainOneHour) {
        this.rainOneHour = rainOneHour;
    }

    public float getRainOneHour() { return rainOneHour;}

}
