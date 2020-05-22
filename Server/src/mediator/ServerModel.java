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

public class ServerModel implements RemoteModel, LocalListener<String,Book>
{

  private Model model;
  private PropertyChangeProxy<String,Book> property;
  private String user;

  public ServerModel(Model model)
      throws MalformedURLException, RemoteException, SQLException
  {
    this.property = new PropertyChangeProxy<>(this, true);
    this.model = model;
    model.addListener(this,"book");
    startRegistry();
    startServer();
  }

  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
//      model.addLog("Registry started... ");
      System.out.println("Registry started...");
    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("Registry already started?" + " " + e.getMessage());
//      model.addLog("Registry already started?" + " " + e.getMessage());
    }
  }

  private void startServer()
      throws RemoteException, MalformedURLException, SQLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Book", this);
//    model.addLog("Server started");
    System.out.println("Server started...");
  }

  public void close()
  {
    try
    {
      UnicastRemoteObject.unexportObject(this, true);
    }
    catch (Exception e) {/*nothing*/ }
  }

  @Override public boolean verifyPass(String password, String username)
      throws RemoteException
  {
    return false; // make it functional
  }

  @Override public void registerUser(String Username, String passWord,
      String eMail, String firstName, String lastName, String city,
      String contactInfo) throws Exception
  {
    System.out.println("SERVER MODEL");
    model.registerUser(Username,passWord,eMail,firstName,lastName,city,contactInfo);
  }

  @Override public User getUser(String username)
      throws RemoteException, SQLException
  {
    return model.getUser(username);
  }

  @Override public boolean checkUser(User registrant)
      throws RemoteException, SQLException
  {
    System.out.println("Check server");
    return model.checkUser(registrant);
  }

  @Override public boolean checkEmail(User registrant)
      throws RemoteException, SQLException
  {
    return model.check_Email(registrant);
  }

  @Override public void addBook(Book book) throws RemoteException, SQLException
  {
    System.out.println("Server");
    model.addBook(book);
  }

  @Override public ArrayList<Book> allBooks() throws SQLException
  {
    return model.allBooks();
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
