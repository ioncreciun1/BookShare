package model;

import mediator.Client;
import mediator.ClientModel;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ModelManager implements Model, LocalListener<String,String>
{

  ClientModel client;
  public ModelManager()
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.client = new Client(this,"localhost");
  }
  @Override public void propertyChange(ObserverEvent<String, String> event)
  {

  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    return false;
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return false;
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

  @Override public String getUsers() throws RemoteException
  {
    return null;
  }
}
