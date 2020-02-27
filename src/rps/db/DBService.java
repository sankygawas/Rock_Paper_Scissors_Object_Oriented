package rps.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import rps.bean.LeaderBoard;
import rps.bean.Player;
import rps.constants.Constants;
import rps.logger.Logger;
import rps.logger.PrintLogger;

/**
 * This class consists the different methods which communicate with the database
 * @author Sanket
 *
 */
public class DBService {
	/**
	 * logger for the Database service
	 */
	PrintLogger logger;
	/**
	 * The connection object for the database
	 */
	Connection con = null;
	
	DBConnection db = null;
	
	/**
	 * default Constructor
	 */
	public DBService() {
		logger = new PrintLogger();
	}
	
	public boolean isConnecting() {
		System.out.println("Checlking Connection");
		db =  new DBConnection();
		con = db.createConnection();
		if(con == null)
			return false;
		else {
			try {
				con.close();
				db.closeConnection(con);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
	}
	
	
	/**
	 * Build the data required to insert into database
	 * @param playerList List of Player
	 * @param winnerName Player object who is the winner of the Round
	 * @param points in int
	 * @return SUCCESS, if inserted into DB, FAIL if error while inserting
	 */
	public String insertResult(List<Player> playerList,String winnerName, int points) {
		try {
			db =  new DBConnection();
			con = db.createConnection();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
			String playedOn = format.format(new Date());
			String players = playerList.stream().map(o->o.getName()).collect(Collectors.joining(". "));
			
			return insertData(playedOn,players,winnerName,points,playerList.size());
		}
		catch(Exception e){
			e.printStackTrace();
			logger.log(Logger.ERROR, e);
			return Constants.FAIL;
		}
		finally {
			if(db!= null)
				db.closeConnection(con); 
		}
		
		
		
	}
	
	/**
	 * Method which has the DML query to insert into DB
	 * @param playedOn Date on which the game was played 
	 * @param players List of Player in the game
	 * @param winnerName Player object who is the winner
	 * @param winnerPoints Points of the winner Player
	 * @param numberOfPlayers number of players in the game
	 * @return SUCCESS, if inserted into DB, FAIL if error while inserting
	 */
	public  String insertData(String playedOn, String players, String winnerName, int winnerPoints, int numberOfPlayers) {
		// TODO Auto-generated method stub
		try {
			String SQL_INSERT = new String("INSERT INTO Scoreboard	 (played_on,players,winner,points,number_players) "
					+ "VALUES (?, ?, ?, ?, ?);");
			
			if(con == null) {
				logger.log(Logger.ERROR,"Communication Failure");
				return Constants.FAIL;
			}
			PreparedStatement statement = con.prepareStatement(SQL_INSERT);
		      
		      statement.setString(1,playedOn);
		      statement.setString(2, players);
		      statement.setString(3, winnerName);
		      statement.setInt(4, winnerPoints);
		      statement.setInt(5, numberOfPlayers);
		      
		      statement.executeUpdate();
		      
		      return Constants.SUCCESS;
		    		  
		 
		} catch (SQLException e) {
			logger.log(Logger.ERROR, e);
			  return Constants.FAIL;
		}
		
		
	}
	/**
	 * This methods gets the data from the ScoreBoard table in a descending order of date played
	 * @return List of Leaderboard Objects
	 */
	public  List<LeaderBoard> getData() {
		// TODO Auto-generated method stub
		try {
			
			db =  new DBConnection();
			con = db.createConnection(); 
			String SQL_SELECT = new String("Select * from Scoreboard order by played_on desc");
			if(con == null)
			{
				return null;
			}
			PreparedStatement statement = con.prepareStatement(SQL_SELECT);
			
		     ResultSet rs = statement.executeQuery();
		     List<LeaderBoard> leaderBoardList = new  ArrayList<LeaderBoard>();
		     
		      while (rs.next())
		      {
		    	LeaderBoard leaderBoard = new LeaderBoard();
		    	leaderBoard.setId(rs.getInt("id"));
		    	//System.out.println(rs.getTimestamp(rs.getString("played_on")));
		        leaderBoard.setPlayedOn(rs.getString("played_on"));
		        leaderBoard.setPlayers(rs.getString("players"));
		        leaderBoard.setWinner(rs.getString("winner"));
		        leaderBoard.setPoints( rs.getInt("points"));
		        leaderBoard.setNumberOfPlayers(rs.getInt("number_players"));
		        leaderBoardList.add(leaderBoard);
		        
		      }
		      statement.close();
		      
		      return leaderBoardList;
		    		  
		 
		} catch (SQLException e) {
			logger.log(Logger.ERROR, e);
			  return null;
			  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
		
	}


}
