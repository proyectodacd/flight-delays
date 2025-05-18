package com.duodinamico.aviationstackfeeder.tools.mappers;

import com.duodinamico.aviationstackfeeder.domain.schema.FlightDescription;
import com.duodinamico.aviationstackfeeder.domain.model.FlightModel;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;

import java.util.ArrayList;

public class FlightModelMapper {

    public ArrayList<FlightModel> mapToFlightModels(FlightResponse flightResponse) {

        ArrayList<FlightModel> flightModelList = new ArrayList<>();
        for (FlightDescription flightDescription : flightResponse.getData()) {
            flightModelList.add(new FlightModel(
                    flightDescription.getFlightId().getFlightIcao(),
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

        return flightModelList;
    }

}
