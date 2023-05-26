package by.fpmibsu.EasyLearning.dao.impl;

import by.fpmibsu.EasyLearning.bean.dao_bean.ModuleContentDaoBean;
import by.fpmibsu.EasyLearning.dao.ModulesDao;
import by.fpmibsu.EasyLearning.exception.DaoException;
import by.fpmibsu.EasyLearning.dao.ModulesContentsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ModulesContentsDaoImpl extends AbstractDao<Long, ModuleContentDaoBean> implements ModulesContentsDao {
    private static final String SQL_CREATE_MODULE_CONTENT =
            "INSERT INTO modules_contents (module_id, word, translation) VALUES (?, ?, ?)";
    private static final String SQL_FIND_BY_MODULE_ID =
            "SELECT * FROM modules_contents WHERE module_id = ?";
    private static final String SQL_UPDATE_MODULE_CONTENT =
            "UPDATE modules_contents SET word = ?, translation = ? WHERE module_id = ? AND card_id = ?";
    private static final String SQL_DELETE_MODULE_CONTENT =
            "DELETE FROM modules_contents WHERE module_id = ? AND card_id = ?";

    public ModulesContentsDaoImpl() {
        super();
    }

    public ModulesContentsDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(ModuleContentDaoBean moduleContentBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_MODULE_CONTENT)) {
            statement.setLong(1, moduleContentBean.getModuleId());
            statement.setString(2, moduleContentBean.getWord());
            statement.setString(3, moduleContentBean.getTranslation());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<ModuleContentDaoBean> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<ModuleContentDaoBean> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, ModuleContentDaoBean newBean) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<ModuleContentDaoBean> findByModuleId(Long moduleId) throws DaoException {
        ArrayList<ModuleContentDaoBean> moduleContentBeans = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_MODULE_ID)) {
            statement.setLong(1, moduleId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ModuleContentDaoBean moduleContentBean = new ModuleContentDaoBean();
                moduleContentBean.setModuleId(moduleId);
                moduleContentBean.setCardId(resultSet.getLong("card_id"));
                moduleContentBean.setWord(resultSet.getString("word"));
                moduleContentBean.setTranslation(resultSet.getString("translation"));
                moduleContentBeans.add(moduleContentBean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return moduleContentBeans;
    }

    @Override
    public void update(Long moduleId, Long cardId, ModuleContentDaoBean newBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MODULE_CONTENT)) {
            statement.setString(1, newBean.getWord());
            statement.setString(2, newBean.getTranslation());
            statement.setLong(3, moduleId);
            statement.setLong(4, cardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long moduleId, Long cardId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MODULE_CONTENT)) {
            statement.setLong(1, moduleId);
            statement.setLong(2, cardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
