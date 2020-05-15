package view;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.LogInViewModel;

import java.io.IOException;

public class LogInViewController extends ViewController
{
  public TextField username;
  public PasswordField password;
  public Label error;
  private ViewHandler viewHandler;
  private LogInViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, LogInViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    password.textProperty().bindBidirectional(viewModel.passwordProperty());
    error.textProperty().bind(viewModel.errorProperty());
    username.textProperty().bindBidirectional(viewModel.usernameProperty());
  }

  public void reset()
  {
  }

  public Region getRoot()
  {
    return root;
  }

  public void openSignUp() throws IOException
  {
    viewHandler.openView("SignUp");
  }

  public void openMainPage() throws IOException
  {
    if(viewModel.verifyPass())
    {
      viewHandler.openView("");
    }
  }
}
