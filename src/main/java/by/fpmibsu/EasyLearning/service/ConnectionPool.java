package by.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.exception.ServiceException;
import org.postgresql.ds.PGConnectionPoolDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static final PGConnectionPoolDataSource source;

    static {
        source = new PGConnectionPoolDataSource();
        Properties props = new Properties();
        try {
            props.load(new FileReader("/home/polyushka/java/EasyLearning/database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(new ServiceException(e));
        }

        source.setDatabaseName(props.getProperty("db_name"));
        source.setUser(props.getProperty("user"));
        source.setPassword(props.getProperty("password"));
    }

    public static Connection getConnection() throws SQLException {
        return source.getConnection();
    }
}
