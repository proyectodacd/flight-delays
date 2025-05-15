package com.duodinamico.aviationstackfeeder.tools.mappers;

import com.duodinamico.aviationstackfeeder.domain.model.FlightEvent;
import com.duodinamico.aviationstackfeeder.domain.schema.Flight;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;

import java.util.ArrayList;

public class FlightEventMapper {
    public ArrayList<FlightEvent> mapToFlightEvents(FlightResponse flightResponse) {

        ArrayList<FlightEvent> flightEvents = new ArrayList<>();
        for (Flight flight : flightResponse.getData()) {

            flightEvents.add(new FlightEvent(flight.getFlightId().getFlightIcao(),
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
            ));
        }

        return flightEvents;
    }

}
