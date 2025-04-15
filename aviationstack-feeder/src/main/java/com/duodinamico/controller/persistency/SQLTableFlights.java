package com.duodinamico.controller.persistency;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTableFlights {

    public void createTable(Statement statement) throws SQLException {
        statement.execute("create table flights (" +
                "    flight_icao text," +
                "    query_time text," +
                "    flight_date text," +
                "    flight_status text," +
                "    departure_airport text," +
                "    departure_timezone text," +
                "    departure_iata text," +
                "    departure_icao text," +
                "    departure_delay integer," +
                "    departure_scheduled text," +
                "    departure_actual text," +
                "    arrival_airport text," +
                "    arrival_timezone text," +
                "    arrival_iata text," +
                "    arrival_icao text," +
                "    arrival_delay integer," +
                "    arrival_scheduled text," +
                "    arrival_actual text," +
                "    livestatus_updated text," +
                "    livestatus_latitude real," +
                "    livestatus_longitude real," +
                "    livestatus_altitude real," +
                "    livestatus_horizontalspeed real," +
                "    livestatus_verticalspeed real," +
                "    livestatus_isground boolean," +
                "    primary key (flight_icao, flight_date)" +
                ");");
    }


    public static void main(String[] args) {
        SQLConnection sql = new SQLConnection();
        String dbPath = "";
        try (Connection connection = sql.connect(dbPath)) {
            Statement statement = connection.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
