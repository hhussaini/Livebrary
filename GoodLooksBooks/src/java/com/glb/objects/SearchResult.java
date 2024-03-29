package com.glb.objects;

import com.glb.constants.CategoryMap;
import com.glb.constants.UserTypes;
import com.glb.objects.Item.ItemSorting;
import com.glb.services.BookService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
    private BookService bookService;
    private boolean onlyInStock;
    private ArrayList<String> selectedFormats;
    private ArrayList<String> selectedLanguages;
    private HttpServletRequest request;
    private ArrayList<String> readingLevels;
    
    public SearchResult(HttpServletRequest request, HttpSession session, BookService bookService) {
        this.request = request;
        System.out.println("IN " + this.getClass() + " : GETTING result set");
        categoryMap = new CategoryMap();
        
        HashMap<String, String> searchTermMap = getTerms(request);
        
        this.onlyInStock = request.getParameter("only-instock") == null ? false : true;
        System.out.println("instock = " + onlyInStock);
        String columnToSort = request.getParameter("sort");
        String[] categories = request.getParameterValues("categories");
        categories = (categories == null) ? new String[]{""} : categories;
        if (categories.length > 0)
            setSelectedCategories(categories);
        
        getSidebar();
        
        
        currentPage = 1;
        if (request.getParameter("page") != null)
            currentPage = Integer.parseInt(request.getParameter("page"));
        int offset = (currentPage-1) * recordsPerPage;
        
        this.bookService = bookService;
        books = bookService.searchBooks(searchTermMap, this.getSelectedCategories(), offset, recordsPerPage, onlyInStock, selectedFormats, selectedLanguages, readingLevels);
        
        int removedBooksCounter =  removeBannedBooks(session);
//        User user = (User)session.getAttribute("user");
//        int removedBooksCounter = 0;
//        if(user==null || user.getType().equalsIgnoreCase(UserTypes.GUEST.toString()) || user.getType().equalsIgnoreCase(UserTypes.CUSTOMER.toString())){
//            for(int i = 0; i<books.size(); i++){
//                if(books.get(i).getIsBanned()){
//                    books.remove(i);
//                    removedBooksCounter++;
//                }
//            }
//        }
        setPages(bookService.getNumberOfResults() - removedBooksCounter);
         if (columnToSort != null)
                this.sortBooks(columnToSort);
    }
    
    public void getSidebar() {
        // FORMATS
        selectedFormats = new ArrayList<String>();
        String paperback = request.getParameter("paperback");
        if (paperback != null)
            selectedFormats.add("paperback");
        String ebook = request.getParameter("ebook");
        if (ebook != null)
            selectedFormats.add("ebook");
        
        // LANGUAGES
        selectedLanguages = new ArrayList<String>();
        String english = request.getParameter("English");
        if (english != null)
            selectedLanguages.add("English");
        String international = request.getParameter("International");
        if (international != null)
            selectedLanguages.add("International");
       
        // READING LEVEL
        readingLevels = new ArrayList<String>();
         String range1 = request.getParameter("range1");
        if (range1 != null)
            readingLevels.add("range1");
        String range2 = request.getParameter("range2");
        if (range2 != null)
            readingLevels.add("range2");
        String range3 = request.getParameter("range3");
        if (range3 != null)
            readingLevels.add("range3");
    }
    public int removeBannedBooks(HttpSession session){
        User user = (User)session.getAttribute("user");
        int removedBooksCounter = 0;
        if(user==null || user.getType().equalsIgnoreCase(UserTypes.GUEST.toString()) || user.getType().equalsIgnoreCase(UserTypes.CUSTOMER.toString())){
            for(int i = 0; i<books.size(); i++){
                if(books.get(i).getIsBanned()){
                    books.remove(i);
                    removedBooksCounter++;
                }
            }
        }
        return removedBooksCounter;
    }
    
    public void setPages(int numResults){
        //  numResults = bookService.getNumberOfResults();
        this.numResults = numResults;
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
    
    public boolean getOnlyInStock() {
        return onlyInStock;
    }
    
      public void setOnlyInStock(boolean onlyInStock) {
        this.onlyInStock = onlyInStock;
    }
      
      public ArrayList<String> getSelectedFormats() {
          return selectedFormats;
      }
      
      public void setSelectedFormats(ArrayList<String> formats) {
          this.selectedFormats = formats;
      }
      
       public ArrayList<String> getSelectedLanguages() {
          return selectedLanguages;
      }
      
      public void setSelectedLanguages(ArrayList<String> languages) {
          this.selectedLanguages = languages;
      }
      
             public ArrayList<String> getReadingLevels() {
          return readingLevels;
      }
      
      public void setReadingLevels(ArrayList<String> levels) {
          this.readingLevels = levels;
      }
    
    public void setSelectedCategories(String[] selectedCategories) {
        this.selectedCategories = new ArrayList<String>();
        for (String category : selectedCategories) {
            this.selectedCategories.add(category);
        }
        
    }
    
    public void sortBooks(String colNumberString){
        
        switch(colNumberString){
            case "title"       :  ItemSorting.sort = 1;   break;
            case "author"      :  ItemSorting.sort = 2;   break;
            case "releaseDate" :  ItemSorting.sort = 3;   break;
            case "addedToSite" :  ItemSorting.sort = 4;   break;
            default            :  ItemSorting.sort = 5;
        }
        
        Collections.sort(books, (o1, o2) -> o1.compareTo(o2));
        setPages(books.size());
    }
    
    public void goToPage(int page, HttpServletRequest request) {
        currentPage = page;
        int offset = (currentPage-1) * recordsPerPage;
        this.bookService = bookService;
        
        books = bookService.searchBooks(this.getTerms(request), this.getSelectedCategories(), offset, recordsPerPage, onlyInStock, selectedFormats, selectedLanguages, readingLevels);
        int numOfRemovedBooks = removeBannedBooks(request.getSession());
        setPages(bookService.getNumberOfResults() - numOfRemovedBooks);
    }
}
