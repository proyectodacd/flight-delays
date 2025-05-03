package com.duodinamico;

import com.duodinamico.infrastructure.adapters.sqlite.FlightSQLStore;
import com.duodinamico.infrastructure.rest.controller.FlightController;
import com.duodinamico.controller.TaskScheduler;
import com.duodinamico.infrastructure.adapters.apiconsumer.AviationStackProvider;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FlightController controller = new FlightController(new AviationStackProvider(Arrays.copyOfRange(args,2,args.length)), new FlightSQLStore(args[0]), new TaskScheduler());
        controller.execute();
    }
}
