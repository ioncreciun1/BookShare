package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import model.Book;
import view.ViewController;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Optional;

public class MyBooksViewController extends ViewController
{
  @FXML private TableView<TableRowData> bookListTable1;
  @FXML private TableColumn<TableRowData, Integer> orderColumn1;
  @FXML private TableColumn<TableRowData, String> titleColumn1;
  @FXML private TableColumn<TableRowData, String> authorColumn1;
  @FXML private TableColumn<TableRowData, String> languageColumn1;
  @FXML private TableColumn<TableRowData, String> categoryColumn1;
  @FXML private  TableColumn<TableRowData,String> availableColumn;

  public MyBooksViewController()
  {
    super();
  }

  public void init(ViewHandler viewHandler, ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    //    orderColumn.setCellValueFactory(
    //        cellData -> cellData.getValue().getOrderNumber()
    //    );
    titleColumn1.setCellValueFactory(cellData -> cellData.getValue().getBookTitle());
    authorColumn1.setCellValueFactory(cellData -> cellData.getValue().authorName());
    languageColumn1.setCellValueFactory(cellData -> cellData.getValue().getBookLanguage());
    categoryColumn1.setCellValueFactory(cellData -> cellData.getValue().bookCategory());
    availableColumn.setCellValueFactory(cellData -> cellData.getValue().availabilityProperty());
    this.bookListTable1.setItems(super.getViewModels().getMyBooksViewModel().getTable());
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

  public void openBookInfoView()
  {
    super.getHandler().openView("BookInfoView");
  }
  public void openMyBooksView() throws SQLException, RemoteException
  {
    super.getViewModels().getMyBooksViewModel().createList();
    super.getHandler().openView("MyBooksView");
  }

  public void setBorrowed() throws SQLException, RemoteException{
    if (this.bookListTable1.getSelectionModel().getSelectedItem() != null){
    Book toUpdate = this.bookListTable1.getSelectionModel().getSelectedItem().getBook();
    toUpdate.setBorrowed();
    super.getViewModels().getMyBooksViewModel().changeAvailable(toUpdate, toUpdate.available());
    this.bookListTable1.setItems(super.getViewModels().getMyBooksViewModel().getTable());
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error Dialog");
      alert.setHeaderText("ERROR: NO BOOK SELECTED");
      alert.setContentText("Please select a book in order to set it to be borrowed");
      alert.showAndWait();
      System.out.println("NO BOOK SELECTED AVAILABLE");
    }
  }

  public void setAvailable() throws SQLException, RemoteException{
    if (this.bookListTable1.getSelectionModel().getSelectedItem() != null){
    Book toUpdate = this.bookListTable1.getSelectionModel().getSelectedItem().getBook();
    toUpdate.setAvailable();
    super.getViewModels().getMyBooksViewModel().changeAvailable(toUpdate, toUpdate.available());
    this.bookListTable1.setItems(super.getViewModels().getMyBooksViewModel().getTable());
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error Dialog");
      alert.setHeaderText("ERROR: NO BOOK SELECTED");
      alert.setContentText("Please select a book in order to set it to be available");
      alert.showAndWait();
      System.out.println("NO BOOK SELECTED BORROW");
    }
  }

  public void remove()  throws SQLException,RemoteException{
    if (this.bookListTable1.getSelectionModel().getSelectedItem() != null){
    boolean remove = confirmation();
    if (remove)
    {
      Book toRemove = this.bookListTable1.getSelectionModel().getSelectedItem().getBook();
      super.getViewModels().getMyBooksViewModel().removeBook(toRemove);
      this.bookListTable1.getItems().remove(this.bookListTable1.getSelectionModel().getSelectedItem());
      this.bookListTable1.refresh();
    }
    } else {
  Alert alert = new Alert(Alert.AlertType.INFORMATION);
  alert.setTitle("Error Dialog");
  alert.setHeaderText("ERROR: NO BOOK SELECTED");
  alert.setContentText("Please select a book in order to remove it from the system");
  alert.showAndWait();
  System.out.println("NO BOOK SELECTED REMOVE");
}
  }

  private boolean confirmation()
  {
    Book book = this.bookListTable1.getSelectionModel().getSelectedItem().getBook();
    int index = bookListTable1.getSelectionModel().getSelectedIndex();
    if (index < 0 || index >= bookListTable1.getItems().size())
    {
      return false;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Remove book: " + book.getTitle() + " by " + book.getAuthor() + "?");
    alert.setContentText("Please confirm your choice");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  public void openUserInfoView() throws RemoteException
  {
    getViewModels().getUserInfoViewModel().setUser();
    getHandler().openView("UserInfoView");
  }
}
