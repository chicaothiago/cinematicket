package io.github.chicaothiago.cinematicket.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class MovieCinemaDao extends MysqlBasicDao {

    public void insertMovieCinema(Integer idMovie, Integer cinemaId) throws SQLException {
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        if (!this.alreadyJoin(idMovie, cinemaId)) {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                "INSERT INTO movie_cinema (cinema_id, movie_id, created_at, updated_at) VALUES (?, ? ,? ,?)"
            );
            preparedStatement.setInt(1, cinemaId);
            preparedStatement.setInt(2, idMovie);
            preparedStatement.setTimestamp(3, time);
            preparedStatement.setTimestamp(4, time);
            preparedStatement.execute();
            preparedStatement.close();
        }
    }

    public boolean alreadyJoin(Integer idMovie, int idCinema) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                "SELECT id FROM movie_cinema WHERE movie_id = ? AND cinema_id = ? LIMIT 1"
            );
            preparedStatement.setInt(1, idMovie);
            preparedStatement.setInt(2, idCinema);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean has = false;
            if (resultSet.next()) {
                has = true;
            }

            resultSet.close();
            preparedStatement.close();

            return has;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
