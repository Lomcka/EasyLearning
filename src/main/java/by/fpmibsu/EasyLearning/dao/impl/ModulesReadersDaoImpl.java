package by.fpmibsu.EasyLearning.dao.impl;

import by.fpmibsu.EasyLearning.bean.dao_bean.ModuleReaderDaoBean;
import by.fpmibsu.EasyLearning.dao.DaoException;
import by.fpmibsu.EasyLearning.dao.ModulesReadersDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ModulesReadersDaoImpl extends AbstractDao<Long, ModuleReaderDaoBean> implements ModulesReadersDao {
    private static final String SQL_CREATE_MODULE_READER =
            "INSERT INTO modules_readers VALUES (?, ?)";
    private static final String SQL_DELETE_MODULE_READER =
            "DELETE FROM modules_readers WHERE module_id = ? AND reader_id = ?";
    private static final String SQL_FIND_BY_MODULE_ID =
            "SELECT * FROM modules_readers WHERE module_id = ?";
    private static final String SQL_FIND_BY_READER_ID =
            "SELECT * FROM modules_readers WHERE reader_id = ?";

    public ModulesReadersDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(ModuleReaderDaoBean moduleReaderBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_MODULE_READER)) {
            statement.setLong(1, moduleReaderBean.getModuleId());
            statement.setLong(2, moduleReaderBean.getReaderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<ModuleReaderDaoBean> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<ModuleReaderDaoBean> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(ModuleReaderDaoBean moduleReaderBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MODULE_READER)) {
            statement.setLong(1, moduleReaderBean.getModuleId());
            statement.setLong(2, moduleReaderBean.getReaderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Long id, ModuleReaderDaoBean newBean) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<ModuleReaderDaoBean> findByModuleId(Long moduleId) throws DaoException {
        return findBySomeId(moduleId, SQL_FIND_BY_MODULE_ID);
    }

    @Override
    public ArrayList<ModuleReaderDaoBean> findByReaderId(Long readerId) throws DaoException {
        return findBySomeId(readerId, SQL_FIND_BY_READER_ID);
    }

    private ArrayList<ModuleReaderDaoBean> findBySomeId(Long someId, String sqlQuery) throws DaoException {
        ArrayList<ModuleReaderDaoBean> moduleReaderBeans = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setLong(1, someId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ModuleReaderDaoBean moduleReaderBean = new ModuleReaderDaoBean();
                moduleReaderBean.setModuleId(resultSet.getLong("module_id"));
                moduleReaderBean.setReaderId(resultSet.getLong("reader_id"));
                moduleReaderBeans.add(moduleReaderBean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return moduleReaderBeans;
    }
}
