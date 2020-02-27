package rps.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import rps.controller.RPSController;

/**
 * This Class acts as a abstraction for the RPSController and calls the MAIN view of the game
 * @author Sanket
 *
 */
public class MainView extends Application{ 
	private static final MainView mainViewSingleTon = new MainView();
	private static Stage primaryStage;
	public RPSController rpsController;
	
	/**
	 * Method which returns the reference of the Controller Object
	 * @return RPSController object
	 */
	public RPSController getRpsController() {
		return rpsController;
	}

	/**
	 * Gets the primary stage of the application
	 * @return Stage returns the primary stage of the application
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage= stage;
		primaryStage.setTitle("Stone Paper Scissor!!");
		
		showMainView();
	}
	
	
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
	
	}

	/**
	 * Private method which loads the mainVeiw and the MAinViewController
	 * @throws IOException
	 */
	private void showMainView() throws IOException{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
		Pane root = loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	/**
	 * main method which launches the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}


	/**
	 * Initializes the RPSController
	 */
	public void initialize() {
		rpsController = new RPSController();		
	}

	/**
	 * Returns the instance of this MainView
	 * @return MainViewSingleton object
	 */
	public static MainView getInstance() {
		// TODO Auto-generated method stub
		return mainViewSingleTon;
	}
	

}
