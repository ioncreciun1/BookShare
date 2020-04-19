package view;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
  public ChoiceBox<String> city;
  public Label error;
  private ViewHandler viewHandler;
  private SignUpViewModel  viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, SignUpViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    //    password.textProperty().bindBidirectional(viewModel.passwordProperty());
    //    error.textProperty().bind(viewModel.errorProperty());
    //    username.textProperty().bindBidirectional(viewModel.usernameProperty());
  }

  public void reset()
  {
  }

  public Region getRoot()
  {
    return root;
  }

  public void openLogIn() throws IOException
  {
    viewHandler.openView("LogIn");
  }
}
