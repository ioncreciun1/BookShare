package view;

import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import viewModel.SignUpViewModel;

import java.io.IOException;

public class SignUpViewController
{
  public TextField username;
  public TextField firstName;
  public TextField lastName;
  public TextField email;
  public TextField phone;
  public PasswordField password;
  public PasswordField confirmPassword;
  public ComboBox city;
  public Label error;
  private TextField one;
  private ViewHandler viewHandler;
  private SignUpViewModel  viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, SignUpViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    username.textProperty().bindBidirectional(viewModel.usernameProperty());
    firstName.textProperty().bindBidirectional(viewModel.firstNameProperty());
    lastName.textProperty().bindBidirectional(viewModel.lastNameProperty());
    email.textProperty().bindBidirectional(viewModel.emailProperty());
    phone.textProperty().bindBidirectional(viewModel.phoneProperty());
city.itemsProperty().bind(viewModel.cityProperty());
city.getSelectionModel().select(0);
    password.textProperty().bindBidirectional(viewModel.passwordProperty());
    confirmPassword.textProperty().bindBidirectional(viewModel.confirmPasswordProperty());
    error.textProperty().bind(viewModel.errorProperty());
  }

  public void reset()
  {
  }

  public Region getRoot()
  {
    return root;
  }

  public boolean verifyPassword()
  {
    return (password == confirmPassword);
  }

  public void openLogIn() throws Exception
  {
    if(viewModel.verifyPasswords())
    {
      viewModel.registerUser(city.getSelectionModel().getSelectedItem().toString());
      viewHandler.openView("LogIn");
    }
  }

  public void Cancel()
  {
    viewHandler.openView("LogIn");
  }

  public void checkLogin(KeyEvent keyEvent)
  {
    viewModel.checkUsername();
  }

  public void checkPassword(KeyEvent keyEvent)
  {
    viewModel.checkPassword();
  }
}
