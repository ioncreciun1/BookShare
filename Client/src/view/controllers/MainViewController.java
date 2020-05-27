package view.controllers;

import javafx.fxml.FXML;
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

public class MainViewController extends ViewController
{
  @FXML private TableView<TableRowData> bookListTable;
  @FXML private TableColumn<TableRowData,Integer> orderColumn;
  @FXML private TableColumn<TableRowData,String> titleColumn;
  @FXML private TableColumn<TableRowData,String> authorColumn;
  @FXML private TableColumn<TableRowData,String> languageColumn;
  @FXML private TableColumn<TableRowData,String> categoryColumn;

  public MainViewController()
  {
    super();
  }

  public void init(
      ViewHandler viewHandler, ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
//    orderColumn.setCellValueFactory(
//        cellData -> cellData.getValue().getOrderNumber()
//    );
    titleColumn.setCellValueFactory(
        cellData -> cellData.getValue().getBookTitle()
    );
    authorColumn.setCellValueFactory(
        cellData -> cellData.getValue().authorName()
    );
    languageColumn.setCellValueFactory(
        cellData -> cellData.getValue().bookLanguage()
    );
    categoryColumn.setCellValueFactory(
        cellData -> cellData.getValue().bookCategory()
    );
this.bookListTable.itemsProperty().bindBidirectional(super.getViewModels().getMainViewModel().tablePropertyProperty());
   // this.bookListTable.setItems(super.getViewModels().getMainViewModel().getTable());
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

  public void openBookInfoView(MouseEvent event) throws RemoteException {

    if (this.bookListTable.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2)
    {
      Book selectedBook = this.bookListTable.getSelectionModel().getSelectedItem().getBook();
      super.getViewModels().getBookInfoViewModel().setBook(selectedBook);
      super.getHandler().openView("BookInfoView");
    }
  }

  public void openMyBooksView() throws SQLException, RemoteException
  {

    super.getViewModels().getMyBooksViewModel().createList();
    super.getHandler().openView("MyBooksView");
  }


}
