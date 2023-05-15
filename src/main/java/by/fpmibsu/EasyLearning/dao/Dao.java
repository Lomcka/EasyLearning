package by.fpmibsu.EasyLearning.dao;

import by.fpmibsu.EasyLearning.bean.Bean;

import java.util.ArrayList;
import java.util.Optional;

public interface Dao <K, T extends Bean> {
    void create(T bean) throws DaoException;
    Optional<T> findById(K id) throws DaoException;
    ArrayList<T> findAll() throws DaoException;
    void update(K id, T newBean) throws DaoException;
    void delete(K id) throws DaoException;
}
