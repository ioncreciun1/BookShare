package Database;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*BookDAOImplementation is a Singleton, only one instance is created
* static instance, synchronized methods*/
public class BookDAOImplementation implements BookDAO
{
  private static BookDAOImplementation instance;

  /*Class constructor registers the given driver with the DriverManager
   * @throws an error JDBC encounters an error during an interaction with a data source,  @throws an instance of SQLException
   *
   * */
  public BookDAOImplementation() throws SQLException
  {

    DriverManager.registerDriver(new org.postgresql.Driver());
  }

/*@throws exception if there is an error
* @returns an instance of database connection
* @throws exception if there is an error */
  public static synchronized BookDAOImplementation getInstance()
      throws SQLException
  {/*lazy instantiation*/
    if (instance == null)
    {
      instance = new BookDAOImplementation();
    }
    return instance;
  }
  /*Driver Manager class attempts to establish a connection to the given database URL.
   * @throws if a database access error occurs or the url is null
   * @returns connection*/
  private Connection getConnection() throws SQLException
  {
    return DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
            "admin");
  }
/* @throws if a database access error occurs or the parameter is  null
    * @param User if a database error occurs or the user is null */
  @Override public void add(String Username, String Title, String Author,
      String BookLanguage, String Description, String Category) throws SQLException
  {

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".book(username, title, author, bookLanguage, description, category) VALUES (?, ?, ?, ?, ?, ?);");
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
      statement.setString(1, Author);
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

  @Override
  public List<Book> readByFilter(String filter,String value) throws SQLException {
    List<Book> books = new ArrayList<>();
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book WHERE "+filter+" = ? AND Available = true");
      statement.setString(1, value);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String Username = resultSet.getString("Username");
        String BookID = resultSet.getString("BookID");
        String Title = resultSet.getString("Title");
        String Author = resultSet.getString("Author");
        String BookLanguage = resultSet.getString("BookLanguage");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        books.add(new Book(Username,BookID,Title,Author,BookLanguage,Description,Category));
      }
      return books;
    }
  }

   //@Override
  public List<Book> readByTitle(String searchString) throws SQLException {
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book "
          + "WHERE title LIKE ? AND Available = true");
      statement.setString(1, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Book> books = new ArrayList<>();
      while (resultSet.next()) {
        String Username = resultSet.getString("Username");
        String Title = resultSet.getString("Title");
        String BookID = resultSet.getString("BookID");
        String Author = resultSet.getString("Author");
        String BookLanguage = resultSet.getString("BookLanguage");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        books.add(new Book(Username,BookID,Title,Author,BookLanguage,Description,Category));
       // books.add(book);
      }
      return books;
    }
  }
  //@Override
  public List<Book> allBooks() throws SQLException {
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".book order by bookid desc"
          );
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Book> books = new ArrayList<>();
      while (resultSet.next()) {
       // System.out.println("Here");
        String Username = resultSet.getString("Username");
        String BookID = resultSet.getString("BookID");
        String Title = resultSet.getString("Title");
        String Author = resultSet.getString("Author");
        String BookLanguage = resultSet.getString("BookLanguage");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        books.add(new Book(Username,BookID,Title,Author,BookLanguage,Description,Category));

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

