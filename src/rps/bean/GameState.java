package rps.bean;

import java.util.ArrayList;
import java.util.List;


/**
 * This class represents the statte of a game at any given point of time. It carries a state by specifying the current 2 players that are for faceOff and the current round number they are in. 
 * This class also holds a list of GameStates which are used for the Game Tournament. 
 * @author Sanket
 *
 */
public class GameState {

	/**
	 * A counter for the Current stage of the Game States in the Tournament
	 */
	private static int currentStageCounter = 0; 
	
	/**
	 * Holds the total rounds for a tournament
	 */
	private int TOTAL_ROUNDS = 3;
	/**
	 * istGameStates Static as to just keep one copy of game stages
	 */
	private static List<GameState> listGameStates;
	/**
	 * A list of total players in the game
	 */
	private List<Player> playerList;
	
	/** 
	 * Stores the current context of the round
	 */
	private int currentRound;
	
	/**
	 * Stores the the current players playing in the faceOff
	 */
	private Player player1,player2;	    
	
	/**
	 * Gets the stageCOunter
	 * @return integer value whic returns the stageCounter of the tournament
	 */
	public static int getStageCounter() {
		return currentStageCounter;
	}
	
	/**
	 * Gets the list of gameStates, ie the tournament matches
	 * @return List the static GameState List which holds all the game stages
	 */
	public static List<GameState> getListGameStates() {
		return listGameStates;
	}
	
	/**
	 * Gets the current Round
	 * @return int which returns the current ongoing round
	 */
	public int getCurrentRound() {
		return currentRound;
	}
	
	/**
	 * GameState constrcutor which takes the playerList as input
	 * @param playerList List of Player Objects
	 */
	public GameState(List<Player> playerList) {
		currentRound = 1;
		this.playerList = playerList;
		listGameStates = new ArrayList<GameState>();
		currentStageCounter = 0;
	}
	
	/**
	 * Constructor which takes player1 and player2 and the round number as input parameter
	 * @param player1 Player object for player 1
	 * @param player2 Player object for player2
	 * @param round round-number in integer
	 */
	public GameState(Player player1, Player player2, int round) {
		this.player1 = player1;
		this.player2 = player2;
		this.currentRound = round; 
	}
	
	/**
	 * checks whether it's the last stage of the game
	 * @return True if it's the last stage of the game, false if not the last stage of the game
	 */
	public static boolean isGameEnd() {
		return (currentStageCounter-1  >= listGameStates.size()-1);
	}

	/**
	 * This method builds the stages for the tournament and stores it in the List of GameState
	 */
	public void buildStages() {
		
		listGameStates = new ArrayList<GameState>();
		for(int round=0;round<TOTAL_ROUNDS;round++) {
			
			//this logic is used to play one player to all other players
			for(int i=0;i<getPlayerList().size();i++) {	
				Player player1 = getPlayerList().get(i);
				for(int j=i+1;j<getPlayerList().size();j++) {
					Player player2 = getPlayerList().get(j);
					if(player1.equals(player2)) {
						continue;
					}
						
					else {
						System.out.println("Playing " + player1.getName() + " against " + player2.getName());
						//logger.log(Logger.INFO, "Playing " + player1.getName() + " against " + player2.getName());
						listGameStates.add(new GameState(player1,player2,round));
					}
				
				}
			}
		}
		
	}
	
	/**
	 * Returns the list of total players in the tournament
	 * @return List of players
	 */
	public List<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * gets the nextStge of the game tournament
	 * @return next stage of the tournament of type GameState
	 */
	public static GameState getNextState() {
		
		if(currentStageCounter > listGameStates.size()-1)
			return null;  
		else
			return listGameStates.get(currentStageCounter++);
		
	}
	
	/**
	 * getter method for player1
	 * @return Player object for player 1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * getter method for player2
	 * @return Player object for player 2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * Ovverrides the default toString method
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Round:"+currentRound + ". Playing player1:" + player1.toString() + " with player2: " + player2.toString();
	}

}
