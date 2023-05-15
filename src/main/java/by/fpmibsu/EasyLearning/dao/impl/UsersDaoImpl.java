package by.fpmibsu.EasyLearning.dao.impl;

import by.fpmibsu.EasyLearning.bean.dao_bean.UserDaoBean;
import by.fpmibsu.EasyLearning.dao.DaoException;
import by.fpmibsu.EasyLearning.dao.UsersDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class UsersDaoImpl extends AbstractDao<Long, UserDaoBean> implements UsersDao {
    private static final String SQL_CREATE_USER =
            "INSERT INTO users (login, key_word) VALUES (?, ?)";
    private static final String SQL_FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id = ?";
    private static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login = ?";
    private static final String SQL_FIND_ALL_USERS =
            "SELECT * FROM users";
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET login = ?, key_word = ? WHERE id = ?";

    public UsersDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(UserDaoBean userBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
            statement.setString(1, userBean.getLogin());
            statement.setString(2, userBean.getKeyWord());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<UserDaoBean> findById(Long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserDaoBean userBean = new UserDaoBean();
                userBean.setId(id);
                userBean.setLogin(resultSet.getString("login"));
                userBean.setKeyWord(resultSet.getString("key_word"));
                return Optional.of(userBean);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public ArrayList<UserDaoBean> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, UserDaoBean newBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, newBean.getLogin());
            statement.setString(2, newBean.getKeyWord());
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<UserDaoBean> findByLogin(String login) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserDaoBean userBean = new UserDaoBean();
                userBean.setId(resultSet.getLong("id"));
                userBean.setLogin(login);
                userBean.setKeyWord(resultSet.getString("key_word"));
                return Optional.of(userBean);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}