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

import java.util.Optional;

public class SignInfoServiceImpl implements SignInfoService {
    @Override
    public Optional<SignInfoBean> findById(Long id) throws ServiceException {
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
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<SignInfoBean> findByLogin(String login) throws ServiceException {
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

            return Optional.of(new SignInfoBean(user.get().getId(), login, password.get().getPassword()));
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }
}
