/**
 * 
 * @author CS2334. Modified by: David Jones & Will Booker
 *         <P>
 *         Date: 2015-09-28 <BR>
 *         Project 3
 *         <P>
 *         This class represents individual, real-valued observations. This
 *         class explicitly addresses the fact that some observations are
 *         invalid.
 *
 */

public class Observation {
	/** The observed value. */
	private double value;
	/** Indicates whether the observation is a valid one */
	private boolean valid;

	/**
	 * Observation constructor.
	 * <P>
	 * Values that are larger than -900 are assumed to be valid
	 * 
	 * @param value
	 *            The value to be stored
	 */
	public Observation(double value) {
		this.value = value;
		valid = value > -900.0;
	}

	/**
	 * Observation constructor: construct an invalid object.
	 */
	public Observation() {
		// TODO: complete implementation
		this(-990.0);
	}

	/**
	 * Compare this to o. Return true if this is strictly less than o
	 * 
	 * @param o
	 *            Observation to compare with
	 * @return true if 1) this is valid AND 2) o is invalid OR this is less than
	 *         o
	 */
	public boolean isLessThan(Observation o) {
		// TODO: complete implementation
		// if both values are valid, compare the two values
		if (this.valid && o.valid)
			return this.value < o.value;
		// if this object is invalid or both objects are invalid, return false
		if ((!this.valid && o.valid) || (!this.valid && !o.valid))
			return false;
		// if the value being compared to is invalid, return false
		return true;
	}

	/**
	 * Compares this to o and returns true if this is strictly larger than o
	 * 
	 * @param o
	 *            Observation to compare against
	 * @return true if 1) this is valid AND 2) o is invalid OR this is strictly
	 *         larger than o.
	 */
	public boolean isGreaterThan(Observation o) {
		// TODO: complete implementation
		// if both values are valid, compare the two values
		if (this.valid && o.valid)
			return this.value > o.value;
		// if this object is invalid or both objects are invalid, return false
		if ((!this.valid && o.valid) || (!this.valid && !o.valid))
			return false;
		// if the value being compared to is invalid, return false
		return true;
	}

	/**
	 * The numerical value of the observation
	 * 
	 * @return value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * The boolean indicating whether the observation is valid or not
	 * 
	 * @return valid
	 */
	public boolean getValid() {
		return valid;
	}

	/**
	 * Describes the observation.
	 * 
	 * @return String that describes the observation: either the value or
	 *         "invalid";
	 */
	public String toString() {
		if (valid)
			return "" + value;
		else
			return "invalid";
	}

}
