package rps.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import rps.bean.GameState;
import rps.bean.LeaderBoard;
import rps.bean.Player;
import rps.constants.Constants;
import rps.logger.Logger;
import rps.logger.PrintLogger;
import rps.service.GameEngine;
import rps.service.GameEngine.Element;

/**
 * This Class represents the controller, which acts as a mediator between the GUI (or console) and the model 
 * @author Sanket
 *
 */
public class RPSController {
	/**
	 * GameEngine instance variable
	 */
	private  GameEngine rpsGameEngine;
	private PrintLogger logger;

	/**
	 * Reference for Board controler
	 */
	public BoardController boardController;
	/**
	 * Reference for leaderboard controller
	 */
	public LeaderBoardController leaderController;
	
	/**
	 * default Constructor
	 */
	public RPSController() {
		logger = new PrintLogger();
		//boardController = new BoardController();
		if(rpsGameEngine==null)
			rpsGameEngine = new GameEngine();
		
	}
	
	public RPSController getInstance() {
		return this;
	}
	

	/**
	 * controller to set names of players in the game engine
	 * @param namesOfPlayers List of plaey names
	 */
	public void setNamesOfPlayers(List<String> namesOfPlayers) {
		rpsGameEngine.initializeGame(namesOfPlayers);
	}

	/**
	 * controller to get list of players from the game engine
	 * @return
	 */
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return rpsGameEngine.getPlayerList();
	}
	
	/**
	 * Controller to start the game
	 * @param previousStage stage of the previous screen to hide
	 */
	public void play(Stage previousStage) {
		try {
			viewBoard(previousStage);  
			rpsGameEngine.initializeGameState();
			logger.log(Logger.DEBUG,rpsGameEngine.getCurrentGameState().toString());
			if(rpsGameEngine.getCurrentGameState()!= null)
				getChoiceFromPlayers(rpsGameEngine.getCurrentGameState().getPlayer1(),rpsGameEngine.getCurrentGameState().getPlayer2());
			else {
				logger.log(Logger.INFO, "Game has Ended");
				rpsGameEngine.processFinalWinner();
			}
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			logger.log(Logger.ERROR, "Error in RPSController.play " +e.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.log(Logger.ERROR, "Error in RPSController.play() " +e.toString());
		}
		
	}

	
	/**
	 * View the GameBoard
	 * @param previousStage
	 */
	public void viewBoard(Stage previousStage) {
		try {
			FXMLLoader loader = getBoardViewLoader();
			Pane root = loader.load();
			boardController = loader.getController();
			Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setTitle("Board");
	        stage.setScene(scene);
	        stage.show();
	        
	        previousStage.hide();
	        //play();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * controller to get the choice from gui Gets the choice from PLayer1 and Player 2
	 * @param player1
	 * @param player2
	 */
	public void getChoiceFromPlayers(Player player1, Player player2) {
		
		boardController.updatePlayerNames(player1.getName(),player2.getName(),rpsGameEngine.getCurrentGameState().getCurrentRound());
		
		boardController.updatedStageList(GameState.getListGameStates());
	} 
	
	
	/**
	 * controller to update board with result details
	 * @param winnerLoserMap
	 */
	public void updateBoardWithWinner(Map<String,Player> winnerLoserMap) {
		
		if(winnerLoserMap!=null) { // The faceOff is not a Tie
			Player winner = winnerLoserMap.get(Constants.WINNER);
			Player loser = winnerLoserMap.get(Constants.LOSER);
			List<Player> leaderBoard = rpsGameEngine.sortByPoints();
			boardController.updateWinnerLoser(winner.getName(),winner.getSelectedChoice().name(),loser.getName(),loser.getSelectedChoice().name(),leaderBoard,rpsGameEngine.getCurrentGameState().getCurrentRound(),GameState.isGameEnd());
		}else {// The faceOff is A Tie
			List<Player> leaderBoard = rpsGameEngine.sortByPoints();
			boardController.Tie(rpsGameEngine.getCurrentGameState().getPlayer1().getSelectedChoice().name(),rpsGameEngine.getCurrentGameState().getPlayer2().getSelectedChoice().name(),leaderBoard,rpsGameEngine.getCurrentGameState().getCurrentRound(),GameState.isGameEnd());
		
		}
		
	}
	
	/**
	 * controller to get outcome from the faceoff
	 * @param choiceMap
	 */
	public void getOutcome(Map<Integer, String> choiceMap) {
		if(choiceMap != null){ 
				rpsGameEngine.setElementInPlayer(rpsGameEngine.getCurrentGameState().getPlayer1(), choiceMap.get(1));
				rpsGameEngine.setElementInPlayer(rpsGameEngine.getCurrentGameState().getPlayer2(), choiceMap.get(2));
				logger.log(Logger.DEBUG, "nextStage():choice set for both players:"+rpsGameEngine.getCurrentGameState().getPlayer1() + " and " + rpsGameEngine.getCurrentGameState().getPlayer2() );
		} 
		
		Element winnerElement = rpsGameEngine.getWinnerForElements(rpsGameEngine.getCurrentGameState().getPlayer1().getSelectedChoice(), rpsGameEngine.getCurrentGameState().getPlayer2().getSelectedChoice());
		if(winnerElement == null) {
			String msg = "This faceoff is a Tie";
			logger.log(Logger.INFO, msg);
			rpsGameEngine.processTieEvent(rpsGameEngine.getCurrentGameState().getPlayer1(),rpsGameEngine.getCurrentGameState().getPlayer2());
			updateBoardWithWinner(null);
		}
		else {
			Map<String,Player> winnerLoserMap = rpsGameEngine.processWinEvent(winnerElement,rpsGameEngine.getCurrentGameState().getPlayer1(),rpsGameEngine.getCurrentGameState().getPlayer2());
			updateBoardWithWinner(winnerLoserMap);
			
		}
	}
	
	public void getNextState() {
		
		processNextState();
			
	}

	/**
	 * controller to get the next stage from the game engine
	 */
	public void processNextState() {
		if( rpsGameEngine.getNextGameState()!= null){
					getChoiceFromPlayers(rpsGameEngine.getCurrentGameState().getPlayer1(),rpsGameEngine.getCurrentGameState().getPlayer2());
		}
		else {
			logger.log(Logger.INFO, "Game has Ended");
			rpsGameEngine.processFinalWinner(); 
		}
	}

	
	/**
	 * controller to get the board view 
	 * @return
	 * @throws IOException
	 */
	public FXMLLoader getBoardViewLoader() throws IOException{
		return new FXMLLoader(getClass().getResource("/rps/gui/BoardView.fxml"));
	}
	
	/**
	 * controller to get the leadeboard data from the game engine
	 * @param previousStage
	 */
	public void showLeaderBoardView(Stage previousStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/rps/gui/LeaderBoardView.fxml"));
			Pane root = loader.load();
			leaderController = loader.getController();
			Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setTitle("LeaderBoard");
	        stage.setScene(scene);
	       
      	    List<LeaderBoard> leaderboard =  getLeaderBoardData();
      	    if(leaderboard != null) {
	      		 stage.show();
	      		 previousStage.hide();
	      		leaderController.updateLeaderboard(leaderboard);
      	    }
      	    else
  			{
      	    	viewBoard( (Stage)leaderController.leaderBoardTbl.getScene().getWindow());
  			
  			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * gets the leaderboard data from the game engine
	 * @return
	 */
	private List<LeaderBoard> getLeaderBoardData() {
		// TODO Auto-generated method stub
		return rpsGameEngine.getLeaderBoardData(Constants.USE_DB);
		
	}

	/**
	 * controller to audit the game results
	 */
	public void auditGame() {
		
		if(rpsGameEngine.processFinalWinner().equalsIgnoreCase(Constants.SUCCESS))
			logger.log(Logger.INFO, "Game audited succeffully");
		else
			logger.log(Logger.INFO, "Game audit Failed");
	}


}
