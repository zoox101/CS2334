import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Define the main interaction window for the state-based information
 * <P>
 * Project 5<BR>
 * Date: 2015-11-11
 * 
 * @author CS 2334.  Modified by ???
 *
 */

public class StateFrame extends JFrame {
	/** County info list.  */
	private CountyInfoList countyInfoList;
	/** Panel for displaying the set of counties.  */
	private CountyPanel countyPanel;
	/** Connection between stations and counties.  */
	private CountyConnector countyConnector;
	/** Station info list: all stations  */
	private StationInfoList stationInfoList;
	/** Data info list: all measurements made at a station.  */
	private DataInfoList dataInfoList;
	/** Menu bar for files.  */
	private FileMenuBar fileMenuBar;
	/** Panel for selecting what to display.  */
	private SelectionPanel selectionPanel;

	//////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Menu bar
	 * 
	 * <P>
	 * THIS INNER CLASS IS COMPLETE
	 * 
	 */
	public class FileMenuBar extends JMenuBar{
		/**  Makes Eclipse happy */
		private static final long serialVersionUID = 1L;
		/** Menu object.  */
		JMenu menu;
		/** Exit menu item.  */
		JMenuItem menuExit;
		/** File open menu item.  */
		JMenuItem menuOpen;
		/** Binary file open menu item. */
		//JMenuItem menuOpenBinary;
		/** Binary file save menu item. */
		//JMenuItem menuSaveBinary;
		/** Pop-up file chooser.  */
		JFileChooser fileChooser;

		/**
		 * Constructor
		 */
		public FileMenuBar(){
			// Add the menu to the menu bar
			menu = new JMenu("File");
			add(menu);
			
			// Create and add menu items 
			menuOpen = new JMenuItem("Open Text File");
			//menuOpenBinary = new JMenuItem("Open Binary File");
			//menuSaveBinary = new JMenuItem("Save Binary File");
			menuExit = new JMenuItem("Exit");
			menu.add(menuOpen);
			//menu.add(menuOpenBinary);
			//menu.add(menuSaveBinary);
			menu.add(menuExit);

			// Exit menu item behavior
			menuExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});

			// Create the file chooser
			fileChooser = new JFileChooser(new File("./data"));

