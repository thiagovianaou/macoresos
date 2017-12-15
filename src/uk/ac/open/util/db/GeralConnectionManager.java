package uk.ac.open.util.db;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class GeralConnectionManager implements ConnectionManager {
  private static GeralConnectionManager instance;

  private String driver = "org.postgresql.Driver";
  private String url = "jdbc:postgresql://localhost:5432/MaCoRe_SoS";
  private String login = "postgres";
  private String password = "scarlet77";
  private Connection conn = null;
  private boolean locker = false;

  public synchronized static GeralConnectionManager obterInstancia() throws
      ClassNotFoundException, SQLException {

    if (instance == null) {
    		instance = new GeralConnectionManager();
    }
    return instance;
  }

  private GeralConnectionManager() throws ClassNotFoundException,SQLException{
    open();
  }

  private void open() throws SQLException, ClassNotFoundException {
    if(this.conn == null || this.conn.isClosed()){
      Class.forName(driver);
      conn = DriverManager.getConnection(url,login,password);
      locker = false;
      System.out.println("Open!");
    }
  }
  
  public synchronized Connection getConnection(boolean lock) throws SQLException {
    if(conn == null || conn.isClosed()){
      try {
        open();
      } catch (ClassNotFoundException ex) {
        throw new SQLException(ex.getMessage());
      }
    }
    
    while(locker){
      try {
        wait();
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
     }
     locker = lock;
     
    return conn;
  }
  
  public void returnConnection(boolean unlock){
    this.locker = !locker;
    notifyAll();
  }

}