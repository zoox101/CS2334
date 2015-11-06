import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author CS2334. Modified by: David Jones
 *         <P>
 *         Date: 2015-10-19 <BR>
 *         Project 3
 *         <P>
 *         This class represents a summary of one day's data from a single
 *         Mesonet station.
 *         <P>
 *
 */

public class DailyData extends StatisticsAbstract implements Comparable<DailyData> {
	/** Year in which the data were sampled */
	private int year;
	/** Month in which the data were sampled */
	private int month;
	/** The day on which the data were sampled (1=January, 2=February, etc */
	private int day;
	/** The identifier for the station. */
	private String stationId;
	/** Map from variable ID to the corresponding Observation */
	private HashMap<String, Observation> observationMap;

	/**
	 * Reference to the DataInfoList - must be set before creating instances of
	 * DailyData
	 */
	private static DataInfoList dataInfoList; //Currently does nothing
	/**
	 * Reference to the list of column names in the data file (first line of the
	 * data file). Must be set before creating instances of DailyData
	 */
	private static ArrayList<String> dataFields;

	/**
	 * Constructor.
	 * <P>
	 * <UL>
	 * <LI>Extracts the year, month, day and stationId from the set of elements.
	 * <LI>For all other elements, if the column name is a variable ID (i.e, it
	 * is contained in the dataInfo ArrayList), then add an entry to the hashmap that
	 * maps the variable ID to the element value (guaranteed to be a double)
	 * </UL>
	 * 
	 * @param elements
	 *            Array of values read from the data file.
	 */
	public DailyData(String[] elements) {
		// TODO: complete the implementation
		
		observationMap = new HashMap<String, Observation>();
		
		for (int i = 0; i < dataFields.size(); i++) {
			if (dataFields.get(i).equals("YEAR")){
				year = Integer.parseInt(elements[i]);
			}
			if (dataFields.get(i).equals("MONTH")) {
				month = Integer.parseInt(elements[i]);
			}
			if (dataFields.get(i).equals("DAY")) {
				day = Integer.parseInt(elements[i]);
			}
			if (dataFields.get(i).equals("STID")) {
				stationId = elements[i];
			}
			if (dataInfoList.getVariableIds().contains(dataFields.get(i))) {
				observationMap.put(dataFields.get(i), new Observation(Double.parseDouble(elements[i])));
			}
		}
		/*year = Integer.parseInt(elements[0]);
		month = Integer.parseInt(elements[1]);
		day = Integer.parseInt(elements[2]);
		stationId = elements[3];
		
		//initialize the observationMap
		observationMap = new HashMap<String, Observation>();
		
		//TODO: finish the implementation of DataInfoList
		
		//for all data field variables, add a new observation from the csv that is at the i+4 index
		for (int i = 0; i < dataFields.size(); i++) 
		{
			if ()
			observationMap.put(dataFields.get(i), new Observation(Double.parseDouble(elements[i])));
		}*/
	}

	/**
	 * Set the data info list, which describes all of the variables that can be
	 * encoded in the data file
	 * 
	 * @param dataInfoList
	 *            Reference to the data info list.
	 */
	protected static void setDataInfoList(DataInfoList dataInfos) {
		dataInfoList = dataInfos;
	}

