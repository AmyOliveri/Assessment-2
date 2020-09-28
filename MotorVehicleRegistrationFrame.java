/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: OwnerJPanel.java
Purpose: MotorVehicleRegistrationFrame class to contain the three JPanel classes
Date: 10 August 2020
 */
package assignmnent_2;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * The MotorVehicleRegistrationFrame creates the tabbed pane facilitates an
 * interconnection between the three JPanel classes and also provides universal
 * exit functionality through a menu bar
 *
 * @author aread
 */
public class MotorVehicleRegistrationFrame extends JFrame implements ActionListener {

    private static final int TABBED_PANE_OWNER = 0;
    private static final int TABBED_PANE_VEHICLE = 1;
    private static final int TABBED_PANE_SEARCH = 2;
    // Based on the tabbed pane, static constants are declared and initialised to avoid writing magic numbers when reassigning names to the tabbed pane based on when add modes and edit modes are activated in ifferent tabs    

    private final String PROGRAM_TITLE;
    private final String PROGRAM_TITLE_LOWER_CASE;
    // Declared program title constants as instance fields

    private int confirmDialogResponse;
    // Declared an instance field to check the user's response to various dialog boxes

    public static ArrayList<Owner> ownerArray;
    public static ArrayList<Vehicle> vehicleArray;
    // Declared the owner and vehicle array lists

    private final Font HEADINGONE_FONT_STYLE;
    // Declared the heading one font style for tabbed pane title

    private JPanel mainPanel;
    // Declared the main panel which will hold the tabbed paned

    public static JTabbedPane tabbedPane;
    // Declared the tabbed pane to hold the JPanel classes

    private JLabel titleLabel;
    // Declared the tabbed pane title

    private JMenuBar MotorVehicleRegistrationMenuBar;
    // Declared a menu bar

    private JMenu menuBarFile;
    private JMenuItem menuBarFileExit;
    // Declared a file menu and an exit menu item to faciliate universal exit functionality 

