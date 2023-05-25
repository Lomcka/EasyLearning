package by.fpmibsu.EasyLearning.dao;

import by.fpmibsu.EasyLearning.bean.dao_bean.PasswordDaoBean;
import by.fpmibsu.EasyLearning.exception.DaoException;

import java.util.Optional;

public interface PasswordsDao extends Dao<Long, PasswordDaoBean> {
    Optional<PasswordDaoBean> findByUserId(Long userId) throws DaoException;
    void updateByUserId(Long userId, PasswordDaoBean newBean) throws DaoException;
}
