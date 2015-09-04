package by.epam.training.task06.dao;

import by.epam.training.task06.dao.query.QueryOption;
import by.epam.training.task06.exception.DaoException;

import java.util.List;

/**
 * <p>
 *      Basic interface for dao
 * </p>
 *
 * @param <T>
 */
public interface DAO<T> {
    public int create(T t) throws DaoException;
    public T read(int id) throws DaoException;
    public void update(T t) throws DaoException;
    public void delete(int id) throws DaoException;
    public List<T> loadAll() throws DaoException;
    public List<T> select(QueryOption option) throws DaoException;
}
