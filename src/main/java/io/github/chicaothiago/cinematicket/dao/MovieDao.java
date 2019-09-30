package io.github.chicaothiago.cinematicket.dao;

import com.mysql.jdbc.PreparedStatement;
import io.github.chicaothiago.cinematicket.bean.CinemaBean;
import io.github.chicaothiago.cinematicket.bean.MovieBean;
import io.github.chicaothiago.cinematicket.utils.CreateHash;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class MovieDao extends MysqlBasicDao {
    public MovieDao() {
        this.bean = new MovieBean();
        this.table = "movies";
    }



    public ArrayList<MovieBean> selectAllMovies(Integer limit) throws SQLException {
        ArrayList<MovieBean> datas = new ArrayList<>();

        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(
            "SELECT movies.id, movies.name, movies.url_image, c.id, c.name FROM movies " +
                "JOIN movie_cinema mc on movies.id = mc.movie_id " +
                "JOIN cinema c on mc.cinema_id = c.id WHERE movies.deleted_at is null " +
                "ORDER BY movies.updated_at desc "
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        int currentMovie = 0;
        while (resultSet.next()) {
            MovieBean movieBean = new MovieBean();
            movieBean.id = resultSet.getInt("movies.id");
            movieBean.name = resultSet.getString("movies.name");
            movieBean.url_image = resultSet.getString("movies.url_image");

            if (datas.size() != 0) {
                if (datas.get(currentMovie).id == resultSet.getInt("id")) {
                    CinemaBean cinemaBean = new CinemaBean();
                    cinemaBean.id = resultSet.getInt("c.id");
                    cinemaBean.name = resultSet.getString("c.name");
                    datas.get(currentMovie).cinemas.put(cinemaBean.id.toString(), cinemaBean);
                    continue;
                }
            }

            datas.add(movieBean);
            currentMovie = datas.size() - 1;
            if (datas.size() == limit +1) {
                datas.remove(currentMovie);
                break;
            }
        }

        resultSet.close();
        preparedStatement.close();

        return datas;
    }

    public ArrayList<MovieBean> selectAllMovies() throws SQLException {
        ArrayList<MovieBean> datas = new ArrayList<>();

        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(
            "SELECT movies.id, movies.name, movies.url_image, c.id, c.name FROM movies " +
                "JOIN movie_cinema mc on movies.id = mc.movie_id " +
                "JOIN cinema c on mc.cinema_id = c.id WHERE movies.deleted_at is null"
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        int currentMovie = 0;
        while (resultSet.next()) {
            MovieBean movieBean = new MovieBean();
            movieBean.id = resultSet.getInt("movies.id");
            movieBean.name = resultSet.getString("movies.name");
            movieBean.url_image = resultSet.getString("movies.url_image");

            if (datas.size() != 0) {
                if (datas.get(currentMovie).id == resultSet.getInt("id")) {
                    CinemaBean cinemaBean = new CinemaBean();
                    cinemaBean.id = resultSet.getInt("c.id");
                    cinemaBean.name = resultSet.getString("c.name");
                    datas.get(currentMovie).cinemas.put(cinemaBean.id.toString(), cinemaBean);
                    continue;
                }
            }

            datas.add(movieBean);
            currentMovie = datas.size() - 1;
        }

        resultSet.close();
        preparedStatement.close();

        return datas;
    }

    public MovieBean getMovie(Integer id) throws SQLException {

        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(
            "SELECT movies.id, movies.name, movies.url_image, c.id, c.name FROM movies " +
                "JOIN movie_cinema mc on movies.id = mc.movie_id " +
                "JOIN cinema c on mc.cinema_id = c.id WHERE movies.id = ?"
        );
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        MovieBean movieBean = new MovieBean();
        while (resultSet.next()) {
            movieBean.id = resultSet.getInt("movies.id");
            movieBean.name = resultSet.getString("movies.name");
            movieBean.url_image = resultSet.getString("movies.url_image");

            CinemaBean cinemaBean = new CinemaBean();
            cinemaBean.id = resultSet.getInt("c.id");
            cinemaBean.name = resultSet.getString("c.name");
            movieBean.cinemas.put(cinemaBean.id.toString(), cinemaBean);
        }

        resultSet.close();
        preparedStatement.close();

        return movieBean;
    }


    public void insertMovie(HttpServletRequest req) {
        String[] cinemas = req.getParameterValues("cinemas[]");
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        String hash = "";
        try {
            hash = CreateHash.create(
                req.getParameter("name") + req.getParameter("url_image") + time.getTime()
            );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        try {
            PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(
                "INSERT INTO movies (name, url_image, hash, created_at, updated_at) VALUES (?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, req.getParameter("name"));
            preparedStatement.setString(2, req.getParameter("url_image"));
            preparedStatement.setString(3, hash);
            preparedStatement.setTimestamp(4, time);
            preparedStatement.setTimestamp(5, time);
            preparedStatement.execute();
            preparedStatement.close();

            this.relationCinema(cinemas, hash);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void relationCinema(String[] cinemas, String hash) throws SQLException {
        MovieCinemaDao movieCinemaDao = new MovieCinemaDao();
        Integer idMovie = this.getIdMovie(hash);
        for (String cinema : cinemas) {
            movieCinemaDao.insertMovieCinema(idMovie, Integer.parseInt(cinema));
        }
    }

    public Integer getIdMovie(String hash) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(
            "SELECT id FROM movies WHERE hash = ? LIMIT 1"
        );
        preparedStatement.setString(1, hash);
        ResultSet resultSet = preparedStatement.executeQuery();
        Integer id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }

        return id;
    }

    public void deleteMovie(Integer delete_id) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        try {
            PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(
                "UPDATE movies SET deleted_at = ? WHERE id = ?"
            );
            preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setInt(2, delete_id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(HttpServletRequest req) throws SQLException {
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        String hash = "";

        try {
            hash = CreateHash.create(
                req.getParameter("name") + req.getParameter("url_image") + time.getTime()
            );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(
            "UPDATE movies SET name = ?, url_image = ?, hash = ?, updated_at = ? WHERE id = ?"
        );

        preparedStatement.setString(1, req.getParameter("name"));
        preparedStatement.setString(2, req.getParameter("url_image"));
        preparedStatement.setString(3, hash);
        preparedStatement.setTimestamp(4, time);
        preparedStatement.setInt(5, Integer.parseInt(req.getParameter("id")));
        preparedStatement.execute();
        preparedStatement.close();

        this.relationCinema(req.getParameterValues("cinemas[]"), hash);
    }
}
