package com.duodinamico.aviationstackfeeder.tools.mappers;

import com.duodinamico.aviationstackfeeder.domain.schema.Flight;
import com.duodinamico.aviationstackfeeder.domain.model.FlightModel;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;

import java.util.ArrayList;

public class FlightModelMapper {

    public ArrayList<FlightModel> mapToFlightModels(FlightResponse flightResponse) {

        ArrayList<FlightModel> flightModelList = new ArrayList<>();
        for (Flight flight : flightResponse.getData()) {
            flightModelList.add(new FlightModel(
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
            ));
        }

        return flightModelList;
    }

}
