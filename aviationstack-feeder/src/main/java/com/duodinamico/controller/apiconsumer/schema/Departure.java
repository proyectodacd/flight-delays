package com.duodinamico.controller.apiconsumer.schema;

public class Departure {
    private final String airport;
    private final String timezone;
    private final String iata;
    private final String icao;
    private final int delay;
    private final String scheduled;
    private final String actual;

    public Departure(String airport, String timezone, String iata, String icao, int delay, String scheduled, String actual) {
        this.airport = airport;
        this.timezone = timezone;
        this.iata = iata;
        this.icao = icao;
        this.delay = delay;
        this.scheduled = scheduled;
        this.actual = actual;
    }

    public String getAirport() {
        return airport;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getIata() {
        return iata;
    }

    public String getIcao() {
        return icao;
    }

    public int getDelay() {
        return delay;
    }

    public String getScheduled() {
        return scheduled;
    }

    public String getActual() {
        return actual;
    }
}
