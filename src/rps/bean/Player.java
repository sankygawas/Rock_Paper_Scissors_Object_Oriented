package rps.bean;

import rps.service.GameEngine.Element;

/**
 * The class represents the Player with name, current points and selected choice of Element in a Round
 * @author Sanket Gawas
 */
public class Player implements Comparable<Player>{
	
	/**
	 * Name of the Player
	 */
	public String name = "";
	/**
	 * Current points of the player for the Round
	 */
	public int points = 0;
	
	/**
	 * Current selected Element by the player in the round
	 */
	public Element selectedChoice = null;
	
	/**
	 * Default constructor of the Player
	 */
	public Player() {
	}
	
	/**
	 * Constructor which takes name of the Player
	 * @param name  Name of the Player of type String
	 */
	public Player(String name) {
		if(!validateName(name))
			throw new IllegalArgumentException("Name cannot be empty. Please Try Again");
		this.name = name;
	}
	
	/**
	 * Gets the selected choice of Element
	 * @return the selectedChoice of type Element
	 */
	public Element getSelectedChoice() {
		return selectedChoice;
	}
	
	/**
	 * Sets the selected choice of Element
	 * @param choice  Selected Element by the Player
	 */
	public void setSelectedChoice(Element choice) {
		if(choice == null)
			throw new IllegalArgumentException("Choice Cannot be Empty. Please Try Again");
		this.selectedChoice = choice;
	}
	
	/**
	 * Gets the name of the Player
	 * @return String with name of the Player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the Player
	 * @param name  String with name of the player
	 */
	public void setName(String name) {
		if(!validateName(name))
			throw new IllegalArgumentException("Name cannot be empty. Please Try Again");
		this.name = name;
	}
	
	/**
	 * Gets the points for the Player
	 * @return the current Points of the Player
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Sets the points for the PLayer
	 * @param points  Integer with points
	 */
	public void setPoints(int points) {
		if(!validatePoints(points))
			throw new IllegalArgumentException("Points cannot be negative. Please Try Again");
		this.points = points;
	}
	
	/** 
	 * Overrides the equals Object. Return true if the name of the Player is same
	 * @param obj  Object with whose equals method is checked
	 * @return True if the object is Player and the name is same, False otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Player) {
				return ((Player) obj).getName().equals(this.getName());
		}else
			return false;
	}
	
	/**
	 * Increments the point by one for the player
	 */
	public void incrementPoint() {
		this.points+=1;
		
	}
	
	/**
	 * Validates the Name for empty String and null
	 * @param name  String type
	 * @return True if the name is valid, false if the name is null or empty
	 */
	private boolean validateName(String name) {
		if(name == null || name.trim().equalsIgnoreCase(""))
			return false;
		return true;
	}
	
	/**
	 * Validates the points of positive integer value
	 * @param Integer
	 * @return True if no error, false if the points is less than 0
	 */
	private boolean validatePoints(Integer points) {
		if(points < 0)
			return false;
		return true;
	}
	
	/**
	 * Overrides the default toString by returning the Name of the Player, The current choice and the current points in the table
	 * @return a String with Name, selected Element and the points
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name: "+getName() + " Element:"+getSelectedChoice() + " Points:" + getPoints();
	}
	
	/**
	 * Overrides the compareTo method to sort the Player object by descending value of it's points
	 * @param o Player object o with whom the comparison is to be done 
	 * @return integer with value -1, 0 or 1
	 */
	@Override
	public int compareTo(Player o) {
		if(this.points < o.points)
			return 1;
		else if(this.points > o.points)
			return -1;
		return 0;
	}
	
	
}
