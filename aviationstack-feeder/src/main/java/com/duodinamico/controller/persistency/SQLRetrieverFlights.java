package com.duodinamico.controller.persistency;


import com.duodinamico.model.*;

import java.sql.*;
import java.time.LocalDate;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLRetrieverFlights {

    public List<Flight> select(Connection connection) throws SQLException {
        List<Flight> flights = new ArrayList<>();

        String sql = "SELECT * FROM flights WHERE flight_date = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, LocalDate.now().minusDays(1).toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Flight flight = new Flight(
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
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
