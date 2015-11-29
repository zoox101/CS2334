import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/*
 * Project 5 <BR>
 * Date: 2015-11-11 <BR>
 * <P>
 * This abstract class defines a common set of properties and 
 * methods that are supplied by all classes that contain statistics 
 * about one or more days.
 * 
 * <P>
 * 
 * NEW2<P>
 * 
 * @author CS2334. 
 */
public abstract class StatisticsAbstract implements Serializable {
	

	////////////////////////////////////////////////////////////////////
	// Abstract methods
	
	/**
	 * Return the minimum of the measurement defined by the variableId parameter.  If
	 * there is more than one sample that is the minimum, then return the one with the 
	 * earliest date. If there are no samples, then null CAN BE returned.
	 * 
	 * @param variableId String describing the measurement to compute the minimum over 
	 * @return The DailyData object that contains the minimum value of the measurement.  
	 * 
	 */
	public abstract DailyData getMinimumStat(String variableId);
	
	/**
	 * Return the minimum of the measurement defined by the variableId parameter.  If
	 * there is more than one sample that is the minimum, then return the one with the 
	 * earliest date. If there are no valid samples, then null CAN BE returned.
	 * 
	 * @param variableId String describing the measurement to compute the minimum over
	 * @param constraints Set of constraints for this query (0=years; 1=months; 2=days)
	 * @param constraintIndex The current constraint level
	 * @return The DailyData object that contains the minimum value of the measurement.  
	 * 
	 */
	public abstract DailyData getMinimumStat(String variableId, 
			ArrayList<List<Integer>> constraints, int constraintIndex);

	
	
	/**
	 * Return the maximum of the measurement defined by the variableId parameter.  If
	 * there is more than one sample that is the maximum, then return the one with the 
	 * earliest date. If there are no samples, then null CAN BE returned.
	 * 
	 * @param variableId String describing the measurement to compute the maximum over 
	 * @return The DailyData object that contains the maximum value of the measurement.  
	 * 
	 */
	public abstract DailyData getMaximumStat(String variableId);
	
	/**
	 * Return the maximum of the measurement defined by the variableId parameter.  If
	 * there is more than one sample that is the minimum, then return the one with the 
	 * earliest date. If there are no valid samples, then null CAN BE returned.
	 * 
	 * @param variableId String describing the measurement to compute the minimum over
	 * @param constraints Set of constraints for this query (0=years; 1=months; 2=days)
	 * @param constraintIndex The current constraint level
	 * @return The DailyData object that contains the minimum value of the measurement.  
	 * 
	 */
	
	public abstract DailyData getMaximumStat(String variableId,
			ArrayList<List<Integer>> constraints, int constraintIndex);
	
	/**
	 * Return the average of the measurement defined by the variableId parameter.  If there
	 * are no valid samples, then returns an invalid Observation 
	 * 
	 * @param variableId String describing the measurement to compute the average over 
	 * @return The average of the valid measurements.
	 */
	public abstract Observation getAverageStat(String variableId);
	
	/**
	 * Return the average of the measurement defined by the variableId parameter.  If there
	 * are no valid samples, then returns an invalid Observation 
	 * 
	 * @param variableId String describing the measurement to compute the average over 
	 * @param constraints Set of constraints for this query (0=years; 1=months; 2=days)
	 * @param constraintIndex The current constraint level
	 * @return The average of the valid measurements.
	 */
	public abstract Observation getAverageStat(String variableId,
			ArrayList<List<Integer>> constraints, int constraintIndex);
	}
