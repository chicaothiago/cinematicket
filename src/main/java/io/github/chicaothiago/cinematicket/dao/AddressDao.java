package io.github.chicaothiago.cinematicket.dao;

import io.github.chicaothiago.cinematicket.bean.AddressBean;
import jdk.jfr.Timespan;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class AddressDao extends MysqlBasicDao {
    AddressBean addressBean = new AddressBean();

    public AddressDao() {
        this.table = "address";
        this.bean = new AddressBean();
    }

    public AddressBean findAddress(Integer id) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "SELECT * FROM " + this.table + " WHERE id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.addressBean.id = resultSet.getInt("id");
            this.addressBean.city = resultSet.getString("city");
            this.addressBean.state = resultSet.getString("state");
            this.addressBean.country = resultSet.getString("country");
            this.addressBean.number = resultSet.getString("number");
            this.addressBean.street = resultSet.getString("street");
            this.addressBean.zipcode = resultSet.getString("zipcode");
        }
        resultSet.close();
        preparedStatement.close();
        return this.addressBean;
    }

    public Integer insertBean(AddressBean addressBean, Boolean getId) throws SQLException {
        java.util.Date date = new java.util.Date();
        Timestamp time = new java.sql.Timestamp(date.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        addressBean.created_at = calendar;
        addressBean.updated_at = calendar;
        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "INSERT INTO " + this.table +
                " (street, number, zipcode, city, state, country, created_at, updated_at) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setString(1, addressBean.street);
        preparedStatement.setString(2, addressBean.number);
        preparedStatement.setString(3, addressBean.zipcode);
        preparedStatement.setString(4, addressBean.city);
        preparedStatement.setString(5, addressBean.state);
        preparedStatement.setString(6, addressBean.country);
        preparedStatement.setTimestamp(7, new Timestamp(addressBean.created_at.getTimeInMillis()));
        preparedStatement.setTimestamp(8, new Timestamp(addressBean.updated_at.getTimeInMillis()));
        preparedStatement.execute();

        if (getId) {
            return this.selectIdBean(addressBean);
        }
        return 0;
    }

    public Integer selectIdBean(AddressBean addressBean) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement(
            "SELECT id FROM " + this.table +
                " WHERE street = ? AND " +
                " number = ? AND " +
                " zipcode = ? AND " +
                " city = ? AND " +
                " state = ? AND " +
                " country = ? AND " +
                " created_at = ? AND " +
                " updated_at = ? " +
                "LIMIT 1"
        );
        ps.setString(1, addressBean.street);
        ps.setString(2, addressBean.number);
        ps.setString(3, addressBean.zipcode);
        ps.setString(4, addressBean.city);
        ps.setString(5, addressBean.state);
        ps.setString(6, addressBean.country);
        ps.setTimestamp(7, new Timestamp(addressBean.created_at.getTimeInMillis()));
        ps.setTimestamp(8, new Timestamp(addressBean.updated_at.getTimeInMillis()));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            addressBean.id = rs.getInt("id");
        }
        rs.close();
        ps.close();
        return addressBean.id;

    }

    public void updateAddress(HttpServletRequest req) throws SQLException {
        java.util.Date date = new java.util.Date();
        Timestamp time = new java.sql.Timestamp(date.getTime());
        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "UPDATE " + this.table +
                " SET street = ?, " +
                " number = ?, " +
                " zipcode = ?, " +
                " city = ?, " +
                " state = ?, " +
                " country = ?, " +
                " updated_at = ? " +
                "WHERE id = ?"
        );
        preparedStatement.setString(1, req.getParameter("street").toString());
        preparedStatement.setString(2, req.getParameter("number").toString());
        preparedStatement.setString(3, req.getParameter("zipcode").toString());
        preparedStatement.setString(4, req.getParameter("city").toString());
        preparedStatement.setString(5, req.getParameter("state").toString());
        preparedStatement.setString(6, req.getParameter("country").toString());
        preparedStatement.setTimestamp(7, time);
        preparedStatement.setInt(8, Integer.parseInt(req.getParameter("address_id").toString()));
        preparedStatement.execute();
        preparedStatement.close();
    }
}
