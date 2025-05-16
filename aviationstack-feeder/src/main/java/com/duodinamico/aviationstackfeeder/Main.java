package com.duodinamico.aviationstackfeeder;

import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.AviationStackProcessor;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.FlightDeserializer;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.store.activemq.FlightEventSerializer;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.store.activemq.FlightEventStore;
import com.duodinamico.aviationstackfeeder.application.usecases.collectorandstore.FlightController;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.store.sqlite.FlightSQLStore;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.store.sqlite.SQLConnection;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.store.sqlite.SQLModifierFlights;
import com.duodinamico.aviationstackfeeder.tools.mappers.FlightEventMapper;
import com.duodinamico.aviationstackfeeder.tools.mappers.FlightModelMapper;
import com.duodinamico.aviationstackfeeder.tools.scheduler.TaskScheduler;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.AviationStackProvider;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FlightController controller = new FlightController(new AviationStackProvider(new AviationStackProcessor(Arrays.copyOfRange(args,6,args.length)),new FlightDeserializer(), Arrays.copyOfRange(args,2,6)), new FlightEventStore(args[1],new FlightEventSerializer(),new FlightEventMapper()), new TaskScheduler());
        controller.execute();
    }
}
