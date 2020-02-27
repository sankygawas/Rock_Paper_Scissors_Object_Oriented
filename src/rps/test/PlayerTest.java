/**
 * 
 */
package rps.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import rps.bean.Player;
import rps.controller.RPSController;
import rps.service.GameEngine.Element;

/**
 * @author Sanket
 *
 */
public class PlayerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link rps.bean.Player#setSelectedChoice(rps.bean.Element)}.
	 */
	@Test
	public  void testSetSelectedChoice() {
		
		try {
			Player p = new Player("Sanket");
			p.setSelectedChoice(null);
	     } catch (final IllegalArgumentException e) {
	         assertTrue(true);
	     }	
		
		try {
			Player p = new Player("");
			p.setSelectedChoice(null);
	         fail("Should have thrown an exception");
	     } catch (final IllegalArgumentException e) {
	         assertTrue(true);
	     }	
		
		try {
			Player p = new Player("Sanket");
			p.setSelectedChoice(Element.PAPER);
	        assertTrue(Element.PAPER.equals(p.getSelectedChoice()));
	     } catch (final IllegalArgumentException e) {
	         fail("Should not throw an error");
	     }	
		
	}
	
	

	

	/**
	 * Test method for {@link rps.bean.Player#setName(java.lang.String)}.
	 */
	@Test
	public final void testConstructorForEmpty() {
		try {
			new Player("");
	        fail("Should have thrown an exception");
	     } catch (final RuntimeException e) {
	         assertTrue(true);
	     }	
	}
	
	@Test
	public final void testConstructorForNull() {
		try {
			new Player(null);
	        fail("Should have thrown an exception");
	     } catch (final RuntimeException e) {
	         assertTrue(true);
	     }	
	}
	
	
	@Test
	public final void testNameForNull() {
		try {
			Player p = new Player("Test");
			p.setName(null);
	        fail("Should have thrown an exception");
	     } catch (final RuntimeException e) {
	         assertTrue(true);
	     }	
	}
	
	@Test
	public final void testNameForEmpty() {
		try {
			Player p = new Player("Test");
			p.setName("");
	        fail("Should have thrown an exception");
	     } catch (final RuntimeException e) {
	         assertTrue(true);
	     }	
	}

	@Test
	public final void testNameForValid() {
		try {
			Player p = new Player("Test");
			p.setName("Test");
			assertTrue(p.getName().equals("Test"));
	     } catch (final RuntimeException e) {
	         assertFalse(true);
	     }	
	}

	/**
	 * Test method for {@link rps.bean.Player#setPoints(int)}.
	 */
	@Test
	public final void testSetPoints() {
		try {
			Player p = new Player("Sanket");
			p.setPoints(-1);
	         fail("Should have thrown an exception");
	     } catch (final RuntimeException e) {
	         assertTrue(true);
	     }	}

	/**
	 * Test method for {@link rps.bean.Player#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject() {
			{
				Player p1 = new Player("Sanket");
				Player p2 = new Player("Rohit");
		        assertFalse(p1.equals(p2));
			}
	        try {
	        	Player p1 = new Player("Sanket");
				RPSController o2= new RPSController();
		        assertFalse(p1.equals(o2));
		     } catch (final RuntimeException e) {
		         assertTrue(true);
		     }
	}

	/**
	 * Test method for {@link rps.bean.Player#incrementPoint()}.
	 */
	@Test
	public final void testIncrementPoint() {
		Player p = new Player("Sanket");
		p.setPoints(1);
		p.incrementPoint();
		assertTrue(p.getPoints() == 2);
	}

	/**
	 * Test method for {@link rps.bean.Player#toString()}.
	 */
	@Test
	public final void testToString() {
		{
			Player p = new Player("Sanket");
			p.setSelectedChoice(Element.ROCK);
			assertTrue(p.toString().equals("Name: Sanket Element:ROCK Points:0"));
		}
		
		{
			Player p = new Player("Rohit");
			p.setSelectedChoice(Element.SCISSORS);
			p.incrementPoint();
			assertTrue(p.toString().equals("Name: Rohit Element:SCISSORS Points:1"));
		}
	}
	
	@Test
	public final void testCompareTo() {
		{
			Player p =new Player("Sanket");
			Player p2 =new Player("Test");
			p.incrementPoint();
			int val = p.compareTo(p2);
			assertTrue(val == -1);
		}
		
		{
			Player p =new Player("Sanket");
			Player p2 =new Player("Test");
			p2.incrementPoint();
			int val = p.compareTo(p2);
			assertTrue(val == 1);
		}
		
		{
			Player p =new Player("Sanket");
			Player p2 =new Player("Test");
			p2.incrementPoint();
			p.incrementPoint();
			int val = p.compareTo(p2);
			assertTrue(val == 0);
		}
	}

}
