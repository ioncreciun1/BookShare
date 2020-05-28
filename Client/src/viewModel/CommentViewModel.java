package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.collections.ObservableList;
import model.Book;
import model.Model;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class CommentViewModel
{
  private Model model;
  private StringProperty comment;
  private StringProperty error;

  public CommentViewModel(Model model)
  {
    this.model = model;
    this.comment = new SimpleStringProperty("");
    this.error = new SimpleStringProperty("");
  }

  public StringProperty getCommentProperty()
  {
    return comment;
  }

  public StringProperty getError()
  {
    return error;
  }

  public void reset()
  {
    comment.set("");
  }


  public boolean checkComment()
  {
    if(comment.get().length() >= 200)
    {
      error.set("Field Description can't be longer than 200 characters");
      return false;
    }
    return true;
  }

  public void addComment(Book book, String comment)
      throws SQLException, RemoteException
  {
    model.addComment(book, comment);
  }
}
