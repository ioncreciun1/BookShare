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

public class ModelManager implements Model, LocalListener<String,Book>
{

  private ClientModel client;
  private String user;
  private PropertyChangeProxy<String,Book> property;
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
  public boolean checkUsername(String username)
  {
    return ( username.length() < 8 ) || ( username.length() > 30 );
  }

  public boolean checkPassword(String password)
  {
    return ( password.length() < 6 ) || ( password.length() > 20 );
  }

  @Override public boolean checkUser(User user)
      throws RemoteException
  {
    return client.checkUser(user);
  }

  @Override public boolean checkEmail(User user)
      throws RemoteException
  {
    return client.checkEmail(user);
  }

  @Override public ArrayList<Book> allBooks()
      throws SQLException, RemoteException
  {
    return client.allBooks();
  }

  @Override public void addBook(String title,String author,String description,String language,String category) throws RemoteException, SQLException
  {
    Book book = new Book(user,title,author,language,description,category);
    client.addBook(book);
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

  @Override public boolean verifyLog(String password, String name)
      throws IOException
  {
    return false;
  }

  @Override public void registerUser(String Username, String passWord,
      String eMail, String firstName, String lastName, String city,
      String contactInfo) throws Exception
  {


    client.registerUser(Username,passWord,eMail,firstName,lastName,city,contactInfo);
  }

  public String getUser()
  {
    return user;
  }

  @Override public String getUsers() throws RemoteException
  {
    return null;
  }

  @Override public User getUser(String username) throws RemoteException
  {
    this.user = username;
    System.out.println(user);
    return client.getUser(username);
  }
}