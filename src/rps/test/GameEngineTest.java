package rps.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;
import rps.bean.GameState;
import rps.bean.Player;
import rps.constants.Constants;
import rps.service.GameEngine;
import rps.service.GameEngine.Element;

public class GameEngineTest extends TestCase {
	GameEngine ge;

	protected void setUp() throws Exception {
		super.setUp();
		ge =  new GameEngine();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		ge = null;
	}
	
	@Test
	public final void checkForNull() {
			assertNull(ge.getWinnerForElements(null, Element.PAPER));
			assertNull(ge.getWinnerForElements(Element.PAPER, null));
			assertNull(ge.getWinnerForElements(null, null));
	}

	public final void testGameEngine() {
		assertTrue(ge.getPlayerList() != null);
	}

	@Test
	public final void testForWinLogic() {
			GameEngine ge = new GameEngine();
			Element element1 = ge.getWinnerForElements(Element.PAPER, Element.ROCK);
		    assertTrue(element1.equals(Element.PAPER));
		    
			Element element2 = ge.getWinnerForElements(Element.PAPER, Element.SCISSORS);
		    assertTrue(element2.equals(Element.SCISSORS));
		    
			Element element3 = ge.getWinnerForElements(Element.ROCK, Element.SCISSORS);
		    assertTrue(element3.equals(Element.ROCK));
	}
	
	@Test
	public final void testForTie() {
		GameEngine ge = new GameEngine();
		Element element1 = ge.getWinnerForElements(Element.PAPER, Element.PAPER);
	    assertTrue(element1 == null);
	    
		Element element2 = ge.getWinnerForElements(Element.SCISSORS, Element.SCISSORS);
	    assertTrue(element2 == null);
	    
		Element element3 = ge.getWinnerForElements(Element.ROCK, Element.ROCK);
	    assertTrue(element3 == null);
	}
	
	@Test
	public final void testForLoseLogic() {
			GameEngine ge = new GameEngine();
			Element element1 = ge.getWinnerForElements(Element.PAPER, Element.ROCK);
		    assertTrue(!element1.equals(Element.ROCK));
		    
			Element element2 = ge.getWinnerForElements(Element.PAPER, Element.SCISSORS);
		    assertTrue(!element2.equals(Element.PAPER));
		    
			Element element3 = ge.getWinnerForElements(Element.ROCK, Element.SCISSORS);
		    assertTrue(!element3.equals(Element.SCISSORS));
	}

	public final void testGetPlayerList() {
		//null cases
		{
			assertTrue(ge.getPlayerList() != null);
		}
		//positive cases
		{
			Player p = new Player();
			ge.joinPlayerInTheGame(p);
			assertTrue(ge.getPlayerList().size() == 1);
		}
	}
	
	
	public final void testGetNextGameState() {
		
		//Positive test Case
		{
			Player p1 = new Player("Sanket");
			ge.joinPlayerInTheGame(p1);
			Player p2 = new Player("Rohit");
			ge.joinPlayerInTheGame(p2);
			ge.initializeGameState();
			assertTrue(ge.getNextGameState()!= null);
		}
		
		//Positive test Case check for right number of game stages for 2 players
		{
			ge = new GameEngine();
			Player p1 = new Player("Sanket");
			ge.joinPlayerInTheGame(p1);
			Player p2 = new Player("Rohit");
			ge.joinPlayerInTheGame(p2);
			ge.initializeGameState();
			System.out.println(GameState.getListGameStates().size());
			System.out.println(Arrays.toString(GameState.getListGameStates().toArray()));
			ge.getNextGameState();
			assertTrue(GameState.getStageCounter() == 2);
			ge.getNextGameState();
			assertTrue(GameState.getStageCounter() == 3);
			ge.getNextGameState();
			assertTrue(ge.getCurrentGameState() == null);
		}
		
		//Positive test Case check for right number of game stages for 3 players
			{
					ge = new GameEngine();
					Player p1 = new Player("Sanket");
					ge.joinPlayerInTheGame(p1);
					Player p2 = new Player("Rohit");
					ge.joinPlayerInTheGame(p2);
					Player p3 = new Player("Mrun");
					ge.joinPlayerInTheGame(p3);
					ge.initializeGameState();
					System.out.println(GameState.getListGameStates().size());
					System.out.println(Arrays.toString(GameState.getListGameStates().toArray()));
					ge.getNextGameState();
					assertTrue(GameState.getStageCounter() == 2);
					ge.getNextGameState();
					assertTrue(GameState.getStageCounter() == 3);
					ge.getNextGameState();
					assertTrue(GameState.getStageCounter() == 4);
					ge.getNextGameState();
					assertTrue(GameState.getStageCounter() == 5);
					ge.getNextGameState();
					assertTrue(GameState.getStageCounter() == 6);
					ge.getNextGameState();
					assertTrue(GameState.getStageCounter() == 7);
					ge.getNextGameState();
					assertTrue(GameState.getStageCounter() == 8);
					ge.getNextGameState();
					assertTrue(GameState.getStageCounter() == 9);
					ge.getNextGameState();
					assertTrue(ge.getCurrentGameState() == null);
		}
	}
	
	
	public final void testInitializeGameState() {
		
		//nullcheck
		{
			Player p1 = new Player("Sanket");
			ge.joinPlayerInTheGame(p1);
			Player p2 = new Player("Rohit");
			ge.joinPlayerInTheGame(p2);
			ge.initializeGameState();
			assertTrue(ge.getCurrentGameState()!= null);
		}
	}
	
	
	public final void testProcessFinalWinner() {
		
		//nullcheck
		{
			Player p1 = new Player("Sanket");
			p1.incrementPoint();
			p1.incrementPoint();
			ge.joinPlayerInTheGame(p1);
			Player p2 = new Player("Rohit");
			p2.incrementPoint();
			ge.joinPlayerInTheGame(p2);
			ge.initializeGameState();
			assertTrue(ge.processFinalWinner() != null);
		}
		
		//positive test Case{
		{
			ge = new GameEngine();
			Player p1 = new Player("Sanket");
			p1.incrementPoint();
			p1.incrementPoint();
			ge.joinPlayerInTheGame(p1);
			Player p2 = new Player("Rohit");
			p2.incrementPoint();
			ge.joinPlayerInTheGame(p2);
			ge.initializeGameState();
			assertTrue(ge.processFinalWinner().equals(Constants.SUCCESS));
		}
	}

