package mediator;

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

public class Client implements ClientModel, RemoteListener<String,String>
{

  public static final String HOST = "localhost";
  private String host;
  private Model model;
  private RemoteModel remoteModel;
  private PropertyChangeProxy<String,String> property;

  public Client(Model model,String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = model;
    this.host = host;
    this.remoteModel = (RemoteModel) Naming.lookup("rmi://" + host + ":1099/Book");
    UnicastRemoteObject.exportObject(this, 0);
    this.property = new PropertyChangeProxy<>(this);
    remoteModel.addListener(this,"idk what have to be here");
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

  @Override public void getRegistrant(String username) throws RemoteException
  {
    remoteModel.getRegistrant(username);
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

  @Override public void propertyChange(ObserverEvent<String, String> event)
      throws RemoteException
  {
    property.firePropertyChange(event);
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.removeListener(listener,propertyNames);
  }
}