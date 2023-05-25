package by.fpmibsu.EasyLearning.service.impl;

import by.fpmibsu.EasyLearning.bean.ShareInfoBean;
import by.fpmibsu.EasyLearning.bean.dao_bean.UserDaoBean;
import by.fpmibsu.EasyLearning.dao.UsersDao;
import by.fpmibsu.EasyLearning.dao.impl.UsersDaoImpl;
import by.fpmibsu.EasyLearning.exception.DaoException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.ShareInfoService;
import by.fpmibsu.EasyLearning.service.Transaction;

import java.util.Optional;

public class ShareInfoServiceImpl implements ShareInfoService {

    @Override
    public Optional<ShareInfoBean> findById(Long id) throws ServiceException {
        UsersDao usersDao = new UsersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(usersDao);

        try {
            Optional<UserDaoBean> user = usersDao.findById(id);
            return user.map(userDaoBean -> new ShareInfoBean(
                    userDaoBean.getId(), userDaoBean.getLogin(), userDaoBean.getKeyWord()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Optional<ShareInfoBean> findByLogin(String login) throws ServiceException {
        UsersDao usersDao = new UsersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(usersDao);

        try {
            Optional<UserDaoBean> user = usersDao.findByLogin(login);
            return user.map(userDaoBean -> new ShareInfoBean(
                    userDaoBean.getId(), userDaoBean.getLogin(), userDaoBean.getKeyWord()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void updateLogin(Long idToUpdate, String newLogin) throws ServiceException {
        UsersDao usersDao = new UsersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(usersDao);

        try {
            usersDao.updateLogin(idToUpdate, newLogin);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void updateKeyword(Long idToUpdate, String newKeyword) throws ServiceException {
        UsersDao usersDao = new UsersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(usersDao);

        try {
            usersDao.updateKeyword(idToUpdate, newKeyword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
