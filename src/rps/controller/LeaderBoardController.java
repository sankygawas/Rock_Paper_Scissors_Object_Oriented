package rps.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import rps.bean.LeaderBoard;
import rps.gui.MainView;

/**
 * This class represents the Controller for the leaderBoard View
 * @author Sanket
 *
 */
public class LeaderBoardController {
	
	
	@FXML
	public TableView<LeaderBoard> leaderBoardTbl;
	
	@FXML
	private TableColumn<LeaderBoard, Integer> id, points; 
	
	@FXML
	private TableColumn<LeaderBoard, String> players,winner,date;
	
	
	/**
	 * controller to set the leaderboard veiw data
	 * @param leaderboard
	 */
	public void updateLeaderboard(List<LeaderBoard> leaderboard){
		final ObservableList<LeaderBoard> data = FXCollections.observableArrayList(leaderboard);
		
			id.setCellValueFactory(new PropertyValueFactory<LeaderBoard,Integer>("id"));
			points.setCellValueFactory(new PropertyValueFactory<LeaderBoard,Integer>("points"));
			players.setCellValueFactory(new PropertyValueFactory<LeaderBoard,String>("players"));
			winner.setCellValueFactory(new PropertyValueFactory<LeaderBoard,String>("winner"));
			date.setCellValueFactory(new PropertyValueFactory<LeaderBoard,String>("playedOn"));
			leaderBoardTbl.setItems(data);
	}
	
	/**
	 * controller to go to the menu screen
	 */
	public void onMenu() {
		Stage stage = (Stage) leaderBoardTbl.getScene().getWindow();
	    // do what you have to do
	    stage.close();	
	    stage = MainView.getPrimaryStage();
	    MainView.getInstance().initialize();
		stage.show();
	}
	
}
