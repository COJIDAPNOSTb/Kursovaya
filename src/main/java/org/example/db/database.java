package org.example.db;

import org.example.spring.Train;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class database {

    public static Connection connectDb(){

        try{
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TrainDB", "postgres", "12345");

            return connect;
        }
        catch(Exception e){e.printStackTrace();}
        return null;
    }

    public static List<Map<String, Object>> executeQuery(String sqlQuery) {
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection connection = connectDb();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("trainId", resultSet.getString("train_id"));
                row.put("trainStart", resultSet.getString("train_start"));
                row.put("trainEnd", resultSet.getString("train_end"));
                row.put("trainDuration", resultSet.getString("train_duration"));
                results.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

}