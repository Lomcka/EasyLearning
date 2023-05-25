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

import java.util.Optional;

public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public void create(UserInfoBean user) throws ServiceException {
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
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<UserInfoBean> findById(Long id) throws ServiceException {
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
            return Optional.of(new UserInfoBean(
                    id, user.get().getLogin(), password.get().getPassword(), user.get().getKeyWord()));
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<UserInfoBean> findByLogin(String login) throws ServiceException {
        UsersDao usersDao = new UsersDaoImpl();
        PasswordsDao passwordsDao = new PasswordsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(usersDao, passwordsDao);

        try {
            var user = usersDao.findByLogin(login);
            if (user.isEmpty()) {
                transaction.commit();
                return Optional.empty();
            }
            var password = passwordsDao.findByUserId(user.get().getId());
            transaction.commit();

            return Optional.of(new UserInfoBean(
                    user.get().getId(), login, password.get().getPassword(), user.get().getKeyWord()));
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void updatePassword(Long id, String newPassword) throws ServiceException {
        PasswordsDao passwordsDao = new PasswordsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(passwordsDao);

        try {
            passwordsDao.updateByUserId(id, new PasswordDaoBean(id, newPassword));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
