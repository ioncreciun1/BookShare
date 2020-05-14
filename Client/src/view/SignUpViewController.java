package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import viewModel.SignUpViewModel;
import viewModel.ViewModelFactory;

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
    if(super.getViewModels().getSignUpViewModel().verifyPasswords())
    {
      super.getViewModels().getSignUpViewModel().registerUser(city.getSelectionModel().getSelectedItem().toString());
      super.getHandler().openView("LogInView");
    }
  }

  public void openLogInView()
  {
    super.getHandler().openView("LogInView");
  }
}