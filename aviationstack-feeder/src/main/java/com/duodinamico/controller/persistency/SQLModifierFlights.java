package com.duodinamico.controller.persistency;

import com.duodinamico.model.Flight;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SQLModifierFlights {

    public void insert(Statement statement, Flight flight) throws SQLException {
        try {
            if (flight.getLiveStatus() != null && flight.getArrival().getTimezone() != null && !(flight.getDeparture().getAirport().contains("'")) && !(flight.getArrival().getAirport().contains("'"))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                statement.execute("insert into flights (flight_icao, query_time, flight_date, flight_status, departure_airport, departure_timezone, departure_iata, departure_icao, departure_delay, departure_scheduled, departure_actual, arrival_airport, arrival_timezone, arrival_iata, arrival_icao, arrival_delay, arrival_scheduled, arrival_actual, livestatus_updated, livestatus_latitude, livestatus_longitude, livestatus_altitude, livestatus_horizontalspeed, livestatus_verticalspeed, livestatus_isground) values ('" +
                        flight.getFlightId().getFlightIcao() + "', '" +
                        ZonedDateTime.now(ZoneId.of(flight.getArrival().getTimezone()))
                                .format(formatter) + "'," +
                        "'" + flight.getFlightDate() + "'," +
                        "'" + flight.getFlightStatus() + "'," +
                        "'" + flight.getDeparture().getAirport() + "'," +
                        "'" + flight.getDeparture().getTimezone() + "'," +
                        "'" + flight.getDeparture().getIata() + "'," +
                        "'" + flight.getDeparture().getIcao() + "'," +
                        "'" + flight.getDeparture().getDelay() + "'," +
                        "'" + ZonedDateTime.parse(flight.getDeparture().getScheduled()).format(formatter) + "'," +
                        "'" + ZonedDateTime.parse(flight.getDeparture().getActual()).format(formatter) + "'," +
                        "'" + flight.getArrival().getAirport() + "'," +
                        "'" + flight.getArrival().getTimezone() + "'," +
                        "'" + flight.getArrival().getIata() + "'," +
                        "'" + flight.getArrival().getIcao() + "'," +
                        "'" + flight.getArrival().getDelay() + "'," +
                        "'" + ZonedDateTime.parse(flight.getArrival().getScheduled()).format(formatter) + "'," +
                        "'" + "unknown" + "'," +
                        "'" + ZonedDateTime.parse(flight.getLiveStatus().getUpdated()).format(formatter) + "'," +
                        "'" + flight.getLiveStatus().getLatitude() + "'," +
                        "'" + flight.getLiveStatus().getLongitude() + "'," +
                        "'" + flight.getLiveStatus().getAltitude() + "'," +
                        "'" + flight.getLiveStatus().getSpeedHorizontal() + "'," +
                        "'" + flight.getLiveStatus().getSpeedVertical() + "'," +
                        "'" + flight.getLiveStatus().getIsGround() + "')" +




                        ";");
            }

            else if (flight.getLiveStatus() == null && flight.getArrival().getTimezone() != null && !(flight.getDeparture().getAirport().contains("'")) && !(flight.getArrival().getAirport().contains("'"))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                statement.execute("insert into flights (flight_icao, query_time, flight_date, flight_status, departure_airport, departure_timezone, departure_iata, departure_icao, departure_delay, departure_scheduled, departure_actual, arrival_airport, arrival_timezone, arrival_iata, arrival_icao, arrival_delay, arrival_scheduled, arrival_actual) values ('" +
                        flight.getFlightId().getFlightIcao() + "', '" +
                        ZonedDateTime.now(ZoneId.of(flight.getArrival().getTimezone()))
                                .format(formatter) + "'," +
                        "'" + flight.getFlightDate() + "'," +
                        "'" + flight.getFlightStatus() + "'," +
                        "'" + flight.getDeparture().getAirport() + "'," +
                        "'" + flight.getDeparture().getTimezone() + "'," +
                        "'" + flight.getDeparture().getIata() + "'," +
                        "'" + flight.getDeparture().getIcao() + "'," +
                        "'" + flight.getDeparture().getDelay() + "'," +
                        "'" + ZonedDateTime.parse(flight.getDeparture().getScheduled()).format(formatter) + "'," +
                        "'" + ZonedDateTime.parse(flight.getDeparture().getActual()).format(formatter) + "'," +
                        "'" + flight.getArrival().getAirport() + "'," +
                        "'" + flight.getArrival().getTimezone() + "'," +
                        "'" + flight.getArrival().getIata() + "'," +
                        "'" + flight.getArrival().getIcao() + "'," +
                        "'" + flight.getArrival().getDelay() + "'," +
                        "'" + ZonedDateTime.parse(flight.getArrival().getScheduled()).format(formatter) + "'," +
                        "'" + "unknown" + "')" +





                        ";");

            }
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

