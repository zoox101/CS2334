import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author CS2334.  Modified by: ?????
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This class represents a summary of one year's data from a single Mesonet station.
 *
 * 
 */
public class YearlyData extends MultiStatisticsAbstract implements Comparable<YearlyData>{
	/**
	 * The set of months within the year
	 */
	private TreeMap<Integer, MonthlyData> months;
	/** The year represented by this object.  */
	private int year;

	/** Number of months in a year.  */
	public final int MONTHS_PER_YEAR = 12;
	
	/** NEW: keep track of the full set of years */
	private static TreeSet<Integer> yearSet = new TreeSet<Integer>();  // NEW
	
	/**
	 * Constructor
	 */
	public YearlyData(){
		months = new TreeMap<Integer, MonthlyData>();
		
		// Create the space for the months
		for(int i = 0; i < MONTHS_PER_YEAR; ++i){
			months.put(i+1, new MonthlyData());
		}
	}
	
	/**
	 * Add a day to this year.
	 * 
	 * @param day The day to add
	 */
	protected void add(DailyData day){
		year = day.getYear();
		yearSet.add(year);  // NEW
		int month = day.getMonth();
		
		months.get(month).add(day);
	}
	
	/**
	 * Return the set of months that make up this year
	 * 
	 * @return The set of component months
	 */
	protected TreeMap<Integer, MonthlyData> getContents(){
		return months;
	}
	
	/**
	 * Provide natural ordering for the group of years.
	 * 
	 * @param y YearlyData object to compare against
	 * @return -1 if this comes before y; 0 if they are the same year; and 1 if this comes after y
	 */
	public int compareTo(YearlyData y){
		if(this.year < y.year) return -1;
		if(this.year > y.year) return 1;
		return 0;
	}
	
	/**
	 * Return the year set that has been collected
	 * 
	 * @return The set of years
	 * 
	 * NEW
	 */
	public static TreeSet<Integer> getYearSet(){
		return yearSet;
	}
	
	/**
	 * Clear out the year set.
	 * 
	 * NEW2
	 */
	public static void clearYearSet(){
		yearSet.clear();
	}
	
}
