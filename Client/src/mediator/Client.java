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
    remoteModel.addListener(this,"book");
  }

  @Override public boolean verifyPass(String password, String username)
      throws RemoteException
  {
    return remoteModel.verifyPass(password,username);
  }

  @Override public void registerUser(String Username, String passWord,
      String eMail, String firstName, String lastName, String city,
      String contactInfo) throws Exception
  {
    remoteModel.registerUser(Username,passWord,eMail,firstName,lastName,city,contactInfo);
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

  @Override public void propertyChange(ObserverEvent<String, Book> event)
      throws RemoteException
  {
    System.out.println("FIRe in Client");
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