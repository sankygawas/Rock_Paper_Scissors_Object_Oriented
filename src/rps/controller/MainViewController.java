package rps.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import rps.gui.MainView;

/**
 * This class represents the Conroller for the main View
 * @author Sanket
 *
 */
public class MainViewController {
	
	@FXML
	private  Button exitBtn;
	private Button newGameBtn;
	private Button leaderBoardBtn;
	
	@FXML
	public void onExit() {
		Stage stage = (Stage) exitBtn.getScene().getWindow();
	    // do what you have to do
	    stage.close();	
	    }
	
	/**
	 * controller to show the leaderboard view
	 */
	public void showLeaderBoard() {
		//show leaderBoard
		MainView.getInstance().getRpsController().showLeaderBoardView((Stage) exitBtn.getScene().getWindow());
		System.out.println("LeaderBoard Shown");
	}
	
	/**
	 * load the new game screen
	 */
	@FXML
	public void startNewGame() {

		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/rps/gui/NameView.fxml"));
			Pane root = loader.load(); 
			Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setTitle("Player Selection");
	        stage.setScene(scene);
	        stage.show();
	        
      	    Stage stage2 = (Stage) exitBtn.getScene().getWindow();
	        stage2.hide();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
