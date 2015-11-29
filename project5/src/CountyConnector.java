import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class that establishes an association between stations and counties.  We explicitly
 * represent both mappings.  In particular:
 * <UL>
 * <LI> Provide lookup of stations given a county
 * <LI> Provide lookup of a county given a station
 * <LI> Provide statistics computations for counties (based on 
 * statistics of contained stations)
 * <LI> Provides a translation from statistic to coloring of county
 * </UL>
 * 
 * Project 5<BR>
 * Date: 2015-11-18
 * 
 * @author CS2334.  Modified by ??
 *
 */

public class CountyConnector {
	/** Map from county names to list of stations.  */
	private HashMap<CountyInfo, ArrayList<StationInfo>> countyMap;
	/** Map from StationId to county.  */
	private HashMap<StationInfo, CountyInfo> stationMap;
	/** Color used when we do not have data for a county.  */
	private static final Color BAD_DATA_COLOR = new Color(200, 0, 0);
	/** Reference to the colorBar object that is being rendered */
	private ColorBar colorBar;

	/**
	 * Constructor.  Creates the associations between counties and stations based
	 * on the county polygons and the station longitude/latitude
	 * 
	 * @param stationInfoList List of all stations
	 * @param countyInfoList List of all counties
	 */
	public CountyConnector(StationInfoList stationInfoList, CountyInfoList countyInfoList){
		// Initialize the mapping structures
		countyMap = new HashMap<CountyInfo, ArrayList<StationInfo>>();
		stationMap = new HashMap<StationInfo, CountyInfo>();
				
		// TODO: complete the implementation
		
	}

	/**
	 * Given a station object, return the corresponding county.
	 * 	
	 * @param station A stationInfo object
	 * @return The corresponding county
	 */
	public CountyInfo getCountyFromStation(StationInfo station){
		return stationMap.get(station);
	}
	
	/**
	 * Translate from a county to a list of stations.
	 * 
	 * @param county The CountyInfo object
	 * @return An ArrayList of stations contained in the county
	 */
	public ArrayList<StationInfo> getStationFromCounty(CountyInfo county){
		return countyMap.get(county);
	}
	
	/**
	 * Provide a reference to the displayed colorBar.  This will allow the CountyConnector
	 * to look up colors given values.
	 * 
	 * @param colorBar A reference to an exisiting ColorBar
	 */
	public void setColorBar(ColorBar colorBar){
		this.colorBar = colorBar;
	}
	

	/**
	 * Compute the average statistic over a list of stations (presumably from a single county).
	 * 
	 * @param variableId The variable of interest
	 * @param stations A list of StationInfo objects
	 * @param constraints The inclusivity constraints: determines which years, months and days 
	 * to include in computing the statistic.  
	 * 
	 * @return Observation containing the average (which may be invalid)
	 */
	private Observation getAverageStat(String variableId, ArrayList<StationInfo> stations, 
			ArrayList<List<Integer>> constraints){
		double sum = 0.0;
		int count = 0;
		// Loop over all stations
		for(StationInfo si: stations){
			// Fetch the average stat for the station
			Observation val = si.getAverageStat(variableId, constraints);
			// Valid?
			if(val.getValid()){
				// Yes: include in computation
				sum += val.getValue();
				count++;
			}
		}
		
		if(count == 0)
			// No valid values to use: return invalid answer
			return new Observation();
		else
			// Compute the final mean
			return new Observation(sum/count);
	}
	
	/**
	 * Compute the maximum statistic over a list of stations (presumably from a single county).
	 * 
	 * @param variableId The variable of interest
	 * @param stations A list of StationInfo objects
	 * @param constraints The inclusivity constraints: determines which years, months and days 
	 * to include in computing the statistic.  
	 * 
	 * @return Observation containing the maximum (which may be invalid)
	 */
	private Observation getMaximumStat(String variableId, 
			ArrayList<StationInfo> stations, 
			ArrayList<List<Integer>> constraints){
		
		// Start with an invalid observation
		Observation max = new Observation();
		
		// Loop over all stations
		for(StationInfo si: stations){
			// Fetch the maximum stat from the station
			DailyData d = si.getMaximumStat(variableId, constraints);
			// Did we get an answer
			if(d != null){
				// Yes
				// Pull out the actual value from the identified day
				Observation val = d.getAverageStat(variableId);
				// A new max?
				if(val.isGreaterThan(max)){
					// Yes!
					max = val;
				}
			}
		}
		// Return the best over all attempts
		return max;
	}
	
	/**
	 * Compute the minimum statistic over a list of stations (presumably from a single county).
	 * 
	 * @param variableId The variable of interest
	 * @param stations A list of StationInfo objects
	 * @param constraints The inclusivity constraints: determines which years, months and days 
	 * to include in computing the statistic.  
	 * 
	 * @return Observation containing the minimum (which may be invalid)
	 */
	private Observation getMinimumStat(String variableId, 
			ArrayList<StationInfo> stations, 
			ArrayList<List<Integer>> constraints){
		// Default observation: invalid
		Observation min = new Observation();
		// Loop over all stations
		for(StationInfo si: stations){
			// Fetch the day with the minimum statistic given the constraints
			DailyData d = si.getMinimumStat(variableId, constraints);
			// Did we get back a day that we can use?
			if(d != null){
				// Yes!
				Observation val = d.getAverageStat(variableId);
				// Is this the best so far?
				if(val.isLessThan(min)){
					// Yes - replace best.
					min = val;

				}
			}
		}
		return min;
				
	}
	
	/**
	 * Paint the counties given a variableId, the time constraints and the statistic
	 * to examine.
	 * 
	 * @param variableId Variable used to pain the counties
	 * @param constraints: the year, months and days over which the statistic is computed
	 * @param stat The type of statistic to compute: min, max or average
	 */
	public void paintCounties(String variableId, 
			ArrayList<List<Integer>> constraints,
			StatType stat){
		
		// Loop over all counties
		for(CountyInfo ci: countyMap.keySet()){
			// TODO: Compute the selected statistic on the selected variableId for
			//  the county
			// TODO: Store the resulting observation with the county

		}
		
		////////////////////////////////////////////
		// Find the range of the data across all counties
		double max = -Double.MAX_VALUE;
		double min = Double.MAX_VALUE;
		boolean flag = false;

		// Loop over all of the counties again: identify the min and max values
		for(CountyInfo ci: countyMap.keySet()){
			// Get the value for the county
			Observation o = ci.getObservation();
			// Is it valid?
			if(o.getValid()){
				// Yes: get the value
				double val = o.getValue();
				// Is this the best max?
				if(val > max){
					// Yes
					max = val;
				}
				// Is this the best min?
				if(val < min){
					// Yes
					min = val;
				}
				// We have found at least one value that is valid
				flag = true;
			}
		}

		// Check range
		if(max == min) 
			// No range: just pick something that is real 
			max = min + 0.1;
		////////////////////////////////////////////
		
		// Did we find at least one valid value?
		if(flag){
			// Set the range of values displayed for the colorBar
			colorBar.setRange(min, max);
			
			// TODO: paint all of the counties appropriately for their
			//  statistic value
		}else{
			// There was no data across any of the counties
			// TODO: Assign a bad data color to all counties
			
		}
	}
	
}
