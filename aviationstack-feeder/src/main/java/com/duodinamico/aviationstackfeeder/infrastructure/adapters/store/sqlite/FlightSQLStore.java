package com.duodinamico.aviationstackfeeder.infrastructure.adapters.store.sqlite;

import com.duodinamico.aviationstackfeeder.domain.model.FlightModel;
import com.duodinamico.aviationstackfeeder.infrastructure.ports.FlightStore;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;
import com.duodinamico.aviationstackfeeder.tools.mappers.FlightModelMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FlightSQLStore implements FlightStore {

    private final String dbPath;
    private FlightModelMapper mapper;
    private final SQLConnection connection;
    private final SQLModifierFlights sqlmodder;

    public FlightSQLStore(String dbPath, SQLConnection connection, SQLModifierFlights sqlmodder, FlightModelMapper mapper) {
        this.dbPath = dbPath;
        this.mapper = mapper;
        this.sqlmodder = sqlmodder;
        this.connection = connection;
    }

    public String getDbPath() {
        return dbPath;
    }

    @Override
    public void saveFlights(FlightResponse flightResponse) {
        try(Connection connection = this.connection.connect(this.dbPath)) {
            ArrayList<FlightModel> flights = this.mapper.mapToFlightModels(flightResponse);
            Statement statement = connection.createStatement();
            for(FlightModel flight : flights) {
                this.sqlmodder.insert(statement, flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
