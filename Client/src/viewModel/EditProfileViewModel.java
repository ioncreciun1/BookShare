package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.User;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class EditProfileViewModel
{
  private Model model;
  private User user;
  private StringProperty username;
  private StringProperty firstName;
  private StringProperty lastName;
  private StringProperty email;
  private StringProperty phone;
  private StringProperty password;
  private StringProperty newPassword;
  private StringProperty confirmPassword;
  private StringProperty error;
  private ObjectProperty<ObservableList> city;

  public EditProfileViewModel(Model model)
  {
    this.model = model;
    ObservableList<String> list = FXCollections.observableArrayList();
    list.addAll("Horsens","Aarhus","Alborg","Copenhagen","Odense","Vejle", "Esbjerg", "Randers", "Kolding", "Roskilde",
        "Herning", "Silkeborg", "Fredericia", "Viborg", "Holstebro", "Køge", "Helsingør");
    this.username = new SimpleStringProperty("");
    this.firstName = new SimpleStringProperty("");
    this.lastName = new SimpleStringProperty("");
    this.email = new SimpleStringProperty("");
    this.phone = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
    this.newPassword = new SimpleStringProperty("");
    this.confirmPassword = new SimpleStringProperty("");
    this.city = new SimpleObjectProperty<>();
    city.setValue(list);
    this.error = new SimpleStringProperty("");
  }

  public void setEditInformation() throws RemoteException
  {
    this.user = model.getUser(model.getUser());
    this.usernameProperty().setValue(user.getUserName());
    this.passwordProperty().setValue(user.getPassWord());
    this.firstNameProperty().setValue(user.getName());
    this.lastNameProperty().setValue(user.getLastName());
    ObservableList<String> newList = FXCollections.observableArrayList();
    newList.add(user.getCity());
    this.cityProperty().setValue(newList); // So here it should be a list object but than how to take all cities?
    // you see how i solved it but not sure that it wors
    if (!(user.getphone().equals("")))
    {
      this.phoneProperty().setValue(user.getphone());
    } else {
      this.phoneProperty().setValue("No phone number");
    }
    this.emailProperty().setValue(user.getEMail());
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

  public StringProperty newPasswordProperty()
  {
    return newPassword;
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

  public void reset()
  {
    firstName.set("");
    lastName.set("");
    username.set("");
    password.set("");
    newPassword.set("");
    confirmPassword.set("");
    email.set("");
    phone.set("");
  }

  public void editUser(String city) throws Exception
  {
    model.registerUser(username.get(), password.get(), email.get(), firstName.get(),
        lastName.get(), city, phone.get());
  }

  public boolean checkUsername() throws RemoteException, SQLException
  {
    if(model.checkUsername(username.get()))
    {
      error.set("The username must be at least 8 characters and less than 30 characters");
    }
    else {error.set("");}
    return model.checkUsername(username.get());
  }

  public boolean checkPassword()
  {
    if(model.checkPassword(password.get()))
    {
      error.set("The password must be at least 6 characters and less than 20 characters");
    }
    else {error.set("");}
    return model.checkPassword(password.get());
  }

  public boolean verifyPasswords()
  {
    if(this.newPassword.get().equals(confirmPassword.get()))
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
      error.set("Empty username field, Insert username");
    }
    else     if(password.get().length()==0)
    {
      error.set("Field password can’t be empty");
    }
    else     if(firstName.get().length()==0)
    {
      error.set("Field First Name can’t be empty");
    }
    else if (firstName.get().length() >= 20){
      error.set("First Name must be less than 20 characters");
    }
    else     if(lastName.get().length()==0)
    {
      error.set("Field Last Name can’t be empty");
    }
    else if (lastName.get().length() >= 25){
      error.set("Last Name must be less than 25 characters");
    }
    else     if(email.get().length()==0)
    {
      error.set("Email field can’t be empty. Insert Email");
    }
    else if (email.get().length() > 30){
      error.set("Email must be less than 30 characters");
    }
    return username.get().length() != 0 && firstName.get().length()!=0 && firstName.get().length()< 20 && lastName.get().length()!=0 && lastName.get().length()<25
        && password.get().length() != 0 && email.get().length() != 0 && email.get().length() < 30;

  }

  public boolean checkUser(String city) throws RemoteException
  {
    boolean check =  model.checkUser(new User(username.get(),password.get(),email.get(),firstName.get(),lastName.get(),city,phone.get()));
    if(check)
    {
      error.set("This username is already in the system");
    }
    return check;
  }

  public boolean checkEmail(String city) throws RemoteException
  {
    boolean check =  model.checkEmail(new User(username.get(),password.get(),email.get(),firstName.get(),lastName.get(),city,phone.get()));
    if(check)
    {
      error.set("This Email is already in the system");
    }
    return check;
  }
}
