package by.fpmibsu.EasyLearning.service.impl;

import by.fpmibsu.EasyLearning.bean.UserInfoBean;
import by.fpmibsu.EasyLearning.bean.dao_bean.PasswordDaoBean;
import by.fpmibsu.EasyLearning.bean.dao_bean.UserDaoBean;
import by.fpmibsu.EasyLearning.dao.PasswordsDao;
import by.fpmibsu.EasyLearning.dao.UsersDao;
import by.fpmibsu.EasyLearning.dao.impl.PasswordsDaoImpl;
import by.fpmibsu.EasyLearning.dao.impl.UsersDaoImpl;
import by.fpmibsu.EasyLearning.exception.DaoException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.Transaction;
import by.fpmibsu.EasyLearning.service.UserInfoService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserInfoServiceImpl implements UserInfoService {
    private static Logger logger = LogManager.getLogger(UserInfoServiceImpl.class);

    @Override
    public void create(UserInfoBean user) throws ServiceException {
        logger.info("Create user method was called in service");

        UsersDao usersDao = new UsersDaoImpl();
        PasswordsDao passwordsDao = new PasswordsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(usersDao, passwordsDao);

        try {
            usersDao.create(new UserDaoBean(0L, user.getLogin(), user.getKeyWord()));
            var createdUser = usersDao.findByLogin(user.getLogin());
            passwordsDao.create(new PasswordDaoBean(createdUser.get().getId(), user.getPassword()));
            transaction.commit();
        } catch (DaoException e) {
            logger.error("Something went wrong in service: " + DaoException.class);

            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<UserInfoBean> findById(Long id) throws ServiceException {
        logger.info("Find user by id method was called in service");
        UsersDao usersDao = new UsersDaoImpl();
        PasswordsDao passwordsDao = new PasswordsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(usersDao, passwordsDao);

        try {
            var user = usersDao.findById(id);
            var password = passwordsDao.findByUserId(id);
            transaction.commit();

            if (user.isEmpty() || password.isEmpty()) {
                logger.warn("User or password is empty");

                return Optional.empty();
            }
            return Optional.of(new UserInfoBean(
                    id, user.get().getLogin(), password.get().getPassword(), user.get().getKeyWord()));
        } catch (DaoException e) {
            logger.error("Something went wrong in service: " + DaoException.class);
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<UserInfoBean> findByLogin(String login) throws ServiceException {
        logger.info("Find user by login method was called in service");
        UsersDao usersDao = new UsersDaoImpl();
        PasswordsDao passwordsDao = new PasswordsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(usersDao, passwordsDao);

        try {
            var user = usersDao.findByLogin(login);
            if (user.isEmpty()) {
                logger.warn("User is empty");

                transaction.commit();
                return Optional.empty();
            }
            var password = passwordsDao.findByUserId(user.get().getId());
            transaction.commit();

            return Optional.of(new UserInfoBean(
                    user.get().getId(), login, password.get().getPassword(), user.get().getKeyWord()));
        } catch (DaoException e) {
            logger.error("Something went wrong: in service " + DaoException.class);

            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void updatePassword(Long id, String newPassword) throws ServiceException {
        logger.info("Update password method was called in service");

        PasswordsDao passwordsDao = new PasswordsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(passwordsDao);

        try {
            passwordsDao.updateByUserId(id, new PasswordDaoBean(id, newPassword));
        } catch (DaoException e) {
            logger.error("Something went wrong in service: " + DaoException.class);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
