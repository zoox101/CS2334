/**
 * 
 * @author CS2334.  
 * <P>
 * Date: 2015-10-19 <BR>
 * Project 2
 * <P>
 * This class represents individual, real-valued observations.  This class
 * explicitly addresses the fact that some observations are invalid.  
 *
 */

public interface ObservationInterface {
	public abstract double getValue();
	public abstract boolean getValid();
	public abstract boolean isLessThan(ObservationInterface o);
	public abstract boolean isGreaterThan(ObservationInterface o);
	public abstract String toString();
	public abstract String toShortString();
	
}
