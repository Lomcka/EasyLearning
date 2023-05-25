package by.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.SignInfoBean;
import by.fpmibsu.EasyLearning.exception.ServiceException;

import java.util.Optional;
import java.util.OptionalInt;

public interface SignInfoService extends Service {
    Optional<SignInfoBean> findById(Long id) throws ServiceException;

    Optional<SignInfoBean> findByLogin(String login) throws ServiceException;
}
