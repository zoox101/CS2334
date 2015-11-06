import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 
 * @author CS2334.  Modified by: Will Booker
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
	public DataInfoList(String fileName) throws IOException, FileNotFoundException 
	{
		// FIXED: provide implementation
		// TODO: guarantee that the file reads in the same every time
		dataInfos = new ArrayList<DataInfo>();
		dataInfoMap = new HashMap<String, DataInfo>();
		BufferedReader translationReader = new BufferedReader(new FileReader(fileName));
		translationReader.readLine();
		
		String nextLine = translationReader.readLine();
		while(nextLine != null)
		{
			String[] array = nextLine.split(",");
			DataInfo info = new DataInfo(array[0], array[1], array[2], Boolean.parseBoolean(array[3]), array[4]);
			dataInfos.add(info);
			dataInfoMap.put(info.getVariableId(), info);
			nextLine = translationReader.readLine();
		}		
		translationReader.close();
		DailyData.setDataInfoList(this);
	}
	
	/**
	 * Return the sorted list of variable IDs
	 * @return
	 */
	public ArrayList<String> getVariableIds()
	{
		// Output list
		ArrayList<String> out = new ArrayList<String>();
		
		// FIXED: complete implementation
		out.addAll(dataInfoMap.keySet());
		Collections.sort(out);
		
		return out;
	}
	/**
	 * Tests whether this variable ID is a member of the variable list
	 * 
	 * @param variableId String describing the variable ID
	 * @return true if variableId describes a variable in DataInfoList; false otherwise
	 * 
	 */
	public boolean isValidStat(String variableId)
	{
		// FIXED: provide implementation
		return dataInfoMap.containsKey(variableId);
	}
	
	/**
	 * Return the DataInfo object that corresponds to the specified variable ID
	 * 
	 * @param variableId A String describing a variable ID
	 * @return The corresponding DataInfo object
	 */
	public DataInfo getDataInfo(String variableId)
	{
		// FIXED: provide implementation
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
