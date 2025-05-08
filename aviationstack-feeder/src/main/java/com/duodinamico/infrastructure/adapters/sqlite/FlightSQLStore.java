package com.duodinamico.infrastructure.adapters.sqlite;

import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.domain.model.FlightModel;
import com.duodinamico.domain.ports.FlightStore;
import com.duodinamico.infrastructure.adapters.apiconsumer.schema.Flight;
import com.duodinamico.infrastructure.adapters.apiconsumer.schema.FlightResponse;
import com.duodinamico.infrastructure.adapters.mappers.FlightEventMapper;
import com.duodinamico.infrastructure.adapters.mappers.FlightModelMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FlightSQLStore implements FlightStore<FlightModel> {

    private final String dbPath;
    private FlightModelMapper mapper;

    public FlightSQLStore(String dbPath) {
        this.dbPath = dbPath;
        this.mapper = new FlightModelMapper();

    }

    public String getDbPath() {
        return dbPath;
    }

    @Override
    public void saveFlights(FlightResponse flightResponse) {

        SQLConnection sql = new SQLConnection();
        SQLModifierFlights sqlmodder = new SQLModifierFlights();
        try(Connection connection = sql.connect(this.dbPath)) {
            ArrayList<FlightModel> flights = mapper.mapToFlightModels(flightResponse);
            Statement statement = connection.createStatement();
            for(FlightModel flight : flights) {
                sqlmodder.insert(statement, flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<FlightModel> loadFlights(){
        SQLConnection sql = new SQLConnection();
        SQLRetrieverFlights sqlretriever = new SQLRetrieverFlights();
        try(Connection connection = sql.connect(this.dbPath)) {
            return sqlretriever.select(connection);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
