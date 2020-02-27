package rps.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import rps.bean.GameState;
import rps.bean.LeaderBoard;
import rps.bean.Player;
import rps.constants.Constants;
import rps.db.DBService;
import rps.db.FileService;
import rps.logger.Logger;
import rps.logger.PrintLogger;

/**
 * This class is the main class of the system. It holds all the base logic of the game for ROCK PAPER and SCISSORS. 
 * It contains the total Player List and the current GameState of the tournament
 * @author Sanket
 *
 */
public class GameEngine implements LeaderbOard {
	 
	/**
	 * List of players in the tournament
	 */
	List<Player> playerList = null; 
	/**
	 * Logger for the game
	 */
	PrintLogger logger;
	
	/**
	 * Current state of the Game
	 */
	private  GameState currentGameState;
	
	/**
	 * Gets the current State of the Game
	 * @return GameState object which depics the current State
	 */
	public  GameState getCurrentGameState() {
		return currentGameState; 
	}

	/**
	 * Setter method for the current gameState
	 * @param gameState Gamestate that is to be set in the currentGameState instance variable
	 */
	public  void setCurrentGameState(GameState gameState) {
		currentGameState = gameState;
	}

	/**
	 * ENUM which holds the lements of the Game
	 * @author Sanket
	 *
	 */
	public enum Element{
		ROCK, PAPER, SCISSORS;
		/**
		 * BetasList which holds the ELement which can the beat the element is being called upon
		 */
		public List<Element> beatsList;
		
		/**
		 * Returns a boolean value whether the passed element is present in the  beats list 
		 * @param element Element object to check whether it wins with the compared element
		 * @return true if the param Element object beats the object on whom it is called and false if vice versa
		 */
		public boolean beats(Element element) {
			return beatsList.contains(element);
		}
		
		static {
			SCISSORS.beatsList = Arrays.asList(PAPER);
			ROCK.beatsList = Arrays.asList(SCISSORS);
			PAPER.beatsList = Arrays.asList(ROCK);
		}
		
	}	
	
	/**
	 * Game Engine Constructor which initializes the logger and the playerList
	 */
	public GameEngine() {
		playerList = new ArrayList<Player>(); 
		logger = new PrintLogger();
		
	}
	
	/**
	 * gets the winner element from the 2 elements that are being passed
	 * @param element1 element which is compared to element2
	 * @param element2 element2 whitch is being compared to element1
	 * @return Element elelemt2 if it beats element1 or element2 if it beats element1
	 */
	public Element getWinnerForElements(Element element1,Element element2) {
		
		if(element1 == null || element2 == null)
			throw new NullPointerException("Element cannot be null");
		
		if(element1.beats(element2))
			return element1;
		else if (element2.beats(element1))
			return element2;
		else
			return null;
		
	}
	
	
	/**
	 * Getter for the playerList
	 * @return List of Player objects
	 */
	public List<Player> getPlayerList() {
		return playerList;
	}
	
	
	/**
	 * Add the player object to the PlayerList
	 * @param player player Object
	 */
	public void joinPlayerInTheGame(Player player) {
		if(player == null) {
			String error = "Player cannot be null while adding";
			logger.log(Logger.ERROR, error);
			throw new NullPointerException(error);
		}
			
		playerList.add(player);
		
	}
	
	/**
	 * Writes the game results to persistent storage, DB or File
	 * @return String return SUCCESS on sriting successfully and FAIL if ther;s failure on storing
	 */
	public String processFinalWinner() {
		DBService dbService = new DBService();
		List<Player> sortedByPointsPlayerList = sortByPoints();
		
		String winner;
		if(sortedByPointsPlayerList.get(0).getPoints() == sortedByPointsPlayerList.get(1).getPoints())
			winner = "Tie";
		else
			winner = sortedByPointsPlayerList.get(0).getName();
		
		logger.log(Logger.INFO,"Checking DB connection");
		if(Constants.USE_DB && dbService.isConnecting())
		{
			logger.log(Logger.INFO,"Inserting into Database"); 
			return dbService.insertResult(sortedByPointsPlayerList, winner, sortedByPointsPlayerList.get(0).getPoints());
		}
		else {
			logger.log(Logger.INFO,"Inserting into File"); 
			FileService fileService = new FileService();
			return fileService.writeToFile(sortedByPointsPlayerList,winner,sortedByPointsPlayerList.get(0).getPoints());
		}
		
	}
	
	/**
	 * Sort the current playerList according to the points achieved  ordered by maximum first
	 * @return
	 */
	public List<Player> sortByPoints(){
		List<Player> displayList = playerList.stream().collect(Collectors.toList());
		Collections.sort(displayList);
		return displayList;
		
	}
	
	/**
	 * Process the Win Event by updating the leaderBoard for the player 
	 * @param winnerElement winner element
	 * @param player1 Player object for player1
	 * @param player2 player object for player2
	 * @return Map of Winner Loser key with values of Respective Player Objects
	 */
	public Map<String,Player> processWinEvent(Element winnerElement, Player player1, Player player2) {
		Map<String,Player> winnerLoserMap = getWinnerLoserPlayerMapFromElement(winnerElement, player1, player2);
		updateLeaderBoard(winnerLoserMap.get(Constants.WINNER));
		displayLeaderBoard(sortByPoints()); 
		return winnerLoserMap;
		
	}
	
