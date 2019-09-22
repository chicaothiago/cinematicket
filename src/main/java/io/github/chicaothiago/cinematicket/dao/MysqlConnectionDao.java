package io.github.chicaothiago.cinematicket.dao;

import io.github.chicaothiago.cinematicket.factory.ConnectionFactory;

import java.sql.Connection;

public class MysqlConnectionDao {
    public Connection connection;
    public MysqlConnectionDao() {
        ConnectionFactory factory = new ConnectionFactory();
        this.connection = new ConnectionFactory().get();
    }
}
