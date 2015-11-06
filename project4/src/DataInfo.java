
/**
 * 
 * @author CS2334.  Modified by: Will Booker
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 3
 * <P>
 * This class represents the data for a single variable that is described by station
 *
 */

public class DataInfo {
	/** Name of the variable.  */
	private String variableName;
	/** ID of the variable.  */
	private String variableId;
	/** Units of the variable.  */
	private String unit;
	/** True if positive values should be plotted in the upwards direction (used in a coming project)*/
	private boolean positive;
	/** Variable description */
	private String description;
	
	/**
	 * Constructor
	 * 
	 * @param variableName String name of the variable
	 * @param id String ID of the variable
	 * @param unit Units that the variable is encoded in
	 * @param positive Boolean that indicates whether positive values should be plotted upwards
	 * @param description String description of the variable
	 */
	public DataInfo(String variableName, String id, String unit, boolean positive, String description) 
	{
		// FIXED: complete implementation
		this.variableName = variableName;
		this.variableId = id;
		this.unit = unit;
		this.positive = positive;
		this.description = description;
	}

	// Getters
	//FIXED: provide implementation


	/**
	 * Indicate whether positive changes in the variable value corresponds to upwards
	 * changes in a plot.
	 * 
	 * @return Boolean positive property
	 */
	public boolean isPositive() 
	{
		return positive;
	}

	/**
	 * Return a formatted string describing the variable
	 * 
	 * @return String describing this variable
	 */
	public String getFormattedString()
	{
		return String.format("%-12s %-60s %s", variableId, variableName, unit);
	}

	/**
	 * Describe this variable in short form.
	 * 
	 * @return String describing this variable
	 */
	public String toString()
	{
		return String.format("%s, %s (%s)", variableId, variableName, unit);
	}

	public String getVariableName() {
		return variableName;
	}

	public String getVariableId() {
		return variableId;
	}

	public String getUnit() {
		return unit;
	}

	public String getDescription() {
		return description;
	}
	
}
