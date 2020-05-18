package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.User;

import java.io.IOException;

public class LogInViewModel
{
  private Model model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty error;

  public LogInViewModel(Model model)
  {
    this.model = model;
    this.username = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
    this.error = new SimpleStringProperty("");
  }

  public StringProperty errorProperty()
  {
    return error;
  }

  public StringProperty passwordProperty()
  {
    return password;
  }

  public StringProperty usernameProperty()
  {
    return username;
  }


  public boolean verifyUser() throws IOException
  {
    User user =  model.getUser(username.get());
    if(user!= null)
    {
      if(user.getPassWord().equals(password.get()))
      {
        error.set("");
        return true;

      }
      else {
        error.set("Wrong password. Insert Again");
      return false;
      }
    }
    else
    {
      error.set("Wrong username. Insert Again");
      return false;
    }
  }
}