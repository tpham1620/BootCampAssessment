package com.cooksys.assessment;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Completed solution for FastTrack'D Boot Camp Assessment.
 * 
 * @author Tan Pham
 * @version 2/10/2017
 */
public class Window {

	private JFrame frame;
	private JList<String> lstLeft;
	private JList<String> lstRight;
	private DefaultListModel<String> rightList = new DefaultListModel<String>();

	/**
	 * Launch the application. The main method is the entry point to a Java application. 
	 * For this assessment, you shouldn't have to add anything to this.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application. This is the constructor for this Window class.
	 * All of the code here will be executed as soon as a Window object is made.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame. This is where Window Builder
	 * will generate its code.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Create the list of computer parts list and display to JList.
		 * This list should not be modified while the application is running.
		 */
		final DefaultListModel<String> leftList = new DefaultListModel<String>();		
		leftList.addElement("Case");
		leftList.addElement("Motherboard");
		leftList.addElement("CPU");
		leftList.addElement("GPU");
		leftList.addElement("PSU");
		leftList.addElement("RAM");
		leftList.addElement("HDD");
	
		//Left JList
		lstLeft = new JList<String>(leftList);		
		lstLeft.setBounds(15, 20, 250, 430);
		frame.getContentPane().add(lstLeft);		
			
		//Right JList
		lstRight = new JList<String>(rightList);
		lstRight.setBounds(380, 20, 250, 430);
		frame.getContentPane().add(lstRight);
		
		
		JButton btnAdd = new JButton(">>");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();	//add the selected part to the right list
			}
		});
		btnAdd.setBounds(275, 120, 90, 30);
		frame.getContentPane().add(btnAdd);
		
		JButton btnRemove = new JButton("<<");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(); //remove the selected part from the right list
			}
		});
		btnRemove.setBounds(275, 200, 95, 30);
		frame.getContentPane().add(btnRemove);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('0');
		menuBar.add(mnFile);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();	//load XML file to the right list.
			}
		});
		mnFile.add(mntmLoad);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();	//save the right list to a XML file.
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
	}
	
	/**
	 * As the addBotton (>>) clicked, the selected item on the left list will be added
	 * to the right list.
	 */
	public void add(){
		rightList.addElement(lstLeft.getSelectedValue());
	}
	
	/**
	 * As the removeBotton (<<) clicked, the selected item on the right list will be remove
	 * from the list.
	 */
	public void remove(){
		rightList.removeElement(lstRight.getSelectedValue());
	}
	
	
	/**
	 * When the save menu item is clicked, the data on the right list will be 
	 * collected and stored as an XML file, namely "file.xml".
	 */
	public void save(){
		try {
			File file = new File("file.xml");
			Items items = new Items();
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			for(int i = 0; i < rightList.size(); i++){
				items.addItems(rightList.getElementAt(i));				
			}
			
			jaxbMarshaller.marshal(items, file);	

		    } catch (JAXBException e1) {
		    	  e1.printStackTrace();
		    }
	}
	
	
	/**
	 * When the load menu item is clicked, the right list will be 
	 * cleared and display data load from a XML, namely "file.xml".
	 */
	public void load(){
		try {
			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Items items = (Items) jaxbUnmarshaller.unmarshal(file);
			rightList.clear();
			for(String item:items.getItems()){
				rightList.addElement(item);
			}

		  } catch (JAXBException e1) {
			e1.printStackTrace();
		  }
	}
}
