package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewModel.ViewModelFactory;

import java.rmi.RemoteException;

public class SignUpViewController extends ViewController
{
  @FXML public TextField username;
  @FXML public TextField firstName;
  @FXML public TextField lastName;
  @FXML public TextField email;
  @FXML public TextField phone;
  @FXML public PasswordField password;
  @FXML public PasswordField confirmPassword;
  @FXML public ComboBox city;
  @FXML public Label error;

  public SignUpViewController()
  {
    super();
  }

  public void init(ViewHandler viewHandler, ViewModelFactory viewModel, Region root)
  {
    super.init(viewHandler, viewModel, root);
    super.getViewModels().getSignUpViewModel().reset();
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



  public void openMainView() throws Exception
  {
    boolean check = !super.getViewModels().getSignUpViewModel().checkUser(city.getSelectionModel().toString())
        && super.getViewModels().getSignUpViewModel().validateUser()
        && !super.getViewModels().getSignUpViewModel().checkEmail(city.getSelectionModel().toString());
    if(check)
    {
      if (super.getViewModels().getSignUpViewModel().verifyPasswords())
      {
        super.getViewModels().getSignUpViewModel().checkPassword();
        super.getViewModels().getSignUpViewModel().checkUsername();
        super.getViewModels().getSignUpViewModel().registerUser(city.getSelectionModel().getSelectedItem().toString());
        super.getHandler().openView("LogInView");

      }
    }
  }

  public void checkLogin() throws RemoteException
  {
    super.getViewModels().getSignUpViewModel().checkUsername();
  }

  public void checkPassword()
  {
    super.getViewModels().getSignUpViewModel().checkPassword();
  }

  public void openLogInView()
  {
    super.getHandler().openView("LogInView");
  }
}