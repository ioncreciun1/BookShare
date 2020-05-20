package view;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class AddBookViewController extends ViewController
{
  public TextField title;
  public TextField author;
  public ComboBox language;
  public ComboBox type;
  public TextArea description;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModel, Region root)
  {
    super.init(viewHandler, viewModel, root);
    super.getViewModels().getAddBookViewModel().reset();
    title.textProperty().bindBidirectional(super.getViewModels().getAddBookViewModel().titleProperty());
    author.textProperty().bindBidirectional(super.getViewModels().getAddBookViewModel().authorProperty());
    description.textProperty().bindBidirectional(super.getViewModels().getAddBookViewModel().descriptionProperty());
    language.itemsProperty().bind(super.getViewModels().getAddBookViewModel().languageProperty());
    type.itemsProperty().bind(super.getViewModels().getAddBookViewModel().typeProperty());

  }

  public void openMainView(ActionEvent event)
      throws RemoteException, SQLException
  {
    String bookType = type.getSelectionModel().getSelectedItem().toString();
    String bookLanguage = language.getSelectionModel().getSelectedItem().toString();
getViewModels().getAddBookViewModel().addBook(bookType,bookLanguage);
    boolean check = super.getViewModels().getAddBookViewModel().validate();
  }
}
