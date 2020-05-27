package view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.ViewController;
import view.ViewControllerFactory;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class BookInfoViewController extends ViewController
{
  public Text title; // it`s not used yet because have some problems with id in fxml

  public Text authorName;
  public Label language;
  public Label category;
  public Label ownerName;
  public Label phoneNumber;
  public Label email;
  public ListView<String> description;

  public BookInfoViewController()
  {
    super();
  }

  public void init(ViewHandler viewHandler, ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    title.textProperty().bind(super.getViewModels().getBookInfoViewModel().titleProperty());
    authorName.textProperty().bind(super.getViewModels().getBookInfoViewModel().authorNameProperty());
    language.textProperty().bind(super.getViewModels().getBookInfoViewModel().languageProperty());
    category.textProperty().bind(super.getViewModels().getBookInfoViewModel().categoryProperty());
    ownerName.textProperty().bind(super.getViewModels().getBookInfoViewModel().ownerNameProperty());
    phoneNumber.textProperty().bind(super.getViewModels().getBookInfoViewModel().phoneNumberProperty());
    email.textProperty().bind(super.getViewModels().getBookInfoViewModel().emailProperty());
    String descriptionString = super.getViewModels().getBookInfoViewModel().descriptionProperty().getValue();
    ObservableList<String> items = FXCollections.observableArrayList ();
    items.add(descriptionString);
    description.setItems(items);
  }

  public void openAddBookView()
  {
    super.getHandler().openView("AddBookView");
  }

  public void openSearchView()
  {
    super.getHandler().openView("SearchView");
  }

  public void openMainView()
  {
    super.getHandler().openView("MainView");
  }

  public void openMyBooksView() throws SQLException, RemoteException
  {
    super.getViewModels().getMyBooksViewModel().createList();
    super.getHandler().openView("MyBooksView");
  }

  public void openCommentView() throws IOException
  {
    super.getHandler().openView("CommentView");
  }
}
