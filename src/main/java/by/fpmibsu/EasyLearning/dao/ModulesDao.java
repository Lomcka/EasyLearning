package by.fpmibsu.EasyLearning.dao;

import by.fpmibsu.EasyLearning.bean.dao_bean.ModuleDaoBean;

import java.util.ArrayList;
import java.util.Optional;

public interface ModulesDao extends Dao<Long, ModuleDaoBean> {
    Optional<ModuleDaoBean> findByName(String moduleName) throws DaoException;
    ArrayList<ModuleDaoBean> findByOwnerId(Long ownerId) throws DaoException;
    void updateModuleName(Long id, String newName) throws DaoException;
}
