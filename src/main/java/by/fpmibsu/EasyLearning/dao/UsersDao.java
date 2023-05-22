package by.fpmibsu.EasyLearning.dao;

import by.fpmibsu.EasyLearning.bean.dao_bean.UserDaoBean;
import by.fpmibsu.EasyLearning.exception.DaoException;

import java.util.Optional;

public interface UsersDao extends Dao<Long, UserDaoBean> {
    Optional<UserDaoBean> findByLogin(String login) throws DaoException;
    void updateLogin(Long id, String newLogin) throws DaoException;
    void updateKeyword(Long id, String newKeyword) throws DaoException;
}
