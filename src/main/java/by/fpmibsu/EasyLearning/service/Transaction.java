package by.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.Bean;
import by.fpmibsu.EasyLearning.dao.Dao;
import by.fpmibsu.EasyLearning.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
    private Connection connection;

    public void init(Dao dao) throws ServiceException {
        try {
            if (connection == null) {
                connection = ConnectionPool.getConnection();
            }
            dao.setConnection(connection);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void end() {
        connection = null;
    }

    public <T extends Bean> void initTransaction(Dao... daos) throws ServiceException {
        try {
            if (connection == null) {
                connection = ConnectionPool.getConnection();
            }

            connection.setAutoCommit(false);
            for (Dao dao : daos) {
                dao.setConnection(connection);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void endTransaction() throws ServiceException {
        try {
            if (connection == null) return;

            connection.setAutoCommit(true);
            connection = null;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void commit() throws ServiceException {
        try {
            if (connection != null) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void rollback() throws ServiceException {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}
