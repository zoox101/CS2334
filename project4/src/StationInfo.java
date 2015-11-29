import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * 
 * @author CS2334.  Modified by: David Jones
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This class represents the data for a single station
 *
 */

public class StationInfo {
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
			double elon, GregorianCalendar datc, GregorianCalendar datd) 
	{
		this.stationId = stationId;
		this.name = name;
		this.city = city;
		this.nlat = nlat;
		this.elon = elon;
		this.datc = datc;
		this.datd = datd;
		
		dataSet = new DataSet();
	}
	
	/**
	 * Add a day to this station.
	 * @param day
	 */
	protected void add(DailyData day)
	{
		// FIXED: complete implementation
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
	// TODO: provide implementation
	
	
	/**
	 * Compute the average of the indicated variable over the entire data set.
	 * 
	 * @param variableId String description of the variable
	 * @return The Observation that contains the average (may be invalid)
	 */
	public Observation getAverageStat(String variableId)
	{
		// FIXED: complete implementation
		return dataSet.getAverageStat(variableId);
	}
	
	/**
	 * Compute the maximum of the indicated variable over the entire data set.
	 * 
	 * @param variableId String description of the variable
	 * @return The DailyData object that contains the maximum of that variable (could be invalid)
	 */
	public DailyData getMaximumStat(String variableId)
	{
		// FIXED: complete implementation
		return dataSet.getMaximumStat(variableId);
	}
	
	
	/**
	 * Compute the minimum of the indicated variable over the entire data set.
	 * 
	 * @param variableId String description of the variable
	 * @return The DailyData object that contains the minimum of that variable (could be invalid)
	 */
	public DailyData getMinimumStat(String variableId)
	{
		// FIXED: complete implementation
		return dataSet.getMinimumStat(variableId);
	}
	
	/**
	 * The station's ID
	 * @return The 4 character string that represents this station's ID
	 */
	public String getStationId() {
		return stationId;
	}

	/**
	 * The station's name
	 * @return The full string representation of the station's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * The city in which the station exists
	 * @return String - The name of the city where the station is
	 */
	public String getCity() {
		return city;
	}

	/**
	 * The latitude of the station's location
	 * @return double - the station's latitude
	 */
	public double getNlat() {
		return nlat;
	}

	/**
	 * The longitude of the station's location
	 * @return double - the station's longitude
	 */
	public double getElon() {
		return elon;
	}

	/**
	 * The date the station started recording
	 * @return GregorianCalendar - The starting date of the station's recording
	 */
	public GregorianCalendar getDatc() {
		return datc;
	}

	/**
	 * The date the station stopped recording
	 * @return GregorianCalendar - The ending date of the staiton's recording
	 */
	public GregorianCalendar getDatd() {
		return datd;
	}

	/**
	 * The data set containing all of the station's data (in the form of DailyData objects)
	 * @return DataSet - set of DailyData objects
	 */
	public DataSet getDataSet() {
		return dataSet;
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
	 * Resets the dataset (for when a new file is loaded)
	 */
	public void resetDataSet() {
		dataSet = new DataSet();
	}
}
