package rps.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The class represents the bean to store the past game data into the DB or CSV File
 * @author Sanket
 *
 */
public class LeaderBoard {
	
	/**
	 * id of the leaderBoard Object
	 */
	private int id;
	
	/**
	 * gets the id of the leaderboard object
	 * @return integer with id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the leaderobject with an integer id
	 * @param id integer value of id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * String representing the date time the game was played in the format  yyyy-MM-dd HH:mm:ss
	 */
	private String playedOn;
	
	/**
	 * The players in the game separated by a full stop
	 */
	private String players;
	
	/**
	 * The name of the winning player
	 */
	private String winner;
	
	/**
	 * The total pojnts of the wiining player
	 */
	private int points;
	
	/**
	 * The total number of Players
	 */
	private int numberOfPlayers;
	
	
	/**
	 * Returns the date the game was played in string format of yyyy-MM-dd HH:mm:ss
	 * @return String date playedOn
	 */
	public String getPlayedOn() {
		return playedOn;
	}
	
	/**
	 * Sets the date in string format
	 * @param playedOn String date on which the game was played
	 * @throws ParseException exception upon parsing
	 */
	public void setPlayedOn(String playedOn) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		this.playedOn = format.format(format.parse(playedOn));
	}
	
	/**
	 * get method for  the players instance variable
	 * @return String of players 
	 */
	public String getPlayers() {
		return players;
	}
	
	/**
	 * mutator method for setting players
	 * @param players String of players
	 */
	public void setPlayers(String players) {
		if(players == null || players.trim().equals(""))
			throw new NullPointerException("players cannnot be null");
		this.players = players;
	}
	
	/**
	 * getter method for wiiner name
	 * @return winner name in string
	 */
	public String getWinner() {
		return winner;
	}
	
	/**
	 * Setter method for winner name
	 * @param winner Winner name in String
	 */
	public void setWinner(String winner) {
		if(winner == null || winner.trim().equals(""))
			throw new NullPointerException("Winner cannnot be null");
		this.winner = winner;
	}
	
	/**
	 * Getter method for the winnner points
	 * @return Integer with winnner points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Setter method for the winning player
	 * @param points integer with winning points
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * getter for number of players
	 * @return integer with number of players
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	/**
	 * setter for number of players
	 * @param numberOfPlayers sets the number of players
	 */
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

}
