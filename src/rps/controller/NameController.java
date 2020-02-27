package rps.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rps.gui.MainView;

/**
 * Controller which for the NameView
 * @author Sanket
 *
 */
public class NameController{ 
	
	@FXML
	private Button playBtn;
	@FXML
	private Slider numberOfPlayersInput;
	@FXML
	private TextField Player1NameTxt,Player2NameTxt,Player3NameTxt,Player4NameTxt,Player5NameTxt;
	@FXML
	private Label Player1NameLbl,Player2NameLbl,Player3NameLbl,Player4NameLbl,Player5NameLbl,nameValid;
	
	private int numberOfPlayers = 2;
	private List<String> namesOfPlayers;
	
	RPSController rpsController = MainView.getInstance().getRpsController();
	
	/**
	 * controller to start the game
	 * @throws IOException
	 */
	@FXML
	public void play() throws IOException {

		boolean valid = true;
		namesOfPlayers = new ArrayList<String>();
		
		if(numberOfPlayers == 2) {
			if(isEmptyCheck(Player1NameTxt.getText()) || isEmptyCheck(Player2NameTxt.getText())) {
				nameValid.setVisible(true);
				valid = false;
			}
				
			
			namesOfPlayers.add(Player1NameTxt.getText());
			namesOfPlayers.add(Player2NameTxt.getText());
		}
			
		else if(numberOfPlayers == 3) {
			if(isEmptyCheck(Player1NameTxt.getText()) || isEmptyCheck(Player2NameTxt.getText()) || isEmptyCheck(Player3NameTxt.getText())) {
				nameValid.setVisible(true);
				valid = false;
			}
			namesOfPlayers.add(Player1NameTxt.getText());
			namesOfPlayers.add(Player2NameTxt.getText());
			namesOfPlayers.add(Player3NameTxt.getText());
		}
		
		else if(numberOfPlayers == 4) {
			if(isEmptyCheck(Player1NameTxt.getText()) || isEmptyCheck(Player2NameTxt.getText()) || isEmptyCheck(Player3NameTxt.getText()) || isEmptyCheck(Player4NameTxt.getText())) {
				nameValid.setVisible(true);
				valid = false;
			}
			namesOfPlayers.add(Player1NameTxt.getText());
			namesOfPlayers.add(Player2NameTxt.getText());
			namesOfPlayers.add(Player3NameTxt.getText());
			namesOfPlayers.add(Player4NameTxt.getText());
		}
		else if(numberOfPlayers == 5) {
			if(isEmptyCheck(Player1NameTxt.getText()) || isEmptyCheck(Player2NameTxt.getText()) || 
					isEmptyCheck(Player3NameTxt.getText()) || isEmptyCheck(Player4NameTxt.getText()) || isEmptyCheck(Player5NameTxt.getText())) {
				nameValid.setVisible(true);
				valid = false;
			}
			namesOfPlayers.add(Player1NameTxt.getText());
			namesOfPlayers.add(Player2NameTxt.getText());
			namesOfPlayers.add(Player3NameTxt.getText());
			namesOfPlayers.add(Player5NameTxt.getText());
		}
		
		if(valid) {
			rpsController.setNamesOfPlayers(namesOfPlayers);
			viewBoard((Stage) playBtn.getScene().getWindow());
		}
		
	}
	
	/**
	 * controller to view the game board
	 * @param stage
	 */
	private void viewBoard(Stage stage) {
		rpsController.play(stage);
		
	}

	/**
	 * set the name fields for the players
	 */
	@FXML
	public void setNameFields() {
		numberOfPlayers = (int)numberOfPlayersInput.getValue();
		System.out.println(numberOfPlayers);
		switch (numberOfPlayers){
			case 2:
				Player3NameTxt.setVisible(false);
				Player3NameLbl.setVisible(false); 
				Player4NameTxt.setVisible(false);
				Player4NameLbl.setVisible(false); 
				Player5NameTxt.setVisible(false);
				Player5NameLbl.setVisible(false);
				break;
			case 3:
				Player3NameTxt.setVisible(true);
				Player3NameLbl.setVisible(true); 
				break;
			case 4:
				Player3NameTxt.setVisible(true);
				Player3NameLbl.setVisible(true); 
				Player4NameTxt.setVisible(true);
				Player4NameLbl.setVisible(true); 
				break;
			case 5:
				Player3NameTxt.setVisible(true);
				Player3NameLbl.setVisible(true); 
				Player4NameTxt.setVisible(true);
				Player4NameLbl.setVisible(true); 
				Player5NameTxt.setVisible(true);
				Player5NameLbl.setVisible(true); 
				break;
			default:
				break;
		}
			
		
	}
	
	/**
	 * empty check validation
	 * @param str
	 * @return
	 */
	public boolean isEmptyCheck(String str){
		if(str != null && !str.trim().equalsIgnoreCase(""))
			return false; 
		return true;
	}

}
