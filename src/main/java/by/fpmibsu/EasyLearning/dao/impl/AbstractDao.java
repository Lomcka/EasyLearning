package by.fpmibsu.EasyLearning.dao.impl;

import by.fpmibsu.EasyLearning.bean.Bean;
import by.fpmibsu.EasyLearning.dao.Dao;

import java.sql.Connection;

public abstract class AbstractDao<K, T extends Bean> implements Dao<K, T> {
    protected Connection connection;

    public AbstractDao() {}

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
