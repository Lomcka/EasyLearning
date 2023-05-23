package by.fpmibsu.EasyLearning.service.impl;

import by.fpmibsu.EasyLearning.bean.CardBean;
import by.fpmibsu.EasyLearning.bean.ModuleBean;
import by.fpmibsu.EasyLearning.bean.dao_bean.ModuleContentDaoBean;
import by.fpmibsu.EasyLearning.bean.dao_bean.ModuleDaoBean;
import by.fpmibsu.EasyLearning.bean.dao_bean.ModuleReaderDaoBean;
import by.fpmibsu.EasyLearning.dao.ModulesContentsDao;
import by.fpmibsu.EasyLearning.dao.ModulesDao;
import by.fpmibsu.EasyLearning.dao.ModulesReadersDao;
import by.fpmibsu.EasyLearning.dao.impl.ModulesContentsDaoImpl;
import by.fpmibsu.EasyLearning.dao.impl.ModulesDaoImpl;
import by.fpmibsu.EasyLearning.dao.impl.ModulesReadersDaoImpl;
import by.fpmibsu.EasyLearning.exception.DaoException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.ModuleService;
import by.fpmibsu.EasyLearning.service.Transaction;

import java.util.ArrayList;
import java.util.Optional;

public class ModuleServiceImpl implements ModuleService {
    @Override
    public void create(String name, Long ownerId) throws ServiceException {
        ModulesDao modulesDao = new ModulesDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(modulesDao);

        try {
            modulesDao.create(new ModuleDaoBean(0L, name, ownerId));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Optional<ModuleBean> findById(Long id) throws ServiceException {
        ModulesDao modulesDao = new ModulesDaoImpl();
        ModulesContentsDao modulesContentsDao = new ModulesContentsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(modulesDao, modulesContentsDao);

        try {
            var module = modulesDao.findById(id);
            if (module.isEmpty()) {
                return Optional.empty();
            }

            var contents = modulesContentsDao.findByModuleId(id);
            transaction.commit();
            var cards = getCards(contents);
            return Optional.of(new ModuleBean(id, module.get().getModuleName(), cards));
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<ModuleBean> findByName(String name) throws ServiceException {
        ModulesDao modulesDao = new ModulesDaoImpl();
        ModulesContentsDao modulesContentsDao = new ModulesContentsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(modulesDao, modulesContentsDao);

        try {
            var module = modulesDao.findByName(name);
            if (module.isEmpty()) {
                return Optional.empty();
            }

            var contents = modulesContentsDao.findByModuleId(module.get().getId());
            transaction.commit();
            var cards = getCards(contents);
            return Optional.of(new ModuleBean(module.get().getId(), name, cards));
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    private ArrayList<CardBean> getCards(ArrayList<ModuleContentDaoBean> contents) {
        ArrayList<CardBean> cards = new ArrayList<>(contents.size());
        contents.forEach(content -> cards.add(new CardBean(
                content.getCardId(),content.getModuleId(), content.getWord(), content.getTranslation())));
        return cards;
    }

    @Override
    public ArrayList<String> findByOwnerId(Long ownerId) throws ServiceException {
        ModulesDao modulesDao = new ModulesDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(modulesDao);

        try {
            var moduleDaoBeans = modulesDao.findByOwnerId(ownerId);
            ArrayList<String> modules = new ArrayList<>();
            moduleDaoBeans.forEach(moduleDaoBean -> modules.add(moduleDaoBean.getModuleName()));
            return modules;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public ArrayList<String> findByReaderId(Long readerId) throws ServiceException {
        ModulesDao modulesDao = new ModulesDaoImpl();
        ModulesReadersDao modulesReadersDao = new ModulesReadersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(modulesDao, modulesReadersDao);

        try {
            var modulesReaders = modulesReadersDao.findByReaderId(readerId);
            ArrayList<String> modules = new ArrayList<>();
            for (var moduleReader : modulesReaders) {
                var module = modulesDao.findById(moduleReader.getModuleId());
                modules.add(module.get().getModuleName());
            }
            transaction.commit();
            return modules;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void updateModuleName(Long moduleId, String newName) throws ServiceException {
        ModulesDao modulesDao = new ModulesDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(modulesDao);

        try {
            modulesDao.updateModuleName(moduleId, newName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void addReader(Long moduleId, Long readerId) throws ServiceException {
        ModulesReadersDao modulesReadersDao = new ModulesReadersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(modulesReadersDao);

        try {
            modulesReadersDao.create(new ModuleReaderDaoBean(moduleId, readerId));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void updateCard(Long moduleId, Long cardId, CardBean newCard) throws ServiceException {
        ModulesContentsDao modulesContentsDao = new ModulesContentsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(modulesContentsDao);

        try {
            modulesContentsDao.update(
                    moduleId, cardId, new ModuleContentDaoBean(0L, 0L, newCard.getWord(), newCard.getTranslation()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void deleteCard(Long moduleId, Long cardId) throws ServiceException {
        ModulesContentsDao modulesContentsDao = new ModulesContentsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(modulesContentsDao);

        try {
            modulesContentsDao.delete(moduleId, cardId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void addCard(Long moduleId, CardBean card) throws ServiceException {
        ModulesContentsDao modulesContentsDao = new ModulesContentsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(modulesContentsDao);

        try {
            modulesContentsDao.create(new ModuleContentDaoBean(
                    moduleId, 0L, card.getWord(), card.getTranslation()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
