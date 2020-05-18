package Database;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookDAOImplementation implements BookDAO
{
  private static BookDAOImplementation instance;

  private BookDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized BookDAOImplementation getInstance()
      throws SQLException
  {
    if (instance == null)
    {
      instance = new BookDAOImplementation();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
            "2011");
  }

  @Override public void add(String Username, String Title, String Author,
      String BookLanguage, String Description, String Category) throws SQLException
  {
    try (Connection connection = getConnection())
    {

      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Book(Username, Title, Author, BookLanguage, Description, Category) VALUES (?, ?, ?, ?, ?, ?, ?);");
      statement.setString(1, Username);
      statement.setString(2, Title);
      statement.setString(3, Author);
      statement.setString(4, BookLanguage);
      statement.setString(5, Description);
      statement.setString(6, Category);
      statement.executeUpdate();
    }
  }


  @Override
  public List<Book> readByAuthor(String Author) throws SQLException {
    List<Book> books = new ArrayList<>();
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book WHERE author = ? AND Available = true");
      statement.setString(4, Author);
      ResultSet resultSet = statement.executeQuery();
     while (resultSet.next()) {
        String Username = resultSet.getString("Username");
        String Title = resultSet.getString("Title");
        Author = resultSet.getString("Author");
        String BookLanguage = resultSet.getString("BookLanguage");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        books.add(new Book(Username, Title, Author, BookLanguage, Description, Category));
      }
        return books;
      }
    }


   //@Override
  public List<Book> readByTitle(String searchString) throws SQLException {
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book "
          + "WHERE title LIKE ?");
      statement.setString(3, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Book> books = new ArrayList<>();
      while (resultSet.next()) {
        String Username = resultSet.getString("Username");
        String Title = resultSet.getString("Title");
        String Author = resultSet.getString("Author");
        String BookLanguage = resultSet.getString("BookLanguage");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
       // Book book =  new Book(Username,Title, Author, BookLanguage, Description, Category);
       // books.add(book);
      }
      return books;
    }
  }

  @Override public void update(Book book) throws SQLException
  {

  }

  // @Override


//  }

 // @Override
  public void delete(Book book) throws SQLException {

  }
}

