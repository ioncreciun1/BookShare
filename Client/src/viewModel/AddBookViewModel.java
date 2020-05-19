package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class AddBookViewModel
{
  private Model model;
  private StringProperty title;
  private StringProperty author;
  private StringProperty description;
  private ObjectProperty<ObservableList> language;
  private ObjectProperty<ObservableList> type;
  public AddBookViewModel(Model model)
  {
    ObservableList<String> languageList = FXCollections.observableArrayList();
    ObservableList<String> typeList = FXCollections.observableArrayList();
    languageList.addAll("Danish","English","German","Romanian");
    typeList.addAll("Drama","Action","Fiction","Adventure");
    this.model = model;
    this.language = new SimpleObjectProperty<>();
    this.type = new SimpleObjectProperty<>();
    this.title = new SimpleStringProperty("");
    this.author = new SimpleStringProperty("");
    this.description = new SimpleStringProperty("");
    language.setValue(languageList);
    type.setValue(typeList);
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

  public void addBook(String type,String language)
      throws RemoteException, SQLException
  {
    model.addBook(title.get(),author.get(),description.get(),language,type);

  }
  public void reset()
  {
  }
}
