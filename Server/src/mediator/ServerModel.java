package mediator;

import model.Book;
import model.Model;
import model.User;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A class representing the server
 */
public class ServerModel implements RemoteModel, LocalListener<String,Book>
{

  private Model model;
  private PropertyChangeProxy<String,Book> property;
  private String user;


  /**
   * One argument Constructor that initiate all variables and start Registry and Server
   *
   * @param model
   * the model from server Module
   */
  public ServerModel(Model model)
      throws MalformedURLException, RemoteException, SQLException
  {
    this.property = new PropertyChangeProxy<>(this, true);
    this.model = model;
    model.addListener(this,"book","change");
    startRegistry();
    startServer();
  }

  /**
  *Starting the registry by Creating a registry with port 1099 and
   *  print out in console a message if this registry is already started
   */
  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);

    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("Registry already started?" + " " + e.getMessage());
    }
  }

  /**
   *Starting the server by using UnicastRemoteObject and export this object
   * Give the name of this specific server "Book"
   */
  private void startServer()
      throws RemoteException, MalformedURLException, SQLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Book", this);

    System.out.println("Server started...");
  }

  /**
   * Closing the server by stop exporting the server
   */
  public void close()
  {
    try
    {
      UnicastRemoteObject.unexportObject(this, true);
    }
    catch (Exception e) {/*nothing*/ }
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
    System.out.println("SERVER MODEL");
    model.registerUser(Username,passWord,eMail,firstName,lastName,city,phone);
  }

  /**
   * Get a user with this username
   * @param username name of user
   * @return User from a database
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public User getUser(String username)
      throws RemoteException, SQLException
  {
    return model.getUser(username);
  }

  /**
   * Check if Username of this user is in the system
   * @param registrant User
   * @return true if user with this username is in the system, otherwise return false
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public boolean checkUser(User registrant)
      throws RemoteException, SQLException
  {
    System.out.println("Check server");
    return model.checkUser(registrant);
  }

  /**
   * Check if this user email is in the system
   * @param registrant User
   * @return true if user with this email is already in the system, otherwise return false
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public boolean checkEmail(User registrant)
      throws RemoteException, SQLException
  {
    return model.check_Email(registrant);
  }

  /**
   * Add a book to the system
   * @param book a simple book
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void addBook(Book book) throws RemoteException, SQLException
  {
    System.out.println("Server");
    model.addBook(book);
  }



  /**
   * Getting all books from the system
   * @return all books from database
   * @throws SQLException
   */
  @Override public ArrayList<Book> allBooks() throws SQLException
  {
    return model.allBooks();
  }

  /**
   * Getting all books by a filter and specific value
   * @param filter filter for searching for a book
   * @param value value of the filter
   * @return An list of books after searching by this filter
   * @throws SQLException
   */
  @Override public ArrayList<Book> readByFilter(String filter, String value)
      throws SQLException
  {
    return model.readByFilter(filter,value);
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
      String filter1, String value1) throws SQLException
  {
    return model.readByTwoFilters(filter,value,filter1,value1);
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
      String value2) throws SQLException
  {
    return model.readByThreeFilters(filter,value,filter1,value1,filter2,value2);
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
      String language, String category) throws SQLException
  {
    return model.readByAllFilters(title,author,language,category);
  }

  /**
   * Getting a list of books by username
   * @param username username of a specific user
   * @return An list of books that have this specific username
   * @throws SQLException
   * @throws RemoteException
   */
  @Override
 public ArrayList<Book> booksByUser(String username) throws SQLException,RemoteException
  {
    System.out.println("BooksByUserServerModel");
    return model.booksByUser(username);
  }

  /**
   * remove a book from system
   * @param book specific book
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public void removeBook(Book book) throws SQLException,RemoteException
  {
    model.removeBook(book);
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
    model.changeAvailable(book, bool);
  }

  /**
   * Add comment to a specific book
   * @param comment comment text
   * @throws SQLException
   */
  @Override public void addComment(String BookID, String Username, String comment) throws RemoteException,SQLException
  {
    model.add(BookID, Username, comment);
  }

  @Override public void propertyChange(ObserverEvent<String, Book> event)
  {
    property.firePropertyChange(event.getPropertyName(),event.getValue1(),event.getValue2());
  }

  @Override public boolean addListener(GeneralListener<String, Book> listener,
      String... propertyNames) throws RemoteException
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, Book> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener,propertyNames);
  }
}
