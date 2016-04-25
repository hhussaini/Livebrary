package com.blb.daos;

import java.sql.Connection;

public interface JdbcDaoSupport {
    public void setConnection(Connection connection);
    public Connection getConnection();
}