import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

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
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public WeatherFrame() throws FileNotFoundException, IOException {
		//TODO: All of the frame properties are subject to change
		setSize(1000, 600);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//initialize all of the components
		fileMenuBar = new FileMenuBar();
		
		//TODO: create the stationInfoList and dataInfoList by reading the files
		stationInfoList = new StationInfoList("data/geoinfo.csv");
		dataInfoList = new DataInfoList("data/DataTranslation.csv");
		
		//pass the station and data info list to the constructor of selection panel
		selectionPanel = new SelectionPanel(stationInfoList, dataInfoList);
		dataPanel = new DataPanel();
		
		//TODO: Determine the layout of the components (subject to change)
		this.setJMenuBar(fileMenuBar);
		
		GridLayout layout = new GridLayout(1, 2);
		this.setLayout(layout);
		add(selectionPanel);
		add(dataPanel);
		
		
	
		//leave setVisible to the last 
		setVisible(true);
	}
}
