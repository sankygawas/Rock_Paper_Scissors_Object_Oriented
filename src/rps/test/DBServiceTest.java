package rps.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import rps.bean.LeaderBoard;
import rps.bean.Player;
import rps.constants.Constants;
import rps.db.DBService;

public class DBServiceTest extends TestCase{

	DBService db;
	protected void setUp() throws Exception {
		super.setUp();
		db = new DBService();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testIsConnecting() {
		
		if(Constants.USE_DB) {
			assertTrue(db.isConnecting());
		}
	} 

	public final void testInsertData() {
		
		//fa;iure Test
		{
			if(Constants.USE_DB) {
				Player p1= new Player("Sanket");
				Player p2= new Player("Rohit");
				List<Player> playerList = new ArrayList<Player>();
				playerList.add(p1);
				playerList.add(p2);
				assertTrue(db.insertResult(playerList, "Sanket", 3).equals(Constants.SUCCESS));
			}
			else
				assertTrue(db.insertData("", "Sanket. Rohit", "Rohit", 3, 2).equals(Constants.FAIL));
				
		}
	}

	public final void testGetData() {
		List<LeaderBoard>  list = db.getData();
		if(Constants.USE_DB) 
			assertNotNull(list);
	}

}