	/**
	 * Set the data field ArrayList.
	 * 
	 * @param dataFieldList
	 *            An array of field array names (from the first line in the data
	 *            file).
	 * 
	 */
	protected static void setDataFields(String[] dataFieldList) {
		// FIXED: complete implementation
		dataFields = new ArrayList<String>();
				
		//in the csv file, the field names begin at the 4th index
		for(int i=0; i<dataFieldList.length; i++) dataFields.add(dataFieldList[i]);
		/*
		dataFields.add(dataFieldList[4]);4
		dataFields.add(dataFieldList[5]);
		dataFields.add(dataFieldList[6]);
		dataFields.add(dataFieldList[7]);
		dataFields.add(dataFieldList[8]);
		dataFields.add(dataFieldList[9]);
		dataFields.add(dataFieldList[10]);
		dataFields.add(dataFieldList[11]);
		dataFields.add(dataFieldList[12]);
		dataFields.add(dataFieldList[13]);
		dataFields.add(dataFieldList[14]);
		dataFields.add(dataFieldList[15]);
		dataFields.add(dataFieldList[16]);
		dataFields.add(dataFieldList[17]);
		dataFields.add(dataFieldList[18]);
		dataFields.add(dataFieldList[19]);
		dataFields.add(dataFieldList[20]);
		dataFields.add(dataFieldList[21]);
		dataFields.add(dataFieldList[22]);
		dataFields.add(dataFieldList[23]);
		dataFields.add(dataFieldList[24]);
		dataFields.add(dataFieldList[25]);
		dataFields.add(dataFieldList[26]);
		dataFields.add(dataFieldList[27]);
		dataFields.add(dataFieldList[28]);
		dataFields.add(dataFieldList[29]);
		dataFields.add(dataFieldList[30]);
		dataFields.add(dataFieldList[31]);
		dataFields.add(dataFieldList[32]);
		dataFields.add(dataFieldList[33]);
		dataFields.add(dataFieldList[34]);
		dataFields.add(dataFieldList[35]);
		dataFields.add(dataFieldList[36]);
		dataFields.add(dataFieldList[37]);
		dataFields.add(dataFieldList[38]);
		dataFields.add(dataFieldList[39]);
		dataFields.add(dataFieldList[40]);
		*/
	}

	/////////////////////////////////////////////////////
	// The getters
	// FIXED: provide implementation

	/**
	 * The year in which the day occurs
	 * 
	 * @return int year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * The month in which the day occurs
	 * 
	 * @return int month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * The numerical representation of the day
	 * 
	 * @return int day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * The station from which the daily data is taken
	 * 
	 * @return String stationId
	 */
	public String getStationId() {
		return stationId;
	}

	/**
	 * HashMap containing all of the daily data's observations
	 * 
	 * @return HashMap<String,Observation> observationMap
	 */
	public HashMap<String, Observation> getObservationMap() 
	{
		return observationMap;
	}

	/**
	 * Describe the data for the day
	 * 
	 * @return String describing the day
	 */
	public String toString() {
		return "Day: " + getDate();

	}

	/**
	 * Return the day with the minimum measurement for the indicated variable
	 * 
	 * @param variableId
	 *            The String that describes the variable to be considered
	 * @return Reference to the day that contains the minimum variable value
	 * 
	 */
	public DailyData getMinimumStat(String variableId) {
		return this;
	}

	/**
	 * Return the day with the maximum measurement for the indicated variable
	 * 
	 * @param variableId
	 *            The String that describes the variable to be considered
	 * @return Reference to the day that contains the maximum variable value
	 * 
	 */
	public DailyData getMaximumStat(String variableId) {
		return this;
	}

	/**
	 * Return the average value of the indicated variable
	 * 
	 * @param variableId
	 *            The String that describes the variable to be considered
	 * @return Observation containing the average value of the indicated
	 *         variable
	 * 
	 */
	public Observation getAverageStat(String variableId) {
		// FIXED: complete implementation
		return observationMap.get(variableId);
	}

	/**
	 * 
	 * @return A string that describes the date and the station
	 */
	public String getDate() {
		return "" + month + "/" + day + "/" + year;
	}

	/**
	 * Define natural ordering between DailyData objects based on their dates.
	 * 
	 * @param d
	 *            The DailyData object to compare against
	 * @return -1 if this comes before d on the calendar; 0 if they correspond
	 *         to the same day; 1 otherwise
	 */
	public int compareTo(DailyData d) {
		// FIXED: complete implementation
		if (this.day < d.getDay()) {
			return -1;
		} else if (this.day > d.getDay()) {
			return 1;
		}
		return 0;
	}
}
