package by.fpmibsu.EasyLearning.service.impl;

import by.fpmibsu.EasyLearning.bean.FolderBean;
import by.fpmibsu.EasyLearning.bean.ModuleNameBean;
import by.fpmibsu.EasyLearning.bean.dao_bean.FolderContentDaoBean;
import by.fpmibsu.EasyLearning.bean.dao_bean.FolderDaoBean;
import by.fpmibsu.EasyLearning.bean.dao_bean.ModuleDaoBean;
import by.fpmibsu.EasyLearning.dao.FoldersContentsDao;
import by.fpmibsu.EasyLearning.dao.FoldersDao;
import by.fpmibsu.EasyLearning.dao.ModulesDao;
import by.fpmibsu.EasyLearning.dao.impl.FoldersContentsDaoImpl;
import by.fpmibsu.EasyLearning.dao.impl.FoldersDaoImpl;
import by.fpmibsu.EasyLearning.dao.impl.ModulesDaoImpl;
import by.fpmibsu.EasyLearning.exception.DaoException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.FolderService;
import by.fpmibsu.EasyLearning.service.Transaction;

import java.util.ArrayList;
import java.util.Optional;

public class FolderServiceImpl implements FolderService {
    @Override
    public void create(String name, Long ownerId) throws ServiceException {
        FoldersDao foldersDao = new FoldersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(foldersDao);

        try {
            foldersDao.create(new FolderDaoBean(0L, name, ownerId));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Optional<FolderBean> findById(Long id) throws ServiceException {
        FoldersDao foldersDao = new FoldersDaoImpl();
        FoldersContentsDao foldersContentsDao = new FoldersContentsDaoImpl();
        ModulesDao modulesDao = new ModulesDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(foldersDao, foldersContentsDao, modulesDao);

        try {
            var folder = foldersDao.findById(id);
            if (folder.isEmpty()) {
                return Optional.empty();
            }

            var contents = foldersContentsDao.findByFolderId(id);
            var modules = getModules(modulesDao, contents);
            transaction.commit();
            return Optional.of(new FolderBean(id, folder.get().getFolderName(), modules));
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<FolderBean> findByName(String name) throws ServiceException {
        FoldersDao foldersDao = new FoldersDaoImpl();
        FoldersContentsDao foldersContentsDao = new FoldersContentsDaoImpl();
        ModulesDao modulesDao = new ModulesDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(foldersDao, foldersContentsDao, modulesDao);

        try {
            Optional<FolderDaoBean> folder = foldersDao.findByName(name);
            if (folder.isEmpty()) {
                return Optional.empty();
            }

            var contents = foldersContentsDao.findByFolderId(folder.get().getId());
            var modules = getModules(modulesDao, contents);
            transaction.commit();
            return Optional.of(new FolderBean(folder.get().getId(), name, modules));
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public ArrayList<String> findByOwnerId(Long ownerId) throws ServiceException {
        FoldersDao foldersDao = new FoldersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(foldersDao);

        try {
            var folders = foldersDao.findByOwnerId(ownerId);
            ArrayList<String> result = new ArrayList<>(folders.size());
            folders.forEach(folderDaoBean -> result.add(folderDaoBean.getFolderName()));
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void addModule(Long folderId, Long moduleId) throws ServiceException {
        FoldersContentsDao foldersContentsDao = new FoldersContentsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(foldersContentsDao);

        try {
            foldersContentsDao.create(new FolderContentDaoBean(folderId, moduleId));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void removeModule(Long folderId, Long moduleId) throws ServiceException {
        FoldersContentsDao foldersContentsDao = new FoldersContentsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(foldersContentsDao);

        try {
            foldersContentsDao.delete(folderId, moduleId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void updateFolderName(Long folderId, String newName) throws ServiceException {
        FoldersDao foldersDao = new FoldersDaoImpl();
        Transaction transaction = new Transaction();
        transaction.init(foldersDao);

        try {
            foldersDao.updateFolderName(folderId, newName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        FoldersDao foldersDao = new FoldersDaoImpl();
        FoldersContentsDao foldersContentsDao = new FoldersContentsDaoImpl();
        Transaction transaction = new Transaction();
        transaction.initTransaction(foldersDao, foldersContentsDao);

        try {
            foldersContentsDao.deleteAllByFolderId(id);
            foldersDao.delete(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    private ArrayList<ModuleNameBean> getModules(ModulesDao modulesDao,
                                                 ArrayList<FolderContentDaoBean> contents) throws DaoException {
        ArrayList<ModuleNameBean> modules = new ArrayList<>(contents.size());
        for (var folderContent : contents) {
            ModuleDaoBean module = modulesDao.findById(folderContent.getModuleId()).get();
            modules.add(new ModuleNameBean(module.getId(), module.getModuleName()));
        }
        return modules;
    }
}
