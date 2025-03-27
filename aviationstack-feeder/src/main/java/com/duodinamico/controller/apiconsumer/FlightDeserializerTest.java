package com.duodinamico.controller.apiconsumer;

import com.duodinamico.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class FlightDeserializerTest {

    public static void main(String[] args) {
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor();
        FlightDeserializer flightDeserializer = new FlightDeserializer();
        List<Flight> lista = flightDeserializer.flightDeserializer(aviationStackProcessor.petitionValidator(aviationStackProcessor.flightsPetition(args),args));

        System.out.println(lista.size());
        System.out.println(LocalDateTime.now());
        System.out.println(lista.get(3).getFlightDate());
        System.out.println(lista.get(1).getFlightStatus());
        System.out.println(lista.get(1).getDeparture().getAirport());
        System.out.println(lista.get(1).getDeparture().getTimezone());
        System.out.println(lista.get(1).getDeparture().getIata());
        System.out.println(lista.get(1).getDeparture().getIcao());
        System.out.println(lista.get(1).getDeparture().getDelay());
        System.out.println(lista.get(1).getDeparture().getScheduled());
        System.out.println(lista.get(1).getDeparture().getActual());
        System.out.println(lista.get(1).getArrival().getAirport());
        System.out.println(lista.get(1).getArrival().getTimezone());
        System.out.println(lista.get(1).getArrival().getIata());
        System.out.println(lista.get(1).getArrival().getIcao());
        System.out.println(lista.get(1).getArrival().getDelay());
        System.out.println(lista.get(40).getArrival().getActual());
        System.out.println(lista.get(1).getArrival().getScheduled());
        System.out.println(lista.get(1).getArrival().getActual());
        System.out.println(lista.get(1).getFlightId().getFlightIcao());
        System.out.println("/////////////////////////////////////");
        System.out.println(lista.get(1).getLiveStatus().getLatitude());
        System.out.println(lista.get(1).getLiveStatus().getUpdated());
        System.out.println(lista.get(1).getLiveStatus().getLatitude());
        System.out.println(lista.get(1).getLiveStatus().getLongitude());
        System.out.println(lista.get(1).getLiveStatus().getAltitude());
        System.out.println(lista.get(1).getLiveStatus().getSpeedHorizontal());
        System.out.println(lista.get(1).getLiveStatus().getSpeedVertical());
        System.out.println(lista.get(1).getLiveStatus().getIsGround());
    }
}
