package com.duodinamico.controller.persistency;


import com.duodinamico.controller.FlightMapper;
import com.duodinamico.model.FlightModel;

import java.sql.*;
import java.time.LocalDate;


import java.util.ArrayList;

public class SQLRetrieverFlights {

    public ArrayList<FlightModel> select(Connection connection) throws SQLException {
        ArrayList<FlightModel> flights = new ArrayList<>();
        FlightMapper mapper = new FlightMapper();

        String sql = "SELECT * FROM flights WHERE flight_date = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, LocalDate.now().minusDays(1).toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FlightModel flight = new FlightModel(
                        rs.getString("flight_icao"),
                                rs.getString("flight_date"),
                                rs.getString("flight_status"),
                                rs.getString("departure_airport"),
                                rs.getString("departure_timezone"),
                                rs.getString("departure_iata"),
                                rs.getString("departure_icao"),
                                rs.getInt("departure_delay"),
                                rs.getString("departure_scheduled"),
                                rs.getString("departure_actual"),
                                rs.getString("arrival_airport"),
                                rs.getString("arrival_timezone"),
                                rs.getString("arrival_iata"),
                                rs.getString("arrival_icao"),
                                rs.getInt("arrival_delay"),
                                rs.getString("arrival_scheduled"),
                                rs.getString("arrival_actual"),
                                rs.getString("livestatus_updated"),
                                rs.getFloat("livestatus_latitude"),
                                rs.getFloat("livestatus_longitude"),
                                rs.getFloat("livestatus_altitude"),
                                rs.getFloat("livestatus_horizontalspeed"),
                                rs.getFloat("livestatus_verticalspeed"),
                                rs.getBoolean("livestatus_isground")
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
