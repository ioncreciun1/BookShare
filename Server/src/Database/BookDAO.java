package Database;

import model.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookDAO {
 void add(String Username, String Title, String Author, String BookLanguage, String Description, String Category) throws SQLException;

 public List<Book> allBooks() throws SQLException;
List<Book> readByFilter(String filter,String value) throws SQLException;
 List<Book> readByAuthor(String searchString) throws SQLException;
 List<Book> readByTitle(String searchString) throws SQLException;
 void update(Book book) throws SQLException;
  void delete(Book book) throws SQLException;
  ArrayList<Book> readByTwoFilters(String filter, String value, String filter1, String value1) throws SQLException;
 ArrayList<Book> readByThreeFilters(String filter, String value, String filter1, String value1,
     String filter2, String value2) throws SQLException;
 ArrayList<Book> readByAllFilters(String title, String author,
     String language, String category) throws SQLException;
   ArrayList<Book> booksByUser(String username) throws SQLException;

  void changeAvailable(Book book, boolean bool) throws SQLException;
  // void addComment(String username, Book book, String comment) throws SQLException;
}
