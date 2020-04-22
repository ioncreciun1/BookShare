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
    username.textProperty().bindBidirectional(viewModel.usernameProperty());
    firstName.textProperty().bindBidirectional(viewModel.firstNameProperty());
    lastName.textProperty().bindBidirectional(viewModel.lastNameProperty());
    email.textProperty().bindBidirectional(viewModel.emailProperty());
    phone.textProperty().bindBidirectional(viewModel.phoneProperty());
//   // city.itemsProperty().bindBidirectional(viewModel.cityProperty()); // Not sure about it, idk what it should be
//    city.getItems().addAll("Copenhagen", "Aarhus", "Odense", "Aalborg",
//        "Esbjerg", "Randers", "Kolding", "Horsens", "Vejle", "Roskilde", "Herning", "Hørsholm", "Helsingør",
//        "Silkeborg", "Næstved", "Fredericia", "Viborg", "Køge");
//    city.getSelectionModel().select("Copenhagen");
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

  public void openLogIn() throws IOException
  {
    if (verifyPassword())
    {
      viewHandler.openView("LogIn");
    }
    error.setText("Different Password");
  }

  public void Cancel()
  {
    viewHandler.openView("LogIn");
  }
}
