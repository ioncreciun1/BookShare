package Database;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * BookDAOImplementation is a Singleton, only one instance is created
* static instance, synchronized methods*/
public class BookDAOImplementation implements BookDAO
{
  private static BookDAOImplementation instance;

  /**
   * Class constructor registers the given driver with the DriverManager
   * @throws SQLException JDBC encounters an error during an interaction with a data source,  @throws an instance of SQLException
   * */
  public BookDAOImplementation() throws SQLException
  {

    DriverManager.registerDriver(new org.postgresql.Driver());
  }

/**
 * @throws  if there is an error
* @returns an instance of database connection
* @throws if there is an error */
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
    return DriverManager.getConnection("jdbc:postgresql://btv9bsc3ws7gpfnylr1c-postgresql.services.clever-cloud.com:5432/btv9bsc3ws7gpfnylr1c", "uhxksonl4uvuqdftznsg",
            "pnwEWl0YlSc6A2z619ff");
  }
/** @throws if a database access error occurs or the parameter is  null
    * @param Username
    the users identification
* @param Title title
  the title of the book
   @param Author
     the author of the book
   @param  language
         the language that is book is written selected by dropdown by user
    @param Description
       the description of the book entered by the user,
    @param Category
         the category of the book selected by the user */
  @Override public void add(String Username, String Title, String Author,
      String language, String Description, String Category) throws SQLException
  {

    try (Connection connection = getConnection())
    {/*A Statement is an interface that represents a SQL statement. You execute Statement objects, and they generate
     ResultSet objects, which is a table of data representing a database result set.
      You need a Connection object to create a Statement object.*/
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".book(username, title, author, language, description, category) VALUES (?, ?, ?, ?, ?, ?);");
      statement.setString(1, Username);
      statement.setString(2, Title);
      statement.setString(3, Author);
      statement.setString(4, language);
      statement.setString(5, Description);
      statement.setString(6, Category);
      statement.executeUpdate();
    }
  }

  @Override public void addComment(String username, Book book,  String comment)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
//      PreparedStatement statement = connection.prepareStatement(
//          "INSERT INTO \"SEP2\".comment(username, bookid, comment) VALUES (?,?,?);");
////      String bookid = book.getId();
//      statement.setString(1, username);
////      statement.setString(2, bookid);
//      statement.setString(3, comment);
//      statement.executeUpdate();
    }
  }

  @Override
  /**
  @throws if a database access error occurs or the parameter is null
  @param Author is an author entered by the user??? is this going to change
  @return books
        returns a list of books by author*/
  public List<Book> readByAuthor(String Author) throws SQLException {
    List<Book> books = new ArrayList<>();
    try(Connection connection = getConnection()) {
      /*{/*A Statement is an interface that represents a SQL statement. You execute Statement objects, and they generate
     ResultSet objects, which is a table of data representing a database result set.
      You need a Connection object to create a Statement object.*/
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book WHERE author = ? AND Available = true");
      statement.setString(1, Author);
      ResultSet resultSet = statement.executeQuery();
     while (resultSet.next()) {
       /*access the data in a ResultSet object through a cursor. Note that this cursor is not a database cursor.
     This cursor is a pointer that points to one row of data in the ResultSet object.
     Initially, the cursor is positioned before the first row. You call various methods defined in the ResultSet object to move the cursor.*/
        String Username = resultSet.getString("Username");
       String BookID = resultSet.getString("BookID");
        String Title = resultSet.getString("Title");
        Author = resultSet.getString("Author");
        String language = resultSet.getString("language");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        books.add(new Book(Username, BookID, Title, Author, language, Description, Category));
      }
        return books;
      }
    }
