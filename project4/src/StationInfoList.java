import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * 
 * @author CS2334.  Modified by: Will Booker
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This class represents the data for a set of stations.
 *
 */

public class StationInfoList {
	/** List of stations */
	private ArrayList<StationInfo> stations;
	/** Map from station ID to station. */
	private HashMap<String,StationInfo> stationMap;
	
	/**
	 * Constructor that loads the station information from the geoinfo file.
	 * 
	 * 
	 * @param fileName The file that contains all station data.
	 * @throws FileNotFoundException If the specified file cannot be opened
	 * @throws IOException If there is an error reading bytes from the file.
	 */
	public StationInfoList(String fileName) throws FileNotFoundException,IOException
	{
		// FIXED???: complete implementation
		stations = new ArrayList<StationInfo>();
		stationMap = new HashMap<String, StationInfo>();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		reader.readLine();
		
		String input = reader.readLine();
		while(input != null)
		{
			String[] inputArray = input.split(",");
			StationInfo station =   new StationInfo(inputArray[0], inputArray[1], inputArray[2], 
									Double.parseDouble(inputArray[3]), Double.parseDouble(inputArray[4]), 
									str2Cal(inputArray[5]), str2Cal(inputArray[6]));
			
			stations.add(station);
			stationMap.put(inputArray[0], station);
			input = reader.readLine();
		}
		reader.close();
	}

	/**
	 * Convert a string in the station file format to a Gregorian calendar representation
	 * 
	 * @param strg The String description of the date
	 * @return The Gregorian calendar corresponding to the specified date.
	 */
	private static GregorianCalendar str2Cal(String strg){
		// Extract the year from the string
		String str = strg.substring(0, 4);
		int year = Integer.parseInt(str);
		str = strg.substring(4,6);
		int month = Integer.parseInt(str);
		str = strg.substring(6,8);
		int day = Integer.parseInt(str);
		
		// Create the corresponding GregorianCalendar
		return new GregorianCalendar(year, month-1, day);
	}
	
	
	/**
	 * Return the station information corresponding to the station ID
	 * 
	 * @param stationId The station identifier
	 * @return The corresponding station
	 */
	public StationInfo getStationInfo(String stationId){
		return stationMap.get(stationId);
	}
	
	/**
	 * Describe the station list
	 * 
	 * @return String describing the list of all station
	 */
	public String toString(){
		String out = "";
		for(StationInfo si: stations){
			out += si + "\n";
		}
		return out;
	}
	
	/**
	 * Add a day to the station list
	 * 
	 * @param day DailyData object to add to the station list
	 */
	private void add(DailyData day)
	{
		// FIXED: provide implementation
		stationMap.get(day.getStationId()).add(day);
	}
		
	
	/**
	 * Load the file containing all of the observation data and place the individual days
	 * into this station list structure.
	 * <P>
	 * Note that the first line in this file gives us the names of all of the fields contained
	 * in the file
	 * 
	 * @param fileName File to open
	 * @throws FileNotFoundException If the specified file is not found
	 * @throws IOException If an error occurs in reading bytes from the file
	 */
	public void loadData(String fileName) throws FileNotFoundException, IOException
	{
		// FIXED: provide implementation
		BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
		String nextLine = fileReader.readLine();
		String[] array = nextLine.split(",");
		DailyData.setDataFields(array);
		
		nextLine = fileReader.readLine();
		while(nextLine != null)
		{
			this.add(new DailyData(nextLine.split(",")));
			nextLine = fileReader.readLine();
		}
		fileReader.close();
	}	
	
	/** 
	 * Return the sorted list of station IDs
	 * 
	 * @return ArrayList of station IDs
	 */
	public ArrayList<String> getStationIds()
	{
		// FIXED: provide implementation
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(stationMap.keySet());
		Collections.sort(list);
		return list;
	}
	
	/**
	 * Compute the average of the indicated variable over the entire data set for the specified
	 * station.
	 * 
	 * @param stationId String description of the station ID
	 * @param variableId String description of the variable
	 * @return The Observation that contains the average value
	 */
	public Observation getAverageStat(String stationId, String variableId)
	{
		// FIXED: provide implementation
		return stationMap.get(stationId).getAverageStat(variableId);
	}
	
	/**
	 * Compute the maximum of the indicated variable over the entire data set for the specified
	 * station.
	 * <P>
	 * Note: the return value may be null
	 * 
	 * @param stationId String description of the station ID
	 * @param variableId String description of the variable
	 * @return The day object that contains the maximum value.  
	 */
	public DailyData getMaximumStat(String stationId, String variableId)
	{
		// FIXED: provide implementation
		return stationMap.get(stationId).getMaximumStat(variableId);
	}
	
	/**
	 * Compute the minimum of the indicated variable over the entire data set for the specified
	 * station.
	 * <P>
	 * Note: the return value may be null
	 * 
	 * @param stationId String description of the station ID
	 * @param variableId String description of the variable
	 * @return The day object that contains the minimum value
	 */
	public DailyData getMinimumStat(String stationId, String variableId)
	{
		// FIXED: provide implementation
		return stationMap.get(stationId).getMinimumStat(variableId);
	}
}
