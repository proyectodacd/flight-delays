package com.duodinamico.aviationstackfeeder;

import com.duodinamico.aviationstackfeeder.infrastructure.adapters.store.activemq.FlightEventStore;
import com.duodinamico.aviationstackfeeder.application.usecases.collectorandstore.FlightController;
import com.duodinamico.aviationstackfeeder.tools.scheduler.TaskScheduler;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.AviationStackProvider;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FlightController controller = new FlightController(new AviationStackProvider(Arrays.copyOfRange(args,2,args.length)), new FlightEventStore(args[1]), new TaskScheduler());
        controller.execute();
    }
}
