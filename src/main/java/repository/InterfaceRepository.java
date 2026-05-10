package repository;

import java.util.List;

public interface InterfaceRepository<T,ID> {
    void add(T entity);
    T findById(ID id);
    List<T> findAll();
    void update(T entity);
    void remove(ID id);
}
