package by.fpmibsu.EasyLearning.controller;

import by.fpmibsu.EasyLearning.bean.dao_bean.*;
import by.fpmibsu.EasyLearning.dao.*;
import by.fpmibsu.EasyLearning.dao.impl.*;
import by.fpmibsu.EasyLearning.exception.DaoException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;

public class Runner {
    public static void main(String[] args) {
        Properties props = new Properties();
        String dbURL = "";
        try {
            props.load(new FileReader("database.properties"));
            dbURL = (String) props.get("url");
            Class.forName((String) props.get("db.driver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(dbURL, props)) {

            UsersDao usersDao = new UsersDaoImpl(connection);
            PasswordsDao passwordsDao = new PasswordsDaoImpl(connection);

            try {
                usersDao.create(new UserDaoBean(0L, "login", "keyword"));
                usersDao.create(new UserDaoBean(0L, "login2", "keyword2"));
                Optional<UserDaoBean> user = usersDao.findByLogin("login");
                System.out.println(user.get());  // {u1, 'login', 'keyword'}
                usersDao.update(user.get().getId(), new UserDaoBean(0L, "new", "key"));
                Optional<UserDaoBean> userById = usersDao.findById(user.get().getId());
                System.out.println(userById.get());  // {u1, 'new', 'key'}

                passwordsDao.create(new PasswordDaoBean(userById.get().getId(), "password"));
                Optional<PasswordDaoBean> password = passwordsDao.findByUserId(userById.get().getId());
                System.out.println(password.get());  // {u1, 'password'}
                passwordsDao.updateByUserId(userById.get().getId(), new PasswordDaoBean(0L, "new_password"));
                password = passwordsDao.findByUserId(userById.get().getId());
                System.out.println(password.get());  // {u1, 'new_password'}

                ModulesDao modulesDao = new ModulesDaoImpl(connection);
                ModulesReadersDao modulesReadersDao = new ModulesReadersDaoImpl(connection);

                modulesDao.create(new ModuleDaoBean(0L, "module", userById.get().getId()));
                modulesDao.create(new ModuleDaoBean(0L, "module2", userById.get().getId()));
                Optional<ModuleDaoBean> moduleByName = modulesDao.findByName("module");
                System.out.println(moduleByName.get());  // {m1, 'module', u1}
                Optional<ModuleDaoBean> module = modulesDao.findById(moduleByName.get().getId());
                System.out.println(module.get());  // {m1, 'module', u1}
                modulesDao.updateModuleName(module.get().getId(), "new_module");
                module = modulesDao.findById(moduleByName.get().getId());
                System.out.println(module.get());  // {m1, 'new_module', u1}
                ArrayList<ModuleDaoBean> modulesByOwnerId = modulesDao.findByOwnerId(userById.get().getId());
                System.out.println(modulesByOwnerId);  // [ {m1, 'new_module', u1}, {m2, 'module2', u1} ]

                Optional<UserDaoBean> user2 = usersDao.findByLogin("login2");
                modulesByOwnerId = modulesDao.findByOwnerId(user2.get().getId());
                System.out.println(modulesByOwnerId);  // [ ]

                moduleByName = modulesDao.findByName("module2");
                usersDao.create(new UserDaoBean(0L, "reader", "key_word"));
                Optional<UserDaoBean> reader = usersDao.findByLogin("reader");
                System.out.println(reader.get());  // {u3, 'reader', 'key_word'}
                modulesReadersDao.create(new ModuleReaderDaoBean(moduleByName.get().getId(), reader.get().getId()));
                ArrayList<ModuleReaderDaoBean> moduleReadersByModuleId = modulesReadersDao.findByModuleId(moduleByName.get().getId());
                System.out.println(moduleReadersByModuleId);  // [ {m2, u3} ]
                ArrayList<ModuleReaderDaoBean> moduleReadersByReaderId = modulesReadersDao.findByReaderId(reader.get().getId());
                System.out.println(moduleReadersByReaderId);  // [ {m2, u3} ]
                modulesReadersDao.delete(new ModuleReaderDaoBean(moduleByName.get().getId(), reader.get().getId()));
                moduleReadersByModuleId = modulesReadersDao.findByModuleId(moduleByName.get().getId());
                System.out.println(moduleReadersByModuleId);  // [ ]
            } catch (DaoException e) {
                e.printStackTrace();
            }

            ModulesContentsDao modulesContentsDao = new ModulesContentsDaoImpl(connection);
            ModulesDao modulesDao = new ModulesDaoImpl(connection);

            try {
                Optional<ModuleDaoBean> module = modulesDao.findByName("module2");
                modulesContentsDao.create(new ModuleContentDaoBean(module.get().getId(), 0L, "word", "trans"));
                ArrayList<ModuleContentDaoBean> moduleContents = modulesContentsDao.findByModuleId(module.get().getId());
                System.out.println(moduleContents);  // [ {m2, c1, 'word', 'trans'} ]

                ModuleContentDaoBean modCont = moduleContents.get(0);
                ModuleContentDaoBean newModuleContent = new ModuleContentDaoBean(0L, 0L, "new_word", "new_trans");
                modulesContentsDao.update(modCont.getModuleId(), modCont.getCardId(), newModuleContent);
                moduleContents = modulesContentsDao.findByModuleId(modCont.getModuleId());
                System.out.println(moduleContents);  // [ {m2, c1, 'new_word', 'new_trans'} ]

                modCont = moduleContents.get(0);

                modulesContentsDao.delete(modCont.getModuleId(), modCont.getCardId());
                moduleContents = modulesContentsDao.findByModuleId(modCont.getModuleId());
                System.out.println(moduleContents);  // [ ]
            } catch (DaoException e) {
                e.printStackTrace();
            }

            FoldersDao foldersDao = new FoldersDaoImpl(connection);
            try {
                Optional<UserDaoBean> user = usersDao.findByLogin("login2");
                foldersDao.create(new FolderDaoBean(0L, "Folder", user.get().getId()));
                Optional<FolderDaoBean> folder = foldersDao.findByName("Folder");
                System.out.println(folder.get());  // {f1, 'Folder', u2}
                folder = foldersDao.findById(folder.get().getId());
                System.out.println(folder.get());  // {f1, 'Folder', u2}

                foldersDao.updateFolderName(folder.get().getId(), "new_folder");
                folder = foldersDao.findById(folder.get().getId());
                System.out.println(folder.get());  // {f1, 'new_folder', u2}

                foldersDao.delete(folder.get().getId());
                folder = foldersDao.findById(folder.get().getId());
                System.out.println(folder);  // empty

                foldersDao.create(new FolderDaoBean(2L, "folder2", user.get().getId()));
                foldersDao.create(new FolderDaoBean(2L, "folder3", user.get().getId()));
                Optional<FolderDaoBean> folderByName = foldersDao.findByName("folder2");
                System.out.println(folderByName.get());  // {f2, 'folder2', u2}
                ArrayList<FolderDaoBean> foldersByOwner = foldersDao.findByOwnerId(user.get().getId());
                System.out.println(foldersByOwner);  // [ {f2, 'folder2, u2}, {f3, 'folder3', u2} ]
            } catch (DaoException e) {
                e.printStackTrace();
            }

            FoldersContentsDao foldersContentsDao = new FoldersContentsDaoImpl(connection);
            try {
                Optional<UserDaoBean> user = usersDao.findByLogin("login2");
                modulesDao.create(new ModuleDaoBean(0L, "module3", user.get().getId()));
                modulesDao.create(new ModuleDaoBean(0L, "module4", user.get().getId()));
                Optional<ModuleDaoBean> module3 = modulesDao.findByName("module3");
                Optional<ModuleDaoBean> module4 = modulesDao.findByName("module4");
                System.out.println(module3.get());  // {m3, 'module3', u2}
                System.out.println(module4.get());  // {m4, 'module4', u2}
                Optional<FolderDaoBean> folder = foldersDao.findByName("folder2");

                foldersContentsDao.create(new FolderContentDaoBean(folder.get().getId(), module3.get().getId()));
                ArrayList<FolderContentDaoBean> folderContents = foldersContentsDao.findByFolderId(folder.get().getId());
                System.out.println(folderContents);  // [ {f2, m3} ]
                foldersContentsDao.deleteAllByFolderId(folder.get().getId());
                folderContents = foldersContentsDao.findByFolderId(folder.get().getId());
                System.out.println(folderContents);  // [ ]

                foldersContentsDao.create(new FolderContentDaoBean(folder.get().getId(), module4.get().getId()));
                foldersContentsDao.create(new FolderContentDaoBean(folder.get().getId(), module3.get().getId()));
                foldersContentsDao.delete(folder.get().getId(), module3.get().getId());
                folderContents = foldersContentsDao.findByFolderId(folder.get().getId());
                System.out.println(folderContents);  // [ {f2, m4} ]
            } catch (DaoException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
