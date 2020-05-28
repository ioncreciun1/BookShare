package view.controllers;

import javafx.fxml.FXML;
import view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.rmi.RemoteException;


public class EditProfileViewController extends ViewController
{
  @FXML public TextField username;
  @FXML public TextField firstName;
  @FXML public TextField lastName;
  @FXML public TextField email;
  @FXML public TextField phone;
  @FXML public TextField password;
  @FXML public PasswordField newPassword;
  @FXML public PasswordField confirmNewPassword;
  @FXML public ComboBox city;
  @FXML public Label error;

  public EditProfileViewController()
  {
    super();
  }

  public void init(ViewHandler viewHandler, ViewModelFactory viewModel, Region root)
  {
    // hz why but bind don`t work or method setInfo dont work idk
    super.init(viewHandler, viewModel, root);
    super.getViewModels().getEditProfileViewModel().reset();
    username.textProperty().bindBidirectional(super.getViewModels().getEditProfileViewModel().usernameProperty());
    firstName.textProperty().bindBidirectional(super.getViewModels().getEditProfileViewModel().firstNameProperty());
    lastName.textProperty().bindBidirectional(super.getViewModels().getEditProfileViewModel().lastNameProperty());
    email.textProperty().bindBidirectional(super.getViewModels().getEditProfileViewModel().emailProperty());
    phone.textProperty().bindBidirectional(super.getViewModels().getEditProfileViewModel().phoneProperty());
    city.itemsProperty().bind(super.getViewModels().getEditProfileViewModel().cityProperty());
    city.getSelectionModel().select(0);
    password.textProperty().bindBidirectional(super.getViewModels().getEditProfileViewModel().passwordProperty());
    newPassword.textProperty().bindBidirectional(super.getViewModels().getEditProfileViewModel().newPasswordProperty());
    confirmNewPassword.textProperty().bindBidirectional(super.getViewModels().getEditProfileViewModel().confirmPasswordProperty());
    error.textProperty().bind(super.getViewModels().getEditProfileViewModel().errorProperty());
  }

  public void openUserInfoViewEdited() throws Exception
  {
    boolean check = !super.getViewModels().getEditProfileViewModel().checkUser(city.getSelectionModel().toString())
        && super.getViewModels().getEditProfileViewModel().validateUser()
        && !super.getViewModels().getEditProfileViewModel().checkEmail(city.getSelectionModel().toString());
    if(check)
    {
      if (super.getViewModels().getEditProfileViewModel().verifyPasswords()
          && !super.getViewModels().getEditProfileViewModel().checkUsername()
          && !super.getViewModels().getEditProfileViewModel().checkPassword())
      {
        super.getViewModels().getEditProfileViewModel().checkPassword();
        super.getViewModels().getEditProfileViewModel().checkUsername();
        super.getViewModels().getEditProfileViewModel().editUser(city.getSelectionModel().getSelectedItem().toString());
        super.getHandler().openView("UserInfoView");

      }
    }
  }

  public void checkLogin() throws RemoteException
  {
    super.getViewModels().getEditProfileViewModel().checkUsername();
  }

  public void checkPassword()
  {
    super.getViewModels().getEditProfileViewModel().checkPassword();
  }

  public void verifyPassword()
  {
    super.getViewModels().getEditProfileViewModel().verifyPasswords();
  }

  public void openUserInfoView()
  {
    super.getHandler().openView("UserInfoView");
  }

  public void verifyPassword(KeyEvent keyEvent)
  {
    super.getViewModels().getEditProfileViewModel().verifyPasswords();
  }
}
