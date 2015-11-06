import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

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

// TODO: complete implementation
public class FileMenuBar extends JMenuBar {

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
		// TODO: set display properties
		this.setPreferredSize(new Dimension(800, 50));

		// initialize fields
		menu = new JMenu("File");
		menuOpen = new JMenuItem("Open Data File");
		menuExit = new JMenuItem("Exit");
		fileChooser = new JFileChooser();

		// configure fileChooser
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));

		// add action listeners to the menu items
		menuOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Load the selected file using JFileChooser
				fileChooser.showOpenDialog(FileMenuBar.this);
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
