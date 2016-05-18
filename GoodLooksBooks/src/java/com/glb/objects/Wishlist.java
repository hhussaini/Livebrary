package com.glb.objects;

import com.glb.factories.ServiceFactory;
import com.glb.services.BookService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author PaulMan
 */

public class Wishlist extends ArrayList {

   BookService bookService;
    
    public boolean contains(String isbn) {
        bookService = ServiceFactory.getBookService();
        for (Object book : this) {
            if (((Book)book).getIsbn().equals(isbn))
                return true;
        }
        return false;
    }

}
