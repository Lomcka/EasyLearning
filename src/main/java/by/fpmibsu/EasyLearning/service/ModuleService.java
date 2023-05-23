package by.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.CardBean;
import by.fpmibsu.EasyLearning.bean.ModuleBean;
import by.fpmibsu.EasyLearning.exception.ServiceException;

import java.util.ArrayList;
import java.util.Optional;

public interface ModuleService extends Service {
    void create(String name, Long ownerId) throws ServiceException;

    Optional<ModuleBean> findById(Long id) throws ServiceException;

    Optional<ModuleBean> findByName(String name) throws ServiceException;

    ArrayList<String> findByOwnerId(Long ownerId) throws ServiceException;

    ArrayList<String> findByReaderId(Long readerId) throws ServiceException;

    void updateModuleName(Long moduleId, String newName) throws ServiceException;

    void addReader(Long moduleId, Long readerId) throws ServiceException;

    void updateCard(Long moduleId, Long cardId, CardBean newCard) throws ServiceException;

    void deleteCard(Long moduleId, Long cardId) throws ServiceException;

    void addCard(Long moduleId, CardBean card) throws ServiceException;
}
