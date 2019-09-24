package io.github.chicaothiago.cinematicket.dao;

import io.github.chicaothiago.cinematicket.bean.DefaultBean;
import io.github.chicaothiago.cinematicket.dao.mysql.queries.InsertMysql;
import io.github.chicaothiago.cinematicket.dao.mysql.queries.SelectMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MysqlBasicDao extends MysqlConnectionDao {
    public String table = "";
    public DefaultBean bean = new DefaultBean();

    public ArrayList<HashMap<String, Object>> selectAll(ArrayList<String> fields) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "SELECT ? FROM ? WHERE deleted_at IS NULL"
        );

        preparedStatement.setString(2, this.table);
        if (fields.size() > 0) {
            preparedStatement.setString(1, String.join(", ", fields));
        } else {
            preparedStatement.setString(1, "*");
        }


        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        Integer countFields = fields.size() > 0 ? fields.size() : this.bean.countFields;

        ArrayList<HashMap<String, Object>> results = new ArrayList<>();
        while (resultSet.next()) {
            HashMap<String, Object> mapBean = new HashMap<>();
            for (int i = 0; i < countFields; i++) {
                mapBean.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
            }
            results.add(mapBean);
        }

        preparedStatement.close();
        resultSet.close();

        return results;
    }

    public HashMap<String, Object> getById(Integer id, ArrayList<String> fields) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "SELECT ? FROM ? WHERE id = ? LIMIT 1"
        );

        preparedStatement.setString(2, this.table);
        preparedStatement.setInt(3, id);

        if (fields.size() > 0) {
            preparedStatement.setString(1, String.join(", ", fields));
        } else {
            preparedStatement.setString(1, "*");
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        Integer countFields = fields.size() > 0 ? fields.size() : this.bean.countFields;

        HashMap<String, Object> result = new HashMap<>();
        while (resultSet.next()) {
            for (int i = 0; i < countFields; i++) {
                result.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
            }
        }

        preparedStatement.close();
        resultSet.close();

        return result;
    }

    public boolean deleteById(Integer id) throws SQLException {
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        PreparedStatement preparedStatement = this.connection.prepareStatement(
            "UPDATE ? SET updated_at = ?, deleted_at = ? WHERE id = ?"
        );
        preparedStatement.setString(1, this.table);
        preparedStatement.setTimestamp(2, time);
        preparedStatement.setTimestamp(3, time);
        preparedStatement.setInt(4, id);
        preparedStatement.execute();
        preparedStatement.close();
        return true;
    }

    public void insert(ArrayList<String> fields, ArrayList<Object> values)
        throws SQLException {
        new InsertMysql(
            this.connection,
            this.table,
            fields,
            values
        ).insert();
    }

    public ArrayList<HashMap<String, Object>> select(
        ArrayList<String> fields,
        ArrayList<String> where
    )
        throws SQLException {
        return new SelectMysql(
            this.connection,
            this.table,
            where,
            fields,
            this.bean
        ).select();
    }

    public ArrayList<HashMap<String, Object>> find(Integer id)
        throws SQLException {
        ArrayList<String> where = new ArrayList<>();
        where.add(" id = " + id.toString() + " ");
        return new SelectMysql(
            this.connection,
            this.table,
            new ArrayList<String>(),
            where,
            this.bean
        ).select();
    }
}
