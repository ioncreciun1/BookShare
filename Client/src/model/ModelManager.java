package model;

import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;

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
}
