package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class BookInfoViewModel
{
  private Model model;
  public StringProperty title;
  public StringProperty authorName;
  public StringProperty language;
  public StringProperty category;
  public StringProperty ownerName;
  public StringProperty phoneNumber;
  public StringProperty email;
  public StringProperty description;

  public BookInfoViewModel(Model model)
  {
    this.model = model;
    this.title = new SimpleStringProperty("");
    this.authorName = new SimpleStringProperty("");
    this.language = new SimpleStringProperty("");
    this.category = new SimpleStringProperty("");
    this.ownerName = new SimpleStringProperty("");
    this.phoneNumber = new SimpleStringProperty("");
    this.email = new SimpleStringProperty("");
    this.description = new SimpleStringProperty("");
  }

  public StringProperty titleProperty()
  {
    return title;
  }

  public StringProperty authorNameProperty()
  {
    return authorName;
  }

  public StringProperty categoryProperty()
  {
    return category;
  }

  public StringProperty descriptionProperty()
  {
    return description;
  }

  public StringProperty emailProperty()
  {
    return email;
  }

  public StringProperty languageProperty()
  {
    return language;
  }

  public StringProperty ownerNameProperty()
  {
    return ownerName;
  }

  public StringProperty phoneNumberProperty()
  {
    return phoneNumber;
  }
}
