package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class SearchViewController extends ViewController
{
  @FXML private TableView<TableRowData> bookListTable;
  @FXML private TableColumn<TableRowData,Integer> orderColumn;
  @FXML private TableColumn<TableRowData,String> titleColumn;
  @FXML private TableColumn<TableRowData,String> authorColumn;
  @FXML private TableColumn<TableRowData,String> languageColumn;
  @FXML private TableColumn<TableRowData,String> categoryColumn;
  public TextField title;
  public TextField author;
  public ComboBox language;
  public ComboBox type;
  public Label error;

  public SearchViewController()
  {
    super();
  }

  public void init(
      ViewHandler viewHandler, ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    super.getViewModels().getSearchViewModel().reset();
    title.textProperty().bindBidirectional(super.getViewModels().getSearchViewModel().titleProperty());
    author.textProperty().bindBidirectional(super.getViewModels().getSearchViewModel().authorProperty());
    language.itemsProperty().bind(super.getViewModels().getSearchViewModel().languageProperty());
    type.itemsProperty().bind(super.getViewModels().getSearchViewModel().typeProperty());
    error.textProperty().bind(super.getViewModels().getSearchViewModel().errorProperty());

    titleColumn.setCellValueFactory(
        cellData -> cellData.getValue().getBookTitle()
    );
    authorColumn.setCellValueFactory(
        cellData -> cellData.getValue().authorName()
    );
    languageColumn.setCellValueFactory(
        cellData -> cellData.getValue().language()
    );
    categoryColumn.setCellValueFactory(
        cellData -> cellData.getValue().bookCategory()
    );
    this.bookListTable.setItems(super.getViewModels().getSearchViewModel().getTable());
  reset();
    }

  public void reset()
  {
    super.getViewModels().getSearchViewModel().reset();
    language.getSelectionModel().select(0);
    type.getSelectionModel().select(0);
  }

  public void openAddBookView()
  {
    super.getHandler().openView("AddBookView");
  }

  public void openMyBooksView() throws SQLException, RemoteException
  {
    super.getViewModels().getMyBooksViewModel().createList();
    super.getHandler().openView("MyBooksView");
  }

  public void openMainView()
  {
    super.getHandler().openView("MainView");
  }

  public void searchBooks(ActionEvent event)
      throws SQLException, RemoteException
  {
    bookListTable.getItems().clear();
    String bookType = type.getSelectionModel().getSelectedItem().toString();
    String bookLanguage = language.getSelectionModel().getSelectedItem().toString();
    super.getViewModels().getSearchViewModel().searchBook(bookType,bookLanguage);
    reset();
  }

  public void openUserInfoView(MouseEvent mouseEvent)
  {
  }
}
