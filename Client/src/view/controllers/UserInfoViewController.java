package view.controllers;


import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.rmi.RemoteException;

public class UserInfoViewController extends ViewController
{
  public Label username;
  public Label password;
  public Label fullName;
  public Label city;
  public Label phone;
  public Label email;

  public UserInfoViewController()
  {
    super();
  }

  public void init(
      ViewHandler viewHandler, ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    username.textProperty().bind(super.getViewModels().getUserInfoViewModel().usernameProperty());
    password.textProperty().bind(super.getViewModels().getUserInfoViewModel().passwordProperty());
    fullName.textProperty().bind(super.getViewModels().getUserInfoViewModel().fullNameProperty());
    city.textProperty().bind(super.getViewModels().getUserInfoViewModel().cityProperty());
    phone.textProperty().bind(super.getViewModels().getUserInfoViewModel().phoneProperty());
    email.textProperty().bind(super.getViewModels().getUserInfoViewModel().getEmail());
  }

  public void openAddBookView()
  {
    super.getHandler().openView("AddBookView");
  }

  public void openUserInfoView()
  {
    super.getHandler().openView("UserInfoView");
  }

  public void openMainView()
  {
    super.getHandler().openView("MainView");
  }

  public void openMyBooksView()
  {
    super.getHandler().openView("MyBooksView");
  }

  public void openEditProfileView() throws RemoteException
  {
    getViewModels().getEditProfileViewModel().setEditInformation();
    getHandler().openView("EditProfileView");
  }
}
