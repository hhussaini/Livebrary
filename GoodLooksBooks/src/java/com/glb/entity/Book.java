package com.glb.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author PaulMan
 */

@Entity
@Table(name = "books2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findByIsbn", query = "SELECT b FROM Books b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Books.findByBookId", query = "SELECT b FROM Books b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title"),
    @NamedQuery(name = "Books.findByLanguage", query = "SELECT b FROM Books b WHERE b.language = :language"),
    @NamedQuery(name = "Books.findByDescription", query = "SELECT b FROM Books b WHERE b.description = :description"),
    @NamedQuery(name = "Books.findByEditionInfo", query = "SELECT b FROM Books b WHERE b.editionInfo = :editionInfo"),
    @NamedQuery(name = "Books.findByAuthorId", query = "SELECT b FROM Books b WHERE b.authorId = :authorId"),
    @NamedQuery(name = "Books.findByAuthorName", query = "SELECT b FROM Books b WHERE b.authorName = :authorName"),
    @NamedQuery(name = "Books.findByPublisherId", query = "SELECT b FROM Books b WHERE b.publisherId = :publisherId"),
    @NamedQuery(name = "Books.findByPublisherName", query = "SELECT b FROM Books b WHERE b.publisherName = :publisherName"),
    @NamedQuery(name = "Books.findByImageUrl", query = "SELECT b FROM Books b WHERE b.imageUrl = :imageUrl"),
    @NamedQuery(name = "Books.findBySummary", query = "SELECT b FROM Books b WHERE b.summary = :summary")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "isbn")
    private String isbn;
    @Size(max = 200)
    @Column(name = "bookId")
    private String bookId;
    @Size(max = 200)
    @Column(name = "title")
    private String title;
    @Size(max = 200)
    @Column(name = "language")
    private String language;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 200)
    @Column(name = "editionInfo")
    private String editionInfo;
    @Size(max = 200)
    @Column(name = "authorId")
    private String authorId;
    @Size(max = 200)
    @Column(name = "authorName")
    private String authorName;
    @Size(max = 200)
    @Column(name = "publisherId")
    private String publisherId;
    @Size(max = 200)
    @Column(name = "publisherName")
    private String publisherName;
    @Size(max = 200)
    @Column(name = "imageUrl")
    private String imageUrl;
    @Size(max = 5000)
    @Column(name = "summary")
    private String summary;

    public Book() {
    }

    public Book(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditionInfo() {
        return editionInfo;
    }

    public void setEditionInfo(String editionInfo) {
        this.editionInfo = editionInfo;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.glb.entity.Books[ isbn=" + isbn + " ]";
    }

}
