import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author CS2334. Modified by: Will Booker, David Jones
 *         <P>
 *         Date: 2015-11-06 <BR>
 *         Project 4
 *         Version 2
 *         <P>
 *         This class is the frame containing all of the GUI components for
 *         display statistics that are loaded from a file.
 */
public class WeatherFrame extends JFrame {

	/** Menu that will allow the user to load in a data file */
	private FileMenuBar fileMenuBar;
	/** Panel containing the stations and variables that the user can select */
	private SelectionPanel selectionPanel;
	/** Panel containing text boxes that will output most of the data */
	private DataPanel dataPanel;
	/** A list of stations that there will be statistics available for */
	private StationInfoList stationInfoList;
	/** A list to be loaded in containing all the Mesonet variables */
	private DataInfoList dataInfoList;

	/**
	 * Constructor
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public WeatherFrame() throws FileNotFoundException, IOException {

		super();

		setSize(1000, 600);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// initialize all of the components
		fileMenuBar = new FileMenuBar();

		// load in the files and create the stationList and dataInfoList
		stationInfoList = new StationInfoList("data/geoinfo.csv");
		dataInfoList = new DataInfoList("data/DataTranslation.csv");
		DailyData.setDataInfoList(dataInfoList);

		// pass the station and data info list to the constructor of selection
		// panel
		selectionPanel = new SelectionPanel(stationInfoList, dataInfoList);
		dataPanel = new DataPanel();

		// add the menu bar
		this.setJMenuBar(fileMenuBar);

		// configure the layout of components within the frame
		GridLayout layout = new GridLayout(1, 2);
		this.setLayout(layout);
		add(selectionPanel);
		add(dataPanel);

		// leave setVisible to the last
		setVisible(true);
	}

	/**
	 * 
	 * @author CS2334. Modified by: Will Booker, David Jones
	 *         <P>
	 *         Date: 2015-11-06 <BR>
	 *         Project 4
	 *         <P>
	 *         This class represents a menu bar that can open a file and then
	 *         populate a list of years according to how many there are in the
	 *         Mesonet data file.
	 */

	class FileMenuBar extends JMenuBar {

		/** The menu itself */
		private JMenu menu;
		/** The file opener menu item */
		private JMenuItem menuOpen;
		/** The exit program menu item */
		private JMenuItem menuExit;
		/** The file chooser that opens when menuOpen is clicked */
		private JFileChooser fileChooser;

		/**
		 * Constructor
		 */
		public FileMenuBar() {

			super();

			// initialize fields
			menu = new JMenu("File");
			menuOpen = new JMenuItem("Open Data File");
			menuExit = new JMenuItem("Exit");
			fileChooser = new JFileChooser(new File("./data"));

			// configure fileChooser
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));

