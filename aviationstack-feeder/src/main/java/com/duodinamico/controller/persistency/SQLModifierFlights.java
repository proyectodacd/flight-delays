package com.duodinamico.controller.persistency;

import com.duodinamico.controller.model.FlightModel;

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
                statement.execute("create table if not exists flights(" +
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
                statement.execute(" insert into flights (flight_icao, query_time, flight_date, flight_status, departure_airport, departure_timezone, departure_iata, departure_icao, departure_delay, departure_scheduled, departure_actual, arrival_airport, arrival_timezone, arrival_iata, arrival_icao, arrival_delay, arrival_scheduled, arrival_actual, livestatus_updated, livestatus_latitude, livestatus_longitude, livestatus_altitude, livestatus_horizontalspeed, livestatus_verticalspeed, livestatus_isground) values ('" +
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
                statement.execute("create table if not exists flights(" +
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

