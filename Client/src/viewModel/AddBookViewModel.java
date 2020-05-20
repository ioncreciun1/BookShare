package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import javax.print.DocFlavor;
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
  private StringProperty error;
  public AddBookViewModel(Model model)
  {

    ObservableList<String> languageList = FXCollections.observableArrayList();
    ObservableList<String> typeList = FXCollections.observableArrayList();
    languageList.addAll("Add Language","Danish","English","German","Romanian");
    typeList.addAll("Add Category","Drama","Action","Fiction","Adventure");
    this.model = model;
    this.language = new SimpleObjectProperty<>();
    this.type = new SimpleObjectProperty<>();
    this.title = new SimpleStringProperty("");
    this.author = new SimpleStringProperty("");
    this.description = new SimpleStringProperty("");
    language.setValue(languageList);
    this.error = new SimpleStringProperty("");
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

  public StringProperty errorProperty()
  {
    return error;
  }

  public void addBook(String type,String language)
      throws RemoteException, SQLException, InterruptedException
  {


    model.addBook(title.get(),author.get(),description.get(),language,type);
    error.set("The book have successfully added in the system");
  }
  public boolean checkBook(String type,String language)
  {
    if(title.get().length() == 0)
    {
      error.set("Title field can't be Empty. Please insert the Title");
      return false;
    }
    else if(author.get().length() == 0)
    {
      error.set("Author field can't be Empty. Please insert the Author");
      return false;
    }

    else if(language.equals("Add Language"))
    {
      error.set("Select Language from a list");
      return  false;
    }
    else if(type.equals("Add Category"))
    {
      error.set("Select Category from a list");
      return  false;
    }
      return true;

  }
  public void reset()
  {
    title.set("");
    author.set("");
  }
}
