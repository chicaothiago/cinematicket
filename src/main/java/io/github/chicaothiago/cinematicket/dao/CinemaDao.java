package io.github.chicaothiago.cinematicket.dao;

import io.github.chicaothiago.cinematicket.bean.AddressBean;
import io.github.chicaothiago.cinematicket.bean.CinemaBean;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CinemaDao extends MysqlBasicDao {
    public AddressDao addressDao = new AddressDao();

    public CinemaDao() {
        this.table = "cinema";
        this.bean = new CinemaBean();
    }

    public CinemaBean findOne(Integer id) throws SQLException {
        CinemaBean cinemaBean = new CinemaBean();
        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "select * from " + this.table + " JOIN address ON address.id = address_id WHERE cinema.id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            cinemaBean.id = resultSet.getInt("id");
            cinemaBean.name = resultSet.getString("name");
            cinemaBean.address = new AddressBean(
                resultSet.getInt("address.id"),
                resultSet.getString("address.street"),
                resultSet.getString("address.number"),
                resultSet.getString("address.zipcode"),
                resultSet.getString("address.city"),
                resultSet.getString("address.state"),
                resultSet.getString("address.country")
            );
        }
        resultSet.close();
        preparedStatement.close();
        return cinemaBean;
    }

    public void insertBean(CinemaBean cinemaBean) throws SQLException {
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "INSERT INTO " + this.table +
                " (name, address_id, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?)"
        );
        preparedStatement.setString(1, cinemaBean.name);
        preparedStatement.setInt(2, cinemaBean.address.id);
        preparedStatement.setTimestamp(3, time);
        preparedStatement.setTimestamp(4, time);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public ArrayList<CinemaBean> selectAll() throws SQLException {
        ArrayList<CinemaBean> resultList = new ArrayList<>();

        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "select * from " + this.table + " JOIN address ON address.id = address_id WHERE cinema.deleted_at IS NULL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            CinemaBean cinemaBean = new CinemaBean();
            cinemaBean.id = resultSet.getInt("id");
            cinemaBean.name = resultSet.getString("name");
            cinemaBean.address = new AddressBean(
                resultSet.getInt("address.id"),
                resultSet.getString("address.street"),
                resultSet.getString("address.number"),
                resultSet.getString("address.zipcode"),
                resultSet.getString("address.city"),
                resultSet.getString("address.state"),
                resultSet.getString("address.country")
            );
            resultList.add(cinemaBean);
        }

        preparedStatement.close();
        resultSet.close();

        return resultList;
    }

    public void updateCinema(HttpServletRequest req) throws SQLException {
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "UPDATE " + this.table +
                " SET " +
                " `name` = ?, " +
                " `updated_at` = ? " +
                " WHERE `id` = ?"
        );
        preparedStatement.setString(1, req.getParameter("name").toString());
        preparedStatement.setTimestamp(2, time);
        preparedStatement.setInt(3, Integer.parseInt(req.getParameter("id").toString()));
        preparedStatement.execute();
        preparedStatement.close();

    }

    public void delete(HttpServletRequest req) throws SQLException {
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "UPDATE " + this.table +
                " SET " +
                " `deleted_at` = ? " +
                " WHERE `id` = ?"
        );
        preparedStatement.setTimestamp(1, time);
        preparedStatement.setInt(2, Integer.parseInt(req.getParameter("delete_id").toString()));
        preparedStatement.execute();
        preparedStatement.close();
    }
}
