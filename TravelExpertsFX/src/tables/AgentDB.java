package tables;

import java.sql.*;

import database.DBConnection;

public class AgentDB {
	
	public  AgentDB(){
		
	}
	
	public static void getAgents()
	{
		try{
			Connection con = DBConnection.getConnection();
			java.sql.Statement stmt = con.createStatement();
			ResultSet  rs = stmt.executeQuery("select * from agents"); 
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
				con.close();
			} catch(SQLException ex) {
				
			}
	}

}
