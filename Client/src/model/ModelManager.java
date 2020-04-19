package model;

import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;

import java.io.IOException;
import java.rmi.RemoteException;

public class ModelManager implements Model, LocalListener<String,String>
{


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

  @Override public String getUsers() throws RemoteException
  {
    return null;
  }
}
