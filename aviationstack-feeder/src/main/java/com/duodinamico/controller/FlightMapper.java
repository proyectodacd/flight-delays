package com.duodinamico.controller;

import com.duodinamico.controller.apiconsumer.schema.Flight;
import com.duodinamico.controller.eventIntegration.FlightEvent;
import com.duodinamico.controller.model.FlightModel;

public class FlightMapper {

    public FlightModel getFlightModel(Flight flight) {
        FlightModel flightModel = new FlightModel(
                flight.getFlightId().getFlightIcao(),
                flight.getFlightDate(),
                flight.getFlightStatus(),
                flight.getDeparture().getAirport(),
                flight.getDeparture().getTimezone(),
                flight.getDeparture().getIata(),
                flight.getDeparture().getIcao(),
                flight.getDeparture().getDelay(),
                flight.getDeparture().getScheduled(),
                flight.getDeparture().getActual(),
                flight.getArrival().getAirport(),
                flight.getArrival().getTimezone(),
                flight.getArrival().getIata(),
                flight.getArrival().getIcao(),
                flight.getArrival().getDelay(),
                flight.getArrival().getScheduled(),
                flight.getArrival().getActual(),
                flight.getLiveStatus() == null ? null : flight.getLiveStatus().getUpdated(),
                flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getLatitude(),
                flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getLongitude(),
                flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getAltitude(),
                flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getSpeedHorizontal(),
                flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getSpeedVertical(),
                flight.getLiveStatus() == null ? false : flight.getLiveStatus().getIsGround()
        );

        return flightModel;

    }

    public FlightEvent getFlightEvent(Flight flight) {
        FlightEvent flightEvent = new FlightEvent(
                flight.getFlightId().getFlightIcao(),
                flight.getFlightDate(),
                flight.getFlightStatus(),
                flight.getDeparture().getAirport(),
                flight.getDeparture().getTimezone(),
                flight.getDeparture().getIata(),
                flight.getDeparture().getIcao(),
                flight.getDeparture().getDelay(),
                flight.getDeparture().getScheduled(),
                flight.getDeparture().getActual(),
                flight.getArrival().getAirport(),
                flight.getArrival().getTimezone(),
                flight.getArrival().getIata(),
                flight.getArrival().getIcao(),
                flight.getArrival().getDelay(),
                flight.getArrival().getScheduled(),
                flight.getArrival().getActual(),
                flight.getLiveStatus() == null ? null : flight.getLiveStatus().getUpdated(),
                flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getLatitude(),
                flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getLongitude(),
                flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getAltitude(),
                flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getSpeedHorizontal(),
                flight.getLiveStatus() == null ? 0 : flight.getLiveStatus().getSpeedVertical(),
                flight.getLiveStatus() == null ? false : flight.getLiveStatus().getIsGround()
        );
        return flightEvent;
    }

}
