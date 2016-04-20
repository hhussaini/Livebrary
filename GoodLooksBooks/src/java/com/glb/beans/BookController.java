package com.glb.beans;

import com.glb.entity.Book;
import com.glb.jpaejb.BookAccess;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 * @author PaulMan
 */
@Named(value = "bookController")
@SessionScoped
//@Dependent
public class BookController implements Serializable {
    
    @EJB
    BookAccess bookAccess;
    
    @Inject
    BookBean bookBean;
    
    public List<Book> getAll() {
        return bookAccess.findAll();
    }
    
    public int getCount() {
        return bookAccess.count();
    }
    
    public void delete(Book book) {
        bookAccess.remove(book);
    }
    
    public void add() {
        Book book = new Book();
        book.setIsbn(bookBean.getIsbn());
        book.setBookId(bookBean.getBookId());
        book.setTitle(bookBean.getTitle());
        book.setLanguage(bookBean.getLanguage());
        book.setDescription(bookBean.getDescription());
        book.setEditionInfo(bookBean.getEditionInfo());
        book.setAuthorId(bookBean.getAuthorId());
        book.setAuthorName(bookBean.getAuthorName());
        book.setPublisherId(bookBean.getPublisherId());
        book.setPublisherName(bookBean.getPublisherName());
        book.setImageUrl(bookBean.getImageUrl());
        book.setSummary(bookBean.getSummary());
        
        bookAccess.create(book);
    }
    
    public void edit(Book book) {
        bookBean.setIsbn(book.getIsbn());
        bookBean.setBookId(book.getBookId());
        bookBean.setTitle(book.getTitle());
        bookBean.setLanguage(book.getLanguage());
        bookBean.setDescription(book.getDescription());
        bookBean.setEditionInfo(book.getEditionInfo());
        bookBean.setAuthorId(book.getAuthorId());
        bookBean.setAuthorName(book.getAuthorName());
        bookBean.setPublisherId(book.getPublisherId());
        bookBean.setPublisherName(book.getPublisherName());
        bookBean.setImageUrl(book.getImageUrl());
        bookBean.setSummary(book.getSummary());
    }
    
    public void save() {
        Book book = new Book(bookBean.getIsbn());
        book.setBookId(bookBean.getBookId());
        book.setTitle(bookBean.getTitle());
        book.setLanguage(bookBean.getLanguage());
        book.setDescription(bookBean.getDescription());
        book.setEditionInfo(bookBean.getEditionInfo());
        book.setAuthorId(bookBean.getAuthorId());
        book.setAuthorName(bookBean.getAuthorName());
        book.setPublisherId(bookBean.getPublisherId());
        book.setPublisherName(bookBean.getPublisherName());
        book.setImageUrl(bookBean.getImageUrl());
        book.setSummary(bookBean.getSummary());
        bookAccess.edit(book);        
    }
}
