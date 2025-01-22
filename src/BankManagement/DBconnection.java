package BankManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
       public static Connection connection=null;
       public Connection getConnect()
       {
       try {
           
           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "Aspire@123");
           System.out.println("Connection successful!");
       }
         catch (SQLException exception) {
          exception.printStackTrace();
         }
	   return connection;
       }
}
