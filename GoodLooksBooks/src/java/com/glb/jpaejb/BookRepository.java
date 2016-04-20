package com.glb.jpaejb;

import com.glb.entity.Book;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @author PaulMan
 */

public abstract class BookRepository<T> {

    private Class<T> entityClass;

    public BookRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(Book book) {
        getEntityManager().persist(book);
    }

    public void edit(Book book) {
        getEntityManager().merge(book);
    }

    public void remove(Book book) {
        getEntityManager().remove(getEntityManager().merge(book));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<Book> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
