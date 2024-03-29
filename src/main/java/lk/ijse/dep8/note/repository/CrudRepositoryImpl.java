package lk.ijse.dep8.note.repository;

import lk.ijse.dep8.note.entity.SuperEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class CrudRepositoryImpl <T extends SuperEntity, ID extends Serializable> implements CrudRepository<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> entityClzObj;

    public CrudRepositoryImpl() {
        this.entityClzObj = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void deleteById(Serializable pk) {
        entityManager.remove(entityManager.getReference(entityClzObj, pk));
    }

    @Override
    public boolean existsById(Serializable pk) {
        return findById(pk).isPresent();
    }

    @Override
    public Optional findById(Serializable pk) {
        return Optional.ofNullable(entityManager.find(entityClzObj, pk));
    }

    @Override
    public List findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClzObj.getName() + " e" , entityClzObj).getResultList();
    }

    @Override
    public Long count() {
        return entityManager.createQuery("SELECT COUNT(e) FROM " + entityClzObj.getName() + " e" , Long.class).getSingleResult();
    }
}
