package Database;

import model.Book;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CommentDAOImplementation implements CommentDAO
{
    /**
     * Class constructor registers the given driver with the DriverManager
     * @throws SQLException error JDBC encounters an error during an interaction with a data source, it throws an instance of SQLException
     * If the driver is currently registered, no action is taken. */
    public CommentDAOImplementation( ) throws SQLException{
        DriverManager.registerDriver((new org.postgresql.Driver()));
    }

    /**
     * Driver Manager class attempts to establish a connection to the given database URL.
     * @throws SQLException if a database access error occurs or the url is null*/
    private Connection getConnection() throws SQLException
    {
        return DriverManager
            .getConnection("jdbc:postgresql://btv9bsc3ws7gpfnylr1c-postgresql.services.clever-cloud.com:5432/btv9bsc3ws7gpfnylr1c", "uhxksonl4uvuqdftznsg",
                "pnwEWl0YlSc6A2z619ff");

    }

    public void add(String BookID, String Username, String comment) throws RemoteException,SQLException
    {
        try
            (Connection connection = getConnection()/*auto closes the connection*/) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".\"comment\" (BookID, Username, comment) VALUES (?,?,?);");
            statement.setString(1, BookID);
            statement.setString(2, Username);
            statement.setString(3, comment);
            statement.executeUpdate();
            System.out.println("Comment Posted");
        }
    }

    public ArrayList<String> get(String BookID) throws RemoteException,SQLException{
        ArrayList<String> hash = new ArrayList<>();
        try
                (Connection connection = getConnection()/*auto closes the connection*/) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".\"comment\" WHERE BookID = '"+BookID+"'");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                hash.add(rs.getString("Username") + ": " +rs.getString("comment"));
            }
            return hash;
        }
    }
}
