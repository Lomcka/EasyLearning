package by.fpmibsu.EasyLearning.dao;

import by.fpmibsu.EasyLearning.bean.dao_bean.UserDaoBean;

import java.util.Optional;

public interface UsersDao extends Dao<Long, UserDaoBean> {
    Optional<UserDaoBean> findByLogin(String login) throws DaoException;
}