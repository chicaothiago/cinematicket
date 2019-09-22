package io.github.chicaothiago.cinematicket.dao.mysql.queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class InsertMysql {
    private final ArrayList<Object> values;
    private final String table;
    private final ArrayList<String> fields;
    private final Connection connection;

    public InsertMysql(
        Connection connection,
        String table,
        ArrayList<String> fields,
        ArrayList<Object> values
    ) {
        this.table = table;
        this.fields = fields;
        this.values = values;
        this.connection = connection;
    }

    public void insert() throws SQLException {
        System.out.println(String.join(", ", this.fields));
        String sql = "insert into " + this.table +
            " (" + String.join(", ", this.fields) + ") " +
            " values ( " + this.getPrepareStatementValuesFromFields() + " )";

        PreparedStatement preparedStatement = this.connection
            .prepareStatement(sql);

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getClass() == String.class) {
                preparedStatement.setString(i + 1, values.get(i).toString());
            } else if (values.get(i).getClass() == Integer.class) {
                preparedStatement.setInt(i + 1, Integer.parseInt(values.get(i).toString()));
            } else if (values.get(i).getClass() == Float.class) {
                preparedStatement.setFloat(i + 1, Float.parseFloat(values.get(i).toString()));
            } else {
                preparedStatement.setTimestamp(i + 1, (Timestamp) values.get(i));
            }
        }

        System.out.println(preparedStatement.toString());

        preparedStatement.execute();
        preparedStatement.close();
    }

    private PreparedStatement setStatement(
        PreparedStatement preparedStatement,
        Object value,
        Integer index
    )
        throws SQLException {
        if (value.getClass().getSimpleName() == "String") {
            preparedStatement.setString(index, value.toString());
        }

        if (value.getClass().getSimpleName() == "Integer") {
            preparedStatement.setInt(index, (Integer) value);
        }

        return preparedStatement;
    }

    private String getPrepareStatementValuesFromFields() {
        ArrayList<String> statementValues = new ArrayList<>();
        for (int i = 0; i < this.fields.size(); i++) {
            statementValues.add("?");
        }

        return String.join(", ", statementValues);
    }
}
