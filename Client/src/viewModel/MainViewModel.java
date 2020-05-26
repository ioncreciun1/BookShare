package viewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
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

  public MainViewModel(Model model) throws SQLException, RemoteException
  {
    this.model = model;
    table = createList();
    this.model.addListener(this,"book");
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
  System.out.println("This is happening");
  addToTheList(event.getValue2());
});
  }
}
