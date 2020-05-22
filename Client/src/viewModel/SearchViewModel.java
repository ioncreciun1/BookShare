package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Model;
import view.controllers.TableRowData;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchViewModel
{
  private Model model;
  private ObservableList<TableRowData> table;
  private StringProperty title;
  private StringProperty author;
  private StringProperty description;
  private ObjectProperty<ObservableList> language;
  private ObjectProperty<ObservableList> type;
  private StringProperty error;

  public SearchViewModel(Model model)
  {
    this.model = model;
    table = createList();
    ObservableList<String> languageList = FXCollections.observableArrayList();
    ObservableList<String> typeList = FXCollections.observableArrayList();
    languageList.addAll("Click to choose Language","Danish","English","German","Romanian","Chinese","Spanish", "Arabic", "Russian",
        "Portuguese", "Japanese", "French", "Turkish", "Italian", "Polish","Ukrainian", "Other");
    typeList.addAll("Click to choose Category","Drama","Action","Literary Fiction","Adventure","Classics", "Comic Book","Detective","Fantasy",
        "Historical", "Horror", "Romance", "Science Fiction", "Cookbooks", "Essays","Memoir", "Poetry", "Other");
    this.language = new SimpleObjectProperty<>();
    this.type = new SimpleObjectProperty<>();
    this.title = new SimpleStringProperty("");
    this.author = new SimpleStringProperty("");
    this.description = new SimpleStringProperty("");
    language.setValue(languageList);
    this.error = new SimpleStringProperty("");
    type.setValue(typeList);
  }

  public void reset()
  {
    title.set("");
    author.set("");
  }

  private synchronized ObservableList<TableRowData> createList()
  {
    ObservableList<TableRowData> obsList = FXCollections.observableArrayList();

    ArrayList<Book> books = new ArrayList<>();
    for (int i = 0; i < 99; i++) // Something should be instead of 99
    {

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

  public ObjectProperty<ObservableList> languageProperty()
  {
    return language;
  }

  public ObjectProperty<ObservableList> typeProperty()
  {
    return type;
  }

  public StringProperty authorProperty()
  {
    return author;
  }

  public StringProperty titleProperty()
  {
    return title;
  }

  public StringProperty errorProperty()
  {
    return error;
  }
  private void addToTheList(Book book)
  {
    table.add(new TableRowData(book));
  }

  public void searchBook(String type,String language) throws SQLException, RemoteException
  {

  String filter = "";
  String value = "";
  boolean empty = true;
  if(type.equals("Click to choose Category")
      && language.equals("Click to choose Language")
      && author.get().length() ==0
      && title.get().length() == 0

  )
  {
    error.set("Search by a specific field");
    empty = false;
  }
  else if(
      type.equals("Click to choose Category")
          && language.equals("Click to choose Language")
          && author.get().length() ==0
  )
  {
    filter = "title";
    value = title.get();
  }
  else if(
      type.equals("Click to choose Category")
          && language.equals("Click to choose Language")
          && title.get().length() ==0
  )
  {
    filter = "author";
    value = author.get();
  }
  else if(
      type.equals("Click to choose Category")
          && author.get().length() ==0
          && title.get().length() ==0
  )
  {
    filter = "booklanguage";
    value = language;
  }
  else if(
      language.equals("Click to choose Language")
          && author.get().length() ==0
          && title.get().length() ==0
  )
  {
    filter = "category";
    value = type;
  }


    System.out.println(empty);
  if(empty)
  {
    ArrayList<Book> books = model.readByFilter(filter, value);

    if (books.size() > 0)
    {
      int size = books.size();
      for (int i = 0; i < size; i++)
      {
        addToTheList(books.get(i));
      }
    }
    else
    {
      error.set("There are no books that match your search.");
    }
  }

  }

}
