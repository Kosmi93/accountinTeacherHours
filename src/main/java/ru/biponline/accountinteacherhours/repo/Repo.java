package ru.biponline.accountinteacherhours.repo;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Repo<T> {
    public final Class<T> getClazz() {
        final ParameterizedType superclass = (ParameterizedType)
                getClass().getGenericSuperclass();
        return (Class<T>)superclass.getActualTypeArguments()[0];
    }

    public Optional<List<T>> getAll() {
        Optional<List<T>> result = Optional.of(new ArrayList<>());
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.createSelectionQuery("from "+getClazz().getSimpleName(), getClazz())
                        .getResultList()
                        .forEach(element -> result.get().add(element));
            });return result;
        } catch (Exception e) {
            return result;
        }
    }
    // переделать в Optional
    public T save(T element){
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.persist(element);
            });
            return element;
        } catch (Exception e) {
            return element;
        }
    }

    public T update(T element){
        try {

            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.merge(element);

            });
            return element;
        } catch (Exception e) {
            return element;
        }
    }



    public Optional<List<T>> getById(Long id){
        Optional<List<T>> result = Optional.of(new ArrayList<>());
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            result.get().add(session.get(getClazz(), id));
        });
        return result;
    }
}
