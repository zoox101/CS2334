import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author CS2334. Modified by: Will Booker, David Jones
 *         <P>
 *         Date: 2015-11-06 <BR>
 *         Project 4
 *         <P>
 *         This class represents a panel containing the variables that the user
 *         will select, including the station, variable, and year(s). It should
 *         be possible for a user to select multiple years.
 */

// TODO: complete implementation
public class SelectionPanel extends JPanel {

	/** List containing the list station IDs */
	private JList<String> stationList;
	/** List containing all of the variables */
	private JList<String> variableList;
	/** List containing all the years in a data file */
	private JList<String> yearList;
	/** Models the year list. Used in the yearList constructor */
	private DefaultListModel<String> yearListModel;
	/** Array list containing int values of the data file's years */
	private ArrayList<Integer> yearListValues;

	/** Scroll pane for navigating the station list */
	private JScrollPane stationListScroller;
	/** Scroll pane for navigating the variable list */
	private JScrollPane variableListScroller;
	/** Scroll pane for navigating the year list */
	private JScrollPane yearListScroller;

	/** Label next to the station list */
	private JLabel stationLabel;
	/** Label next to the variable list */
	private JLabel variableLabel;
	/** Label next to the year list */
	private JLabel yearListLabel;

	/**
	 * Constructor
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public SelectionPanel(StationInfoList stations, DataInfoList variables) throws FileNotFoundException, IOException {
		// TODO: set display properties of this panel
		this.setBackground(new Color(0, 200, 0, 30));

		// initialize the labels
		stationLabel = new JLabel("Select Station:");
		variableLabel = new JLabel("Select Variable:");
		yearListLabel = new JLabel("Select Year(s):");

		//create and populate the JList using the stations and variables lists
		populateStationList(stations);
		populateVariableList(variables);
		
		//set some of the properties of stationList
		stationList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		stationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		stationList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				// TODO Auto-generated method stub

			}

		});
		
		//set some of the properties of variableList
		variableList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		variableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		variableList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// TODO: define yearListModel

		// TODO: determine the best way to load in the years from FileMenuBar,
		// and populate the list and yearListValues accordingly
		yearList = new JList<String>();
		yearList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// TODO: see if there is a way to make the scroll pane vertical, not
		// horizontal (low priority)
		Dimension scrollPaneDim = new Dimension(250, 150);
		stationListScroller = new JScrollPane(stationList);
		stationListScroller.setMinimumSize(scrollPaneDim);
		variableListScroller = new JScrollPane(variableList);
		variableListScroller.setMinimumSize(scrollPaneDim);
		yearListScroller = new JScrollPane(yearList);
		yearListScroller.setMinimumSize(scrollPaneDim);

		// TODO: determine layout of this label component (subject to change)
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		this.setLayout(layout);

		c.gridx = 0;
		c.gridy = 0;
		add(stationLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		add(stationListScroller, c);

		c.gridx = 0;
		c.gridy = 1;
		add(variableLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		add(variableListScroller, c);

		c.gridx = 0;
		c.gridy = 2;
		add(yearListLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		add(yearListScroller, c);
	}

	/**
	 * This method, called by the constructor, takes the station ID field from
	 * every StationInfo in the StationInfoList, puts it into an array, and then
	 * populates the JList using this array
	 * 
	 * @param stations
	 *            The list from which the Station IDs will come
	 */
	private void populateStationList(StationInfoList stations) {
		// DONE: complete implementation
		String[] stationArray = stations.getStationIds().toArray(new String[stations.getStationIds().size()]);
		stationList = new JList<String>(stationArray);
	}

	/**
	 * This method, called by the constructor, takes the variable ID field from
	 * every DataInfo in the DataInfoList, puts it into an array, and then
	 * populates the JList using this array
	 * 
	 * @param variables
	 *            The list from which the variable IDs will come
	 */
	private void populateVariableList(DataInfoList variables) {
		// DONE: complete implementation
		String[] variableArray = variables.getVariableIds()
				.toArray(new String[variables.getVariableIds().size()]);
		variableList = new JList<String>(variableArray);
	}
}
