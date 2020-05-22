package model;

import mediator.Client;
import mediator.ClientModel;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeProxy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

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
    client.addListener(this,"book");
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

  @Override public ArrayList<Book> readByFilter(String filter, String value)
      throws SQLException, RemoteException
  {
    return client.readByFilter(filter,value);
  }

  @Override public ArrayList<Book> readByTwoFilters(String filter, String value,
      String filter1, String value1) throws SQLException, RemoteException
  {
    return client.readByTwoFilters(filter,value,filter1,value1);
  }

  @Override public ArrayList<Book> readByThreeFilters(String filter,
      String value, String filter1, String value1, String filter2,
      String value2) throws SQLException, RemoteException
  {
    return client.readByThreeFilters(filter,value,filter1,value1,filter2,value2);
  }

  @Override public ArrayList<Book> readByAllFilters(String title, String author,
      String language, String category) throws SQLException, RemoteException
  {
    return client.readByAllFilters(title,author,language,category);
  }

  @Override public void propertyChange(ObserverEvent<String, Book> event)
  {
    System.out.println("FIRe in Client Model");
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
   * @param contactInfo
   * @throws Exception
   */

  @Override public void registerUser(String Username, String passWord,
      String eMail, String firstName, String lastName, String city,
      String contactInfo) throws Exception
  {
    client.registerUser(Username,passWord,eMail,firstName,lastName,city,contactInfo);
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
    System.out.println(user);
    return client.getUser(username);
  }
}