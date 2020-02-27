package rps.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import rps.bean.GameState;
import rps.bean.Player;
import rps.constants.Constants;
import rps.gui.MainView;

/**
 * This class represents the Game Board of the controller
 * @author Sanket
 *
 */
public class BoardController{
	
	@FXML
	private Button paperBtn1,paperBtn2,rockBtn1,rockBtn2,scissorsBtn1,scissorsBtn2,exitBtn,nextBtn;
	
	@FXML
	private Label player1Lbl,player2Lbl,winnerLbl,roundLbl;
	
	@FXML
	private Label player1Selection,player2Selection;
	
	@FXML
	 private ListView<String> scorecardName;
	
	@FXML
	private ListView<Integer> scorecardPoints;
	
	@FXML
	private ListView<String> stageList;
	
	private Map<Integer,String> choiceMap = new HashMap<Integer,String>();
	
	/**
	 * gets the singleton instance of RPSController
	 */
	RPSController rpsController = MainView.getInstance().getRpsController();
	
	public BoardController() {
		//RPSController.boardController = this;
	}
	
	/**
	 * controller to update the player names in the model
	 * @param player1Name name of player 1
	 * @param player2Name name of player 2
	 * @param currentRound current round number
	 */
	public void updatePlayerNames(String player1Name, String player2Name, int currentRound){
		
		player1Lbl.setText(player1Name);
		player2Lbl.setText(player2Name);
		roundLbl.setText("Round "+(currentRound+1));
		choiceMap.clear();
		
	}
	
	/**
	 * controller to get outcome on paper submit by player 1
	 */
	@FXML
	public void onPaperSubmit1() {
		RPSController rpsController = MainView.getInstance().getRpsController();
		System.out.println("Paper1 called");
		hideButtons(Constants.PAPER_SIGN, 1);
		choiceMap.put(1, Constants.PAPER_SIGN);
		if(checkIfBothPlayed()) {
			rpsController.getOutcome(choiceMap);
		}
			
			
	}
	
	
	/**
	 * controller to get outcome on paper submit by player 1
	 */
	private boolean checkIfBothPlayed() {
		if(choiceMap.containsKey(1) && choiceMap.containsKey(2))
			return true;
		else
			return false;
	}
	
	/**
	 * controller to get outcome on rock submit by player 1
	 */
	@FXML
	public void onRockSubmit1() {
		System.out.println("Rock1 called");
		hideButtons(Constants.ROCK_SIGN, 1);
		choiceMap.put(1, Constants.ROCK_SIGN);
		if(checkIfBothPlayed()) {
			rpsController.getOutcome(choiceMap);
		}
	}
	
	/**
	 * controller to get outcome on scissors submit by player 1
	 */
	@FXML
	public void onScissorsSubmit1() {
		System.out.println("Scissors1 called");
		hideButtons(Constants.SCISSORS_SIGN, 1);
		choiceMap.put(1, Constants.SCISSORS_SIGN);
		if(checkIfBothPlayed()) {
			rpsController.getOutcome(choiceMap);
		}
	}
	
	/**
	 * controller to get outcome on paper submit by player 2
	 */
	@FXML
	public void onPaperSubmit2() {
		System.out.println("Paper2 called");
		hideButtons(Constants.PAPER_SIGN, 2);
		choiceMap.put(2, Constants.PAPER_SIGN);
		if(checkIfBothPlayed()) {
			rpsController.getOutcome(choiceMap);
		}
	}
	
	/**
	 * controller to get outcome on rock submit by player 2
	 */
	@FXML
	public void onRockSubmit2() {
		System.out.println("Rock2 called");
		hideButtons(Constants.ROCK_SIGN, 2);
		choiceMap.put(2, Constants.ROCK_SIGN);
		if(checkIfBothPlayed()) {
			rpsController.getOutcome(choiceMap);
		}
	}
	
	/**
	 * controller to get outcome on paper scissors by player 2
	 */
	@FXML
	public void onScissorsSubmit2() {
		System.out.println("Scissors2 called");
		hideButtons(Constants.SCISSORS_SIGN, 2);
		choiceMap.put(2, Constants.SCISSORS_SIGN);
		if(checkIfBothPlayed()) {
			rpsController.getOutcome(choiceMap);
		}
	}
	
	/**
	 * HIdes the buttons on player choice
	 */
	public void hideButtons(String choice,int playerNumber){
		
		
		switch (playerNumber){
			case 1:
				paperBtn1.setDisable(true);
				scissorsBtn1.setDisable(true);
				rockBtn1.setDisable(true);
				break;
			case 2:
				paperBtn2.setDisable(true);
				scissorsBtn2.setDisable(true);
				rockBtn2.setDisable(true);
				break;
			default:
				break;
		}
		
		
	}
	