	/**
	 * Updates the leaderboard for both players by increments their points by one
	 * @param player1 Object for Player 1 whose points are to be incremented
	 * @param player2 Object for Player 2 whose points are to be incremented
	 */
	public void processTieEvent(Player player1, Player player2) {
		updateLeaderBoard(player1);
		updateLeaderBoard(player2);
	}
	
	
	/**
	 * builds a winnerLoser Map object with key as Winner and Loser and values as the Players respectively
	 * @param winnerElement winner LEmenet
	 * @param player1 Player object for player1
	 * @param player2 player object for player2
	 * @return Map of Winner Loser key with values of Respective Player Objects
	 */
	public Map<String,Player> getWinnerLoserPlayerMapFromElement(Element winnerElement,Player player1, Player player2) {
		if(winnerElement == null || (player1.getSelectedChoice().name().equals(player2.getSelectedChoice().name())))
			return null;
		else {
			if(player1 != null && player2 != null) {
				Map<String,Player> winnerLoserMap = new HashMap<String,Player>();
				if(player1.getSelectedChoice().equals(winnerElement)) {
					winnerLoserMap.put(Constants.WINNER,player1);
					winnerLoserMap.put(Constants.LOSER,player2);
				}
				else {
					winnerLoserMap.put(Constants.WINNER,player2);
					winnerLoserMap.put(Constants.LOSER,player1);
				}
			
				return winnerLoserMap;
			}else
				throw new NullPointerException("Players cannot be null");
			
		}
	}
	
	/**
	 * Entry point for the game from the RPS Controller. initialises and builds the game stages for the tournbament and sets the first stage as current stage
	 */
	public void initializeGameState() {
		GameState newGameState = new GameState(getPlayerList());
		setCurrentGameState(newGameState);
		getCurrentGameState().buildStages();
		setCurrentGameState(GameState.getNextState());
	}
	
	/**
	 * Gets the next stage of the tournament
	 * @return GameState object which has the next gameState
	 */
	public GameState getNextGameState() {
		setCurrentGameState(GameState.getNextState());
		return getCurrentGameState();
		
		
	}
	
	/**
	 * Sets the Element object in the player who made the choice
	 * @param player Player object who mad a choice
	 * @param choice Element type which is attached to the pLayer object
	 */
	public void setElementInPlayer(Player player,String choice) {
		
		if(choice == null) {
			String error = "Choice cannot be null";
			logger.log(Logger.ERROR, error);
			throw new NullPointerException(error);
		}
		
		if(player == null) {
			String error = "Player cannot be null";
			logger.log(Logger.ERROR, error);
			throw new NullPointerException(error);
		}
			
		
		if(choice.equalsIgnoreCase(Constants.ROCK_SIGN)) {
			player.setSelectedChoice(Element.ROCK);
		}else if(choice.equalsIgnoreCase(Constants.PAPER_SIGN)) {
			player.setSelectedChoice(Element.PAPER);
		}
		else if(choice.equalsIgnoreCase(Constants.SCISSORS_SIGN)) {
			player.setSelectedChoice(Element.SCISSORS);
		}else {
			String error = "Invalid Choice. Please enter R,P or S Only";
			logger.log(Logger.ERROR, error);
			throw new IllegalArgumentException(error);
		}
		
	}
	
	
	/**
	 * Updates the leaderboard by incrementing 1 point of the player
	 * @param player PLayer object on whom the point is to be increased
	 */
	private void updateLeaderBoard(Player player) {
		if(player == null)
			throw new NullPointerException("player cannot be null");
		player.incrementPoint();
		
	}
	
	
	/**
	 * To display the leadrboard on the console for debuggin purposes
	 * @param displayList List of Players
	 */
	private void displayLeaderBoard(List<Player> displayList) {
		
		System.out.println("============================LeaderBoard==================================");
		for(Player player:playerList) {
			System.out.println(player.getName() + "\t" + player.getPoints());
		}
		System.out.println("=========================================================================");		
	}

	/**
	 * Initailses the Game by Setting the players name in Player object and Joiing the players to the game
	 * @param namesOfPlayers List of Player names taken from the input
	 */
	public void initializeGame(List<String> namesOfPlayers) {
		
		if(namesOfPlayers == null)
			throw new NullPointerException("List of PLayers cannot be null");
		
		for(String playerName:namesOfPlayers) {
			Player player = new Player(playerName);
			joinPlayerInTheGame(player);
		}
		logger.log(Logger.INFO, "Players and Game initalized");
	}

	/**
	 * Get Past ScoreBoard/LEaderboard data from persistent Storage, DB in this case
	 * @param useDB a boolean variable which holds whether to use DB or not
	 * @return List A list of Leaderboard
	 */
	public List<LeaderBoard> getLeaderBoardData(boolean useDB) {
			List<LeaderBoard> leaderBoardList = new ArrayList<LeaderBoard>();
			DBService dbService = new DBService();
			logger.log(Logger.INFO,"Checking DB connection");
			if(useDB && dbService.isConnecting())
			{
				logger.log(Logger.INFO,"Inserting into Database"); 
				leaderBoardList = dbService.getData();
			}
			else {
				logger.log(Logger.INFO,"Inserting into File"); 
				FileService fileService = new FileService();
				leaderBoardList = fileService.getData();
			}
			
			return leaderBoardList;
		
	}
	
	


	
}
