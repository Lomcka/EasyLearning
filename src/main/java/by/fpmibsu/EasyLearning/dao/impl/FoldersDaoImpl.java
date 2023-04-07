package by.fpmibsu.EasyLearning.dao.impl;

import by.fpmibsu.EasyLearning.bean.dao_bean.FolderDaoBean;
import by.fpmibsu.EasyLearning.dao.DaoException;
import by.fpmibsu.EasyLearning.dao.FoldersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class FoldersDaoImpl extends AbstractDao<Long, FolderDaoBean> implements FoldersDao {
    private static final String SQL_CREATE_FOLDER =
            "INSERT INTO folders (name, owner_id) VALUES (?, ?)";
    private static final String SQL_FIND_FOLDER_BY_ID =
            "SELECT * FROM folders WHERE id = ?";
    private static final String SQL_UPDATE_FOLDER_NAME =
            "UPDATE folders SET name = ? WHERE id = ?";
    private static final String SQL_DELETE_FOLDER =
            "DELETE FROM folders WHERE id = ?";
    private static final String SQL_FIND_FOLDER_BY_NAME =
            "SELECT * FROM folders WHERE name = ?";
    private static final String SQL_FIND_FOLDERS_BY_OWNER_ID =
            "SELECT * FROM folders WHERE owner_id = ?";

    public FoldersDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(FolderDaoBean folderBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_FOLDER)) {
            statement.setString(1, folderBean.getFolderName());
            statement.setLong(2, folderBean.getOwnerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<FolderDaoBean> findById(Long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_FOLDER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                FolderDaoBean folderBean = new FolderDaoBean();
                folderBean.setId(id);
                folderBean.setFolderName(resultSet.getString("name"));
                folderBean.setOwnerId(resultSet.getLong("owner_id"));
                return Optional.of(folderBean);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public ArrayList<FolderDaoBean> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, FolderDaoBean newBean) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FOLDER)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<FolderDaoBean> findByName(String folderName) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_FOLDER_BY_NAME)) {
            statement.setString(1, folderName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                FolderDaoBean folderBean = new FolderDaoBean();
                folderBean.setId(resultSet.getLong("id"));
                folderBean.setFolderName(resultSet.getString("name"));
                folderBean.setOwnerId(resultSet.getLong("owner_id"));
                return Optional.of(folderBean);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public ArrayList<FolderDaoBean> findByOwnerId(Long ownerId) throws DaoException {
        ArrayList<FolderDaoBean> folderBeans = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_FOLDERS_BY_OWNER_ID)) {
            statement.setLong(1, ownerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                FolderDaoBean folderBean = new FolderDaoBean();
                folderBean.setId(resultSet.getLong("id"));
                folderBean.setFolderName(resultSet.getString("name"));
                folderBean.setOwnerId(resultSet.getLong("owner_id"));
                folderBeans.add(folderBean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return folderBeans;
    }

    @Override
    public void updateFolderName(Long id, String newName) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_FOLDER_NAME)) {
            statement.setString(1, newName);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
