package view;

import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import viewModel.SignUpViewModel;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;

public class SignUpViewController extends ViewController
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
  private ViewModelFactory viewModelFactory;
  public SignUpViewController()
  {
    super();
  }

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root)
  {
    super.init(viewHandler,viewModelFactory,root);

    username.textProperty().bindBidirectional(super.getViewModels().getSignUpViewModel().usernameProperty());
    firstName.textProperty().bindBidirectional(super.getViewModels().getSignUpViewModel().firstNameProperty());
    lastName.textProperty().bindBidirectional(super.getViewModels().getSignUpViewModel().lastNameProperty());
    email.textProperty().bindBidirectional(super.getViewModels().getSignUpViewModel().emailProperty());
    phone.textProperty().bindBidirectional(super.getViewModels().getSignUpViewModel().phoneProperty());
city.itemsProperty().bind(super.getViewModels().getSignUpViewModel().cityProperty());
city.getSelectionModel().select(0);
    password.textProperty().bindBidirectional(super.getViewModels().getSignUpViewModel().passwordProperty());
    confirmPassword.textProperty().bindBidirectional(super.getViewModels().getSignUpViewModel().confirmPasswordProperty());
    error.textProperty().bind(super.getViewModels().getSignUpViewModel().errorProperty());
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

    boolean check = !viewModel.checkUser(city.getSelectionModel().toString()) && viewModel.validateUser() && !viewModel.checkEmail(city.getSelectionModel().toString());
    if(check)
    {

      System.out.println("Here");
      if (viewModel.verifyPasswords())
      {
        viewModel.registerUser(city.getSelectionModel().getSelectedItem().toString());
        viewHandler.openView("LogInView");
      }
    }
  }

  public void Cancel()
  {
    viewHandler.openView("LogInView");
  }

  public void checkLogin(KeyEvent keyEvent)
  {
    super.getViewModels().getSignUpViewModel().checkUsername();
  }

  public void checkPassword(KeyEvent keyEvent)
  {
    super.getViewModels().getSignUpViewModel().checkPassword();
  }
}
