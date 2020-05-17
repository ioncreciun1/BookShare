import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
  public DatabaseConnection()
  {
    try
    {
      DriverManager.registerDriver((new org.postgresql.Driver()));
    }
    catch (SQLException e)
    {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      /*System.exit(0);*/
    }
  }


  public static Connection getConnection()
  {
    Connection connection = null;
    try
    {
      connection = DriverManager
          .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "2011");
    }
     catch ( Exception e )
     {
       System.err.println(e.getClass().getName() + ": " + e.getMessage());
       System.exit(0);
     }
    return connection;
  }
}
