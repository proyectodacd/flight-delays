package com.duodinamico.controller.apiconsumer;
import com.duodinamico.controller.eventIntegration.FlightEvent;
import com.duodinamico.controller.model.FlightModel;

import java.util.ArrayList;

public interface FlightProvider {
    ArrayList<FlightModel> flightProvider();
    ArrayList<FlightEvent> flightEventProvider();
}
