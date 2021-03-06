import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 
 * @author CS2334. Modified by: Will Booker, David Jones
 *         <P>
 *         Date: 2015-09-28 <BR>
 *         Project 3
 *         <P>
 *         This class is a representation of a year's worth of statistics. This
 *         class comes with an array list that holds twelve Monthly Data
 *         objects.
 */
public class YearlyData extends MultiStatisticsAbstract {
	/** Year set to keep track of what years have been loaded in */
	private static ArrayList<Integer> yearSet = new ArrayList<Integer>();
	/** Static variable for the number of months in a year */
	public static final int MONTHS_PER_YEAR = 12;
	/** Tree map for storing the 12 months in a year */
	TreeMap<Integer, MonthlyData> months;

	/**
	 * Constructor
	 */
	public YearlyData() {

		// populate the MonthlyData tree map
		months = new TreeMap<Integer, MonthlyData>();
		for (int i = 1; i <= MONTHS_PER_YEAR; i++) {
			months.put(i, new MonthlyData());
		}
	}

	/**
	 * @return A reference to the array list of months this object holds
	 */
	@Override
	protected TreeMap<Integer, ? extends StatisticsAbstract> getContents() {
		return months;
	}

	/**
	 * @param DailyData
	 *            day - A day to be added to the correct month within this
	 *            year's array list. The DailyData should hold a field that will
	 *            allow this code to place it correctly.
	 */
	@Override
	protected void add(DailyData day) {
		if (months.containsKey(day.getMonth())) {
			months.get(day.getMonth()).add(day);
		} else {
			months.put(day.getMonth(), new MonthlyData());
			months.get(day.getMonth()).add(day);
		}
		
		//add the year field from the day to the set if it is not already there
		if (!yearSet.contains(day.getYear())) {
			Integer year = day.getYear();
			yearSet.add(new Integer(year));
		}
	}

	protected static ArrayList<Integer>getYearSet() {
		return yearSet;
	}
}
