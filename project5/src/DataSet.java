import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 
 * @author CS2334.  Modified by: ?????
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This class represents the data for a set of years for a single station
 *
 */


public class DataSet extends MultiStatisticsAbstract{
	/** Represent an array of year/station combinations */
	private TreeMap<Integer, YearlyData> years;
		
	/**
	 * Constructor
	 */
	public DataSet() {
		years = new TreeMap<Integer, YearlyData>();
	}
	
	/**
	 * NEW
	 * Constructor: build a new DataSet from an existing one
	 * 
	 * @param original Original data set
	 * @param years Years we want from this data set
	 */
	public DataSet(DataSet original, ArrayList<Integer> yearList){
		years = new TreeMap<Integer, YearlyData>();
		
		// Loop over the array of years
		for(int i: yearList){
			// Try to pull the year out of the old DS
			YearlyData yd = original.years.get(i);
			
			// Did this year exist?
			if(yd != null){
				// Yes - add it to our new set
				years.put(i, yd);
			}
		}
	}

	/**
	 * Add a day to this data set
	 * 
	 * @param d DailyData object to add to the data set
	 */
	protected void add(DailyData d){
		int year = d.getYear();
		// Does this year exist yet?
		if(!years.containsKey(year)){
			// No - create the year
			years.put(year, new YearlyData());
		}
		// Now add the day to the year
		years.get(year).add(d);
	}
	
	/**
	 * Return the contents of this data set.
	 * 
	 * @return The set of years in this data set.
	 */
	protected TreeMap<Integer, YearlyData> getContents(){
		return years;
	}
	/*
	public Set<Integer> getYears(){
		return years.keySet();
	}
*/
}
