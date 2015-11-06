
/*
 * @author CS2334. Modified by: David Jones
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This abstract class defines a common set of properties and 
 * methods that are supplied by all classes that contain statistics 
 * about one or more days.
 * 
 * <P>
 * 
 */
public abstract class StatisticsAbstract {
	

	////////////////////////////////////////////////////////////////////
	// Abstract methods
	
	/**
	 * Return the minimum of the measurement defined by the variableId parameter.  If
	 * there is more than one sample that is the minimum, then return the one with the 
	 * earliest date. If there are no valid samples, then null CAN BE returned.
	 * 
	 * @param variableId String describing the measurement to compute the minimum over 
	 * @return The DailyData object that contains the minimum value of the measurement.  
	 * 
	 */
	public abstract DailyData getMinimumStat(String variableId);
	
	
	/**
	 * Return the maximum of the measurement defined by the variableId parameter.  If
	 * there is more than one sample that is the maximum, then return the one with the 
	 * earliest date. If there are no valid samples, then null CAN BE returned.
	 * 
	 * @param variableId String describing the measurement to compute the maximum over 
	 * @return The DailyData object that contains the maximum value of the measurement.  
	 * 
	 */
	public abstract DailyData getMaximumStat(String variableId);
	
	/**
	 * Return the average of the measurement defined by the variableId parameter.  If there
	 * are no valid samples, then returns an invalid Observation 
	 * 
	 * @param variableId String describing the measurement to compute the average over 
	 * @return The average of the valid measurements.
	 */
	public abstract Observation getAverageStat(String variableId);
	
}
