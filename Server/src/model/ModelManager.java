package model;

import Database.BookDAO;
import Database.BookDAOImplementation;
import Database.UserDAO;
import Database.UserDAOImplementation;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class representing ModelManger for server
 */
public class ModelManager implements Model
{
  private UserDAO user;
  private BookDAO bookDAO;
  private PropertyChangeProxy<String,Book> property;

  /**
   * Zero parameter constructor where all the parameters for this class are initiate
   * @throws SQLException
   */
  public ModelManager() throws SQLException
  {
    this.user = new UserDAOImplementation();
    this.bookDAO = BookDAOImplementation.getInstance();
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
   * @param passWord
   * @param eMail
   * @param firstName
   * @param lastName
   * @param city
   * @param contactInfo
   * @throws Exception
   */
  public void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception
  {
    System.out.println("SERVER MODEL MODEL");
    user.add(Username,passWord,eMail,firstName,lastName,city,contactInfo);
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

  @Override public void addBook(Book book) throws SQLException
  {
    bookDAO.add(book.getUsername(),book.getTitle(),book.getAuthor(),book.getLanguage(),book.getDescription(),book.getCategory());
    property.firePropertyChange("book",null,book);
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

  @Override public ArrayList<Book> readByFilter(String filter, String value)
      throws SQLException
  {
    ArrayList<Book> books = (ArrayList<Book>)bookDAO.readByFilter(filter,value);
    return books;
  }

  @Override public ArrayList<Book> readByTwoFilters(String filter, String value,
      String filter1, String value1) throws SQLException
  {
    return bookDAO.readByTwoFilters(filter,value,filter1,value1);
  }

  @Override public ArrayList<Book> readByThreeFilters(String filter,
      String value, String filter1, String value1, String filter2,
      String value2) throws SQLException
  {
    return bookDAO.readByThreeFilters(filter,value,filter1,value1,filter2,value2);
  }

  @Override public ArrayList<Book> readByAllFilters(String title, String author,
      String language, String category) throws SQLException
  {
    return bookDAO.readByAllFilters(title,author,language,category);
  }
  @Override
 public ArrayList<Book> booksByUser(String username) throws SQLException,RemoteException{
    System.out.println("BooksByUserModelManager");
    System.out.println(bookDAO.booksByUser(username).toString());
    return bookDAO.booksByUser(username);
  }

  @Override
  public void removeBook(Book book) throws SQLException
  {
    property.firePropertyChange("change","",book);
    bookDAO.delete(book);
  }

  @Override public void changeAvailable(Book book, boolean bool)
      throws SQLException, RemoteException
  {
    property.firePropertyChange("change","",book);
    bookDAO.changeAvailable(book, bool);
  }

  @Override public boolean addListener(GeneralListener<String, Book> listener,
      String... propertyNames)
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, Book> listener, String... propertyNames)
  {
    return property.removeListener(listener,propertyNames);
  }
}
