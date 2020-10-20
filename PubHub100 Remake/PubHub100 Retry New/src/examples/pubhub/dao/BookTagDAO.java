package examples.pubhub.dao;

import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.BookTag;


 public interface BookTagDAO {

     public ArrayList<BookTag> getAllBookTags(Book book);
     public ArrayList<Book> getAllBooksByTag(String tag);

     public boolean addBookTag(BookTag bTag);
     public boolean removeBookTag(BookTag bTag);
 }