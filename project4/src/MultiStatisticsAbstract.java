
import java.util.TreeMap;

/**
 * 
 * @author CS2334. Modified by: Will Booker
 *         <P>
 *         Date: 2015-10-19 <BR>
 *         Project 3
 *         <P>
 *         This abstract class represents the common methods for all statistics
 *         objects that are defined over multiple samples.
 *         <P>
 *         Notes:
 *         <UL>
 *         <LI>A set of samples may or may not contain valid data for a given
 *         Observation type. If all days contain invalid data for a particular
 *         type of Observation (e.g., rainFallMin), then the rainFallMin for
 *         this object is also invalid. However, if at least one day has a valid
 *         Observation of a particular type, then this object's corresponding
 *         Observation is valid.
 *         </UL>
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
	 * @param day
	 *            The day to be added.
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
		
		// Get the set of sub-objects
		TreeMap<Integer, ? extends StatisticsAbstract> contents = getContents();
		
		// Iterate over these objects
		for(StatisticsAbstract sa: contents.values()) {
			Observation o = sa.getAverageStat(variableId);
			if(o.getValid()){
				// Observation is valid: include in the average
				sum += o.getValue();
				count++;
			}
		}
		
		if(count == 0)
			// No valid samples: return an invalid Observation
			return new Observation();
		else
			// Construct a new valid observation
			return new Observation(sum/count);
	}

	/**
	 * Return the maximum of the measurement defined by the variableId parameter.  If
	 * there is more than one sample that is the maximum, then return the one with the 
	 * earliest date. If there are no samples, then null CAN BE returned.
	 * 
	 * @param variableId String describing the measurement to compute the maximum over 
	 * @return The DailyData object that contains the maximum value of the measurement.  
	 * 
	 */
	public DailyData getMaximumStat(String variableId){
		// Get the set of sub-objects
		TreeMap<Integer, ? extends StatisticsAbstract> contents = getContents();
			
		// Default values for search
		DailyData dayOut = null; 
		Observation oOut = new Observation(); 
		
		// Force copy to happen on the first pass through
		boolean flag = true;
		// Loop over contents in order
		for(int i: contents.keySet()){
			// Get the sub-object max
			DailyData d = contents.get(i).getMaximumStat(variableId);
			
			// Get the value of the sub-object
			Observation o = d.getAverageStat(variableId);
			
			// Compare this new observation to the running best
			if(flag || o.isGreaterThan(oOut)){
				// This one is better
				oOut = o;
				dayOut = d;
				flag = false;
			}
		}
		return dayOut;
	}

	/**
	 * Return the minimum of the measurement defined by the variableId parameter.  If
	 * there is more than one sample that is the minimum, then return the one with the 
	 * earliest date. If there are no samples, then null CAN BE returned.
	 * 
	 * @param variableId String describing the measurement to compute the minimum over 
	 * @return The DailyData object that contains the minimum value of the measurement.  
	 * 
	 */
	public DailyData getMinimumStat(String variableId){
		
		// Get the set of sub-objects
		TreeMap<Integer, ? extends StatisticsAbstract> contents = getContents();

		// Default values in search
		DailyData dayOut = null; 
		Observation oOut = new Observation(); 
		
		// Force copy to happen on the first pass
		boolean flag = true;
		
		// Loop over the remaining contents in order
		for(int i: contents.keySet()){ 
			// Get the sub-object min
			DailyData d = contents.get(i).getMinimumStat(variableId);
			
			// Get the value of the sub-object
			Observation o = d.getAverageStat(variableId);
			
			// Compare this new observation to the running best
			if(flag || o.isLessThan(oOut)){
				// This one is better
				oOut = o;
				dayOut = d;
				flag = false;
			}
		}
		return dayOut;
	}

}
