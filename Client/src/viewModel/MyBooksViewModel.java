package viewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.controllers.TableRowData;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyBooksViewModel implements LocalListener<String,Book>
{
  private Model model;
  private ObservableList<TableRowData> table;


  public MyBooksViewModel(Model model) throws SQLException, RemoteException
  {

    this.model = model;
    table = createList();
    this.model.addListener(this,"book");
  }
  public synchronized ObservableList<TableRowData> createList()
      throws SQLException, RemoteException
  {

    ArrayList<Book> books = model.booksByUser();

    ObservableList<TableRowData> obsList = FXCollections.observableArrayList();

    for (int i = 0; i < books.size(); i++)
    {
      obsList.add(new TableRowData(books.get(i)));
    }
    table = obsList;
    return obsList;
  }

  public synchronized void availableList()
      throws SQLException, RemoteException
  {
    ArrayList<Book> available = new ArrayList<>();
    for(int i=0; i < model.booksByUser().size(); i++){
      if(model.booksByUser().get(i).available()){
        available.add(model.booksByUser().get(i));
      }
    }
    int size = 0;
    if(available.size()>20)
    {
      size = 20;
    }
    else  size = available.size();
    ObservableList<TableRowData> obsList = FXCollections.observableArrayList();
    ArrayList<Book> books = new ArrayList<>();
    for (int i = 0; i < size; i++)
    {
      books.add(available.get(i));
    }
    for (int i = 0; i < books.size(); i++)
    {
      obsList.add(new TableRowData(books.get(i)));
    }
    table = obsList;;
  }

  public synchronized void borrowedList()
      throws SQLException, RemoteException
  {
    ArrayList<Book> borrowed = new ArrayList<>();
    for(int i=0; i < model.booksByUser().size(); i++){
      if(model.booksByUser().get(i).available() == false){
        borrowed.add(model.booksByUser().get(i));
      }
    }
    int size = 0;
    if(borrowed.size()>20)
    {
      size = 20;
    }
    else  size = borrowed.size();
    ObservableList<TableRowData> obsList = FXCollections.observableArrayList();
    ArrayList<Book> books = new ArrayList<>();
    for (int i = 0; i < size; i++)
    {
        books.add(borrowed.get(i));
    }
    for (int i = 0; i < books.size(); i++)
    {
      obsList.add(new TableRowData(books.get(i)));
    }
    table = obsList;

  }

  public ObservableList<TableRowData> getTable()
  {
    return table;
  }

  private void addToTheList(Book book)
  {
    table.add(new TableRowData(book));
  }

  public void removeBook(Book book)  throws SQLException,RemoteException{
    model.removeBook(book);
  }

  public void changeAvailable(Book book, boolean bool) throws SQLException,RemoteException{
    model.changeAvailable(book, bool);
    table.clear();
    createList();
  }

  @Override public void propertyChange(ObserverEvent<String, Book> event)
  {

  }
}
