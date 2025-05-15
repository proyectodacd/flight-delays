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
