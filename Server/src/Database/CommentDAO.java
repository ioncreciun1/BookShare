package Database;

import model.Book;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface CommentDAO
{
    void add(String BookID, String Username, String comment) throws RemoteException,SQLException;
    ArrayList<String> get(String BookID) throws RemoteException,SQLException;
}
