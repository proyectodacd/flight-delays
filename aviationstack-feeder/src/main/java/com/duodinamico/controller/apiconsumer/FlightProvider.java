package com.duodinamico.controller.apiconsumer;
import com.duodinamico.model.FlightModel;
import com.duodinamico.model.schema.Flight;

import java.util.ArrayList;
import java.util.List;

public interface FlightProvider {
    ArrayList<FlightModel> flightProvider();
}
