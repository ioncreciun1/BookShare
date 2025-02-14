package model;

import Database.*;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class representing ModelManger for server
 */
public class ModelManager implements Model
{
  private UserDAO user;
  private BookDAO bookDAO;
  private CommentDAO commentDAO;
  private PropertyChangeProxy<String,Book> property;

  /**
   * Zero parameter constructor where all the parameters for this class are initiate
   * @throws SQLException
   */
  public ModelManager() throws SQLException
  {
    this.user = new UserDAOImplementation();
    this.bookDAO = BookDAOImplementation.getInstance();
    this.commentDAO = new CommentDAOImplementation();
    this.property = new PropertyChangeProxy<>(this);
  }

  /**
   * Check if this user with his username is in the system
   * @param user User
   * @return true if user with this username is in the system, otherwise return false
   * @throws SQLException
   */
  @Override public boolean checkUser(User user) throws SQLException
  {
    return this.user.check_User(user);
  }

  @Override public boolean checkUsername(String username) throws SQLException
  {
    return user.checkUsername(username);
  }

  /**
   * Check if this user email is in the system
   * @param user User
   * @return true if user with this email is already in the system, otherwise return false
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public boolean check_Email(User user)
      throws SQLException
  {
    return this.user.check_Email(user);
  }
  /**
   * Register a user in the system
   * @param Username
   * username of the user
   * @param passWord
   * password for this user
   * @param eMail
   * email address
   * @param firstName
   * First name of the user
   * @param lastName
   * Last name of the user
   * @param city
   * city where user live
   * @param phone
   * phone Number of this user
   * @throws Exception
   */
  public void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String phone)
      throws Exception
  {
    System.out.println("SERVER MODEL MODEL");
    user.add(Username,passWord,eMail,firstName,lastName,city,phone);
  }

  /**
   * Getting a user from the system by this specific username
   * @param username username of user
   * @return a user with this username
   * @throws SQLException
   */
  @Override public User getUser(String username) throws SQLException
  {

    return user.getUser(username);
  }

  /**
   * Add a book to the system and fire an event
   * @param book
   * @throws SQLException
   */
  /**
   *
   * @param book
   *        is a Book entered by user
   * @throws SQLException
   * notifies all clients of book update
   */
  @Override public void addBook(Book book) throws SQLException
  {
    bookDAO.add(book.getUsername(),book.getTitle(),book.getAuthor(),book.getLanguage(),book.getDescription(),book.getCategory());
    property.firePropertyChange("book",null,book);
  }

  public void add(String BookID, String Username, String comment) throws RemoteException,SQLException
  {

    commentDAO.add(BookID,Username, comment);
    property.firePropertyChange("comment",Username + " : "+ comment,null);
  }

  public ArrayList<String> getComments(String BookID)
          throws SQLException, RemoteException{
    ArrayList<String> comments = commentDAO.get(BookID);
      return comments;
  }
  /**
   * Getting all books
   * @return all books from the system
   * @throws SQLException
   */
  @Override public ArrayList<Book> allBooks() throws SQLException
  {
    ArrayList<Book> books = (ArrayList<Book>)bookDAO.allBooks();

    return books;
  }

  /**
   *
   * @param filter
   *        is a filter of Title,Author,Category or language
   * @param value
   *        the search criteria  value
   * @return books
   *        is a list of books matching filter criteria
   * @throws SQLException if null or error
   */
  @Override public ArrayList<Book> readByFilter(String filter, String value)
      throws SQLException
  {
    ArrayList<Book> books = (ArrayList<Book>)bookDAO.readByFilter(filter,value);
    return books;
  }

  /**
   *
   * @param filter
   *         is the first filter of Title,Author,Category or language
   * @param value
   *        is the FIRST search criteria  value
   * @param filter1
   *         is the first filter of Title,Author,Category or language
   * @param value1
   *        the second search criteria  value
   * @return a list of books matching the search criteria
   * @throws SQLException
   */

  @Override public ArrayList<Book> readByTwoFilters(String filter, String value,
      String filter1, String value1) throws SQLException
  {
    return bookDAO.readByTwoFilters(filter,value,filter1,value1);
  }

  /**
   *
   * @param filter
   *        is the first search filter of Title,Author,Category or language
   * @param value
   *          is the FIRST search criteria  value
   * @param filter1
   *        is the second search filter of Title,Author,Category or language
   * @param value1
   *         is the second search criteria  value
   * @param filter2
   *        is the third search filter of Title,Author,Category or language
   * @param value2
   *        is the third search criteria  value
   * @return
   *      list of books from database meeting search criteria
   * @throws SQLException if null or error
   */
  @Override public ArrayList<Book> readByThreeFilters(String filter,
      String value, String filter1, String value1, String filter2,
      String value2) throws SQLException
  {
    return bookDAO.readByThreeFilters(filter,value,filter1,value1,filter2,value2);
  }

  /**
   *
   * @param title
   *         is a title of a book from user entry
   * @param author
   *          is an author of a book from user entry
   * @param language
   *         is a language of a book from user entry
   * @param category
   *           is a category of a book from user entry
   * @return
   *        list of books from database meeting search criteria
   * @throws SQLException if null or error
   */
  @Override public ArrayList<Book> readByAllFilters(String title, String author,
      String language, String category) throws SQLException
  {
    return bookDAO.readByAllFilters(title,author,language,category);
  }

  /**
   *
   * @param username
   *         the username of the client
   * @return
   *          list of books from database meeting search criteria
   * @throws SQLException if null or error
   * @throws RemoteException if error
   */
  @Override
 public ArrayList<Book> booksByUser(String username) throws SQLException,RemoteException{
    System.out.println("BooksByUserModelManager");
    System.out.println(bookDAO.booksByUser(username).toString());
    return bookDAO.booksByUser(username);
  }

  /**
   *
   * @param book
   *        is a book selected from a list
   * @throws SQLException if null or error
   */
  @Override
  public void removeBook(Book book) throws SQLException
  {
    property.firePropertyChange("change","",book);
    bookDAO.delete(book);
  }

  /**
   *
   * @param book
   *        is a book selected from a list
   *
   * @param bool
   *        is a boolean true for available false for unavialable
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public void changeAvailable(Book book, boolean bool)
      throws SQLException, RemoteException
  {
    property.firePropertyChange("change","",book);
    bookDAO.changeAvailable(book, bool);
  }

  /**
   *
   * @param listener
   *        an observer
   * @param propertyNames
   *        a list of properties
   * @return
   *
   */
  @Override public boolean addListener(GeneralListener<String, Book> listener,
      String... propertyNames)
  {
    return property.addListener(listener,propertyNames);
  }

  /**
   *
   * @param listener
   *        is an obersver for removal
   * @param propertyNames
   *        list of properties
   * @return
   */
  @Override public boolean removeListener(
      GeneralListener<String, Book> listener, String... propertyNames)
  {
    return property.removeListener(listener,propertyNames);
  }
}