			// Text file open behavior
			menuOpen.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// Choose the file
					int returnVal = fileChooser.showOpenDialog(menuOpen);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						// User gave a file
						File file = fileChooser.getSelectedFile();
						try{
							// Clear the update flag
							StateFrame.this.selectionPanel.updateFlag = false;
							// Set to a "busy" cursor
							StateFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

							// Do the loading work
							stationInfoList.loadData(file.toString());

							// Update the displayed set of available years
							StateFrame.this.selectionPanel.yearListModel.removeAllElements();
							//StateFrame.this.selectionPanel.yearListValues.clear();
							StateFrame.this.selectionPanel.yearListModel.addElement("All");
							for(Integer i: YearlyData.getYearSet()){
								StateFrame.this.selectionPanel.yearListModel.addElement(i.toString());
								//StateFrame.this.selectionPanel.yearListValues.add(i);
							}
							StateFrame.this.selectionPanel.yearList.setSelectedIndex(0);

							// Update the display
							StateFrame.this.countyPanel.updateData();
							//System.out.println("File loaded");
						}catch(FileNotFoundException e2){
							JOptionPane.showMessageDialog(fileChooser, "File not found");
						}catch(IOException e2){
							JOptionPane.showMessageDialog(fileChooser, "File load error");
						}catch(Exception e2){
							JOptionPane.showMessageDialog(fileChooser, "File load error");
						}finally{
							// Set cursor back to pointer
							StateFrame.this.setCursor(null);
							StateFrame.this.selectionPanel.updateFlag = true;
						}
					}else{
						//System.out.println("No file.");

					}
				}

			});
			
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Panel for presenting a set of options to the user.  Specifically: variableIDs, statistic type
	 * (min, max or average), years, months, days.
	 * 
	 *   <P>
	 *   
	 *   THIS INNER CLASS IS COMPLETE
	 */
	public class SelectionPanel extends JPanel{

		/** List display of variables.  */
		private JList<String> variableList;
		/** List display of years */
		private JList<String> yearList;
		/** List display of months.  */
		private JList<String> monthList;
		/** List display of days.  */
		private JList<String> dayList;
		/** Model for storing the displayed years.  */
		private DefaultListModel<String> yearListModel;

		/** Makes Eclipse happy.  */
		private static final long serialVersionUID = 1L;

		// Scrollers for the JLists
		private JScrollPane variableListScroller;
		private JScrollPane yearListScroller;
		private JScrollPane monthListScroller;
		private JScrollPane dayListScroller;

		// Labels 
		private JLabel variableLabel;
		private JLabel yearListLabel;
		private JLabel monthListLabel;
		private JLabel dayListLabel;
		private JLabel statisticLabel;
		
		// Buttons 
		JRadioButton minButton;
		JRadioButton maxButton;
		JRadioButton averageButton;
		JPanel buttonPanel;
		
		// Update flag
		boolean updateFlag;
		
		/**
		 * Constructor
		 */
		public SelectionPanel(){
			// Don't call the data update yet.
			updateFlag = false;
			
			// Configure the panel
			this.setLayout(new GridBagLayout());
			
			// Statistics selection using JRadioButtons
			minButton = new JRadioButton("Minimum");
			maxButton = new JRadioButton("Maximum");
			averageButton = new JRadioButton("Average");
			ButtonGroup group = new ButtonGroup();
			group.add(minButton);
			group.add(averageButton);
			group.add(maxButton);
			// Create the action listener for all of these buttons
			ActionListener al = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					StateFrame.this.countyPanel.updateData();
				}
				
			};
			// Default button mode
			averageButton.setSelected(true);
			
			// Connect the action listener to all of these buttons
			minButton.addActionListener(al);
			maxButton.addActionListener(al);
			averageButton.addActionListener(al);
			
			// Put the panel together
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout());
			buttonPanel.add(minButton);
			buttonPanel.add(averageButton);
			buttonPanel.add(maxButton);
			
			// Variable selection
			
			variableList = new JList<String>(dataInfoList.getVariableIdArray());
			variableListScroller = new JScrollPane(variableList);
			variableListScroller.setPreferredSize(new Dimension(300, 150));
			variableList.setVisibleRowCount(-1);
			variableList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			variableList.setSelectedIndex(0);
			variableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			variableList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent e) {
					if(!e.getValueIsAdjusting() && updateFlag){
						StateFrame.this.countyPanel.updateData();
					}
				}

			});
			variableList.setPrototypeCellValue("ACME--");

			// Year selection
			yearListModel = new DefaultListModel<String>();
			yearListModel.add(0, "All");
			yearList = new JList<String>(yearListModel);
			yearListScroller = new JScrollPane(yearList);
			yearListScroller.setPreferredSize(new Dimension(300, 80));
			yearList.setPrototypeCellValue("2001--");
			yearList.setVisibleRowCount(-1);
			yearList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			yearList.setSelectedIndex(0);
			yearList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

			yearList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent e) {
					if(!e.getValueIsAdjusting() && updateFlag){
						StateFrame.this.countyPanel.updateData();
					}
				}

			});


			// Month list
			String[] mList = {"All", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
					"Aug", "Sept", "Oct", "Nov", "Dec"};
			monthList = new JList<String>(mList);
			monthListScroller = new JScrollPane(monthList);
			monthListScroller.setPreferredSize(new Dimension(300, 80));
			//monthList.setPreferredSize(new Dimension(300, 200));
			monthList.setVisibleRowCount(-1);
			monthList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			monthList.setSelectedIndex(0);
			monthList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			monthList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent e) {
					if(!e.getValueIsAdjusting() && updateFlag){
						StateFrame.this.countyPanel.updateData();
					}
				}

			});
			monthList.setPrototypeCellValue("12345");


			// Day list
			
			// Create the set of entries
			String[] dList = new String[32];
			dList[0] = "All";
			for(Integer i = 1; i <= 31; ++i){
				dList[i] = i.toString();
			}
			// Create the list within a scroller
			dayList = new JList<String>(dList);
			dayListScroller = new JScrollPane(dayList);
			dayListScroller.setPreferredSize(new Dimension(300, 80));
			dayList.setVisibleRowCount(-1);
			dayList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			dayList.setSelectedIndex(0);
			dayList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			dayList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent e) {
					if(!e.getValueIsAdjusting() && updateFlag){
						StateFrame.this.countyPanel.updateData();
					}
				}

			});
			dayList.setPrototypeCellValue("All ");
			
			// Labels
			variableLabel = new JLabel("Select Variable: ");
			yearListLabel = new JLabel("Select Year(s): ");
			monthListLabel = new JLabel("Select Month(s): ");
			dayListLabel = new JLabel("Select Day(s): ");
			statisticLabel = new JLabel("Select statistic: ");

			// Layout of panel
			GridBagConstraints layoutConst;
			
			layoutConst = new GridBagConstraints();
			layoutConst.gridx = 0;
			layoutConst.gridy = 0;
			layoutConst.insets = new Insets(10, 10, 10, 10);
			add(variableLabel, layoutConst);

			layoutConst = new GridBagConstraints();
			layoutConst.gridx = 1;
			layoutConst.gridy = 0;
			layoutConst.insets = new Insets(10, 10, 10, 10);
			add(variableListScroller, layoutConst);

			layoutConst = new GridBagConstraints();
			layoutConst.gridx = 0;
			layoutConst.gridy = 1;
			layoutConst.insets = new Insets(10, 10, 10, 10);
			add(statisticLabel, layoutConst);

			layoutConst = new GridBagConstraints();
			layoutConst.gridx = 1;
			layoutConst.gridy = 1;
			layoutConst.insets = new Insets(10, 10, 10, 10);
			add(buttonPanel, layoutConst);


			layoutConst = new GridBagConstraints();
			layoutConst.gridx = 0;
			layoutConst.gridy = 2;
			layoutConst.insets = new Insets(10, 10, 10, 10);
			add(yearListLabel, layoutConst);

			layoutConst = new GridBagConstraints();
			layoutConst.gridx = 1;
			layoutConst.gridy = 2;
			layoutConst.insets = new Insets(10, 10, 10, 10);
			add(yearListScroller, layoutConst);

			layoutConst = new GridBagConstraints();
			layoutConst.gridx = 0;
			layoutConst.gridy = 3;
			layoutConst.insets = new Insets(10, 10, 10, 10);
			add(monthListLabel, layoutConst);


			layoutConst = new GridBagConstraints();
			layoutConst.gridx = 1;
			layoutConst.gridy = 3;
			layoutConst.insets = new Insets(10, 10, 10, 10);
			add(monthListScroller, layoutConst);

			
			layoutConst = new GridBagConstraints();
			layoutConst.gridx = 0;
			layoutConst.gridy = 4;
			layoutConst.insets = new Insets(10, 10, 10, 10);
			add(dayListLabel, layoutConst);


			layoutConst = new GridBagConstraints();
			layoutConst.gridx = 1;
			layoutConst.gridy = 4;
			layoutConst.insets = new Insets(10, 10, 10, 10);
			add(dayListScroller, layoutConst);

			this.setBackground(new Color(200, 220, 200));
		}


	}
	///////////////////////////////////////////////////////////////
	/**
	 * Panel for displaying the state (all of the counties)
	 * <P>
	 * THIS IS WHERE THE WORK IS
	 * 
	 */
	public class CountyPanel extends JPanel{
		/** Listener for mouse events.  */
		//private CountyListener listener;
		/** Color bar to indicate the relationship between colors and values.  */
		private ColorBar colorBar;
		/** Font to use to display variable information */
		private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
		/** Currently-selected variable */
		private String variableId;
		/** Location of the Variable name in panel.  */
		public final int XVARIABLE = 100;
		/** Location of the Variable name in panel.  */
		public final int YVARIABLE = 370;
		/** Location of the Variable units in panel.  */
		public final int XUNITS = 100;
		/** Location of the Variable units in panel.  */
		public final int YUNITS = 510;
		/** Makes eclipse happy */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Constructor
		 *  
		 */
		public CountyPanel(){
			// enable mouse events
			this.setFocusable(true);

			// Connect the listener to the panel
			this.addMouseListener(new MouseAdapter(){
				/**
				 * Deal with a click event by opening a window describing the
				 * selected county.
				 * 
				 * @param arg0 The mouse event
				 */
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// Get the location
					int x = arg0.getX();
					int y = arg0.getY();
					// TODO: complete implementation
					
				}

			});
			// Create color bar
			// TODO: complete implementation
			
		}

		/**
		 * Paint the full panel.  
		 * 
		 * @param g Graphics context
		 */
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			// TODO: complete implementation.  Draw: counties, color bar, 
			//  and variable labels
		}
		
		/**
		 * Return the preferred size of the panel.  Determined by the 
		 * county display bounding box.
		 * 
		 * @return Dimension containing the panel's preferred size.
		 */
		public Dimension getPreferredSize(){
			Rectangle rec = countyInfoList.getBounds();
			return new Dimension(rec.width+50, rec.height+50);
		}
		
		/**
		 * Translate a list of Strings to an ArrayList of corresponding Integers
		 * 
		 * @param list List of strings containing integers
		 * @return ArrayList of corresponding Integers.  null if the original list is null.
		 */
		private List<Integer> string2Integer(List<String> list){
			// If null, then return null
			if(list == null) return null;
			// Create the new list
			List<Integer> outList = new ArrayList<Integer>();
			// Loop over all elements of the list & add to the new list
			for(String s: list){
				outList.add(Integer.parseInt(s));
			}
			return outList;
		}
		
		/**
		 * Translate from a primitive array of ints to an ArrayList of integers
		 * 
		 * @param list List of ints
		 * @return Arraylist of corresponding Integers.  null if the original list is null
		 */
		private List<Integer> arrayToList(int[] list){
			// If null, then return null
			if(list == null) return null;
			// Create the new list
			List<Integer> outList = new ArrayList<Integer>();
			// Loop over all the ints and add each one
			for(int i: list){
				outList.add(i);
			}
			return outList;
		}
		
		/**
		 * Update what is displayed in the panel.  This method is called any time something new has
		 * been selected
		 */
		public void updateData(){
			// Get selected variable
			variableId = StateFrame.this.selectionPanel.variableList.getSelectedValue();
			
			// Get the years
			List<String> selectedYears = StateFrame.this.selectionPanel.yearList.getSelectedValuesList();
			
			// Punt if there is nothing (must be in the middle of update)
			if(selectedYears.isEmpty()) return;
			
			// If all is included, then we indicate this by setting selectedYears to null
			if(selectedYears.get(0).equals("All")){
				selectedYears = null;
			}
			
			// TODO: complete implementation

			// MONTHS
			// DAYS
			
			// Check which statistic has been selected
			StatType statType = StatType.MAXIMUM;
			if(StateFrame.this.selectionPanel.averageButton.isSelected()){
				statType = StatType.AVERAGE;
			}else if(StateFrame.this.selectionPanel.minButton.isSelected()){
				statType = StatType.MINIMUM;
			}
			
			// Create the set of constraints for the statistic computations
			ArrayList<List<Integer>> constraints = new ArrayList<List<Integer>>();
			// TODO: add years, months days to constraints list

			// TODO: paint the counties
			
			// Force a repaint
			repaint();
		}
		
	}
	
	///////////////////////////////////////////////////////////////
	/**
	 * Constructor for the window
	 * 
	 * @param name Name of the window
	 * @param countyInfoList County info list: all county information
	 * @param countyConnector Mapping between counties and stations.  
	 * @param stationInfoList List of all stations
	 * @param dataInfoList List of all measures (variables)
	 */
	public StateFrame(String name, CountyInfoList countyInfoList, CountyConnector countyConnector,
			StationInfoList stationInfoList, DataInfoList dataInfoList){
		super(name);
		// Remember the key structures
		this.countyInfoList = countyInfoList;
		this.countyConnector = countyConnector;
		this.stationInfoList = stationInfoList;
		this.dataInfoList = dataInfoList;
		
		// Menu bar
		fileMenuBar = new FileMenuBar();
		this.setJMenuBar(fileMenuBar);

		// Create the panels
		this.setLayout(new BorderLayout());
		
		// Selection panel
		selectionPanel = new SelectionPanel();
		this.add(selectionPanel, BorderLayout.WEST);
		
		// County panel
		countyPanel = new CountyPanel();
		this.add(countyPanel, BorderLayout.CENTER);

		// Configure window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,600);
		setVisible(true);

		// Tell the countyConnector about the colorBar
		countyConnector.setColorBar(countyPanel.colorBar);
	}

}
