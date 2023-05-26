package by.fpmibsu.EasyLearning.service.impl;

import by.fpmibsu.EasyLearning.bean.SignInfoBean;
import by.fpmibsu.EasyLearning.dao.PasswordsDao;
import by.fpmibsu.EasyLearning.dao.UsersDao;
import by.fpmibsu.EasyLearning.dao.impl.PasswordsDaoImpl;
import by.fpmibsu.EasyLearning.dao.impl.UsersDaoImpl;
import by.fpmibsu.EasyLearning.exception.DaoException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.SignInfoService;
import by.fpmibsu.EasyLearning.service.Transaction;
import org.apache.log4j.Logger;

import java.util.Optional;

public class SignInfoServiceImpl implements SignInfoService {
    private static Logger logger = Logger.getLogger(SignInfoServiceImpl.class);

    @Override
    public Optional<SignInfoBean> findById(Long id) throws ServiceException {
        logger.info("findById was called in service");

        UsersDao usersDao = new UsersDaoImpl();
        PasswordsDao passwordsDao = new PasswordsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(usersDao, passwordsDao);

        try {
            var user = usersDao.findById(id);
            var password = passwordsDao.findByUserId(id);
            transaction.commit();

            if (user.isEmpty() || password.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(new SignInfoBean(id, user.get().getLogin(), password.get().getPassword()));
        } catch (DaoException e) {
            logger.error("Something went wrong in service: " + DaoException.class);
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<SignInfoBean> findByLogin(String login) throws ServiceException {
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

            return Optional.of(new SignInfoBean(user.get().getId(), login, password.get().getPassword()));
        } catch (DaoException e) {
            logger.error("Something went wrong in service: " + DaoException.class);
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }
}
