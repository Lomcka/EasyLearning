package by.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.UserInfoBean;
import by.fpmibsu.EasyLearning.exception.ServiceException;

import java.util.Optional;

public interface UserInfoService extends Service {
    void create(UserInfoBean user) throws ServiceException;

    Optional<UserInfoBean> findById(Long id) throws ServiceException;

    Optional<UserInfoBean> findByLogin(String login) throws ServiceException;

    void updatePassword(Long id, String newPassword) throws ServiceException;
}
