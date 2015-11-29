import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class creates a pop-up window that displays information about all of the 
 * stations in the county. The new window is non-modal so multiple such windows can be 
 * created.
 * <P>
 * Project 5 <BR>
 * Date: 2015-11-18
 * 
 * @author CS 2334.   Modified by ????
 *
 */

public class CountyFrame extends JFrame {
	
	
    /////////////////////////////////////////////////////////////////////
	/**
	 * Constructor.  <P>
	 * Creates a Frame with one column of row labels + one column for each station in
	 * the stationInfoArray
	 * 
	 * @param countyInfo County info object
	 * @param stationInfoArray Array of stations to descrie in the frame
	 */
	public CountyFrame(CountyInfo countyInfo, ArrayList<StationInfo> stationInfoArray){
		super("County Viewer");
		this.setLayout(new BorderLayout());
		
		// County Panel: displays county name
		JPanel headerPanel;
		headerPanel = new JPanel();
		headerPanel.add(new JLabel("County: " + countyInfo.getName()));
		this.add(headerPanel, BorderLayout.NORTH);
		Color topColor = new Color(240,200,200);
		headerPanel.setBackground(topColor);

		/////////////////////////////////////////////////////
		Color labelColor = new Color(200,200,230);
				
		// Station panel: displays a set of labels down the left hand side column and then one station per column
		JPanel stationPanel;
		stationPanel = new JPanel();
		stationPanel.setLayout(new GridLayout(1,0));
		
		// Label panel
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridBagLayout());
		stationPanel.add(labelPanel);
		labelPanel.setBackground(labelColor);
		
		// Place one label per row 
		GridBagConstraints layoutConst;
		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10, 10, 10, 10);
		layoutConst.anchor = GridBagConstraints.WEST;
		
		labelPanel.add(new JLabel("Station ID"), layoutConst);
		
		layoutConst.gridy++;
		labelPanel.add(new JLabel("Name"), layoutConst);
		
		layoutConst.gridy++;
		labelPanel.add(new JLabel("City"), layoutConst);
		
		layoutConst.gridy++;
		labelPanel.add(new JLabel("Latitude"), layoutConst);
		
		layoutConst.gridy++;
		labelPanel.add(new JLabel("Longitude"), layoutConst);
		
		layoutConst.gridy++;
		labelPanel.add(new JLabel("Start Date"), layoutConst);
		
		layoutConst.gridy++;
		labelPanel.add(new JLabel("End Date"), layoutConst);
		
		//////////////////////////////////////
		// One column per station
		JLabel field;
		// Two different colors for the columns: makes each column visually distinct
		//   We will alternate colors
		Color[] colors = {new Color(200, 220, 200),
				new Color(200, 240, 200)};
		
		// TODO: create new panels for each station (with alternating colors)
		
		
		
		this.add(stationPanel, BorderLayout.CENTER);
		
		// Close button: forces the window to properly close
		JButton button = new JButton("Close");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Simulates pressing the window close button
				CountyFrame.this.dispatchEvent(new WindowEvent(CountyFrame.this, WindowEvent.WINDOW_CLOSING));
				
			}
			
		});
		// Add the button to the frame
		this.add(button, BorderLayout.SOUTH);
		this.pack();
		// Open in the middle of the screen
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
}
