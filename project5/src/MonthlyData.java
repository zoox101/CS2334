import java.util.TreeMap;

/**
 * @author CS2334.  Modified by: David Jones
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 5
 * <P>
 * This class represents a summary of one month's data from a single Mesonet station.
 *
 * 
 */
public class MonthlyData extends MultiStatisticsAbstract
{
	/** Map containing all of the days within the month */
	TreeMap<Integer, DailyData> days;
	/** Integer representation of the month */
	private int month; //Currently Does Nothing
	
	/**
	 * Constructor
	 */
	MonthlyData()
	{
		days = new TreeMap<Integer, DailyData>();
	}

	/**
	 * Returns a reference to the days ArrayList
	 */
	@Override
	protected TreeMap<Integer, ? extends StatisticsAbstract> getContents() 
	{
		return days;
	}

	/**
	 * Adds a DailyData object to the array list contained within this object
	 * @param day A DailyData object to be added to the days ArrayList
	 */
	@Override
	protected void add(DailyData day) 
	{
		days.put(day.getDay(), day);
	}
	
}
