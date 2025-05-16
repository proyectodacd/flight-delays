package com.duodinamico.aviationstackfeeder.tools.mappers;

import com.duodinamico.aviationstackfeeder.domain.model.FlightEvent;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightDescription;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;

import java.util.ArrayList;

public class FlightEventMapper {
    public ArrayList<FlightEvent> mapToFlightEvents(FlightResponse flightResponse) {

        ArrayList<FlightEvent> flightEvents = new ArrayList<>();
        for (FlightDescription flightDescription : flightResponse.getData()) {

            flightEvents.add(new FlightEvent(flightDescription.getFlightId().getFlightIcao(),
                    flightDescription.getFlightDate(),
                    flightDescription.getDeparture().getAirport(),
                    flightDescription.getDeparture().getTimezone(),
                    flightDescription.getDeparture().getIata(),
                    flightDescription.getDeparture().getDelay(),
                    flightDescription.getDeparture().getScheduled(),
                    flightDescription.getArrival().getAirport(),
                    flightDescription.getArrival().getTimezone(),
                    flightDescription.getArrival().getIata(),
                    flightDescription.getArrival().getDelay(),
                    flightDescription.getArrival().getScheduled()
            ));
        }

        return flightEvents;
    }

}
