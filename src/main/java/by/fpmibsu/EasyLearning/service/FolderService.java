package by.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.FolderBean;
import by.fpmibsu.EasyLearning.exception.ServiceException;

import java.util.ArrayList;
import java.util.Optional;

public interface FolderService extends Service {
    void create(String name, Long ownerId) throws ServiceException;

    Optional<FolderBean> findById(Long id) throws ServiceException;

    Optional<FolderBean> findByName(String name) throws ServiceException;

    ArrayList<String> findByOwnerId(Long ownerId) throws ServiceException;

    void addModule(Long folderId, Long moduleId) throws ServiceException;

    void removeModule(Long folderId, Long moduleId) throws ServiceException;

    void updateFolderName(Long folderId, String newName) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
