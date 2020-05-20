package viewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.controllers.MainViewTableRowData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class MainViewModel implements LocalListener<String,Book>
{
  private Model model;
  private ObservableList<MainViewTableRowData> table;

  public MainViewModel(Model model)
  {
    this.model = model;
    table = createList();
    this.model.addListener(this,"book");
  }

  private synchronized ObservableList<MainViewTableRowData> createList()
  {
    ObservableList<MainViewTableRowData> obsList = FXCollections.observableArrayList();

    ArrayList<Book> books = new ArrayList<>();
    for (int i = 0; i < 99; i++) // Something should be instead of 99
    {
      //temp.add(); // should be a book i guess
    }
    for (int i = 0; i < books.size(); i++)
    {
      obsList.add(new MainViewTableRowData(books.get(i)));
    }
    return obsList;
  }

  public ObservableList<MainViewTableRowData> getTable()
  {
    return table;
  }

//  @Override public void propertyChange(PropertyChangeEvent evt)
//  {
//    Platform.runLater(()->{
//      switch (evt.getPropertyName()){
//        case "Book" :
//          addToTheList((Book)evt.getNewValue()); break;
//        default: break;
//
//      }
//    });
//  }
  private void addToTheList(Book book)
  {
    table.add(new MainViewTableRowData(book));
  }

  @Override public void propertyChange(ObserverEvent<String, Book> event)
  {
Platform.runLater(()->{
  System.out.println("This is happening");
  addToTheList(event.getValue2());
});
  }
}
