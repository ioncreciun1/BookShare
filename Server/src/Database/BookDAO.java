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
}
