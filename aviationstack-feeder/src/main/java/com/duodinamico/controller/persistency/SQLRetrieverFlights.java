package com.duodinamico.controller.persistency;


import com.duodinamico.model.*;

import java.sql.*;


public class SQLRetrieverFlights {

    public Flight select(Connection connection, String flightIcao, String date) throws SQLException {

        String sql = "SELECT * FROM flights WHERE flight_icao = ? & flight_date = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, flightIcao);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Flight(
                        new Departure(rs.getString("departure_airport"),
                                rs.getString("departure_timezone"),
                                rs.getString("departure_iata"),
                                rs.getString("departure_icao"),
                                rs.getInt("departure_delay"),
                                rs.getString("departure_scheduled"),
                                rs.getString("departure_actual")),
                        new Arrival(rs.getString("arrival_airport"),
                                rs.getString("arrival_timezone"),
                                rs.getString("arrival_iata"),
                                rs.getString("arrival_icao"),
                                rs.getInt("arrival_delay"),
                                rs.getString("arrival_scheduled"),
                                rs.getString("arrival_actual")),
                        rs.getString("flight_date"),
                        rs.getString("flight_status"),
                        new LiveStatus(rs.getString("livestatus_updated"),
                                rs.getFloat("livestatus_latitude"),
                                rs.getFloat("livestatus_longitude"),
                                rs.getFloat("livestatus_altitude"),
                                rs.getFloat("livestatus_horizontalspeed"),
                                rs.getFloat("livestatus_verticalspeed"),
                                rs.getBoolean("livestatus_isground")),
                        new FlightId(rs.getString("flight_icao"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

