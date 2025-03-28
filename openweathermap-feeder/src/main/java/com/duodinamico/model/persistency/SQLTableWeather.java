package com.duodinamico.model.persistency;

import com.duodinamico.controller.persistency.SQLConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTableWeather {

    public void createTable(Statement statement) throws SQLException {
        statement.execute("create table weather (" +
                "    flight_icao text," +
                "    unixtime integer," +
                "    standard_time text," +
                "    airport_name text," +
                "    timezone text," +
                "    temperature real," +
                "    feels_like real," +
                "    wind_speed real," +
                "    wind_direction integer," +
                "    precipitations real," +
                "    snow_measurement real," +
                "    description text," +
                "    airport_description text," +
                "    primary key (flight_icao, standard_time)" +
                ");");
    }

    // C:\Users\Ayoze\Desktop\DACDproyect\flightdelays\flightdelaysv1.db

    public static void main(String[] args) {
        SQLConnection sql = new SQLConnection();
        String dbPath = "";
        try(Connection connection = sql.connect(dbPath)) {
            Statement statement = connection.createStatement();
            statement.execute("create table weather (" +
                    "    flight_icao text," +
                    "    unixtime integer," +
                    "    standard_time text," +
                    "    airport_name text," +
                    "    timezone text," +
                    "    temperature real," +
                    "    feels_like real," +
                    "    wind_speed real," +
                    "    wind_direction integer," +
                    "    precipitations real," +
                    "    snow_measurement real," +
                    "    description text," +
                    "    airport_description text," +
                    "    primary key (flight_icao, standard_time)" +
                    ");");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
