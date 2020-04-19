package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

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


  public boolean verifyPass() throws IOException
  {
    if(model.verifyLog(password.get(),username.get()))
    {
      error.set("Good boy");
      return true;
    }
    else{
      error.set("Wrong password");
      return false;
    }
  }
}
