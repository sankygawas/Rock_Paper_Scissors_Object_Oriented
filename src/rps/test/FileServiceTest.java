package rps.test;


import java.util.ArrayList;
import java.util.List;


import junit.framework.TestCase;
import rps.bean.Player;
import rps.constants.Constants;
import rps.db.FileService;

public class FileServiceTest  extends TestCase {

	FileService fs = null;
	protected void setUp() throws Exception {
		super.setUp();
		fs = new FileService();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public final void testWriteToFile() {
		{
			List<Player> playerList = new ArrayList<Player>();
			playerList.add(new Player("Sanket"));
			playerList.add(new Player("Rohit"));
			playerList.add(new Player("Apurva"));
			assertTrue(fs.writeToFile(playerList, "Sanket", 3).equals(Constants.SUCCESS));
		}
	}

	public final void testGetData() {
		{
			List<Player> playerList = new ArrayList<Player>();
			playerList.add(new Player("Sanket"));
			playerList.add(new Player("Rohit"));
			playerList.add(new Player("Apurva"));
			assertTrue(fs.getData() != null);
		}
	}

}
