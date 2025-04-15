package com.duodinamico.controller.persistency;

import com.duodinamico.model.FlightModel;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SQLModifierFlights {

    public void insert(Statement statement, FlightModel flight) throws SQLException {
        try {
            if (flight.getLiveStatusTimeStamp() != null && flight.getArrivalTimezone() != null && !(flight.getDepartureAirport().contains("'")) && !(flight.getArrivalAirport().contains("'"))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                statement.execute("insert into flights (flight_icao, query_time, flight_date, flight_status, departure_airport, departure_timezone, departure_iata, departure_icao, departure_delay, departure_scheduled, departure_actual, arrival_airport, arrival_timezone, arrival_iata, arrival_icao, arrival_delay, arrival_scheduled, arrival_actual, livestatus_updated, livestatus_latitude, livestatus_longitude, livestatus_altitude, livestatus_horizontalspeed, livestatus_verticalspeed, livestatus_isground) values ('" +
                        flight.getFlightIcao() + "', '" +
                        ZonedDateTime.now(ZoneId.of(flight.getArrivalTimezone()))
                                .format(formatter) + "'," +
                        "'" + flight.getFlightDate() + "'," +
                        "'" + flight.getFlightStatus() + "'," +
                        "'" + flight.getDepartureAirport() + "'," +
                        "'" + flight.getDepartureTimezone() + "'," +
                        "'" + flight.getDepartureIata() + "'," +
                        "'" + flight.getDepartureIcao() + "'," +
                        "'" + flight.getDepartureDelay() + "'," +
                        "'" + ZonedDateTime.parse(flight.getEstimatedDepartureTime()).format(formatter) + "'," +
                        "'" + ZonedDateTime.parse(flight.getActualDepartureTime()).format(formatter) + "'," +
                        "'" + flight.getArrivalAirport() + "'," +
                        "'" + flight.getArrivalTimezone() + "'," +
                        "'" + flight.getArrivalIata() + "'," +
                        "'" + flight.getArrivalIcao() + "'," +
                        "'" + flight.getArrivalDelay() + "'," +
                        "'" + ZonedDateTime.parse(flight.getEstimatedArrivalTime()).format(formatter) + "'," +
                        "'" + "unknown" + "'," +
                        "'" + ZonedDateTime.parse(flight.getLiveStatusTimeStamp()).format(formatter) + "'," +
                        "'" + flight.getLatitude() + "'," +
                        "'" + flight.getLongitude() + "'," +
                        "'" + flight.getAltitude() + "'," +
                        "'" + flight.getHorizontalSpeed() + "'," +
                        "'" + flight.getVerticalSpeed() + "'," +
                        "'" + flight.isOnGround() + "')" +




                        ";");
            }

            else if (flight.getLiveStatusTimeStamp() == null && flight.getArrivalTimezone() != null && !(flight.getDepartureAirport().contains("'")) && !(flight.getArrivalAirport().contains("'"))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                statement.execute("insert into flights (flight_icao, query_time, flight_date, flight_status, departure_airport, departure_timezone, departure_iata, departure_icao, departure_delay, departure_scheduled, departure_actual, arrival_airport, arrival_timezone, arrival_iata, arrival_icao, arrival_delay, arrival_scheduled, arrival_actual) values ('" +
                        flight.getFlightIcao() + "', '" +
                        ZonedDateTime.now(ZoneId.of(flight.getArrivalTimezone()))
                                .format(formatter) + "'," +
                        "'" + flight.getFlightDate() + "'," +
                        "'" + flight.getFlightStatus() + "'," +
                        "'" + flight.getDepartureAirport() + "'," +
                        "'" + flight.getDepartureTimezone() + "'," +
                        "'" + flight.getDepartureIata() + "'," +
                        "'" + flight.getDepartureIcao() + "'," +
                        "'" + flight.getDepartureDelay() + "'," +
                        "'" + ZonedDateTime.parse(flight.getEstimatedDepartureTime()).format(formatter) + "'," +
                        "'" + ZonedDateTime.parse(flight.getActualDepartureTime()).format(formatter) + "'," +
                        "'" + flight.getArrivalAirport() + "'," +
                        "'" + flight.getArrivalTimezone() + "'," +
                        "'" + flight.getArrivalIata() + "'," +
                        "'" + flight.getArrivalIcao() + "'," +
                        "'" + flight.getArrivalDelay() + "'," +
                        "'" + ZonedDateTime.parse(flight.getEstimatedArrivalTime()).format(formatter) + "'," +
                        "'" + "unknown" + "')" +





                        ";");

            }
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

