package rps.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import rps.bean.GameState;
import rps.bean.Player;

public class GameStateTest extends TestCase{
	
	GameState gameState; 

	protected void setUp() throws Exception {
		super.setUp();
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(new Player("Sanket"));
		playerList.add(new Player("Rohit"));
		gameState =  new GameState(playerList);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		gameState = null;
	}
	

	public final void testGetListGameStates() {
		{
			Player p1 = new Player("Sanket");
			Player p2 = new Player("Rohit");
			gameState = new GameState(p1, p2, 1);
			assertTrue(GameState.getListGameStates().size() == 0);
		}
	} 

	public final void testGetCurrentRound() {
		//positive test cases
		{
			Player p1 = new Player("Sanket");
			Player p2 = new Player("Rohit");
			gameState = new GameState(p1, p2, 2);
			assertTrue(gameState.getCurrentRound() == 2);
		}
		
		//negative test cases
		{
			Player p1 = new Player("Sanket");
			Player p2 = new Player("Rohit");
			gameState = new GameState(p1, p2, 1);
			assertTrue(gameState.getCurrentRound() != 0);
		}	
	}

	public final void testIsGameEnd() {
		//test positive test cases
		{
			assertTrue(GameState.isGameEnd());
		}
		{
			List<Player> playerList = new ArrayList<Player>();
			playerList.add(new Player("Sanket"));
			playerList.add(new Player("Rohit"));
			playerList.add(new Player("Apurva"));
			gameState = new GameState(playerList);
			gameState.buildStages();
			assertTrue(!GameState.isGameEnd());
		}
	}

	public final void testBuildStages() {
		//test for 2 player
		{
			gameState.buildStages();
			assertTrue(GameState.getListGameStates().size() == 3);
		}
		
		//test for more than 2 players
		{
			List<Player> playerList = new ArrayList<Player>();
			playerList.add(new Player("Sanket"));
			playerList.add(new Player("Rohit"));
			playerList.add(new Player("Apurva"));
			gameState = new GameState(playerList);
			gameState.buildStages();
			System.out.println(GameState.getListGameStates().size());
			assertTrue(GameState.getListGameStates().size() == 9);
			
		}
	}

	public final void testGetPlayerList() {
		{
			assertTrue(gameState.getPlayerList().size() == 2);
		}	
	}

	public final void testGetNextState() {
		//positive test cases
		{
			assertNull(GameState.getNextState());
		}
		
		//test for more than 2 players
		{
			List<Player> playerList = new ArrayList<Player>();
			playerList.add(new Player("Sanket"));
			playerList.add(new Player("Rohit"));
			playerList.add(new Player("Apurva"));
			gameState = new GameState(playerList);
			gameState.buildStages();
			assertTrue(GameState.getNextState() != null); ;
			
		}
		
		
	}

	public final void testGetPlayer1() {
		{
			Player p1 = new Player("Sanket");
			Player p2 = new Player("Rohit");
			gameState = new GameState(p1, p2, 1);
			assertTrue(gameState.getPlayer1().getName().equals("Sanket"));
		}
	}

	public final void testGetPlayer2() {
		{
			Player p1 = new Player("Sanket");
			Player p2 = new Player("Rohit");
			gameState = new GameState(p1, p2, 1);
			assertTrue(gameState.getPlayer2().getName().equals("Rohit"));
		}	
	}


}
