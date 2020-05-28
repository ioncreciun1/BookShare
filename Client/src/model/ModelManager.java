package model;

import mediator.Client;
import mediator.ClientModel;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Is a class representing ModelManager for Client
 */
public class ModelManager implements Model, LocalListener<String,Book>
{
  private ClientModel client;
  private String user;
  private PropertyChangeProxy<String,Book> property;

  /**
   * No argument constructor
   * @throws RemoteException
   * @throws NotBoundException
   * @throws MalformedURLException
   */
  public ModelManager()
      throws RemoteException, NotBoundException, MalformedURLException
  {
    try
    {
      this.client = new Client(this, "localhost");
    }catch (Exception e)
    {
      e.getMessage();
    }
    property = new PropertyChangeProxy<>(this);
    client.addListener(this,"book","change");
  }

  /**
   * A method that checks if username is shorter than 8 characters
   * @param username
   * @return true if username fits the requirements  is in the system otherwise returns false
   */

  public boolean checkUsername(String username)
  {
    return ( username.length() < 8 ) || ( username.length() > 30 );
  }

  /**
   * A method that checks if password is shorter than 6 characters or longer than 20
   * @param password
   * @return true if password fits the requirements  is in the system otherwise returns false
   */

  public boolean checkPassword(String password)
  {
    return ( password.length() < 6 ) || ( password.length() > 20 );
  }

  /**
   * A method that is checking if user already exists in database
   * @param user
   * @return true if username is in the system otherwise returns false
   * @throws RemoteException
   */

  @Override public boolean checkUser(User user)
      throws RemoteException
  {
    return client.checkUser(user);
  }

  /**
   * A method that is checking if email already exists in database
   * @param user
   * @return true if email is in the system otherwise returns false
   * @throws RemoteException
   */
  @Override public boolean checkEmail(User user)
      throws RemoteException
  {
    return client.checkEmail(user);
  }

  /**
   * A method that return a list of books from database
   * @return ArrayList of Books from database
   * @throws SQLException
   * @throws RemoteException
   */

  @Override public ArrayList<Book> allBooks()
      throws SQLException, RemoteException
  {
    return client.allBooks();
  }

  /**
   * A Method that adds a book to system
   * @param title
   * @param author
   * @param description
   * @param language
   * @param category
   * @throws RemoteException
   * @throws SQLException
   */

  @Override public void addBook(String title,String author,String description,String language,String category) throws RemoteException, SQLException
  {
    Book book = new Book(user,title,author,language,description,category);
    client.addBook(book);
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
   * @throws RemoteException
   */
  @Override public ArrayList<Book> readByFilter(String filter, String value)
      throws SQLException, RemoteException
  {
    return client.readByFilter(filter,value);
  }

  /**
   *get a list of books that match the search criteria
   *  @param filter
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
      String filter1, String value1) throws SQLException, RemoteException
  {
    return client.readByTwoFilters(filter,value,filter1,value1);
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
      String value2) throws SQLException, RemoteException
  {
    return client.readByThreeFilters(filter,value,filter1,value1,filter2,value2);
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
      String language, String category) throws SQLException, RemoteException
  {
    return client.readByAllFilters(title,author,language,category);
  }

  /**
   *

   * @return
   *          list of books from database for this specific user
   * @throws SQLException if null or error
   * @throws RemoteException if error
   */
@Override
  public ArrayList<Book> booksByUser()
      throws SQLException, RemoteException
  {

    return client.booksByUser(user);
  }

  /**
   * remove a book from system
   * @param book
   *        is a book selected from a list
   * @throws SQLException if null or error
   */
  @Override
  public void removeBook(Book book) throws SQLException,RemoteException{
    client.removeBook(book);
  }

  /**
   * change book status to available
   * @param book specific book
   */
  @Override public void setAvailable(Book book)
  {
    book.setAvailable();
  }

  /**
   * change book status to borrowed
   * @param book specific book
   */
  @Override public void setBorrowed(Book book)
  {
    book.setBorrowed();
  }

  /**
   * Change book status and prompt that change to server
   * @param book specific book
   * @param bool book status
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public void changeAvailable(Book book, boolean bool)
      throws SQLException, RemoteException
  {
    client.changeAvailable(book, bool);
  }

  /**
   * Add comment to a specific book
   * @param book specific book
   * @param comment comment text
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public void addComment(Book book, String comment)
      throws SQLException, RemoteException
  {
    client.addComment(book.getBookID(),user, comment);
  }

  @Override public ArrayList<String> getComments(String BookID)
          throws SQLException, RemoteException
  {
    return client.getComments(BookID);
  }

  @Override public void propertyChange(ObserverEvent<String, Book> event)
  {
    System.out.println("CLIENT MODEL");
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

  /**
   * A method that registers the user into the system
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
    client.registerUser(Username,passWord,eMail,firstName,lastName,city,phone);
  }

  /**
   * A method that returns an User
   * @return an user
   */

  public String getUser()
  {
    return user;
  }

  /**
   * a method that returns an User based on Username
   * @param username
   * @return User
   * @throws RemoteException
   */
  @Override public User getUser(String username) throws RemoteException
  {
    this.user = username;
  //  System.out.println(user);
    return client.getUser(username);
  }
}