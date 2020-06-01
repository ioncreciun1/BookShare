package mediator;

import model.Book;
import model.Model;
import model.User;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Client class that connects with the server
 */
public class Client implements ClientModel, RemoteListener<String,Book>
{

  public static final String HOST = "localhost";
  private String host;
  private Model model;
  private RemoteModel remoteModel;
  private PropertyChangeProxy<String,Book> property;

  /**
   * Two arguments constructor
   * @param model client model
   * @param host host of server
   * @throws RemoteException
   * @throws NotBoundException
   * @throws MalformedURLException
   */
  public Client(Model model,String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = model;
    this.host = host;
    this.remoteModel = (RemoteModel) Naming.lookup("rmi://" + host + ":1099/Book");
    UnicastRemoteObject.exportObject(this, 0);
    this.property = new PropertyChangeProxy<>(this);
    remoteModel.addListener(this,"book","change","comment");
  }


  /**
   * Register a user in the system
   * @param Username
   * @param passWord
   * @param eMail
   * @param firstName
   * @param lastName
   * @param city
   * @param phone
   * @throws Exception
   */

  @Override public void registerUser(String Username, String passWord,
      String eMail, String firstName, String lastName, String city,
      String phone) throws Exception
  {
    remoteModel.registerUser(Username,passWord,eMail,firstName,lastName,city,phone);
  }

  /**
   * Get a user with this username
   * @param username name of user
   * @return User from a database
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public User getUser(String username) throws RemoteException
  {
    return remoteModel.getUser(username);
  }

  /**
   * Check if Username of this user is in the system
   * @param user User
   * @return true if user with this username is in the system, otherwise return false
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public boolean checkUser(User user)
      throws RemoteException
  {
    return remoteModel.checkUser(user);
  }

  /**
   * Check if this user email is in the system
   * @param user User
   * @return true if user with this email is already in the system, otherwise return false
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public boolean checkEmail(User user)
      throws RemoteException
  {
    return remoteModel.checkEmail(user);
  }

  /**
   * Add a book to the system
   * @param book a simple book
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void addBook(Book book) throws RemoteException, SQLException
  {
    remoteModel.addBook(book);
  }

  /**
   * Getting all books from the system
   * @return all books from database
   * @throws SQLException
   */
  @Override public ArrayList<Book> allBooks()
      throws SQLException, RemoteException
  {

    return remoteModel.allBooks();
  }

  /**
   * Getting all books by a filter and specific value
   * @param filter filter for searching for a book
   * @param value value of the filter
   * @return An list of books after searching by this filter
   * @throws SQLException
   */
  @Override public ArrayList<Book> readByFilter(String filter, String value)
      throws SQLException, RemoteException
  {
    return remoteModel.readByFilter(filter,value);
  }

  /**
   * Getting all books by two filters and their specific value
   * @param filter filter for searching for a book
   * @param value value of the filter
   * @param filter1 filter for searching for a book
   * @param value1 value of the filter1
   * @return An list of books after searching by this two filters
   * @throws SQLException
   */
  @Override public ArrayList<Book> readByTwoFilters(String filter, String value,
      String filter1, String value1) throws SQLException, RemoteException
  {
    return remoteModel.readByTwoFilters(filter,value,filter1,value1);
  }

  /**
   *
   * @param filter filter for searching for a book
   * @param value  value of the filter
   * @param filter1 filter for searching for a book
   * @param value1 value of the filter1
   * @param filter2 filter for searching for a book
   * @param value2 value of the filter2
   * @return An list of books after searching by this filters
   * @throws SQLException
   */
  @Override public ArrayList<Book> readByThreeFilters(String filter,
      String value, String filter1, String value1, String filter2,
      String value2) throws SQLException, RemoteException
  {
    return remoteModel.readByThreeFilters(filter,value,filter1,value1,filter2,value2);
  }

  /**
   * Search a book by all search filters
   * @param title title of the book
   * @param author author of the book
   * @param language language of the book
   * @param category category of the book
   * @return An list of book based on these filters
   * @throws SQLException
   */
  @Override public ArrayList<Book> readByAllFilters(String title, String author,
      String language, String category) throws SQLException, RemoteException
  {
    return remoteModel.readByAllFilters(title,author,language,category);
  }

  /**
   * Getting a list of books by username
   * @param username username of a specific user
   * @return An list of books that have this specific username
   * @throws SQLException
   * @throws RemoteException
   */
@Override
 public ArrayList<Book> booksByUser(String username) throws SQLException,RemoteException{
 // System.out.println("ClientBooksByUser");
    return remoteModel.booksByUser(username);
 }

  /**
   * remove a book from system
   * @param book specific book
   * @throws SQLException
   * @throws RemoteException
   */
 @Override public void removeBook(Book book) throws SQLException,RemoteException{
    remoteModel.removeBook(book);
 }

  /**
   * change book status of a specific book
   * @param book specific book
   * @param bool book status
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public void changeAvailable(Book book, boolean bool)
      throws SQLException, RemoteException
  {
    remoteModel.changeAvailable(book,bool );
  }

  /**
   *  Add comment to a specific book by a specific username
   *  @param comment comment text
   * @param BookID specific bookID
   * @param Username username of a user

   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void addComment(String BookID,String Username, String comment)
      throws SQLException, RemoteException
  {
    remoteModel.addComment(BookID,Username, comment);
  }

  /**
   * Getting all comments related to a specific bookID
   * @param BookID bookID of book
   * @return List of comments for this specific book
   * @throws RemoteException
   * @throws SQLException
   *
   * */
  @Override public ArrayList<String> getComments(String BookID)
          throws SQLException, RemoteException
  {
      return remoteModel.getComments(BookID);
  }

  /**
   * check if a specific username is in the system
   * @param username username of user
   * @return true if username is in the system otherwise return false
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public boolean checkUsername(String username)
      throws RemoteException, SQLException
  {
    return remoteModel.checkUsername(username);
  }

  /**
   * Update user information
   * @param actualUsername actual username
   * @param newUsername new username
   * @param passWord new password
   * @param eMail new email
   * @param firstName new first name
   * @param lastName new last name
   * @param city new city
   * @param phone new phone number
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public void update(String actualUsername, String newUsername,
      String passWord, String eMail, String firstName, String lastName,
      String city, String phone) throws SQLException, RemoteException
  {
    remoteModel.update(actualUsername,newUsername,passWord,eMail,firstName,lastName,city,phone);
  }

  @Override public void propertyChange(ObserverEvent<String, Book> event)
      throws RemoteException
  {
    System.out.println("CLIENT");
    System.out.println(event.getPropertyName());
    property.firePropertyChange(event.getPropertyName(),event.getValue1(),event.getValue2());
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