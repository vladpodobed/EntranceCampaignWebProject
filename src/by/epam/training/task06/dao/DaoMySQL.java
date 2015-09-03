package by.epam.training.task06.dao;

import java.util.List;

/**
 * <p>
 *      Basic interface for dao
 * </p>
 *
 * @param <T>
 */
public interface DaoMySQL<T> {
    public int create(T t) throws DaoException;
    public T read(int id) throws DaoException;
    public void update(T t) throws DaoException;
    public void delete(int id) throws DaoException;
    public List<T> loadAll() throws DaoException;
    public List<T> select(QueryOption option) throws DaoException;
}
