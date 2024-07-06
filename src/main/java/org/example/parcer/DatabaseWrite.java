package org.example.parcer;

import org.example.db.database;
import org.example.train.TrainP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWrite {
    static void dbWrite(TrainP trainP) throws SQLException {
        String sql = "INSERT INTO public.train (train_id, train_duration, train_start, train_end) VALUES (?, ?, ?, ?);";
        try (Connection connect = database.connectDb(); PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, trainP.getNumber());
            prepare.setString(2, trainP.getDuration());
            prepare.setString(3, trainP.getStartDate());
            prepare.setString(4, trainP.getEndDate());
            prepare.executeUpdate();
        }
    }
}
