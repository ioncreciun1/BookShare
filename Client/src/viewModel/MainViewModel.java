package viewModel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import model.Book;
import model.Model;
import view.controllers.TableRowData;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainViewModel implements LocalListener<String,Book>
{
  private Model model;
  private ObservableList<TableRowData> table;
private ObjectProperty<ObservableList<TableRowData>> tableProperty;

  public MainViewModel(Model model) throws SQLException, RemoteException
  {
    this.model = model;
    table = createList();
    tableProperty = new SimpleObjectProperty<>();
    tableProperty.setValue(table);
    this.model.addListener(this,"book","change");
  }

  public ObjectProperty<ObservableList<TableRowData>> tablePropertyProperty()
  {
    return tableProperty;
  }

  public synchronized ObservableList<TableRowData> createList()
      throws SQLException, RemoteException
  {
    int size = 0;
    if(model.allBooks().size()>20)
    {
      size = 20;
    }
    else  size = model.allBooks().size();
    ObservableList<TableRowData> obsList = FXCollections.observableArrayList();
    ArrayList<Book> books = new ArrayList<>();
    for (int i = 0; i < size; i++) // Something should be instead of 99
    {


      books.add(model.allBooks().get(i)); // should be a book i guess
    }
    for (int i = 0; i < books.size(); i++)
    {

      obsList.add(new TableRowData(books.get(i)));
    }
    return obsList;
  }

  public ObservableList<TableRowData> getTable()
  {
    return table;
  }

  private void addToTheList(Book book)
  {
    table.add(new TableRowData(book));
  }



  @Override public void propertyChange(ObserverEvent<String, Book> event)
  {
Platform.runLater(()->{
  System.out.println("View Model");
  System.out.println(event.getPropertyName());
  switch (event.getPropertyName())
  {
    case "book":
      addToTheList(event.getValue2());
      break;
    case "change" :

      try
      {
        table.clear();
        table = createList();
        tableProperty.setValue(table);


      }
      catch (SQLException | RemoteException e)
      {
        e.printStackTrace();
      }
      break;

  }
});
  }
}