	/**
	 * ebales the buttons
	 */
	public void enableButtons(){
		
			paperBtn1.setDisable(false);
			scissorsBtn1.setDisable(false);
			rockBtn1.setDisable(false);
			paperBtn2.setDisable(false);
			scissorsBtn2.setDisable(false);
			rockBtn2.setDisable(false);
		
	}

	/**
	 * update the winner loser in the result label
	 * @param winnerName name of the winiing player
	 * @param winnerChoice element of the winning player
	 * @param loser element of the losing player
	 * @param loserChoice element of the loser player
	 * @param leaderBoard list of players with updated points
	 * @param round round number
	 * @param isGameEnd boolean which specifies if the game is over or not
	 */
	public void updateWinnerLoser(String winnerName, String winnerChoice,String loser,String loserChoice, List<Player> leaderBoard,int round, boolean isGameEnd) {

		nextBtn.setVisible(true);
		scorecardName.setItems(FXCollections.observableArrayList (leaderBoard.stream().map(a->a.getName()).collect(Collectors.toList())));  
		
		scorecardPoints.setItems(FXCollections.observableArrayList (leaderBoard.stream().map(a->a.getPoints()).collect(Collectors.toList())));  
		
		roundLbl.setText("Round "+(round+1));
        
		winnerLbl.setText("Its " + winnerChoice + " against " + loserChoice + ". " + winnerName + " wins this FaceOff");
		
		if(isGameEnd) {
			nextBtn.setVisible(false);
			if(leaderBoard.get(0).getPoints() == leaderBoard.get(1).getPoints())
				winnerLbl.setText("The Game is a Tie");
			else
				winnerLbl.setText(leaderBoard.get(0).getName() +" is the winner for this Game ");
		}
	}
	
	/**
	 * Controller to update the Tie event
	 * @param player1Choice element choice of player2
	 * @param player2Choice element choice of player2
	 * @param leaderBoard list of players with updated points
	 * @param round round number
	 * @param isGameEnd whether game end or not
	 */
	public void Tie( String player1Choice,String player2Choice, List<Player> leaderBoard,int round, boolean isGameEnd) {

		nextBtn.setVisible(true);
		scorecardName.setItems(FXCollections.observableArrayList (leaderBoard.stream().map(a->a.getName()).collect(Collectors.toList())));  
		
		scorecardPoints.setItems(FXCollections.observableArrayList (leaderBoard.stream().map(a->a.getPoints()).collect(Collectors.toList())));  
        
		roundLbl.setText("Round "+(round+1));
		
		winnerLbl.setText("Its " + player1Choice + " against " + player2Choice + ". This Faceoff is a Tie");
		
		if(isGameEnd) {
			nextBtn.setVisible(false);
			if(leaderBoard.get(0).getPoints() == leaderBoard.get(1).getPoints())
				winnerLbl.setText("The Game is a Tie");
			else
				winnerLbl.setText(leaderBoard.get(0).getName() +" is the winner for this Game ");
		}
	}
	
	/**
	 * controller to go to the next stage
	 */
	public void getNextStage() {
		nextBtn.setVisible(false);
		enableButtons();
		winnerLbl.setText("");
		
		rpsController = MainView.getInstance().getRpsController();
		rpsController.getNextState();
	}
	
	/**
	 * controller to call the exit action
	 */
	@FXML
	public void onExit() {
		Stage stage = (Stage) exitBtn.getScene().getWindow();
	    // do what you have to do
	    stage.close();	
	    rpsController = MainView.getInstance().getRpsController();
	    rpsController.auditGame();
	    }

	/**
	 * controlelr to call the menu action
	 */
	@FXML
	public void onMenu() {
		
		Stage stage = (Stage) exitBtn.getScene().getWindow(); 
	    // do what you have to do
	    stage.close();	
	    rpsController = MainView.getInstance().getRpsController();
		rpsController.auditGame();
	    stage = MainView.getPrimaryStage();
	    MainView.getInstance().initialize();
		stage.show();
		
		
	    
	}

	/**
	 * controller to update the stages list of the tournament
	 * @param listGameStates
	 */
	public void updatedStageList(List<GameState> listGameStates) {
		// TODO Auto-generated method stub
		
		List<String> l = listGameStates.stream().map(a->a.getPlayer1().getName() + " Vs " + a.getPlayer2().getName() + " - Round:"+(a.getCurrentRound()+1)).collect(Collectors.toList());
		ObservableList<String> ol =  FXCollections.observableArrayList (l);
		
		stageList.setItems(ol);
		stageList.getSelectionModel().select(GameState.getStageCounter()-1);
		//stageList.getFocusModel().focus(1);
	}
}
