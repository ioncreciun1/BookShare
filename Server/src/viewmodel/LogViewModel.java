package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class LogViewModel implements LocalListener<String,String>
{
  private Model model;
  private ObservableList<String> logs;

  public LogViewModel(Model model)
  {
    this.model = model;
    this.model.addListener(this,"add");
    logs = FXCollections.observableArrayList();
  }

  public ObservableList<String> getLogs()
  {
    return logs;
  }



  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    Platform.runLater(() -> {
      logs.add(0, event.getValue2() + "");
    });
  }
}