			// add action listeners to the menu items
			menuOpen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int returnValue = fileChooser.showOpenDialog(FileMenuBar.this);
					if (returnValue == 0) {
						// FIXED If the user hits open, Load the selected file
						// using
						WeatherFrame.this.setCursor(Cursor.getPredefinedCursor(WAIT_CURSOR));
						try {
							// let the user know data is being loaded in
							WeatherFrame.this.setCursor(Cursor.WAIT_CURSOR);
							stationInfoList.loadData(fileChooser.getSelectedFile().toString());

							// selectionPanel.yearListModel.clear();

							// reset the year model
							selectionPanel.yearListModel.clear();
							selectionPanel.yearListModel.addElement("All");
							for (Integer year : YearlyData.getYearSet()) {
								selectionPanel.yearListModel.addElement(year.toString());
							}
							selectionPanel.yearList.setSelectedIndex(0);

						} catch (FileNotFoundException e) {
							// FIXED Auto-generated catch block
							JOptionPane.showMessageDialog(null, "FileNotFoundException. Error loading file.", "Message",
									JOptionPane.OK_OPTION);
							e.printStackTrace();
						} catch (IOException e) {
							// FIXED Auto-generated catch block
							e.printStackTrace();
						} finally {
							WeatherFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
					} else if (returnValue == 1) {
						// don't do anything
					}
				}

			});

			menuExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			// add the menu items to the menu
			menu.add(menuOpen);
			menu.add(menuExit);

			this.add(menu);
		}
	}

	/**
	 * 
	 * @author CS2334. Modified by: Will Booker, David Jones
	 *         <P>
	 *         Date: 2015-11-06 <BR>
	 *         Project 4
	 *         <P>
	 *         This class represents a panel containing the variables that the
	 *         user will select, including the station, variable, and year(s).
	 *         It should be possible for a user to select multiple years.
	 */
	class SelectionPanel extends JPanel {

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
		public SelectionPanel(StationInfoList stations, DataInfoList variables)
				throws FileNotFoundException, IOException {

			super();

			this.setBackground(new Color(0, 200, 0, 30));

			// initialize the labels
			stationLabel = new JLabel("Select Station:");
			variableLabel = new JLabel("Select Variable:");
			yearListLabel = new JLabel("Select Year(s):");

			// create and populate the JList using the stations and variables
			// lists
			populateStationList(stations);
			populateVariableList(variables);

			// set some of the properties of stationList
			stationList.setVisibleRowCount(-1);
			stationList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			stationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			stationList.setSelectedIndex(0);
			stationList.addListSelectionListener(new MyListListener());

			// set some of the properties of variableList
			variableList.setVisibleRowCount(-1);
			variableList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			variableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			variableList.setSelectedIndex(0);
			variableList.addListSelectionListener(new MyListListener());

			// create the list model
			yearListModel = new DefaultListModel<String>();
			yearListModel.addElement("All");

			// create the year JList
			yearList = new JList<String>(yearListModel);
			yearList.setSelectedValue("All", true);
			yearList.addListSelectionListener(new MyListListener());

			// configure the scroll panes
			Dimension scrollPaneDim = new Dimension(250, 150);
			stationListScroller = new JScrollPane(stationList);
			stationListScroller.setMinimumSize(scrollPaneDim);
			variableListScroller = new JScrollPane(variableList);
			variableListScroller.setMinimumSize(scrollPaneDim);
			yearListScroller = new JScrollPane(yearList);
			yearListScroller.setMinimumSize(scrollPaneDim);

			// configure the layout of components
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
		 * This method, called by the constructor, takes the station ID field
		 * from every StationInfo in the StationInfoList, puts it into an array,
		 * and then populates the JList using this array
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
		 * This method, called by the constructor, takes the variable ID field
		 * from every DataInfo in the DataInfoList, puts it into an array, and
		 * then populates the JList using this array
		 * 
		 * @param variables
		 *            The list from which the variable IDs will come
		 */
		private void populateVariableList(DataInfoList variables) {
			// DONE: complete implementation
			String[] variableArray = variables.getVariableIds().toArray(new String[variables.getVariableIds().size()]);
			variableList = new JList<String>(variableArray);
		}
	}

	/**
	 * 
	 * @author CS2334. Modified by: Will Booker, David Jones
	 *         <P>
	 *         Date: 2015-11-06 <BR>
	 *         Project 4
	 *         <P>
	 *         This class represents the main componenet for displaying output
	 *         to the user. It should display relevant statistics based on what
	 *         parameters the user selects from the SelectionPanel object.
	 */
	private class DataPanel extends JPanel {

		/** Label next to the station ID */
		private JLabel stationLabel;
		/** Label next to the variable */
		private JLabel variableLabel;
		/** Label next to the minimum measurement value */
		private JLabel minLabel;
		/** Label next to the maximum measurement value */
		private JLabel maxLabel;
		/** Label next to the average measurement value */
		private JLabel averageLabel;

		/** Text field containing the station Id */
		private JTextField stationIdField;
		/** Text field containing the name of the station */
		private JTextField stationNameField;
		/** Text field containing the city the station is in */
		private JTextField stationCityField;
		/** Text field containing the variable Id */
		private JTextField variableIdField;
		/** Text field containing the minimum value as a double */
		private JTextField minVal;
		/** Text field containing the maximum value as a double */
		private JTextField maxVal;
		/** Text field containing the average value as a double */
		private JTextField averageVal;
		/** Text field containing the units the variable is measured in */
		private JTextField variableUnitsField;
		/**
		 * Text field containing the date on which the minimum value occurred
		 */
		private JTextField minDateField;
		/**
		 * Text field containing the date on which the maximum value occurred
		 */
		private JTextField maxDateField;
		/**
		 * Text area that displays in more detail what the variable represents
		 */
		private JTextArea variableDescription;

		/**
		 * Constructor
		 */
		public DataPanel() {

			super();

			this.setBackground(new Color(0, 0, 200, 30));

			// initialize all of the components and set their properties
			stationLabel = new JLabel("Station:");
			variableLabel = new JLabel("Variable:");
			maxLabel = new JLabel("Maximum:");
			minLabel = new JLabel("Minimum:");
			averageLabel = new JLabel("Average:");

			averageVal = new JTextField();
			averageVal.setEditable(false);
			averageVal.setColumns(15);
			maxDateField = new JTextField();
			maxDateField.setEditable(false);
			maxDateField.setColumns(15);
			maxVal = new JTextField();
			maxVal.setEditable(false);
			maxVal.setColumns(15);
			minDateField = new JTextField();
			minDateField.setEditable(false);
			minDateField.setColumns(15);
			minVal = new JTextField();
			minVal.setEditable(false);
			minVal.setColumns(15);
			stationCityField = new JTextField();
			stationCityField.setEditable(false);
			stationCityField.setColumns(15);
			stationIdField = new JTextField();
			stationIdField.setEditable(false);
			stationIdField.setColumns(15);
			stationNameField = new JTextField();
			stationNameField.setEditable(false);
			stationNameField.setColumns(15);
			variableDescription = new JTextArea();
			variableDescription.setEditable(false);
			variableDescription.setColumns(15);
			variableDescription.setRows(4);
			variableDescription.setLineWrap(true);
			variableDescription.setWrapStyleWord(true);
			variableIdField = new JTextField();
			variableIdField.setEditable(false);
			variableIdField.setColumns(15);
			variableUnitsField = new JTextField();
			variableUnitsField.setEditable(false);
			variableUnitsField.setColumns(15);

			// configure layout
			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			layout.setConstraints(this, c);
			this.setLayout(layout);

			// create spacing between components
			c.insets = new Insets(10, 10, 10, 10);

			// place components within the panel
			c.gridx = 0;
			c.gridy = 0;
			add(stationLabel, c);

			c.gridx = 1;
			c.gridy = 0;
			add(stationIdField, c);

			c.gridx = 2;
			c.gridy = 0;
			add(stationNameField, c);

			c.gridx = 2;
			c.gridy = 1;
			add(stationCityField, c);

			c.gridx = 0;
			c.gridy = 2;
			add(variableLabel, c);

			c.gridx = 1;
			c.gridy = 2;
			add(variableIdField, c);

			c.gridx = 2;
			c.gridy = 2;
			add(variableUnitsField, c);

			c.gridx = 2;
			c.gridy = 3;
			add(variableDescription, c);

			c.gridx = 0;
			c.gridy = 4;
			add(maxLabel, c);

			c.gridx = 1;
			c.gridy = 4;
			add(maxVal, c);

			c.gridx = 2;
			c.gridy = 4;
			add(maxDateField, c);

			c.gridx = 0;
			c.gridy = 5;
			add(averageLabel, c);

			c.gridx = 1;
			c.gridy = 5;
			add(averageVal, c);

			c.gridx = 0;
			c.gridy = 6;
			add(minLabel, c);

			c.gridx = 1;
			c.gridy = 6;
			add(minVal, c);

			c.gridx = 2;
			c.gridy = 6;
			add(minDateField, c);
		}

		/**
		 * This method is called by the action listeners of all three JList.
		 * This will take the currently selected values of the JList and display
		 * the statistics of that data set in the text fields
		 */
		public void updateData() {
			// FIXED: complete implementation
			// Creating references
			SelectionPanel selectionPanel = WeatherFrame.this.selectionPanel;
			DataPanel dataPanel = WeatherFrame.this.dataPanel;

			// Getting values
			String stationId = selectionPanel.stationList.getSelectedValue();
			String variableId = selectionPanel.variableList.getSelectedValue();
			java.util.List<String> yearsString = selectionPanel.yearList.getSelectedValuesList();
			ArrayList<Integer> years = new ArrayList<Integer>();
			if (yearsString.get(0).equalsIgnoreCase("all")) {
				years.addAll(YearlyData.getYearSet());
			} else {
				for (String year : yearsString)
					years.add(Integer.parseInt(year));
			}

			// Getting the relevant station
			StationInfo station = WeatherFrame.this.stationInfoList.getStationInfo(stationId);
			DataInfo dataInfo = WeatherFrame.this.dataInfoList.getDataInfo(variableId);

			// Setting fields
			dataPanel.stationIdField.setText(stationId);
			dataPanel.stationNameField.setText(station.getName());
			dataPanel.stationCityField.setText(station.getCity());
			dataPanel.variableIdField.setText(variableId);
			dataPanel.variableUnitsField.setText(dataInfo.getUnit());
			dataPanel.variableDescription.setText(dataInfo.getDescription());

			// create new data set based on user selected values
			DataSet dataSet = new DataSet(station.getDataSet(), years);

			// initializing strings for null pointer cases
			String max = "No Valid Data";
			String maxDate = "No Valid Data";
			String min = "No Valid Data";
			String minDate = "No Valid Data";

			// if no null pointer occurs, initialize strings to be displayed to
			// user
			if (dataSet.getMaximumStat(variableId) != null) {
				max = Double
						.toString(dataSet.getMaximumStat(variableId).getObservationMap().get(variableId).getValue());
				maxDate = dataSet.getMaximumStat(variableId).getDate();
				min = Double
						.toString(dataSet.getMinimumStat(variableId).getObservationMap().get(variableId).getValue());
				minDate = dataSet.getMinimumStat(variableId).getDate();
			}

			// display statistics to the user
			dataPanel.maxVal.setText(max);
			dataPanel.maxDateField.setText(maxDate);
			dataPanel.averageVal.setText(dataSet.getAverageStat(variableId).toString());
			dataPanel.minVal.setText(min);
			dataPanel.minDateField.setText(minDate);
		}
	}

	/**
	 * 
	 * @author David Since selecting from any of the three lists in
	 *         SelectionPanel should accomplish the same goal (updating the
	 *         DataPanel), they all implement this one action listener
	 */
	class MyListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// FIXED: Update the DataPanel's text fields depending on the
			// currently selected values from the JList
			if (!selectionPanel.yearList.getSelectedValuesList().isEmpty()) {
				dataPanel.updateData();
				WeatherFrame.this.repaint();
			}
		}

	}
}