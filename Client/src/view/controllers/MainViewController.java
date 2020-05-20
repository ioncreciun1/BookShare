package view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import viewModel.ViewModelFactory;

public class MainViewController extends ViewController
{
  @FXML private TableView<MainViewTableRowData> bookListTable;
  @FXML private TableColumn<MainViewTableRowData,Integer> orderColumn;
  @FXML private TableColumn<MainViewTableRowData,String> titleColumn;
  @FXML private TableColumn<MainViewTableRowData,String> authorColumn;
  @FXML private TableColumn<MainViewTableRowData,String> languageColumn;
  @FXML private TableColumn<MainViewTableRowData,String> categoryColumn;

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
  }

  public void openAddBookView()
  {
    super.getHandler().openView("AddBookView");
  }

}
