import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 
 * @author David & Will Booker Project 4 11-11-15 This is a class that holds a
 *         data set across which statistics can be computed. On this level, data
 *         is stored as YearlyData objects
 */
public class DataSet extends MultiStatisticsAbstract {

	/**
	 * TreeMap containing all of the YearlyData objects, with integer years as
	 * keys
	 */
	private TreeMap<Integer, YearlyData> years;

	/**
	 * Constructor
	 */
	public DataSet() {
		years = new TreeMap<Integer, YearlyData>();
	}

	/**
	 * Constructor that will create a dataset including the YearlyData objects
	 * where their year field is also in years
	 * 
	 * @param ds
	 *            A DataSet which has already been loaded
	 * @param years
	 *            An array list containing years that will be included in the
	 *            DataSet
	 */
	public DataSet(DataSet ds, ArrayList<Integer> yearsToIntersect) {

		years = new TreeMap<Integer, YearlyData>();

		// for each year in the static years list, see if the data set contains
		// it
		for (Integer year : yearsToIntersect) {
			if (ds.getContents().containsKey(year)) {
				// if there is an intersection, copy the reference from the
				// object to this one
				years.put(year, (YearlyData) ds.getContents().get(year));
			}
		}
	}

	/**
	 * @return TreeMap years
	 */
	@Override
	protected TreeMap<Integer, ? extends StatisticsAbstract> getContents() {
		return years;
	}

	/**
	 * @param DailyData
	 *            that will be added to the set
	 */
	@Override
	protected void add(DailyData day) {
		int year = day.getYear();
		if (years.containsKey(year)) {
			years.get(day.getYear()).add(day);
		} else {
			years.put(year, new YearlyData());
			years.get(year).add(day);
		}
	}
}
