package com.duodinamico.controller.apiconsumer;
import com.duodinamico.controller.model.FlightModel;

import java.util.ArrayList;

public interface FlightProvider {
    ArrayList<FlightModel> flightProvider();
}
