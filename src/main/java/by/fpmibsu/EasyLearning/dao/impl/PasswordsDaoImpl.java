package by.fpmibsu.EasyLearning.dao.impl;

import by.fpmibsu.EasyLearning.bean.dao_bean.PasswordDaoBean;
import by.fpmibsu.EasyLearning.exception.DaoException;
import by.fpmibsu.EasyLearning.dao.PasswordsDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class PasswordsDaoImpl extends AbstractDao<Long, PasswordDaoBean> implements PasswordsDao {
    private static final String SQL_CREATE_PASSWORD =
            "INSERT INTO passwords VALUES (?, ?)";
    private static final String SQL_UPDATE_PASSWORD_BY_USER_ID =
            "UPDATE passwords SET user_password = ? WHERE user_id = ?";
    private static final String SQL_FIND_BY_USER_ID =
            "SELECT * FROM passwords WHERE user_id = ?";

    public PasswordsDaoImpl() {
        super();
    }

    public PasswordsDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(PasswordDaoBean passwordBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_PASSWORD)) {
            statement.setLong(1, passwordBean.getUserId());
            statement.setString(2, passwordBean.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<PasswordDaoBean> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<PasswordDaoBean> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, PasswordDaoBean newBean) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateByUserId(Long userId, PasswordDaoBean newBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD_BY_USER_ID)) {
            statement.setString(1, newBean.getPassword());
            statement.setLong(2, userId);
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
    public Optional<PasswordDaoBean> findByUserId(Long userId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PasswordDaoBean passwordBean = new PasswordDaoBean();
                passwordBean.setUserId(resultSet.getLong("user_id"));
                passwordBean.setPassword(resultSet.getString("user_password"));
                return Optional.of(passwordBean);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
