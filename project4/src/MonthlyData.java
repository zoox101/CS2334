import java.util.TreeMap;


public class MonthlyData extends MultiStatisticsAbstract
{
	TreeMap<Integer, DailyData> days;
	private int month; //Currently Does Nothing
	
	MonthlyData()
	{
		days = new TreeMap<Integer, DailyData>();
	}

	@Override
	protected TreeMap<Integer, ? extends StatisticsAbstract> getContents() 
	{
		return days;
	}

	@Override
	protected void add(DailyData day) 
	{
		days.put(day.getDay(), day);
	}
	
}
