package by.fpmibsu.EasyLearning.service.impl;

import by.fpmibsu.EasyLearning.bean.ShareInfoBean;
import by.fpmibsu.EasyLearning.bean.dao_bean.UserDaoBean;
import by.fpmibsu.EasyLearning.dao.UsersDao;
import by.fpmibsu.EasyLearning.dao.impl.UsersDaoImpl;
import by.fpmibsu.EasyLearning.exception.DaoException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.ShareInfoService;
import by.fpmibsu.EasyLearning.service.Transaction;
import org.apache.log4j.Logger;

import java.util.Optional;

public class ShareInfoServiceImpl implements ShareInfoService {
    private static Logger logger = Logger.getLogger(ShareInfoServiceImpl.class);

    @Override
    public Optional<ShareInfoBean> findById(Long id) throws ServiceException {
        logger.info("Find user by id method was called in service");

        UsersDao usersDao = new UsersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(usersDao);

        try {
            Optional<UserDaoBean> user = usersDao.findById(id);
            return user.map(userDaoBean -> new ShareInfoBean(
                    userDaoBean.getId(), userDaoBean.getLogin(), userDaoBean.getKeyWord()));
        } catch (DaoException e) {
            logger.error("Something went wrong in service: " + DaoException.class);

            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Optional<ShareInfoBean> findByLogin(String login) throws ServiceException {
        logger.info("Find user by login method was called in service");

        UsersDao usersDao = new UsersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(usersDao);

        try {
            Optional<UserDaoBean> user = usersDao.findByLogin(login);
            return user.map(userDaoBean -> new ShareInfoBean(
                    userDaoBean.getId(), userDaoBean.getLogin(), userDaoBean.getKeyWord()));
        } catch (DaoException e) {
            logger.error("Something went wrong in service: " + DaoException.class);

            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void updateLogin(Long idToUpdate, String newLogin) throws ServiceException {
        logger.info("Update login method was called in service");

        UsersDao usersDao = new UsersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(usersDao);

        try {
            usersDao.updateLogin(idToUpdate, newLogin);
        } catch (DaoException e) {
            logger.error("Something went wrong in service: " + DaoException.class);

            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void updateKeyword(Long idToUpdate, String newKeyword) throws ServiceException {
        logger.info("Update key word method was called in service");

        UsersDao usersDao = new UsersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(usersDao);

        try {
            usersDao.updateKeyword(idToUpdate, newKeyword);
        } catch (DaoException e) {
            logger.error("Something went wrong in service: " + DaoException.class);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
