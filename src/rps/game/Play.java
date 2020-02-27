package rps.game;
import rps.gui.MainView;

/**
 * This class is the entry point of the game. The main method of this class is executed for Game Start
 * @author Sanket
 *
 */
public class Play {
	
	/**
	 * Main method to invoke the game View
	 * @param args args to the main maethod
	 */
	public static void main(String[] args) {
		MainView mainView = MainView.getInstance();
		mainView.initialize();
		MainView.main(null);   
	} 

}
