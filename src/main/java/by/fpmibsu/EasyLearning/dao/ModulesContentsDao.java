package by.fpmibsu.EasyLearning.dao;

import by.fpmibsu.EasyLearning.bean.dao_bean.ModuleContentDaoBean;

import java.util.ArrayList;

public interface ModulesContentsDao extends Dao<Long, ModuleContentDaoBean> {
    ArrayList<ModuleContentDaoBean> findByModuleId(Long moduleId) throws DaoException;
    void update(Long moduleId, Long cardId, ModuleContentDaoBean newBean) throws DaoException;
    void delete(Long moduleId, Long cardId) throws DaoException;
}
