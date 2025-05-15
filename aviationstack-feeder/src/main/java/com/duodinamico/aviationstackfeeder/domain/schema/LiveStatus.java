package com.duodinamico.aviationstackfeeder.domain.schema;

import com.google.gson.annotations.SerializedName;

public class LiveStatus {
    private final String updated;
    private final float latitude;
    private final float longitude;
    private final float altitude;
    @SerializedName("speed_horizontal")
    private final float speedHorizontal;
    @SerializedName("speed_vertical")
    private final float speedVertical;
    @SerializedName("is_ground")
    private final boolean isGround;

    public LiveStatus(String updated, float latitude, float longitude, float altitude, float speed_horizontal, float speed_vertical, boolean is_ground) {
        this.updated = updated;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speedHorizontal = speed_horizontal;
        this.speedVertical = speed_vertical;
        this.isGround = is_ground;
    }

    public String getUpdated() {
        return updated;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public float getSpeedHorizontal() {
        return speedHorizontal;
    }

    public float getSpeedVertical() {
        return speedVertical;
    }

    public boolean getIsGround() {
        return isGround;
    }
}
