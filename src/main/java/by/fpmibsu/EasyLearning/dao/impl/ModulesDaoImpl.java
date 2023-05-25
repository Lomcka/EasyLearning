package by.fpmibsu.EasyLearning.dao.impl;

import by.fpmibsu.EasyLearning.bean.dao_bean.ModuleDaoBean;
import by.fpmibsu.EasyLearning.exception.DaoException;
import by.fpmibsu.EasyLearning.dao.ModulesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ModulesDaoImpl extends AbstractDao<Long, ModuleDaoBean> implements ModulesDao {
    private static final String SQL_CREATE_MODULE =
            "INSERT INTO modules (name, owner_id) VALUES (?, ?)";
    private static final String SQL_FIND_MODULE_BY_ID =
            "SELECT * FROM modules WHERE id = ?";
    private static final String SQL_UPDATE_MODULE_NAME =
            "UPDATE modules SET name = ? WHERE id = ?";
    private static final String SQL_FIND_MODULE_BY_NAME =
            "SELECT * FROM modules WHERE name = ?";
    private static final String SQL_FIND_MODULES_BY_OWNER_ID =
            "SELECT * FROM modules WHERE owner_id = ?";

    public ModulesDaoImpl() {
        super();
    }

    public ModulesDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(ModuleDaoBean moduleBean) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_MODULE)) {
            statement.setString(1, moduleBean.getModuleName());
            statement.setLong(2, moduleBean.getOwnerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<ModuleDaoBean> findById(Long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_MODULE_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ModuleDaoBean moduleBean = new ModuleDaoBean();
                moduleBean.setId(id);
                moduleBean.setModuleName(resultSet.getString("name"));
                moduleBean.setOwnerId(resultSet.getLong("owner_id"));
                return Optional.of(moduleBean);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public ArrayList<ModuleDaoBean> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, ModuleDaoBean newBean) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ModuleDaoBean> findByName(String moduleName) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_MODULE_BY_NAME)) {
            statement.setString(1, moduleName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ModuleDaoBean moduleBean = new ModuleDaoBean();
                moduleBean.setId(resultSet.getLong("id"));
                moduleBean.setModuleName(resultSet.getString("name"));
                moduleBean.setOwnerId(resultSet.getLong("owner_id"));
                return Optional.of(moduleBean);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public ArrayList<ModuleDaoBean> findByOwnerId(Long ownerId) throws DaoException {
        ArrayList<ModuleDaoBean> moduleBeans = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_MODULES_BY_OWNER_ID)) {
            statement.setLong(1, ownerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ModuleDaoBean moduleBean = new ModuleDaoBean();
                moduleBean.setId(resultSet.getLong("id"));
                moduleBean.setModuleName(resultSet.getString("name"));
                moduleBean.setOwnerId(resultSet.getLong("owner_id"));
                moduleBeans.add(moduleBean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return moduleBeans;
    }

    @Override
    public void updateModuleName(Long id, String newName) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MODULE_NAME)) {
            statement.setString(1, newName);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
