import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * @author CS2334.  Modified by: ?????
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This class represents the data for a single station
 *
 */

public class StationInfo implements Serializable {
	/** Station ID code */
	private String stationId;
	/** Station name */
	private String name;
	/** City name */
	private String city;
	/** Latitude */
	private double nlat;
	/** Longitude */
	private double elon;
	/** Date started recording */
	private GregorianCalendar datc;
	/** Date ended recording */
	private GregorianCalendar datd;
	/** Data set associated with this station */
	private DataSet dataSet;
	
	/**
	 * Constructor 
	 * 
	 * @param stationId Station ID code
	 * @param name Station name
	 * @param city City name
	 * @param nlat Latitude
	 * @param elon Longitude
	 * @param datc Date started recording
	 * @param datd Date ended recording
	 */
	public StationInfo(String stationId, String name, String city, double nlat,
			double elon, GregorianCalendar datc, GregorianCalendar datd) {
		super();
		this.stationId = stationId;
		this.name = name;
		this.city = city;
		this.nlat = nlat;
		this.elon = elon;
		this.datc = datc;
		this.datd = datd;
		//System.out.println(formatDate(datc) + " -> " + formatDate(datd));
		
		dataSet = new DataSet();
	}
	
	/**
	 * Add a day to this station.
	 * @param day
	 */
	protected void add(DailyData day){
		dataSet.add(day);
	}

	/**
	 * Return a string that represents the date
	 * 
	 * @param date Date to be converted to a string
	 * @return String describing the date
	 */
	public static String formatDate(GregorianCalendar date){
		SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy");
		fmt.setCalendar(date);
		String dateFormatted = fmt.format(date.getTime());
		return dateFormatted;
	}
	
	////////////////////////////////////////////////
	// Getters
	
	/**
	 * Return the station ID
	 * 
	 * @return String describing the station ID
	 */
	public String getStationId() {
		return stationId;
	}
	
	/**
	 * Return the station name
	 * 
	 * @return String describing the name of the station
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return the name of the city
	 * 
	 * @return String describing the name of the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Return latitude
	 * 
	 * @return Latitude of the station 
	 */
	public double getNlat() {
		return nlat;
	}
	
	/**
	 * Return longitude
	 * 
	 * @return Longitude of the station
	 */
	public double getElon() {
		return elon;
	}
	
	/**
	 * Return the date that the station went on-line
	 * 
	 * @return Date station first started gathering data 
	 */
	public GregorianCalendar getDatc() {
		return datc;
	}
	
	/**
	 * Return the date that the station went off-line.
	 * 
	 * @return Date station last gathered data (or year 2099 if still active)
	 */
	public GregorianCalendar getDatd() {
		return datd;
	}
	
	/**
	 * Compute the average of the indicated variable over the entire data set.
	 * 
	 * @param variableId String description of the variable
	 * @return The Observation that contains the average (may be invalid)
	 */
	public Observation getAverageStat(String variableId){
		return dataSet.getAverageStat(variableId);
	}
	
	/**
	 * Compute the average of the indicated variable over the entire data set.
	 * 
	 * Note: if a particular constraint is null, then all elements are used
	 * 
	 * The average is computed over just the specified years/months/days, as defined
	 * by the constraints.  In particular, we expect that constraints is a 3-length
	 * list: one for each of years, months and days.  Each element of this list is a
	 * list itself that tells us which years, months, or days to include.
	 * 
	 * @param variableId String description of the variable
	 * @param constraints The set of constraints for this query
	 * @return The Observation that contains the average (may be invalid)
	 */
	public Observation getAverageStat(String variableId,
			ArrayList<List<Integer>> constraints){
		return dataSet.getAverageStat(variableId, constraints, 0);
	}
	
	/**
	 * Compute the maximum of the indicated variable over the entire data set.
	 * 
	 * @param variableId String description of the variable
	 * @return The DailyData object that contains the maximum of that variable (could be invalid)
	 */
	public DailyData getMaximumStat(String variableId){
		return dataSet.getMaximumStat(variableId);
	}
	

	/**
	 * Compute the maximum of the indicated variable over the entire data set.
	 * 
	 * The maximum is computed over just the specified years/months/days, as defined
	 * by the constraints.  In particular, we expect that constraints is a 3-length
	 * list: one for each of years, months and days.  Each element of this list is a
	 * list itself that tells us which years, months, or days to include.
	 * 
	 * Note: if a particular constraint is null, then all elements are used
	 * 
	 * @param variableId String description of the variable
	 * @param constraints The set of constraints for this query
	 * @return The DailyData object that contains the maximum of that variable (could be invalid)
	 */
	public DailyData getMaximumStat(String variableId,
			ArrayList<List<Integer>> constraints){
		return dataSet.getMaximumStat(variableId, constraints, 0);
	}
	
	
	/**
	 * Compute the minimum of the indicated variable over the entire data set.
	 * 
	 * @param variableId String description of the variable
	 * @return The DailyData object that contains the minimum of that variable (could be invalid)
	 */
	public DailyData getMinimumStat(String variableId){
		return dataSet.getMinimumStat(variableId);
	}
	
	/**
	 * Compute the minimum of the indicated variable over the entire data set.
	 * 
	 * The minimum is computed over just the specified years/months/days, as defined
	 * by the constraints.  In particular, we expect that constraints is a 3-length
	 * list: one for each of years, months and days.  Each element of this list is a
	 * list itself that tells us which years, months, or days to include.
	 * 
	 * Note: if a particular constraint is null, then all elements are used
	 * 
	 * @param variableId String description of the variable
	 * @param constraints The set of constraints for this query
	 * @return The DailyData object that contains the minimum of that variable (could be invalid)
	 */
	
	public DailyData getMinimumStat(String variableId,
		ArrayList<List<Integer>> constraints){
		return dataSet.getMinimumStat(variableId, constraints, 0);
	}
	
	/**
	 * Return string description of the variable
	 * 
	 * @return String description of the data set.
	 */
	public String toString(){
		return String.format("%s, %s, %s", stationId, name, city);
	}
	
	/**
	 * Return a formatted string description of the variable
	 * 
	 * @return String description of the variable.
	 */
	public String getFormattedString(){
		return String.format("%-12s %-25s %s", stationId, name, city);
	}
	
	/**
	 * Return the data set associated with this station 
	 *  
	 * @return The associated data set
	 */
	public DataSet getDataSet(){
		return dataSet;
	}
	
	/**
	 * Clear the underlying dataset to an empty one
	 * 
	 */
	protected void clear(){
		dataSet = new DataSet();
	}
}
