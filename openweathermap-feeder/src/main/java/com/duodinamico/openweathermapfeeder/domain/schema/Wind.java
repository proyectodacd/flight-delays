package com.duodinamico.openweathermapfeeder.domain.schema;

public class Wind {

    private float speed;
    private int deg;

    public Wind(float speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public float getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }

}
