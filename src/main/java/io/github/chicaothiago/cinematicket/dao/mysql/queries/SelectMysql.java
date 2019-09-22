package io.github.chicaothiago.cinematicket.dao.mysql.queries;

import io.github.chicaothiago.cinematicket.bean.DefaultBean;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectMysql {
    public Connection connection;
    public String table;
    public ArrayList<String> where;
    public ArrayList<String> fields;
    public DefaultBean bean;
    private HashMap<String, Class> fieldsBean = new HashMap<>();

    public SelectMysql(
        Connection connection,
        String table,
        ArrayList<String> fields,
        ArrayList<String> where,
        DefaultBean bean
    ) {
        this.connection = connection;
        this.table = table;
        this.where = where;
        this.fields = fields;
        this.bean = bean;
        Field[] fb = bean.getClass()
                         .getFields();
        for (final Field fieldBean : fb) {
            this.fieldsBean.put(fieldBean.getName(), fieldBean.getType());
        }
    }

    public ArrayList<HashMap<String, Object>> select() throws SQLException {
        ArrayList<HashMap<String, Object>> finalResult = new ArrayList<>();
        String select = "select ";
        select += this.getSelectFields(this.fields);
        select += " from " + this.table + " ";
        select += this.getSelectWhere();
        PreparedStatement ps = this.connection.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            finalResult.add(this.fetchResult(rs));
        }
        ps.close();
        rs.close();
        return finalResult;
    }

    private HashMap<String, Object> fetchResult(ResultSet rs)
        throws SQLException {
        if (this.fields.size() == 0) {
            return this.fetchResultAllFields(rs);
        }
        return this.fetchResultByFields(rs);
    }

    private HashMap<String, Object> fetchResultByFields(ResultSet rs)
        throws SQLException {
        HashMap<String, Object> result = new HashMap<>();
        for (final String str : this.fields) {
            result = this.parseResult(
                result,
                this.fieldsBean.get(str),
                str,
                rs
            );
        }
        return result;
    }

    private HashMap<String, Object> fetchResultAllFields(ResultSet rs)
        throws SQLException {
        HashMap<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Class> entry : this.fieldsBean.entrySet()) {
            result = this.parseResult(
                result,
                entry.getValue(),
                entry.getKey(),
                rs
            );
        }

        return result;
    }

    private HashMap<String, Object> parseResult(
        HashMap<String, Object> result,
        Class cls,
        String field,
        ResultSet rs
    ) throws SQLException {
        if (cls == String.class) {
            result.put(field, rs.getString(field));
        } else if (cls == Integer.class) {
            result.put(field, rs.getInt(field));
        } else if (cls == Float.class) {
            result.put(field, rs.getFloat(field));
        } else {
            result.put(field, rs.getDate(field));
        }
        return result;
    }

    private String getSelectWhere() {
        if (this.where.size() == 0) {
            return "";
        }

        String where = "where ";
        for (int i = 0; i < this.where.size(); i++) {
            where += this.where.get(i);
        }
        return where;
    }

    private String getSelectFields(ArrayList<String> fields) {
        if (fields.size() > 0) {
            return String.join(", ", fields);
        }

        return "*";
    }
}
