import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * 
 * @author CS2334. Modified by: Will Booker, David Jones
 *         <P>
 *         Date: 2015-11-05 <BR>
 *         Project 4
 *         <P>
 *         This class is a driver for project 4. It simply loads the GUI.
 * 
 */
public class Driver {

	public static void main(String[] args) {
		//TODO: think about what exceptions might occur
		try {
			WeatherFrame myFrame = new WeatherFrame();
		} catch (FileNotFoundException e) {
			// DONE: Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File not found.", "Message", JOptionPane.OK_OPTION);
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
