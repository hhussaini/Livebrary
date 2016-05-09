package com.glb.objects;

import com.glb.constants.CategoryMap;
import com.glb.services.BookService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author PaulMan
 */

public class SearchResult {
    
    private TreeMap<String, String> categoryMap;
    private ArrayList<String> selectedCategories;
    private List<Book> books;
    private int numPages;
    private int currentPage;
    private int firstPage;
    private int lastPage;
    private int numResults;
    private int recordsPerPage = 18;
    private String author;
    private String publisher;
    private String isbn;
    private String keyword;
    private String title;
    
    public SearchResult(HttpServletRequest request, HttpSession session, BookService bookService) {
        System.out.println("IN " + this.getClass() + " : GETTING result set");
        categoryMap = new CategoryMap();
        
        HashMap<String, String> searchTermMap = getTerms(request);
        
        String[] categories = request.getParameterValues("categories");
        categories = (categories == null) ? new String[]{""} : categories;
        if (categories.length > 0)
            setSelectedCategories(categories);
        
        currentPage = 1;
        if (request.getParameter("page") != null)
            currentPage = Integer.parseInt(request.getParameter("page"));
        int offset = (currentPage-1) * recordsPerPage;
       
        books = bookService.searchBooks(searchTermMap, categories, offset, recordsPerPage);
        
        numResults = bookService.getNumberOfResults();
        numPages = (int) Math.ceil(numResults * 1.0 / recordsPerPage);
        firstPage = (currentPage - 5 < 1) ? 1 : currentPage - 5;
        lastPage = (currentPage + 5 > numPages) ? numPages : currentPage + 5;
        lastPage = (lastPage == 0) ? lastPage :  (lastPage - 1);
    }
    
    
    public TreeMap<String, String> getCategories() {
        return categoryMap;
    }
    
    private HashMap<String, String> getTerms(HttpServletRequest request) {
        HashMap<String, String> searchTermMap = new HashMap<String, String>();
        setKeyword(request.getParameter("keyword"));
        setKeyword((getKeyword() == null) ? "" : getKeyword());
        searchTermMap.put("term", getKeyword());
        setAuthor(request.getParameter("author"));
        setAuthor(getAuthor() == null ? "" : getAuthor());
        searchTermMap.put("author", getAuthor());
        setPublisher(request.getParameter("publisher"));
        setPublisher(getPublisher() == null ? "" : getPublisher());
        searchTermMap.put("publisher", getPublisher());
        setIsbn(request.getParameter("isbn"));
        setIsbn(getIsbn() == null ? "" : getIsbn());
        searchTermMap.put("isbn", getIsbn());
        setTitle(request.getParameter("title"));
        setTitle(getTitle() == null ? "" : getTitle());
        searchTermMap.put("title", getTitle());
        return searchTermMap;
    }

    public void setCategories(TreeMap<String, String> categories) {
        this.categoryMap = categories;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(String[] selectedCategories) {
        this.selectedCategories = new ArrayList<String>();
        for (String category : selectedCategories) {
            this.selectedCategories.add(category);
        }
        
    }
}
