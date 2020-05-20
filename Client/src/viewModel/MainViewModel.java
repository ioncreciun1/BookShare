package viewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Model;
import view.controllers.TableRowData;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.util.ArrayList;

public class MainViewModel implements LocalListener<String,Book>
{
  private Model model;
  private ObservableList<TableRowData> table;

  public MainViewModel(Model model)
  {
    this.model = model;
    table = createList();
    this.model.addListener(this,"book");
  }

  private synchronized ObservableList<TableRowData> createList()
  {
    ObservableList<TableRowData> obsList = FXCollections.observableArrayList();

    ArrayList<Book> books = new ArrayList<>();
    for (int i = 0; i < 99; i++) // Something should be instead of 99
    {
      //temp.add(); // should be a book i guess
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
