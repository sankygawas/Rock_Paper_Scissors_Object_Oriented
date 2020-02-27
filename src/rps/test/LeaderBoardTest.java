package rps.test;

import java.text.ParseException;

import junit.framework.TestCase;
import rps.bean.LeaderBoard;

public class LeaderBoardTest extends TestCase {
	LeaderBoard leaderBoard;

	protected void setUp() throws Exception {
		super.setUp();
		leaderBoard = new LeaderBoard();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	} 

	public final void testGetId() {
		leaderBoard.setId(1);
		assertTrue(leaderBoard.getId() == 1);
	}

	public final void testSetPlayedOn() {
		
		try {
			leaderBoard.setPlayedOn("12l3");
			fail("Should throw an exception");
		}catch(ParseException e) {
			assertTrue(true);
		}
		
		try {
			leaderBoard.setPlayedOn("2019-01-01 08:20:01");
			assertTrue(leaderBoard.getPlayedOn().equals("2019-01-01 08:20:01"));
		}catch(ParseException e) {
			assertTrue(true);
		}
		
	}

	public final void testSetPlayers() {
		//nullcheck
		try
		{
			leaderBoard.setPlayers(null);
			fail("Should throw null pointer exception");
		}
		catch(NullPointerException e) {
			assertTrue(true);
		}
		
		//empty test
		try
		{
			leaderBoard.setPlayers("");
			fail("Should throw null pointer exception");
		}
		catch(NullPointerException e) {
			assertTrue(true);
		}
		
		//positive test{try
		{
			leaderBoard.setPlayers("Sanket, Rohit");
			assertTrue(leaderBoard.getPlayers().equals("Sanket, Rohit"));
		}
		
	}

	public final void testGetWinner() {
		leaderBoard.setWinner("Sanket");
		assertTrue(leaderBoard.getWinner().equals("Sanket"));
	}

	public final void testSetWinner() {
		//nullcheck
		try
		{
			leaderBoard.setWinner(null);
			fail("Should throw null pointer exception");
		}
		catch(NullPointerException e) {
			assertTrue(true);
		}
		
		//empty test
		try
		{
			leaderBoard.setWinner("");
			fail("Should throw null pointer exception");
		}
		catch(NullPointerException e) {
			assertTrue(true);
		}
	}
	
	public final void testGetPoints() {
		leaderBoard.setPoints(1);
		assertTrue(leaderBoard.getPoints() == 1);
	}
	
	public final void testGetNumberOfPlayers() {
		leaderBoard.setNumberOfPlayers(2);
		assertTrue(leaderBoard.getNumberOfPlayers() == 2);
	}
	
	

}
