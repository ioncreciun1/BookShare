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

public class Client implements ClientModel, RemoteListener<String,Book>
{

  public static final String HOST = "localhost";
  private String host;
  private Model model;
  private RemoteModel remoteModel;
  private PropertyChangeProxy<String,Book> property;

  public Client(Model model,String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = model;
    this.host = host;
    this.remoteModel = (RemoteModel) Naming.lookup("rmi://" + host + ":1099/Book");
    UnicastRemoteObject.exportObject(this, 0);
    this.property = new PropertyChangeProxy<>(this);
    remoteModel.addListener(this,"book","change");
  }

  @Override public boolean verifyPass(String password, String username)
      throws RemoteException
  {
    return remoteModel.verifyPass(password,username);
  }

  @Override public void registerUser(String Username, String passWord,
      String eMail, String firstName, String lastName, String city,
      String phone) throws Exception
  {
    remoteModel.registerUser(Username,passWord,eMail,firstName,lastName,city,phone);
  }

  @Override public User getUser(String username) throws RemoteException
  {
    return remoteModel.getUser(username);
  }

  @Override public boolean checkUser(User user)
      throws RemoteException
  {
    return remoteModel.checkUser(user);
  }

  @Override public boolean checkEmail(User user)
      throws RemoteException
  {
    return remoteModel.checkEmail(user);
  }

  @Override public void addBook(Book book) throws RemoteException, SQLException
  {
    remoteModel.addBook(book);
  }

  @Override public ArrayList<Book> allBooks()
      throws SQLException, RemoteException
  {

    return remoteModel.allBooks();
  }

  @Override public ArrayList<Book> readByFilter(String filter, String value)
      throws SQLException, RemoteException
  {
    return remoteModel.readByFilter(filter,value);
  }

  @Override public ArrayList<Book> readByTwoFilters(String filter, String value,
      String filter1, String value1) throws SQLException, RemoteException
  {
    return remoteModel.readByTwoFilters(filter,value,filter1,value1);
  }

  @Override public ArrayList<Book> readByThreeFilters(String filter,
      String value, String filter1, String value1, String filter2,
      String value2) throws SQLException, RemoteException
  {
    return remoteModel.readByThreeFilters(filter,value,filter1,value1,filter2,value2);
  }

  @Override public ArrayList<Book> readByAllFilters(String title, String author,
      String language, String category) throws SQLException, RemoteException
  {
    return remoteModel.readByAllFilters(title,author,language,category);
  }

@Override
 public ArrayList<Book> booksByUser(String username) throws SQLException,RemoteException{
 // System.out.println("ClientBooksByUser");
    return remoteModel.booksByUser(username);
 }

 @Override public void removeBook(Book book) throws SQLException,RemoteException{
    remoteModel.removeBook(book);
 }

  @Override public void changeAvailable(Book book, boolean bool)
      throws SQLException, RemoteException
  {
    remoteModel.changeAvailable(book,bool );
  }

  @Override public void addComment(String BookID,String Username, String comment)
      throws SQLException, RemoteException
  {
    remoteModel.addComment(BookID,Username, comment);
  }

  @Override public ArrayList<String> getComments(String BookID)
          throws SQLException, RemoteException
  {
      return remoteModel.getComments(BookID);
  }

  @Override public boolean checkUsername(String username)
      throws RemoteException, SQLException
  {
    return remoteModel.checkUsername(username);
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