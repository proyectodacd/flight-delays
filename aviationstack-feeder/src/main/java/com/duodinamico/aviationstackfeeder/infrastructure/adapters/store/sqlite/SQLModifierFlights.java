package com.duodinamico.aviationstackfeeder.infrastructure.adapters.store.sqlite;

import com.duodinamico.aviationstackfeeder.domain.model.FlightModel;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SQLModifierFlights {

    public void insert(Statement statement, FlightModel flight) throws SQLException {
        try {
            if (flight.getArrivalTimezone() != null && flight.getDepartureAirport() != null && !(flight.getDepartureAirport().contains("'")) && !(flight.getArrivalAirport().contains("'"))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                statement.execute("create table if not exists flights(" +
                        "    flight_icao text," +
                        "    flight_date text," +
                        "    departure_airport text," +
                        "    departure_timezone text," +
                        "    departure_iata text," +
                        "    departure_delay integer," +
                        "    departure_scheduled text," +
                        "    arrival_airport text," +
                        "    arrival_timezone text," +
                        "    arrival_iata text," +
                        "    arrival_delay integer," +
                        "    arrival_scheduled text," +
                        "    primary key (flight_icao, flight_date)" +
                        ");");
                statement.execute(" insert or ignore into flights (flight_icao, flight_date, departure_airport, departure_timezone, departure_iata, departure_delay, departure_scheduled, arrival_airport, arrival_timezone, arrival_iata, arrival_delay, arrival_scheduled) values ('" +
                        flight.getFlightIcao() + "', " +
                        "'" + flight.getFlightDate() + "'," +
                        "'" + flight.getDepartureAirport() + "'," +
                        "'" + flight.getDepartureTimezone() + "'," +
                        "'" + flight.getDepartureIata() + "'," +
                        "'" + flight.getDepartureDelay() + "'," +
                        "'" + ZonedDateTime.parse(flight.getEstimatedDepartureTime()).format(formatter) + "'," +
                        "'" + flight.getArrivalAirport() + "'," +
                        "'" + flight.getArrivalTimezone() + "'," +
                        "'" + flight.getArrivalIata() + "'," +
                        "'" + flight.getArrivalDelay() + "'," +
                        "'" + ZonedDateTime.parse(flight.getEstimatedArrivalTime()).format(formatter) + "');" );
            }

        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

