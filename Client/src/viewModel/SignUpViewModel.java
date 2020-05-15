package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Registrant;

import java.rmi.RemoteException;

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
 // private StringProperty city;
  private StringProperty error;
  private ObjectProperty<ObservableList> city;

  public SignUpViewModel(Model model)
  {
    ObservableList<String> list = FXCollections.observableArrayList();
    list.addAll("Horsens","Aarhus","Alborg","Copenhagen","Odense","Veijle");
    this.model = model;
    this.username = new SimpleStringProperty("");
    this.firstName = new SimpleStringProperty("");
    this.lastName = new SimpleStringProperty("");
    this.email = new SimpleStringProperty("");
    this.phone = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
    this.confirmPassword = new SimpleStringProperty("");
    this.city = new SimpleObjectProperty<>();
    city.setValue(list);

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

  public ObjectProperty<ObservableList> cityProperty()
  {
    return city;
  }

  public StringProperty errorProperty()
  {
    return error;
  }
  public void registerUser(String city) throws Exception
  {


      model.registerUser(username.get(), password.get(), email.get(), firstName.get(),
          lastName.get(), city, phone.get());
  }
  public void checkUsername()
  {
    if(model.checkUsername(username.get()))
    {
      error.set("The username must be at least 8 characters");
    }
    else {error.set("");}
  }
  public void checkPassword()
  {
    if(model.checkPassword(password.get()))
    {
      error.set("The password must be at least 6 characters");
    }
    else {error.set("");}
  }
  public boolean verifyPasswords()
  {
    if(this.password.get().equals(confirmPassword.get()))
    {
      error.set("");
      return true;
    }
    else {
      error.set("Passwords does not match. Insert Them Again");
      return false;
    }
  }
  public boolean validateUser()
  {
    if(username.get().length()==0)
    {
      error.set("Username field have to be fulfilled");
    }
    else     if(password.get().length()==0)
    {
      error.set("set password");
    }
    else     if(firstName.get().length()==0)
    {
      error.set("set First name");
    }
    else     if(lastName.get().length()==0)
    {
      error.set("set last name");
    }
    else     if(email.get().length()==0)
    {
      error.set("set email");
    }
    System.out.println("Statement");
    System.out.println(username.get().length() == 0 && firstName.get().length()==0 && lastName.get().length()==0
        && password.get().length() == 0 && email.get().length() == 0);
    return username.get().length() != 0 && firstName.get().length()!=0 && lastName.get().length()!=0
        && password.get().length() != 0 && email.get().length() != 0;

  }
  public boolean checkUser(String city) throws RemoteException
  {
    boolean check =  model.checkUser(new Registrant(username.get(),password.get(),email.get(),firstName.get(),lastName.get(),city,phone.get(),0));
    if(check)
    {
      error.set("This username is already in the system");
    }
   return check;
  }
  public boolean checkEmail(String city) throws RemoteException
  {
    boolean check =  model.checkEmail(new Registrant(username.get(),password.get(),email.get(),firstName.get(),lastName.get(),city,phone.get(),0));
    if(check)
    {
      error.set("This Email is already in the system");
    }
    return check;
  }
}