	public final void testJoinPlayerInTheGame() {
		//positive cases
		{
			Player p = new Player();
			ge.joinPlayerInTheGame(p);
			assertTrue(ge.getPlayerList().size() == 1);
		}
		
		//negative cases
		{
			Player p = new Player();
			ge.joinPlayerInTheGame(p);
			assertTrue(ge.getPlayerList().size() != 0);
		}
		
		//nullcheck
		try
		{
			ge.joinPlayerInTheGame(null);
			fail("Did not Throw null pointer Excelption");
			
		}catch(NullPointerException e) {
			assertTrue(true);
		}
		
	}

	public final void testSortByPoints() {
		//positive cases
		{
			Player p1 = new Player("Sanket");
			p1.incrementPoint();
			Player p2 = new Player("Rohit");
			ge.joinPlayerInTheGame(p1);
			ge.joinPlayerInTheGame(p2);
			assertTrue(ge.sortByPoints().get(0).equals(p1));
		}
		
		
		//negativ cases
		{
			ge = null;
			ge = new GameEngine();
			Player p1 = new Player("Sanket");
			Player p2 = new Player("Rohit");
			p2.incrementPoint();
			ge.joinPlayerInTheGame(p1);
			ge.joinPlayerInTheGame(p2);
			assertTrue(!ge.sortByPoints().get(0).equals(p1));
		}
	}

