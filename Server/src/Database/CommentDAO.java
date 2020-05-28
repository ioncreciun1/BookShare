package Database;

import model.Book;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface CommentDAO
{
    void add(String BookID, String Username, String comment) throws RemoteException,SQLException;
}
