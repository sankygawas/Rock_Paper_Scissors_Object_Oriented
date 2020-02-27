package rps.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import rps.bean.LeaderBoard;
import rps.bean.Player;
import rps.constants.Constants;

/**
 * This class consists methods which do the reading and writing to the CSV File
 * @author Sanket
 *
 */
public class FileService {
	
	/**
	 * This method writes tThe CSV file. Creates a new one if not found and appends the record if found
	 * @param playerList List of Player Objects
	 * @param winnerName Winner Name in String
	 * @param points points of the winner
	 * @return SUCCESS if successfully wriiten to the CSV, FAIL if there's an exception while writing
	 */
	public String writeToFile(List<Player> playerList,String winnerName,int points) {
		
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
			LeaderBoard leaderBoard = new LeaderBoard();
			leaderBoard.setNumberOfPlayers(playerList.size());
			leaderBoard.setWinner(winnerName);
			leaderBoard.setPlayers(playerList.stream().map(o->o.getName()).collect(Collectors.joining(". ")));
			leaderBoard.setPoints(points);
			leaderBoard.setPlayedOn(format.format(new Date()));
			
			FileWriter csvWriter = new FileWriter(Constants.FILE_PATH,true);
			csvWriter.append(leaderBoard.getPlayedOn());
			csvWriter.append(",");
			csvWriter.append(leaderBoard.getPlayers());
			csvWriter.append(",");
			csvWriter.append(leaderBoard.getWinner());
			csvWriter.append(",");
			csvWriter.append(Integer.toString(leaderBoard.getPoints()));
			csvWriter.append(",");
			csvWriter.append(Integer.toString(leaderBoard.getNumberOfPlayers()));
			csvWriter.append("\n");
			csvWriter.flush();
			csvWriter.close();	
			return Constants.SUCCESS;
			
		}catch(Exception e) {
			e.printStackTrace();
			return Constants.FAIL;
		}
		
	}

	/**
	 * Gets the data from the CSV file
	 * @return List of Leaderobjects picked up anmd parsed fom the CSV File
	 */
	public List<LeaderBoard> getData() {
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(Constants.FILE_PATH));
			String row;
			int i =0;
			List<LeaderBoard> leaderBoardList = new ArrayList<LeaderBoard>();
				while ((row = csvReader.readLine()) != null) {
				    String[] data = row.split(",");
				    System.out.println(Arrays.toString(data));
				    LeaderBoard leaderBoard = new LeaderBoard();
				    leaderBoard.setPlayedOn(data[0]);
				    leaderBoard.setPlayers(data[1]);
				    leaderBoard.setWinner(data[2]);
				    leaderBoard.setPoints(Integer.parseInt(data[3]));
				    leaderBoard.setNumberOfPlayers(Integer.parseInt(data[4]));
				    leaderBoard.setId(i++);
				    leaderBoardList.add(leaderBoard);
				    // do something with the data
				}
				csvReader.close();
				Collections.reverse(leaderBoardList);
				return  leaderBoardList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
