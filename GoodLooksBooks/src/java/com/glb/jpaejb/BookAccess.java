package com.glb.jpaejb;

import com.glb.entity.Book;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author PaulMan
 */

@Stateless
public class BookAccess extends BookRepository<Book> {

    @PersistenceContext(unitName = "GoodLooksBooksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookAccess() {
        super(Book.class);
    }

}
