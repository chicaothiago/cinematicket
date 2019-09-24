package io.github.chicaothiago.cinematicket.dao;

import io.github.chicaothiago.cinematicket.bean.MovieBean;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MovieDao extends MysqlBasicDao {
    public MovieDao() {
        this.table = "movies";
        this.bean = new MovieBean();
    }

    public ArrayList<MovieBean> selectAllMovies() throws SQLException {
        ArrayList<MovieBean> datas = new ArrayList<>();
        ArrayList<HashMap<String, Object>> results = this.selectAll(new ArrayList<>());
        for (HashMap<String, Object> result : results) {
            for(Map.Entry<String, Object> entry : result.entrySet()) {
                MovieBean movieBean = new MovieBean();
                if (entry.getKey() == "name") movieBean.name = entry.getValue().toString();
                if (entry.getKey() == "url_image") movieBean.url_image = entry.getValue().toString();
                datas.add(movieBean);
            }
        }

        return datas;
    }

    public MovieBean getMovie(Integer id) throws SQLException {
        HashMap<String, Object> result = this.getById(id, new ArrayList<>());
        MovieBean movieBean = new MovieBean();
        movieBean.name = result.get("name").toString();
        movieBean.url_image = result.get("url_image").toString();
        return movieBean;
    }

    public void deleteMovie(Integer delete_id) {

    }
}