/** @throws if a database access error occurs or the parameter is null
  @param filter
      is a filter type
  @param value
      the value is the entry by user by author name, title, language or  category
  @return books
    returns a list of books by filter type*/
  @Override
  public List<Book> readByFilter(String filter,String value) throws SQLException {
        List<Book> books = new ArrayList<>();
        try(Connection connection = getConnection()) {
          String val = "%"+value+"%";
          System.out.println(val);
          PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".book WHERE "+filter+" ILIKE ? AND Available = true");
          statement.setString(1, val);
          System.out.println(statement.toString());
          ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()) {
              /*access the data in a ResultSet object through a cursor. Note that this cursor is not a database cursor.
     This cursor is a pointer that points to one row of data in the ResultSet object.
     Initially, the cursor is positioned before the first row. You call various methods defined in the ResultSet object to move the cursor.*/

            String Username = resultSet.getString("Username");
            String BookID = resultSet.getString("BookID");
            String Title = resultSet.getString("Title");
            String Author = resultSet.getString("Author");
            String language = resultSet.getString("language");
            String Description = resultSet.getString("Description");
            String Category = resultSet.getString("Category");
            books.add(new Book(Username,BookID,Title,Author,language,Description,Category));
          }
      return books;
    }
  }
  /**
   @throws if a database access error occurs or the parameter is null
   @param searchString
        is a string entry by user
   @return books
         returns a list of books by title*/
   //@Override
  public List<Book> readByTitle(String searchString) throws SQLException {
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book "
          + "WHERE title LIKE ? AND Available = true");
      statement.setString(1, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
         /*access the data in a ResultSet object through a cursor. Note that this cursor is not a database cursor.
     This cursor is a pointer that points to one row of data in the ResultSet object.
     Initially, the cursor is positioned before the first row. You call various methods defined in the ResultSet object to move the cursor.*/
      ArrayList<Book> books = new ArrayList<>();
      while (resultSet.next()) {
        String Username = resultSet.getString("Username");
        String Title = resultSet.getString("Title");
        String BookID = resultSet.getString("BookID");
        String Author = resultSet.getString("Author");
        String language = resultSet.getString("language");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        books.add(new Book(Username,BookID,Title,Author,language,Description,Category));
       // books.add(book);
      }
      return books;
    }
  }

  /**
   *
   * @return
   * @throws SQLException
   */
  /**
 @throws if a database access error occurs or the parameter is null
 @return books
       returns a list of all books*/
  public List<Book> allBooks() throws SQLException {
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".book order by bookid desc"
          );
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Book> books = new ArrayList<>();
      while (resultSet.next()) {
           /*access the data in a ResultSet object through a cursor. Note that this cursor is not a database cursor.
     This cursor is a pointer that points to one row of data in the ResultSet object.
     Initially, the cursor is positioned before the first row. You call various methods defined in the ResultSet object to move the cursor.*/
       // System.out.println("Here");
        String Username = resultSet.getString("Username");
        String BookID = resultSet.getString("BookID");
        String Title = resultSet.getString("Title");
        String Author = resultSet.getString("Author");
        String language = resultSet.getString("language");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        books.add(new Book(Username,BookID,Title,Author,language,Description,Category));
      }
      return books;
    }
  }
  @Override public void update(Book book) throws SQLException
  {

  }

  /**
   * Delete a book from database
   * @param book specific book
   * @throws SQLException
   */
  public void delete(Book book) throws SQLException {
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM \"SEP2\".book WHERE Username = ? AND Title = ? AND Author = ? AND language = ? AND Description = ? AND Category = ?"
      );
      String Username = book.getUsername();
      String Title = book.getTitle();
      String Author = book.getAuthor();
      String language = book.getLanguage();
      String Description = book.getDescription();
      String Category = book.getCategory();
      statement.setString(1, Username);
      statement.setString(2, Title);
      statement.setString(3, Author);
      statement.setString(4, language);
      statement.setString(5, Description);
      statement.setString(6, Category);
      statement.executeUpdate();
    }
  }
