import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * Store information about a single county.
 * 
 * <P>
 * Project 5<BR>
 * Date: 2015-11-18
 * <P>
 * 
 * @author CS2334.  Modified by ???
 *
 */

public class CountyInfo {
	/** County name */
	private String name;
	/** Polygon that represents the outline of the county.  */
	private Polygon polygon;
	/** Color to render the county in.  */
	private Color color;
	/** A temporary value that can be stored with the county */
	private Observation tempValue;
	
	/**
	 * Constructor. <P>
	 * 
	 * Elements array contains: 0. The name of the county 1/2: long/lat of
	 * first point in the polygon.  3/4: long/lat of the 2nd point, ...
	 * <P>
	 * The number of long/lat coordinates is not assumed to be known ahead of time.
	 * 
	 * @param elements Elements that define the county.  
	 * @param scaleX Scale translation from long/lat to screen coordinates
	 * @param scaleY Scale translation from long/lat to screen coordinates
	 */
	public CountyInfo(String[] elements, 
			double scaleX, double scaleY){
		// TODO: complete the implementation
		
		// Default color
		color = Color.RED;
	}

	/**
	 * Color getter
	 * 
	 * @return Return the color of the polygon
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Color setter
	 * 
	 * @param color The new color of the polygon
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Name getter
	 * 
	 * @return Name of the county
	 */
	public String getName() {
		return name;
	}

	/**
	 * Draw the county in the g2 context
	 * 
	 * @param g2 Graphics context in which to draw the polygon
	 */
	protected void draw(Graphics2D g2){
		// TODO: complete the implementation
		
	}
	
	/**
	 * Move the polygon by some amount.
	 * 
	 * @param deltaX Screen units to change X position by
	 * @param deltaY Screen units to change Y position by
	 */
	protected void translate(int deltaX, int deltaY){
		polygon.translate(deltaX, deltaY);
	}
	
	/**
	 * Return the bounding box of the county in screen coordinates
	 * @return Rectangle describing the bounding box
	 */
	public Rectangle getBounds(){
		return polygon.getBounds();
	}

	/**
	 * Determine whether a point in screen coordinates is in the 
	 * the polygon 
	 * 
	 * @param x X coordinate position of query
	 * @param y Y coordinate position of query
	 * @return true if the point x/y is in the polygon
	 */
	public boolean contains(int x, int y){
		// TODO: complete the implementation
	}
	
	/**
	 * Return the temporary observation associated with the county
	 * 
	 * @return Observation stored with the county
	 */
	public Observation getObservation(){
		return tempValue;
	}
	
	/**
	 * Set the observation associated with the county.
	 *  
	 * @param o New value for the observation
	 */
	public void setObservation(Observation o){
		tempValue = o;
	}
	
}
