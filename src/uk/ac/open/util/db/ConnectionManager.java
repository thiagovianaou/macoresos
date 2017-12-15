package uk.ac.open.util.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
  public Connection getConnection(boolean lock) throws SQLException;
  public void returnConnection(boolean unlock) throws SQLException;
  
}