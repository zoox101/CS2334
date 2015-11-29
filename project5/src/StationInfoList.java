import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;

/**
 * 
 * @author CS2334.  Modified by: ?????
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This class represents the data for a set of stations.
 *
 */

public class StationInfoList implements Serializable {
	/** List of stations */
	private ArrayList<StationInfo> stations;
	/** Map from station ID to station. */
	private HashMap<String,StationInfo> stationMap;
	
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
	 * Constructor that loads the station information from the geoinfo file.
	 * 
	 * 
	 * @param fileName The file that contains all station data.
	 * @throws FileNotFoundException If the specified file cannot be opened
	 * @throws IOException If there is an error reading bytes from the file.
	 */
	public StationInfoList(String fileName) throws FileNotFoundException,IOException{
		// Initialize the structures
		stations = new ArrayList<StationInfo>();
		stationMap = new HashMap<String, StationInfo>(); 
		
		// Open the file
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		// Throw away the first line
		br.readLine();
		
		String strg = br.readLine();
		// Loop over lines
		while(strg != null){
			String[] subs = strg.split(",");
			// Create the station
			StationInfo station = new StationInfo(subs[0], subs[1], subs[2],
					Double.parseDouble(subs[3]), Double.parseDouble(subs[4]),
					str2Cal(subs[5]), str2Cal(subs[6]));
			// Add the station to the list and to the map
			stations.add(station);
			stationMap.put(subs[0], station);
			strg = br.readLine();
		}
		// Close the file
		br.close();
	}
	
	/**
	 * Return the station information corresponding to the station ID
	 * 
	 * @param stationId The station identitifier
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
	private void add(DailyData day){
		String stationId = day.getStationID();
		stationMap.get(stationId).add(day);
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
	public void loadData(String fileName) throws FileNotFoundException, IOException{
		// Open the file
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		// Delete remembered set of years  NEW2
		YearlyData.clearYearSet();
		// Clear out all of the component stations of their current data sets  NEW2
		for(String s: stationMap.keySet()){
			StationInfo si = stationMap.get(s);
			si.clear();
		}
		
		// The first line gives us the field list
		String strg = br.readLine();
		String[] fields = strg.split(",");
		// Tell the daily data class what the fields are
		DailyData.setDataFields(fields);
		
		// Now read the lines
		strg = br.readLine();
		while(strg != null){
			// Split up the line
			String[] subs = strg.split(",");
			// Create the day object
			DailyData day = new DailyData(subs);
			//System.out.println(day.getDate());
			// Add the day to this station list
			add(day);
			strg = br.readLine();
		}
		// Close the file
		br.close();
	}	
	
	/** 
	 * Return the sorted list of station IDs
	 * 
	 * @return ArrayList of station IDs
	 */
	public ArrayList<String> getStationIds(){
		ArrayList<String> out = new ArrayList<String>();
		
		// The station IDs are the keys in the map
		out.addAll(stationMap.keySet());
		
		// Sort the list
		//System.out.println(out);
		Collections.sort(out);
		//System.out.println(out);
		return out;
	}
	
	/**
	 * Return the sorted list of station IDs in array form
	 * NEW
	 * 
	 * @return String[] of station IDs
	 */
	public String[] getStationIdArray(){
		Set<String> keys = stationMap.keySet();
		// Need number of entries
		int size = keys.size();
		// Drop into an array list and sort
		ArrayList<String> aList = new ArrayList<String>(keys);
		Collections.sort(aList);
		// Create the list to return
		String[] list = new String[size];
		// Insert the keyset values into the array 
		return aList.toArray(list);
	}
	
	/**
	 * Compute the average of the indicated variable over the entire data set for the specified
	 * station.
	 * 
	 * @param stationId String description of the station ID
	 * @param variableId String description of the variable
	 * @return The Observation that contains the average value
	 */
	public Observation getAverageStat(String stationId, String variableId){
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
	public DailyData getMaximumStat(String stationId, String variableId){
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
	public DailyData getMinimumStat(String stationId, String variableId){
		return stationMap.get(stationId).getMinimumStat(variableId);
	}
	
}
