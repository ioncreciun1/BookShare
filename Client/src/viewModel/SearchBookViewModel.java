package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Model;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchBookViewModel
{
    private Model model;
    private StringProperty title;
    private StringProperty author;
    private ObjectProperty<ObservableList> language;
    private ObjectProperty<ObservableList> type;
    private StringProperty error;
    public SearchBookViewModel(Model model)
    {
        ObservableList<String> languageList = FXCollections.observableArrayList();
        ObservableList<String> typeList = FXCollections.observableArrayList();
        languageList.addAll("Danish","English","German","Romanian");
        typeList.addAll("Drama","Action","Fiction","Adventure");
        this.model = model;
        this.language = new SimpleObjectProperty<>();
        this.type = new SimpleObjectProperty<>();
        this.title = new SimpleStringProperty("");
        this.author = new SimpleStringProperty("");
        language.setValue(languageList);
        type.setValue(typeList);
        this.error = new SimpleStringProperty("");
    }

    public ObjectProperty<ObservableList> languageProperty()
    {
        return language;
    }

    public ObjectProperty<ObservableList> typeProperty()
    {
        return type;
    }

    public StringProperty authorProperty()
    {
        return author;
    }

    public StringProperty titleProperty()
    {
        return title;
    }


    public List<Book> listBooksSearch(String category, String language, String author, String title)
            throws RemoteException, SQLException
    {
        List<Book> books = new ArrayList<>();
        if (category.length() != 0){

        }
        if (language.length() != 0){

        }
        if(author.length() != 0){

        }
        if(title.length() != 0){

        }
        return books;
    }

    public void reset()
    {
    }

    public boolean validate(){
        return true;
    }
}
