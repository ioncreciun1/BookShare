package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class LogViewModel implements LocalListener<String, Book>
{
  private Model model;
  private ObservableList<String> logs;

  public LogViewModel(Model model)
  {
    this.model = model;
    this.model.addListener(this,"book");
    logs = FXCollections.observableArrayList();
  }

  public ObservableList<String> getLogs()
  {
    return logs;
  }



  @Override public void propertyChange(ObserverEvent<String, Book> event)
  {
    Platform.runLater(() -> {
      System.out.println("I am here");
      logs.add(0, event.getValue2() + "");
    });
  }
}
