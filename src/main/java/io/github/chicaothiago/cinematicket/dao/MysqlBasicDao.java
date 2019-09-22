package io.github.chicaothiago.cinematicket.dao;

import io.github.chicaothiago.cinematicket.bean.DefaultBean;
import io.github.chicaothiago.cinematicket.dao.mysql.queries.InsertMysql;
import io.github.chicaothiago.cinematicket.dao.mysql.queries.SelectMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MysqlBasicDao extends MysqlConnectionDao {
    public String table = "";
    public DefaultBean bean = new DefaultBean();

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