    /**
     * The default constructor is used to initialise the constants, variables,
     * arrays, StringBuilders, fonts, colours and swing components declared as
     * instance fields Additionally, fonts, action listeners, and layouts are
     * initialised in separate methods to improve the readability of the code
     * Furthermore, exit functionality either by clicking on the exit button or
     * the closing x button has been overriden with an exit method
     */
    public MotorVehicleRegistrationFrame() {
        super("Motor Vehicle Registration Application");

        PROGRAM_TITLE = "Motor Vehicle Registration Application";
        PROGRAM_TITLE_LOWER_CASE = PROGRAM_TITLE.toLowerCase();
        confirmDialogResponse = 0;

        ownerArray = new ArrayList<>();
        vehicleArray = new ArrayList<>();

        HEADINGONE_FONT_STYLE = new Font("Arial", 1, 24);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Add Owner", new OwnerJPanel());
        tabbedPane.addTab("Add Vehicle", new VehicleJPanel());
        tabbedPane.addTab("Search", new SearchJPanel());
        tabbedPane.addTab("Add Accident", new AccidentPanel());

        /*
        EUGENE: DOES THIS NEED TO GO WITH THE SAVE BUTTON ON THE VEHICLE PANEL OR WITH THE ACCIDENT PANEL   
                tabbedPane.addChangeListener(new ChangeListener(){
                public void stateChanged(ChangeEvent e){
                if(rego doesn't exist in the list){
                    add it
                }
                    
                }
                });
         */
        tabbedPane.addTab("View Accident", new DisplayAccident());

        titleLabel = new JLabel("Motor Vehicle Registration");

        MotorVehicleRegistrationMenuBar = new JMenuBar();

        menuBarFile = new JMenu("File");
        menuBarFileExit = new JMenuItem("Exit");

        setFontStyles();
        this.setLayout(new BorderLayout());
        add(mainPanel);

        setLayout();

        setActionListeners();

        addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                exit();

            }

        }
        );

    }

    /**
     * Assigns fonts to swing components
     */
    private void setFontStyles() {
        titleLabel.setFont(HEADINGONE_FONT_STYLE);
    }

    /**
     * Assigns an item listener to the searchCategoryJComboBox, and action
     * listeners to JButtons
     */
    private void setActionListeners() {
        menuBarFileExit.addActionListener(this);
    }

    /**
     * Sets the layout of the JPanel
     */
    private void setLayout() {
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        // Adds the title label to the top and the tabbed pane to the centre

        setJMenuBar(MotorVehicleRegistrationMenuBar);
        MotorVehicleRegistrationMenuBar.add(menuBarFile);
        menuBarFile.add(menuBarFileExit);
        // Creates the exit button, in a file menu, inside a menu bar
    }

    /**
     * Sets the SearchJPanel as the selected tab
     */
    public static void showSearchTab() {
        tabbedPane.setSelectedIndex(TABBED_PANE_SEARCH);
    }

    /**
     * Sets the OwnerJPanel and renames
     */
    public static void showAddOwnerTab() {
        tabbedPane.setTitleAt(TABBED_PANE_OWNER, "Add Owner");
        tabbedPane.setSelectedIndex(TABBED_PANE_OWNER);
    }

    /**
     * Sets the OwnerJPanel and renames
     */
    public static void showEditOwnerTab() {
        tabbedPane.setTitleAt(TABBED_PANE_OWNER, "Edit Owner");
        tabbedPane.setSelectedIndex(TABBED_PANE_OWNER);
    }

    /**
     * Sets the vehicleJPanel and renames
     */
    public static void showAddVehicleTab() {
        tabbedPane.setTitleAt(TABBED_PANE_VEHICLE, "Add Vehicle");
        tabbedPane.setSelectedIndex(TABBED_PANE_VEHICLE);
    }

    /**
     * Sets the vehicleJPanel and renames
     */
    public static void showEditVehicleTab() {
        tabbedPane.setTitleAt(TABBED_PANE_VEHICLE, "Edit Vehicle");
        tabbedPane.setSelectedIndex(TABBED_PANE_VEHICLE);
    }

    public static void resetVehiclePanelJComboBox() {
        VehicleJPanel.resetJComboBox();
    }

    public static void resetJComboBoxes() {
        OwnerJPanel.resetJComboBox();
        resetVehiclePanelJComboBox();
        SearchJPanel.resetJComboBox();
    }

    private void generateThankYouMessage() {
        JOptionPane.showMessageDialog(null, "Thank you for using the " + PROGRAM_TITLE_LOWER_CASE, PROGRAM_TITLE, JOptionPane.PLAIN_MESSAGE);
    } // End of the generateErrorDialog method

    private void generateConfirmDialog(String messageText) {
        confirmDialogResponse = JOptionPane.showConfirmDialog(null, messageText, PROGRAM_TITLE, JOptionPane.YES_NO_OPTION);
    }

    private void exit() {
        generateConfirmDialog("Are you sure you wish to exit?"); // Request the user to input a customer name to search, and then assign the searchGardenService variable to store the variable. This variable will be used for searching through garden service details 
        if (confirmDialogResponse == JOptionPane.YES_OPTION) {
            generateThankYouMessage();
            System.exit(0);
        } else {
            return;
        }
    }

    public void readVehicleFile() {
        FileReader reader = null;
        try {
            int count = 0;
            String inLine;
            reader = new FileReader("vehicles.txt");
            Scanner fileIn = new Scanner(reader);
            while (fileIn.hasNextLine()) {

                String line = fileIn.nextLine();// read a line
                count++;
                Scanner lineIn = new Scanner(line);//another Scanner to deal a line
                while (lineIn.hasNext()) {
                    String plateNumber = lineIn.next();
                    double engineCapacity = lineIn.nextDouble();
                    String make = lineIn.next();
                    String model = lineIn.next();
                    int year = lineIn.nextInt();
                    int ownerID = lineIn.nextInt();
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MotorVehicleRegistrationFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(MotorVehicleRegistrationFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void readOwnerFile() {
        FileReader reader = null;
        try {
            int count = 0;
            String inLine;
            reader = new FileReader("owners.txt");
            Scanner fileIn = new Scanner(reader);
            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                count++;
                Scanner lineIn = new Scanner(line);
                while (lineIn.hasNext()) {
                    int id = lineIn.nextInt();
                    String firstName = lineIn.next();
                    String lastName = lineIn.next();
                    String address = lineIn.next();
                    String phoneNumber = lineIn.next();

                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MotorVehicleRegistrationFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(MotorVehicleRegistrationFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void readAccidentFile() {
        FileReader reader = null;
        try {
            int count = 0;
            String inLine;
            reader = new FileReader("accidents.txt");
            Scanner fileIn = new Scanner(reader);
            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                count++;
                Scanner lineIn = new Scanner(line);
                while (lineIn.hasNext()) {
                    String accidentID = lineIn.next();
                    String location = lineIn.next();
                    String date = lineIn.next();
                    String comment = lineIn.next();

                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MotorVehicleRegistrationFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(MotorVehicleRegistrationFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void writeVehicleFile() {

        try {
            FileWriter out = new FileWriter("vehicles.txt");
            int arraySize = MotorVehicleRegistrationFrame.vehicleArray.size();
            if (VehicleJPanel.selectVehicleTypeJComboBoxList == "Heavy Vehicle") {
                for (int searchIndex = 0; searchIndex < arraySize; ++searchIndex) {
                    out.write(vehicleArray.plateNumber);
                    out.write(vehicleArray.loadCapacity);
                    out.write(vehicleArray.engineCapacity);
                    out.write(vehicleArray.make);
                    out.write(vehicleArray.model);
                    out.write(vehicleArray.year);
                    out.write(vehicleArray.ownerId);
                }
            } else {
                for (int searchIndex = 0; searchIndex < arraySize; ++searchIndex) {
                    out.write(vehicleArray.plateNumber);
                    out.write(vehicleArray.numberOfseats);
                    out.write(vehicleArray.engineCapacity);
                    out.write(vehicleArray.make);
                    out.write(vehicleArray.model);
                    out.write(vehicleArray.year);
                    out.write(vehicleArray.ownerId);
                }
            }
        }catch (IOException ex) {
            Logger.getLogger(MotorVehicleRegistrationFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        }

    

    public void writeOwnerFile() {
        try {
            FileWriter out = new FileWriter("owners.txt");
            int arraySize = MotorVehicleRegistrationFrame.ownerArray.size();
            for (int searchIndex = 0; searchIndex < arraySize; ++searchIndex) {
                out.write(ownerArray.id);
                out.write(ownerArray.firstName);
                out.write(ownerArray.lastName);
                out.write(ownerArray.address);
                out.write(ownerArray.phoneNumber);
            }
        } catch (IOException ex) {
            Logger.getLogger(MotorVehicleRegistrationFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeAccidentFile() {
        try {
            FileWriter out = new FileWriter("accidents.txt");
            int arraySize = AccidentFrame.accidents.size();
            for (int searchIndex = 0; searchIndex < arraySize; ++searchIndex) {
                out.write(AccidentFrame.accidents.accidentID);
                out.write(AccidentFrame.accidents.location);
                out.write(AccidentFrame.accidents.date);
                out.write(AccidentFrame.accidents.comment);
            }
        } catch (IOException ex) {
            Logger.getLogger(MotorVehicleRegistrationFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.compareTo("Exit") == 0) {
            exit();
        }
    }

}