	public final void testProcessTieEvent() {
		//nullcheck
		try
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.ROCK);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			ge.processTieEvent(null, p2);
			fail("Did not throw an Exception");
		}
		catch(NullPointerException e){
			assertTrue(true);
		}
		
		try
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.ROCK);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			ge.processTieEvent(p1, null);
			fail("Did not throw an Exception");
		}		
		catch(NullPointerException e){
			assertTrue(true);
		}
		//positive cases
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.ROCK);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			ge.processTieEvent(p1, p2);
			assertTrue(p1.getPoints() == p2.getPoints());
		}
		
		//negative cases
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.ROCK);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			p2.incrementPoint();
			ge.processTieEvent(p1, p2);
			assertTrue(p1.getPoints() != p2.getPoints());
		}
		
	}

	public final void testGetWinnerLoserPlayerMapFromElement() {
		//null check
		try
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.ROCK);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			ge.getWinnerLoserPlayerMapFromElement(Element.PAPER, null, p2);
			fail("Did not throw an Exception");
		}catch(NullPointerException e){
			assertTrue(true);
		}
		
		try
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.ROCK);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			ge.getWinnerLoserPlayerMapFromElement(Element.PAPER, p1, null);
			fail("Did not throw an Exception");
		}catch(NullPointerException e){
			assertTrue(true);
		}
		
		//size of 2 check{
		{

			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.ROCK);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			Map<String,Player> map = ge.getWinnerLoserPlayerMapFromElement(Element.PAPER, p1, p2);
			assertTrue(map.keySet().size() == 2);
		}
		
		//positive Cases Win
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.ROCK);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			Map<String,Player> map = ge.getWinnerLoserPlayerMapFromElement(Element.PAPER, p1, p2);
			assertTrue(map.get(Constants.WINNER).equals(p2));
			assertTrue(map.get(Constants.LOSER).equals(p1));
		}
		
		
		//negative cases Win
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.ROCK);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			Map<String,Player> map = ge.getWinnerLoserPlayerMapFromElement(Element.PAPER, p1, p2);
			assertTrue(!map.get(Constants.WINNER).equals(p1));
			assertTrue(!map.get(Constants.LOSER).equals(p2));
		}
		
		//Tie Case Positive
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.PAPER);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			Map<String,Player> map = ge.getWinnerLoserPlayerMapFromElement(null, p1, p2);
			assertTrue(map == null);
		}
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.PAPER);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			Map<String,Player> map = ge.getWinnerLoserPlayerMapFromElement(Element.PAPER, p1, p2);
			assertTrue(map == null);
		}
		
		
		//Tie Case Negative
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.PAPER);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.ROCK);
			Map<String,Player> map = ge.getWinnerLoserPlayerMapFromElement(Element.PAPER, p1, p2);
			assertTrue(map != null && map.keySet().size() == 2);
		}
		
	
	}
	
	public final void testProcessWinEvent() {
		
		{
			Player p1 = new Player("Sanket");
			p1.setSelectedChoice(Element.ROCK);
			Player p2 = new Player("Rohit");
			p2.setSelectedChoice(Element.PAPER);
			p2.incrementPoint();
			ge.joinPlayerInTheGame(p1);
			ge.joinPlayerInTheGame(p2);
			Map<String,Player> map = ge.processWinEvent(Element.PAPER, p1, p2);
			assertTrue(map.get(Constants.WINNER).equals(p2));
			assertTrue(map.get(Constants.LOSER).equals(p1));
		}
		
	}
	
	
	public final void testSetElementInPlayer() {
		
		//null check
		try
		{
			ge.setElementInPlayer(null, Constants.ROCK_SIGN);
			fail("Did not throw null pointer Exception");
		}catch(NullPointerException e) {
			assertTrue(true);
		}	
		
		try
		{
			ge.setElementInPlayer(new Player(), null);
			fail("Did not throw null pointer Exception");
		}catch(NullPointerException e) {
			assertTrue(true);
		}
		
		//invalid input
		try
		{
			ge.setElementInPlayer(new Player(), "Q");
			fail("Did not throw null pointer Exception");
		}catch(IllegalArgumentException e) {
			assertTrue(true);
		}
		try
		{
			ge.setElementInPlayer(new Player(), "1");
			fail("Did not throw null pointer Exception");
		}catch(IllegalArgumentException e) {
			assertTrue(true);
		}
		
		//positive cases
		{
			Player p =new Player("Sanket");
			ge.setElementInPlayer(p, Constants.ROCK_SIGN);
			assertTrue(p.getSelectedChoice().name().toString().equals(Constants.ROCK));
		}
		{
			Player p =new Player("Sanket");
			ge.setElementInPlayer(p, Constants.PAPER_SIGN);
			assertTrue(p.getSelectedChoice().name().toString().equals(Constants.PAPER));
		}
		{
			Player p =new Player("Sanket");
			ge.setElementInPlayer(p, Constants.SCISSORS_SIGN);
			assertTrue(p.getSelectedChoice().name().equals(Constants.SCISSOR));
		}
		
		//positive cases
		{
			Player p =new Player("Sanket");
			ge.setElementInPlayer(p, Constants.ROCK_SIGN);
			assertTrue(!p.getSelectedChoice().name().toString().equals(Constants.PAPER));
		}
		{
			Player p =new Player("Sanket");
			ge.setElementInPlayer(p, Constants.PAPER_SIGN);
			assertTrue(!p.getSelectedChoice().name().toString().equals(Constants.SCISSOR));
		}
		{
			Player p =new Player("Sanket");
			ge.setElementInPlayer(p, Constants.SCISSORS_SIGN);
			assertTrue(!p.getSelectedChoice().name().toString().equals(Constants.ROCK));
		}
		
		
	}

	public final void testInitializeGame() {
		//nullcheck
		try
		{
			ge.initializeGame(null);
			fail("Did not throw null pointer Exception");
		}catch(NullPointerException e) {
			assertTrue(true);
		}
		
		//positive cases
		{
			List<String> list = new ArrayList<String>();
			list.add("Sanket");
			ge.initializeGame(list);
			assertTrue(ge.getPlayerList().size() == list.size());
		}
		
	}
	
	public final void testGetLeaderBoardData() {
		
		try {
				assertNotNull(ge.getLeaderBoardData(Constants.USE_DB));
				
		}catch(Exception e) {
			fail("exception in reading scorecard");
		}
	}
	
	public final void testGetGameState() {
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(new Player("Sanket"));
		playerList.add(new Player("Rohit"));
		playerList.add(new Player("Apurva"));
		GameState gs = new GameState(playerList);
		ge.setCurrentGameState(gs);
		assertNotNull(ge.getCurrentGameState());
	}
	
	public final void testSetGameState() {
		ge.setCurrentGameState(new GameState(new Player(), new Player(),2));
		assertNotNull(ge.getCurrentGameState());
	}
	
	
	


}
