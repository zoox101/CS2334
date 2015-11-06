import java.util.TreeMap;

public class DataSet extends MultiStatisticsAbstract
{
	private TreeMap<Integer, YearlyData> years;
	
	public DataSet()
	{
		years = new TreeMap<Integer, YearlyData>();
	}

	@Override
	protected TreeMap<Integer, ? extends StatisticsAbstract> getContents() 
	{
		return years;
	}

	@Override
	protected void add(DailyData day) 
	{
		int year = day.getYear();
		if(years.containsKey(year)) 
		{
			years.get(day.getYear()).add(day);
		}
		else 
		{
			years.put(year, new YearlyData());
			years.get(year).add(day);
		}
	}
	

}
