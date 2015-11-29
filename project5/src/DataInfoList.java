import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * 
 * @author CS2334.  Modified by: ?????
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This class represents the set of variables that is described by station
 *
 */

public class DataInfoList {
	/** The list of variable descriptions.  */
	private ArrayList<DataInfo> dataInfos;
	/** Map from stationId to variable description */
	private HashMap<String, DataInfo> dataInfoMap;
	
	/**
	 * Constructor.
	 * <P>
	 * Loads the set of variables from the specified file.
	 * 
	 * @param fileName Name of the file that contains the list of variables
	 * @throws FileNotFoundException If the given file does not exist
	 * @throws IOException If an error occurs during the file read
	 */
	public DataInfoList(String fileName) throws IOException, FileNotFoundException {
		dataInfos = new ArrayList<DataInfo>();
		dataInfoMap = new HashMap<String,DataInfo>();
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		// Throw away the first line
		br.readLine();
		
		// Loop over the lines int he file
		String strg = br.readLine();
		while(strg != null){
			// Pull out the elements of the line
			String[] subs = strg.split(",");
			// Create the DataInfo object
			DataInfo di = new DataInfo(subs[0], subs[1], subs[2],
					Boolean.parseBoolean(subs[3]), subs[4]);
			// Add to the list
			dataInfos.add(di);
			// Add to the map
			dataInfoMap.put(subs[1], di);
			// Next line
			strg = br.readLine();
		}
		
		br.close();
	}
	
	/**
	 * Return the sorted list of variable IDs
	 * @return
	 */
	public ArrayList<String> getVariableIds(){
		// Output list
		ArrayList<String> out = new ArrayList<String>();
		
		// Add the keys to the list
		out.addAll(dataInfoMap.keySet());
		
		// Sort
		Collections.sort(out);
		return out;
	}
	
	/**
	 * Return the sorted list of variable IDs in array form
	 * NEW
	 * 
	 * @return String[] of variable IDs
	 */
	public String[] getVariableIdArray(){
		Set<String> keys = dataInfoMap.keySet();
		
		// Need number of entries
		int size = keys.size();
		// Drop into an ArrayList to sort
		ArrayList<String> aList = new ArrayList<String>(keys);
		Collections.sort(aList);
		
		// Create the list to return
		String[] list = new String[size];
		// Insert the keyset values into the array 
		return aList.toArray(list);
	}
	
	/**
	 * Tests whether this variable ID is a member of the variable list
	 * 
	 * @param variableId String describing the variable ID
	 * @return true if variableId describes a variable in DataInfoList; false otherwise
	 * 
	 */
	public boolean isValidStat(String variableId){
		return dataInfoMap.keySet().contains(variableId);
	}
	
	/**
	 * Return the DataInfo object that corresponds to the specified variable ID
	 * 
	 * @param variableId A String describing a variable ID
	 * @return The corresponding DataInfo object
	 */
	public DataInfo getDataInfo(String variableId){
		return dataInfoMap.get(variableId);
	}
	
	/**
	 * String describing the set of variables
	 * 
	 * @return String decribing all of the variables in the set
	 */
	public String toString(){
		String out = "";
		
		for(DataInfo di: dataInfos){
			out += di + "\n";
		}
		
		return out;
	}
	
}
