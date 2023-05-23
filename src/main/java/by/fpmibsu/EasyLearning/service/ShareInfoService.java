package by.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.ShareInfoBean;
import by.fpmibsu.EasyLearning.exception.ServiceException;

import java.util.Optional;

public interface ShareInfoService extends Service {
    Optional<ShareInfoBean> findById(Long id) throws ServiceException;

    Optional<ShareInfoBean> findByLogin(String login) throws ServiceException;

    void updateLogin(Long idToUpdate, String newLogin) throws ServiceException;

    void updateKeyword(Long idToUpdate, String newKeyword) throws ServiceException;
}
