package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Model;
import model.User;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;

public class BookInfoViewModel
{
  private Model model;
  private Book book;
  public StringProperty title;
  public StringProperty authorName;
  public StringProperty language;
  public StringProperty category;
  public StringProperty ownerName;
  public StringProperty phoneNumber;
  public StringProperty email;
  public StringProperty description;
  public ObservableList<String> comments;

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

  public void setBook(Book book) throws RemoteException, SQLException
  {
    this.book = book;
    this.titleProperty().setValue(book.getTitle());
    this.authorNameProperty().setValue(book.getAuthor());
    this.languageProperty().setValue(book.getLanguage());
    this.categoryProperty().setValue(book.getCategory());
    User owner = model.getUser(book.getUsername());
    this.ownerNameProperty().setValue(owner.getName()+" "+owner.getLastName());
    this.phoneNumberProperty().setValue(owner.getphone());
    this.emailProperty().setValue(owner.getEMail());
    this.descriptionProperty().setValue(book.getDescription());
    this.comments = FXCollections.observableArrayList();
    setComments();
  }

  public void setComments() throws RemoteException, SQLException {
    for(int i=0;i<model.getComments(book.getBookID()).size();i++)
    {
    comments.add(model.getComments(book.getBookID()).get(i));
    }
  }

  public Book getBook()
  {
    return book;
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

  public ObservableList<String> getCommentsHash(){
    return comments;
  }
}
