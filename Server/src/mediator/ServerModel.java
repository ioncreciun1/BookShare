package mediator;

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

public class ServerModel implements RemoteModel, LocalListener<String,String>
{

  private Model model;
  private PropertyChangeProxy<String,String> property;
  private String user;

  public ServerModel(Model model) throws MalformedURLException, RemoteException
  {
    this.property = new PropertyChangeProxy<>(this, true);
    this.model = model;
    model.addListener(this,"idk what sould be here","add");
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

  private void startServer() throws RemoteException, MalformedURLException
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

  @Override public void getRegistrant(String username)
      throws RemoteException, SQLException
  {
    model.getRegistrant(username);
  }

  @Override public boolean checkUser(User registrant)
      throws RemoteException, SQLException
  {
    System.out.println("CHeck server");
    return model.checkUser(registrant);
  }

  @Override public boolean checkEmail(User registrant)
      throws RemoteException, SQLException
  {
    return model.check_Email(registrant);
  }

  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    property.firePropertyChange(event.getPropertyName(),event.getValue1(),event.getValue2());
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener,propertyNames);
  }
}
