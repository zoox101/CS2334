
import java.util.TreeMap;

/**
 * 
 * @author CS2334.  Modified by: Will Booker
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This abstract class represents the common methods for 
 * all statistics objects that are defined over multiple samples.
 * <P>
 * Notes:
 * <UL>
 * <LI> A set of samples may or may not contain valid data for a given Observation type.
 * If all days contain invalid data for a particular type of Observation (e.g., rainFallMin),
 * then the rainFallMin for this object is also invalid.  However, if at least one day
 * has a valid Observation of a particular type, then this object's corresponding Observation
 * is valid.
 * </UL>
 * 
 */
public abstract class MultiStatisticsAbstract extends StatisticsAbstract {
	

	///////////////////////////////////////////
		
	/**
	 * Return the set of StatisticAbstract objects contained within this object
	 * 
	 * @return A reference to the TreeMap that contains all of the sub-objects
	 */
	protected abstract TreeMap<Integer, ? extends StatisticsAbstract> getContents();
	
	/**
	 * Add a day to this object
	 * 
	 * @param day The day to be added.
	 */
	protected abstract void add(DailyData day);
	

	/**
	 * Return the average of the measurement defined by the variableId parameter.  If there
	 * are no valid samples, then returns an invalid Observation 
	 * 
	 * @param variableId String describing the measurement to compute the average over 
	 * @return The average of the valid measurements.
	 */
	public Observation getAverageStat(String variableId){
		double sum = 0.0;
		int count = 0;
		
		// FIXED: complete implementation
		TreeMap<Integer, ? extends StatisticsAbstract> tree = getContents();
		for(Integer key: tree.keySet()) if(tree.get(key).getAverageStat(variableId).getValid())
		{
			count++;
			sum += tree.get(key).getAverageStat(variableId).getValue();
		}
		
		return new Observation(sum/count);
	}
	
	/**
	 * Return the maximum of the measurement defined by the variableId parameter.  If
	 * there is more than one sample that is the maximum, then return the one with the 
	 * earliest date. If there are no valid samples, then null CAN BE returned.
	 * 
	 * @param variableId String describing the measurement to compute the maximum over 
	 * @return The DailyData object that contains the maximum value of the measurement.  
	 * 
	 */
	public DailyData getMaximumStat(String variableId)
	{
		// FIXED: complete implementation
				TreeMap<Integer, ? extends StatisticsAbstract> tree = getContents();
				Observation max = new Observation();
				DailyData maxDay = null;
				
				//System.out.println(tree.keySet());
				for(Integer key: tree.keySet())
				{	
					DailyData possibleMax = tree.get(key).getMaximumStat(variableId);
					//System.out.println(possibleMin + " -- " + key);
					
					if(possibleMax != null && possibleMax.getObservationMap().get(variableId).isGreaterThan(max))
					{
						maxDay = possibleMax;
						max = maxDay.getObservationMap().get(variableId);
					}
				}
				//System.out.println(minDay);

				return maxDay;
	}

	/**
	 * Return the minimum of the measurement defined by the variableId parameter.  If
	 * there is more than one sample that is the minimum, then return the one with the 
	 * earliest date. If there are no valid samples, then null CAN BE returned.
	 * 
	 * @param variableId String describing the measurement to compute the minimum over 
	 * @return The DailyData object that contains the minimum value of the measurement.  
	 * 
	 */
	public DailyData getMinimumStat(String variableId)
	{
		// FIXED: complete implementation
		TreeMap<Integer, ? extends StatisticsAbstract> tree = getContents();
		Observation min = new Observation();
		DailyData minDay = null;
		
		//System.out.println(tree.keySet());
		for(Integer key: tree.keySet())
		{	
			DailyData possibleMin = tree.get(key).getMinimumStat(variableId);
			//System.out.println(possibleMin + " -- " + key);
			
			if(possibleMin != null && possibleMin.getObservationMap().get(variableId).isLessThan(min))
			{
				minDay = possibleMin;
				min = minDay.getObservationMap().get(variableId);
			}
		}
		//System.out.println(minDay);

		return minDay;
	}

}
