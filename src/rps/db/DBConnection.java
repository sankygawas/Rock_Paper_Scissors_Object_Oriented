package rps.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

/**
 * This class represents the base for Data Connection. The class can be replaced with other Db class if the underlying database is changed
 * @author Sanket
 *
 */
public class DBConnection {
	
	/**
	 * userName for the DB
	 */
	private static final String userName = "u19200070";
	/**
	 * Password for the DB connection
	 */
	private static final String password = "COMP20300";
	/**
	 * The schema used for the Game
	 */
	private static final String DBName = "db19200070";
	
	/**
	 * Default constructor
	 */
	public DBConnection() {

	}
	
	/**
	 * This method creates a connection for the Database
	 * @return a Connection object for the database
	 */
	public Connection createConnection(){
			
			try {
				DriverManager.setLoginTimeout(20);
				Class.forName("com.mysql.cj.jdbc.Driver");
				return DriverManager.getConnection("jdbc:mysql://folding03.ucd.ie:3306/" + DBName, userName, password);
			} 
			catch(CommunicationsException e) {
				e.printStackTrace();
				return null;
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} 
			catch (SQLException e) {
				e.printStackTrace();
				return null;

			}
	}
	
	/**
	 * Closes the connection if it is not null or already closed
	 * @param con Connection object of the database
	 */
	public void closeConnection(Connection con) {
		try {
			if(con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
