package by.fpmibsu.EasyLearning.dao;

import by.fpmibsu.EasyLearning.bean.dao_bean.ModuleReaderDaoBean;

import java.util.ArrayList;

public interface ModulesReadersDao extends Dao<Long, ModuleReaderDaoBean> {
    ArrayList<ModuleReaderDaoBean> findByModuleId(Long moduleId) throws DaoException;
    ArrayList<ModuleReaderDaoBean> findByReaderId(Long readerId) throws DaoException;
    void delete(ModuleReaderDaoBean moduleReaderBean) throws DaoException;
}
