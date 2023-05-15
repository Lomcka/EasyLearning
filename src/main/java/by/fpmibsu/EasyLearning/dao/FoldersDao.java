package by.fpmibsu.EasyLearning.dao;

import by.fpmibsu.EasyLearning.bean.dao_bean.FolderDaoBean;

import java.util.ArrayList;
import java.util.Optional;

public interface FoldersDao extends Dao<Long, FolderDaoBean> {
    Optional<FolderDaoBean> findByName(String folderName) throws DaoException;
    ArrayList<FolderDaoBean> findByOwnerId(Long ownerId) throws DaoException;
    void updateFolderName(Long id, String newName) throws DaoException;
}
