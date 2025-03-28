package com.duodinamico.controller.persistency;

import com.duodinamico.model.Flight;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SQLStore implements FlightStore{
    @Override
    public void saveFlights(List<Flight> flights, String[] args) {
        SQLConnection sql = new SQLConnection();
        SQLModifierFlights sqlmodder = new SQLModifierFlights();
        try(Connection connection = sql.connect(args[0])) {
            Statement statement = connection.createStatement();
            for(Flight flight : flights) {
                sqlmodder.insert(statement, flight);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Flight> loadFlights(String[] args){
        SQLConnection sql = new SQLConnection();
        SQLRetrieverFlights sqlretriever = new SQLRetrieverFlights();
        try(Connection connection = sql.connect(args[0])) {
            return sqlretriever.select(connection);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
