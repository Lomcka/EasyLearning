package by.fpmibsu.EasyLearning.dao.impl;

import by.fpmibsu.EasyLearning.bean.dao_bean.FolderContentDaoBean;
import by.fpmibsu.EasyLearning.exception.DaoException;
import by.fpmibsu.EasyLearning.dao.FoldersContentsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class FoldersContentsDaoImpl extends AbstractDao<Long, FolderContentDaoBean> implements FoldersContentsDao {
    private static final String SQL_CREATE_FOLDER_CONTENT =
            "INSERT INTO folders_contents VALUES (?, ?)";
    private static final String SQL_FIND_BY_FOLDER_ID =
            "SELECT * FROM folders_contents WHERE folder_id = ?";
    private static final String SQL_DELETE_ALL_BY_FOLDER_ID =
            "DELETE FROM folders_contents WHERE folder_id = ?";
    private static final String SQL_DELETE_FOLDER_CONTENT =
            "DELETE FROM folders_contents WHERE folder_id = ? AND module_id = ?";

    public FoldersContentsDaoImpl() {
        super();
    }

    public FoldersContentsDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(FolderContentDaoBean folderContentBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_FOLDER_CONTENT)) {
            statement.setLong(1, folderContentBean.getFolderId());
            statement.setLong(2, folderContentBean.getModuleId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<FolderContentDaoBean> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<FolderContentDaoBean> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, FolderContentDaoBean newBean) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<FolderContentDaoBean> findByFolderId(Long folderId) throws DaoException {
        ArrayList<FolderContentDaoBean> folderContentBeans = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_FOLDER_ID)) {
            statement.setLong(1, folderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                FolderContentDaoBean folderContentBean = new FolderContentDaoBean();
                folderContentBean.setFolderId(resultSet.getLong("folder_id"));
                folderContentBean.setModuleId(resultSet.getLong("module_id"));
                folderContentBeans.add(folderContentBean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return folderContentBeans;
    }

    @Override
    public void deleteAllByFolderId(Long folderId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ALL_BY_FOLDER_ID)) {
            statement.setLong(1, folderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long folderId, Long moduleId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FOLDER_CONTENT)) {
            statement.setLong(1, folderId);
            statement.setLong(2, moduleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
