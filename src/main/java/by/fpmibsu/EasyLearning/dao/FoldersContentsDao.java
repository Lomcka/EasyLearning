package by.fpmibsu.EasyLearning.dao;

import by.fpmibsu.EasyLearning.bean.dao_bean.FolderContentDaoBean;

import java.util.ArrayList;

public interface FoldersContentsDao extends Dao<Long, FolderContentDaoBean> {
    ArrayList<FolderContentDaoBean> findByFolderId(Long folderId) throws DaoException;
    void deleteAllByFolderId(Long folderId) throws DaoException;
    void delete(Long folderId, Long moduleId) throws DaoException;
}
