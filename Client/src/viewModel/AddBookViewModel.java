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
  private StringProperty error;
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
    this.error = new SimpleStringProperty("");
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

  public boolean validate(String bookLanguage, String bookType){
    if(title.get().length() == 0){
      error.set("Field Title can’t be empty");
    }else if(author.get().length() == 0){
      error.set("Field Author can’t be empty");
    }else if(bookLanguage.length() == 0){
      error.set("Field Language can’t be empty");
    }else if(description.get().length() == 0){
      error.set("Field Description can’t be empty");
    }else if(bookType.length() == 0){
      error.set("Field Category can’t be empty");
    }else if(title.get().length() >= 120){
      error.set("Field Title can't be longer than 120 characters");
    }else if(author.get().length() >= 50){
      error.set("Field Title can't be longer than 50 characters");
    }else if(bookLanguage.length() >= 25){
      error.set("Field Title can't be longer than 25 characters");
    }else if(description.get().length() >= 200){
      error.set("Field Title can't be longer than 200 characters");
    }else if(bookType.length() >= 30){
      error.set("Field Title can't be longer than 30 characters");
    }

    return title.getValue().length() != 0 && author.get().length() != 0 && bookLanguage.length() != 0 && description.get().length() != 0
            && bookType.length() != 0 && !(title.get().length() >= 120) && !(author.get().length() >= 50) &&
            !(bookLanguage.length() >= 25) && !(description.get().length() >= 200) && !(bookType.length() >= 30);
  }
}
