package by.fpmibsu.EasyLearning.dao.impl;

import by.fpmibsu.EasyLearning.bean.Bean;
import by.fpmibsu.EasyLearning.dao.Dao;

import java.sql.Connection;

public abstract class AbstractDao<K, T extends Bean> implements Dao<K, T> {
    protected final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
}
