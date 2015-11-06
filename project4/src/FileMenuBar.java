import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//TODO: complete implementation
public class FileMenuBar extends JMenuBar {

	private JMenu menu;
	private JMenuItem menuOpen;
	private JMenuItem menuExit;
	private JFileChooser fileChooser;
	/**
	 * Constructor
	 */
	public FileMenuBar() {
		//TODO: set display properties
		
		//initialize fields
		menu = new JMenu("File");
		menuOpen = new JMenuItem("Open Data File");
		menuExit = new JMenuItem("Exit");
		
		//add action listeners to the menu items
		menuOpen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Load the selected file using JFileChooser
				
			}
			
		});
		
		menuExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}		
		});
		
		//add the menu items to the menu
		menu.add(menuOpen);
		menu.add(menuExit);
		
		this.add(menu);
	}
}
