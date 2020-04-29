package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class SignUpViewModel
{
  private Model model;
  private StringProperty username;
  private StringProperty firstName;
  private StringProperty lastName;
  private StringProperty email;
  private StringProperty phone;
  private StringProperty password;
  private StringProperty confirmPassword;
  private StringProperty city;
  private StringProperty error;

  public SignUpViewModel(Model model)
  {
    this.model = model;
    this.username = new SimpleStringProperty("");
    this.firstName = new SimpleStringProperty("");
    this.lastName = new SimpleStringProperty("");
    this.email = new SimpleStringProperty("");
    this.phone = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
   // this.confirmPassword = new SimpleStringProperty("");
    this.city = new SimpleStringProperty("");
    this.error = new SimpleStringProperty("");
  }

  public StringProperty usernameProperty()
  {
    return username;
  }

  public StringProperty firstNameProperty()
  {
    return firstName;
  }

  public StringProperty lastNameProperty()
  {
    return lastName;
  }

  public StringProperty emailProperty()
  {
    return email;
  }

  public StringProperty passwordProperty()
  {
    return password;
  }

  public StringProperty confirmPasswordProperty()
  {
    return confirmPassword;
  }

  public StringProperty phoneProperty()
  {
    return phone;
  }

  public StringProperty cityProperty()
  {
    return city;
  }

  public StringProperty errorProperty()
  {
    return error;
  }
  public void registerUser() throws Exception
  {
    model.registerUser(username.get(),password.get(),email.get(),firstName.get(),lastName.get(),city.get(),phone.get());
  }
}
