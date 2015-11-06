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
	 */
	public WeatherFrame() {
		//TODO: All of the frame properties are subject to change
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//initialize all of the components
		fileMenuBar = new FileMenuBar();
		selectionPanel = new SelectionPanel();
		dataPanel = new DataPanel();
		
		add(fileMenuBar);
		
	
		//leave setVisible to the last 
		setVisible(true);
	}
}
