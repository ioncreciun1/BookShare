package view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.sql.SQLException;

public class LogInViewController extends ViewController
{
  @FXML public TextField username;
  @FXML public PasswordField password;
  @FXML public Label error;

  public LogInViewController()
  {
    super();
  }

  public void init(ViewHandler viewHandler, ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    password.textProperty().bindBidirectional(super.getViewModels().getLogInViewModel().passwordProperty());
    error.textProperty().bind(super.getViewModels().getLogInViewModel().errorProperty());
    username.textProperty().bindBidirectional(super.getViewModels().getLogInViewModel().usernameProperty());
  }

  public void openSignUpView()
  {
    super.getHandler().openView("SignUpView");
  }

  public void openMainView() throws IOException, SQLException
  {
    if(super.getViewModels().getLogInViewModel().verifyUser())
    {
      super.getHandler().openView("MainView");
    }
  }
}