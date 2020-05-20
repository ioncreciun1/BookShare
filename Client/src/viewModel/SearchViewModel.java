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
    languageList.addAll("Danish","English","German","Romanian","Chinese","Spanish", "Arabic", "Russian",
        "Portuguese", "Japanese", "French", "Turkish", "Italian", "Polish","Ukrainian", "Other");
    typeList.addAll("Drama","Action","Literary Fiction","Adventure","Classics", "Comic Book","Detective","Fantasy",
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

  public StringProperty descriptionProperty()
  {
    return description;
  }

  public StringProperty errorProperty()
  {
    return error;
  }

}
