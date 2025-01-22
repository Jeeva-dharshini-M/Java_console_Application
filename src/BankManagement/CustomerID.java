package BankManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerID {

	DBconnection dbconnection= new DBconnection();
	
	Connection connection = dbconnection.getConnect();
	
	public int generateCustomerID() throws SQLException
	{
		
		Statement statement = dbconnection.connection.createStatement();

		String sql = "SELECT MAX(username) AS max_id FROM userdetails";
		
        ResultSet resultSet = statement.executeQuery(sql);
        
        int customerId = 0;  
        
        if (resultSet.next()) {
            int maxId = resultSet.getInt("max_id");
            customerId = maxId + 1;  
        }
		return customerId;
	}
}
