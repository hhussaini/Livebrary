package com.glb.services;

import java.util.List;
import com.glb.objects.Book;

public interface BookService {
    public List<Book> getWishlist(String userName);
    public int removeFromWishlist(String username, String bookName);
}
