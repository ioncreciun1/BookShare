package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
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
    languageColumn1.setCellValueFactory(cellData -> cellData.getValue().bookLanguage());
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

  public void searchBooks(ActionEvent actionEvent)
  {
  }

  public void setBorrowed() throws SQLException, RemoteException{
    Book toUpdate = this.bookListTable1.getSelectionModel().getSelectedItem().getBook();
    toUpdate.setBorrowed();
    super.getViewModels().getMyBooksViewModel().changeAvailable(toUpdate, toUpdate.available());
    this.bookListTable1.setItems(super.getViewModels().getMyBooksViewModel().getTable());
  }

  public void setAvailable() throws SQLException, RemoteException{
    Book toUpdate = this.bookListTable1.getSelectionModel().getSelectedItem().getBook();
    toUpdate.setAvailable();
    super.getViewModels().getMyBooksViewModel().changeAvailable(toUpdate, toUpdate.available());
    this.bookListTable1.setItems(super.getViewModels().getMyBooksViewModel().getTable());

  }

  public void remove()  throws SQLException,RemoteException{
    Book toRemove = this.bookListTable1.getSelectionModel().getSelectedItem().getBook();
    super.getViewModels().getMyBooksViewModel().removeBook(toRemove);
    this.bookListTable1.getItems().remove(this.bookListTable1.getSelectionModel().getSelectedItem());
    this.bookListTable1.refresh();
  }

  public void getAvailable(MouseEvent mouseEvent)
  {
  }
}
