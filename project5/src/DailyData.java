import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author CS2334.  Modified by: ?????
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This class represents a summary of one day's data from a single Mesonet station.
 * <P>
 *
 */

public class DailyData extends StatisticsAbstract implements Comparable<DailyData>{
	/** Year in which the data were sampled */
	private int year;
	/** Month in which the data were sampled */
	private int month;
	/** The day on which the data were sampled (1=January, 2=February, etc */
	private int day;
	/** The identifier for the station.  */
	private String stationId;
	/** Map from variable ID to the corresponding Observation */
	private HashMap<String,Observation> observationMap;
	
	/** Reference to the DataInfoList - must be set before creating instances of DailyData */
	private static DataInfoList dataInfoList;
	/** Reference to the list of column names in the data file (first line of the data file).  
	 * Must be set before creating instances of DailyData */
	private static ArrayList<String> dataFields;

	/**
	 * Constructor.
	 * <P>
	 * <UL>
	 * <LI> Extracts the year, month, day and stationId from the set of elements.
	 * <LI> For all other elements, if the column name is a variable ID (i.e, it is
	 * contained in the DataInfoList), then add an entry to the hashmap that maps the
	 * variable ID to the element value (guaranteed to be a double)
	 * </UL>
	 * 
	 * @param elements Array of values read from the data file.
	 */
	public DailyData(String[] elements){
		observationMap = new HashMap<String, Observation>();
		
		// Extract the values that define this particular day/place
		year = Integer.parseInt(elements[dataFields.indexOf("YEAR")]);
		month = Integer.parseInt(elements[dataFields.indexOf("MONTH")]);
		day = Integer.parseInt(elements[dataFields.indexOf("DAY")]);
		stationId = elements[dataFields.indexOf("STID")];
		
		// Now parse all of the data values
		for(int i = 0; i < dataFields.size(); ++i){
			dataFields.get(i);
			dataInfoList.getDataInfo("");
			
			if(dataInfoList.isValidStat(dataFields.get(i))){
				// This is a real statistic
				double value = Double.parseDouble(elements[i]);
				Observation o = new Observation(value);
				observationMap.put(dataFields.get(i), o);
			}
		}
	}

	/**
	 * Set the data info list, which describes all of the variables
	 * that can be encoded in the data file
	 * 
	 * @param dataInfoList Reference to the data info list.
	 */
	protected static void setDataInfoList(DataInfoList dataInfos){
		dataInfoList = dataInfos;
	}
	
	/**
	 * Set the data field ArrayList.
	 * 
	 * @param dataFieldList An array of field array names (from the first line in the data file).
	 * 
	 */
	protected static void setDataFields(String[] dataFieldList){
		dataFields = new ArrayList<String>();
		for(int i = 0; i < dataFieldList.length; ++i){
			dataFields.add(dataFieldList[i]);
		}
	}
	
	/////////////////////////////////////////////////////
	//  The getters
	public int getYear() {
		return year;
	}


	public int getMonth() {
		return month;
	}


	public int getDay() {
		return day;
	}


	public String getStationID() {
		return stationId;
	}


	/**
	 * Describe the data for the day
	 * 
	 * @return String describing the day
	 */
	public String toString(){
		return "Day: " + getDate();
		
	}

	/**
	 * Return the day with the minimum measurement for the indicated variable
	 * 
	 * @param variableId The String that describes the variable to be considered
	 * @return Reference to the day that contains the minimum variable value
	 * 
	 */
	public DailyData getMinimumStat(String variableId){
		return this;
	}
	
	// NEW2
	public DailyData getMinimumStat(String variableId,
			ArrayList<List<Integer>> constraints, int constraintIndex){
		return this;
	}
	
	/**
	 * Return the day with the maximum measurement for the indicated variable
	 * 
	 * @param variableId The String that describes the variable to be considered
	 * @return Reference to the day that contains the maximum variable value
	 * 
	 */
	public DailyData getMaximumStat(String variableId){
		return this;
	}
	
	// NEW2
	public DailyData getMaximumStat(String variableId,
			ArrayList<List<Integer>> constraints, int constraintIndex){
		return this;
	}
	
	/**
	 * Return the average value of the indicated variable
	 * 
	 * @param variableId The String that describes the variable to be considered
	 * @return Observation containing the average value of the indicated variable
	 * 
	 */
	public Observation getAverageStat(String variableId){
		return observationMap.get(variableId);
	}

	// NEW2
	public Observation getAverageStat(String variableId,
			ArrayList<List<Integer>> constraints, int constraintIndex){
		return observationMap.get(variableId);
	}

	/**
	 * 
	 * @return A string that describes the date and the station
	 */
	public String getDate(){
		//return month + "/" + day + "/" + year + " at " + stationId;
		return month + "/" + day + "/" + year;
	}
	
	/**
	 * Define natural ordering between DailyData objects based on their dates.
	 * 
	 * @param d The DailyData object to compare against
	 * @return -1 if this comes before d on the calendar; 0 if they correspond to the same day; 1 otherwise
	 */
	public int compareTo(DailyData d){
		if(this.year < d.year) return -1;
		if(this.year > d.year) return 1;
		if(this.month < d.month) return -1;
		if(this.month > d.month) return 1;
		if(this.day < d.day) return -1;
		if(this.day > d.day) return 1;
		return 0;
	}
}