/**
 * @throws if a database access error occurs or the parameter is null
@param filter
      is a String filter of either an author, title, category or language
   @param value
        is a
    @return books
        returns a list of all books matching the parameters*/
  public ArrayList<Book> readByTwoFilters(String filter, String value, String filter1, String value1) throws SQLException {
    ArrayList<Book> books = new ArrayList<>();
    try(Connection connection = getConnection()) {
      System.out.println(filter);
      System.out.println(filter1);
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".book WHERE "+filter+" ILIKE ? AND "+filter1+" ILIKE ? AND Available = true");
      statement.setString(1, value);
      statement.setString(2, value1);
      System.out.println("Here");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
           /*access the data in a ResultSet object through a cursor. Note that this cursor is not a database cursor.
     This cursor is a pointer that points to one row of data in the ResultSet object.
     Initially, the cursor is positioned before the first row. You call various methods defined in the ResultSet object to move the cursor.*/
        String Username = resultSet.getString("Username");
        String BookID = resultSet.getString("BookID");
        String Title = resultSet.getString("Title");
        String Author = resultSet.getString("Author");
        String language = resultSet.getString("language");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        books.add(new Book(Username,BookID,Title,Author,language,Description,Category));
      }
      return books;
    }
  }

  /**
   *
   * @param filter
   *        is a String of a filter type
   * @param value
   *         is a String of value entered by user
   * @param filter1
   *        is the second String filter of type Author, Title, Category, Language
   * @param value1
   *            is the second String value entered by the user
   * @param filter2
   *      is the thIRD  String filter of type Author, Title, Category, Language
   * @param value2
   *        is the third String value entered by the user
   * @return books
   *          returns a list of books
   * @throws SQLException
   */

  public ArrayList<Book> readByThreeFilters(String filter, String value, String filter1, String value1,
      String filter2, String value2) throws SQLException {
    ArrayList<Book> books = new ArrayList<>();
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".book WHERE "+filter+" ILIKE ? AND "+filter1+" ILIKE ? AND "+filter2+" ILIKE ? AND Available = true");
      statement.setString(1, value);
      statement.setString(2, value1);
      statement.setString(3, value2);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
           /*access the data in a ResultSet object through a cursor. Note that this cursor is not a database cursor.
     This cursor is a pointer that points to one row of data in the ResultSet object.
     Initially, the cursor is positioned before the first row. You call various methods defined in the ResultSet object to move the cursor.*/
        String Username = resultSet.getString("Username");
        String BookID = resultSet.getString("BookID");
        String Title = resultSet.getString("Title");
        String Author = resultSet.getString("Author");
        String language = resultSet.getString("language");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        books.add(new Book(Username,BookID,Title,Author,language,Description,Category));
      }
      return books;
    }
  }

  /**
   * Getting a list of books that have this parameters
   * @param title title of a book
   * @param author author of a book
   * @param language language of a book
   * @param category category of a book
   * @return a list of books
   * @throws SQLException
   */
  @Override public ArrayList<Book> readByAllFilters(String title, String author,
      String language, String category) throws SQLException
  {
    ArrayList<Book> books = new ArrayList<>();
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".book WHERE title ILIKE ? AND author ILIKE ? AND language ILIKE ? and category ILIKE ?  AND Available = true");
      statement.setString(1, title);
      statement.setString(2, author);
      statement.setString(3, language);
      statement.setString(4, category);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
           /*access the data in a ResultSet object through a cursor. Note that this cursor is not a database cursor.
     This cursor is a pointer that points to one row of data in the ResultSet object.
     Initially, the cursor is positioned before the first row. You call various methods defined in the ResultSet object to move the cursor.*/
        String Username = resultSet.getString("Username");
        String BookID = resultSet.getString("BookID");
        String Title = resultSet.getString("Title");
        String Author = resultSet.getString("Author");
        String bookLanguage = resultSet.getString("language");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        books.add(new Book(Username,BookID,Title,Author,bookLanguage,Description,Category));
      }
      return books;
    }
  }

  /**
   * Get a list of books for this username
   * * @param username username of a specific user
   * @return a list of books
   * @throws SQLException
   */
  public ArrayList<Book> booksByUser(String username) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      System.out.println(username + " BOOKS BY USER INPUT USERNAME");
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".book WHERE Username = ?");
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Book> booksByUser = new ArrayList<>();
      while (resultSet.next())
      {
           /*access the data in a ResultSet object through a cursor. Note that this cursor is not a database cursor.
     This cursor is a pointer that points to one row of data in the ResultSet object.
     Initially, the cursor is positioned before the first row. You call various methods defined in the ResultSet object to move the cursor.*/
        // System.out.println("Here");
        String Username = resultSet.getString("Username");
        String BookID = resultSet.getString("BookID");
        String Title = resultSet.getString("Title");
        String Author = resultSet.getString("Author");
        String language = resultSet.getString("language");
        String Description = resultSet.getString("Description");
        String Category = resultSet.getString("Category");
        System.out.println(BookID + "BOOK BY USER");
        Boolean available = resultSet.getBoolean("available");
        System.out.println(available);
        Book book =  new Book(Username, BookID, Title, Author, language, Description,
            Category);
        if(available)
        {
          book.setAvailable();
        }
        else {
          book.setBorrowed();
        }
        booksByUser.add(
            book);
      }

      return booksByUser;
    }
  }

  /**
   * change status of a specific book
   * @param book specific book
   * @param bool status of book
   * @throws SQLException
   */
  @Override public void changeAvailable(Book book, boolean bool)
      throws SQLException
  {
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("UPDATE \"SEP2\".book SET Available = "+bool+" WHERE Username = ? AND Title = ? AND Author = ? AND language = ? AND Description = ? AND Category = ?"
      );
      String Username = book.getUsername();
      String Title = book.getTitle();
      String Author = book.getAuthor();
      String language = book.getLanguage();
      String Description = book.getDescription();
      String Category = book.getCategory();
      statement.setString(1, Username);
      statement.setString(2, Title);
      statement.setString(3, Author);
      statement.setString(4, language);
      statement.setString(5, Description);
      statement.setString(6, Category);
      statement.executeUpdate();
      System.out.println(statement.toString());
    }
  }
}

