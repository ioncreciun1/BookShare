package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.User;

import java.rmi.RemoteException;

public class UserInfoViewModel
{
  private Model model;
  private User user;
  public StringProperty username;
  public StringProperty password;
  public StringProperty fullName;
  public StringProperty city;
  public StringProperty phone;
  public StringProperty email;

  public UserInfoViewModel(Model model)
  {
    this.model = model;
    this.username = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
    this.fullName = new SimpleStringProperty("");
    this.city = new SimpleStringProperty("");
    this.phone = new SimpleStringProperty("");
    this.email = new SimpleStringProperty("");
  }

  public void setUser() throws RemoteException
  {
    this.user = model.getUser(model.getUser());
    this.usernameProperty().setValue(user.getUserName());
    this.passwordProperty().setValue(user.getPassWord());
    this.fullNameProperty().setValue(user.getName() + " " + user.getLastName());
    this.cityProperty().setValue(user.getCity());
    if (!(user.getphone().equals("")))
    {
      this.phoneProperty().setValue(user.getphone());
    } else {
      this.phoneProperty().setValue("No phone number");
    }
    this.getEmail().setValue(user.getEMail());
  }

  public StringProperty usernameProperty()
  {
    return username;
  }

  public StringProperty passwordProperty()
  {
    return password;
  }

  public StringProperty fullNameProperty()
  {
    return fullName;
  }

  public StringProperty cityProperty()
  {
    return city;
  }

  public StringProperty phoneProperty()
  {
    return phone;
  }

  public StringProperty getEmail()
  {
    return email;
  }
}
