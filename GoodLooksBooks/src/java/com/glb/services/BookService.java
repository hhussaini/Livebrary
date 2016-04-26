package com.glb.services;

import java.util.List;
import com.glb.objects.Book;

public interface BookService {
    public List<Book> searchBooks(String parameter, int offset, int recordsPerPage);
    public int getNumberOfResults();
    public List<Book> getAllBooks();
    public Book getBookByIsbn(String isbn);
    public int addBookToUserItems(String username, String isbn);
    public List<Book> getItemsList(String username);
}
