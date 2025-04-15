package com.duodinamico.controller.persistency;

import com.duodinamico.model.FlightModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FlightSQLStore implements FlightStore{

    private final String dbPath;

    public FlightSQLStore(String dbPath) {
        this.dbPath = dbPath;

    }

    public String getDbPath() {
        return dbPath;
    }

    @Override
    public void saveFlights(ArrayList<FlightModel> flights) {
        SQLConnection sql = new SQLConnection();
        SQLModifierFlights sqlmodder = new SQLModifierFlights();
        try(Connection connection = sql.connect(getDbPath())) {
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
        try(Connection connection = sql.connect(getDbPath())) {
            return sqlretriever.select(connection);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
