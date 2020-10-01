/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: OwnerJPanel.java
Purpose: VehicleJpanel class to act as a GUI for adding and editing vehicles
Date: 10 August 2020
*/
package assignmnent_2;
// Labelling the package name for the java project

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
// Group layout constants and the entire awt, awt event, swing, and util libraries are imported to reduce the number of import statements required.

/**
 * The Vehicle JPanel class collects user input for motorcycles, light and heavy vehicles, outputs data to array lists, performs data validation, retrieves data from array lists, and allows motorcycles, light and heavy vehicles to be edited 
 * @author aread
 */
public class VehicleJPanel extends JPanel implements ActionListener, ItemListener
{
        private final int NO_VEHICLE_SELECTED;
        private static final int MOTORCYCLE = 1;
        private static final int LIGHT_VEHICLE = 2;
        private static final int HEAVY_VEHICLE = 3;
        // Based on the selectVehicleTypeJComboBoxList, constants are declared and static constants are declared and initialised to avoid writing magic numbers for determining which item has been selected from the selectOwnerTypeJComboBox      
    
        private final int BUTTON_WIDTH;
        private final int BORDER_GAP;
        private final int FIELD_HORIZONTAL_GAP;
        private final int FIELD_VERTICAL_GAP;
        private final int LARGE_FIELD_VERTICAL_GAP;
        private final int JEXTFIELD_COLUMN_WIDTH;
        // Constants are declared to adjust the gaps between the rows and columns within the set layout method
        
        private int confirmDialogResponse;
        // Declared an instance field to check the user's response to various dialog boxes
        
        private int currentLightAndHeavyVehicleNumberPlate;
        private int currentMotorcycleNumberPlate;
        // Declared number plate instance fields for vehicles
        
        public static boolean editMode;
        // Declared an instance field to check whether or not edit mode is activated
        
        int numberOfSeats;
        int loadCapacity;
        double engineCapacity;
        String make;
        String model;
        int year;
        int ownerId;
        // Declared instance fields to store the text contents of swing components
        
        private String [] selectVehicleTypeJComboBoxList;
        // Declared a instance field to store a list of items for the selectVehicleTypeJComboBox
        
        private final String PROGRAM_TITLE;
        // Declared the program title constant as an instance field
        
        private JComponent commonFieldObjectJComponentContainer;
        private StringBuilder commonErrorDialogStringBuilder;
        private JComponent numberOfSeatsFieldObjectJComponentContainer;
        private StringBuilder numberOfSeatsErrorDialogStringBuilder;
        private JComponent loadCapacityFieldObjectJComponentContainer;
        private StringBuilder loadCapacityErrorDialogStringBuilder;
        // Declared StringBuilders and JComponents to store error messages and references to the relevent swing components which are found to contain errors during data validation. StringBuilders are declared here and their lengths sets to 0 as needed in order to minimise the number of objects being created as well as to minimise the number of declaration and initialisation statements
           
        private final Font NORMAL_FONT_STYLE;
        private final Font HEADINGTWO_FONT_STYLE;
        // Declared normal and heading fonts for use in swing components
        
        private final Color WHITE_COLOUR;
        // Declared a white colout to change the background colour of JComboBoxes
        
        private JPanel vehicleJPanel;
        // Declared the JPanel to contain swing components
        
        private static JLabel vehicleJLabel;
        // Declared the owner title label for the GUI 
        
        private JLabel selectVehicleTypeJLabel;
        private static JComboBox<String> selectVehicleTypeJComboBox;
        // Declared the label and JComboBox for selecting between motorcycles, light and heavy vehicles
        
        private static JLabel enterVehicleDetailsJLabel;
        private JLabel loadCapacityJLabel; 
        private JLabel numberOfSeatsJLabel;
        private JLabel engineCapacityJLabel;
        private JLabel makeJLabel;
        private JLabel modelJLabel;
        private JLabel yearJLabel;
        private JLabel ownerIdJLabel;
        // Declared JLables for the GUI
        
        private static JTextField loadCapacityJTextField;
        private static JTextField numberOfSeatsJTextField;
        private static JTextField engineCapacityJTextField;
        private static JTextField makeJTextField;
        private static JTextField modelJTextField;
        private static JTextField yearJTextField;
        private static JComboBox<String> ownerIdJComboBox;
        // Declared instance fields to contain user input for motorcycles, light and heavy vehicles
        
        private static JButton vehicleJButton;
        private static JButton addModeJButton;
        private JButton clearJButton;
        // Declared the JButtons for the frame
           
        private static final int OWNER_ID_REFERENCE_INDEX = 0;
        // Declared a constant to always set the default selected index of the ownerIdJComboBox to index 1, which represents the value stored in the vehicleSearchReferenceIndex index in the vehicleArray when the panel is set to edit mode
        
        private StringBuilder ownerIdStringBuilder;
        // Declared a StringBuilder to enforce referentialintegrity by retrieving the indexes of ownerId of owners who already have a vehicle
        
        private static ArrayList<LightVehicle> lightVehicleArray;
        private static ArrayList<HeavyVehicle> heavyVehicleArray;
        // Declared a light and a heavy vehcile array to perform temporary casting of the vehicle array. The arrays are declared here to minimise the number of objects being created within the panel
           
        private int numberOfCharacterIndexesInNumberPlate;
        private int [] combinationValues;
        private int [] combinationAccummulatingTotal;
        private int [] maximumNumberPlaceValues;
        private int [] minimumNumberPlaceValues;
        private int [] flipppedMinimumNumberPlaceValues;
        private int [] remainder;
        private int [] quantityInNumberPlace;
        private int amount;
        private StringBuilder characterIndexesInNumberPlate;
        // Declared integers, integer arrays, and a String Builder to enter unqiue integers to be subsequently converted into alpha-numeric numberplate strings 
        
        private GroupLayout vehicleLayout;
        // Declared the Group Layout for the setLayout method

        /**
         * The default constructor is used to initialise the constants, variables, arrays, StringBuilders, fonts, colours and swing components declared as instance fields
         * Additionally, fonts, action listeners, and layouts are initialised in separate methods to improve the readability of the code
         */
        public VehicleJPanel()
        {
                JEXTFIELD_COLUMN_WIDTH = 40;
        
                NO_VEHICLE_SELECTED = 0;
                BUTTON_WIDTH = 130;
                BORDER_GAP = 12;
                FIELD_HORIZONTAL_GAP = 18;
                FIELD_VERTICAL_GAP = 5;
                LARGE_FIELD_VERTICAL_GAP = 24;
                confirmDialogResponse = 0;
                editMode = false;
                lightVehicleArray = new ArrayList<>();
                heavyVehicleArray = new ArrayList<>();
                
                numberOfSeats = 0;
                loadCapacity = 0;
                engineCapacity = 0;
                year = 0;
                ownerId = 0;
                
                selectVehicleTypeJComboBoxList = new String [] {"...", "Motorcycle", "Light Vehicle", "Heavy Vehicle"}; 

                currentLightAndHeavyVehicleNumberPlate = 0 + MotorVehicleRegistrationFrame.numHeavyOrLightVehicles;
                currentMotorcycleNumberPlate = 521000 + MotorVehicleRegistrationFrame.numMotorcycles;
                
                numberOfCharacterIndexesInNumberPlate = 6;
                combinationValues = new int [numberOfCharacterIndexesInNumberPlate];
                combinationAccummulatingTotal = new int [numberOfCharacterIndexesInNumberPlate];
                maximumNumberPlaceValues = new int [numberOfCharacterIndexesInNumberPlate];
                minimumNumberPlaceValues = new int [numberOfCharacterIndexesInNumberPlate];
                flipppedMinimumNumberPlaceValues = new int [numberOfCharacterIndexesInNumberPlate];
                remainder = new int [numberOfCharacterIndexesInNumberPlate];
                quantityInNumberPlace = new int [numberOfCharacterIndexesInNumberPlate];
                amount = 0;
                characterIndexesInNumberPlate = new StringBuilder();
                ownerIdStringBuilder = new StringBuilder();
                
                commonErrorDialogStringBuilder = new StringBuilder();
                numberOfSeatsErrorDialogStringBuilder = new StringBuilder();
                loadCapacityErrorDialogStringBuilder = new StringBuilder();
                
                
                PROGRAM_TITLE = "Motor Vehicle Registration Application";
                
                NORMAL_FONT_STYLE = new Font("Arial", 4, 12);
                HEADINGTWO_FONT_STYLE = new Font("Arial", 1, 14);
                
                WHITE_COLOUR = new Color(255,255,255);
                
                vehicleJPanel = new JPanel();
                
                vehicleJLabel = new JLabel("Add Vehicle");
                selectVehicleTypeJLabel = new JLabel("Select vehicle type: ");
                enterVehicleDetailsJLabel = new JLabel("Enter Vehicle Details");
                loadCapacityJLabel = new JLabel("Load Capacity: ");
                numberOfSeatsJLabel = new JLabel("Number of Seats: ");
                engineCapacityJLabel = new JLabel("Engine Capacity: ");
                makeJLabel = new JLabel("Make: ");
                modelJLabel = new JLabel("Model: ");
                yearJLabel = new JLabel("Year: ");
                ownerIdJLabel = new JLabel("License Number: ");
                
                selectVehicleTypeJComboBox = new JComboBox<>(selectVehicleTypeJComboBoxList);
                selectVehicleTypeJComboBox.setBackground(WHITE_COLOUR);
                loadCapacityJTextField = new JTextField(JEXTFIELD_COLUMN_WIDTH);
                numberOfSeatsJTextField = new JTextField(JEXTFIELD_COLUMN_WIDTH);
                engineCapacityJTextField = new JTextField(JEXTFIELD_COLUMN_WIDTH);
                makeJTextField = new JTextField(JEXTFIELD_COLUMN_WIDTH);
                modelJTextField = new JTextField(JEXTFIELD_COLUMN_WIDTH);
                yearJTextField = new JTextField(JEXTFIELD_COLUMN_WIDTH);
                ownerIdJComboBox = new JComboBox<>();
                ownerIdJComboBox.setBackground(WHITE_COLOUR);
                 
                vehicleJButton = new JButton("Add Vehicle");
                addModeJButton = new JButton("Add Mode");
                clearJButton = new JButton("Clear");
                
                addModeJButton.setVisible(false);
                
                setFontStyles();
 
                vehicleLayout = new GroupLayout(vehicleJPanel);

                setLayout();

                setActionListeners();
                
                setEntryAreaInvisible();
        }
        
        
        
        /**
         * Assigns fonts to swing components
         */
        private void setFontStyles()
        {
                vehicleJLabel.setFont(HEADINGTWO_FONT_STYLE);
                selectVehicleTypeJLabel.setFont(NORMAL_FONT_STYLE);
                enterVehicleDetailsJLabel.setFont(HEADINGTWO_FONT_STYLE);
                loadCapacityJLabel.setFont(NORMAL_FONT_STYLE);
                numberOfSeatsJLabel.setFont(NORMAL_FONT_STYLE);
                engineCapacityJLabel.setFont(NORMAL_FONT_STYLE);
                makeJLabel.setFont(NORMAL_FONT_STYLE);
                modelJLabel.setFont(NORMAL_FONT_STYLE);
                yearJLabel.setFont(NORMAL_FONT_STYLE);
                ownerIdJLabel.setFont(NORMAL_FONT_STYLE);
                selectVehicleTypeJComboBox.setFont(NORMAL_FONT_STYLE);
                loadCapacityJTextField.setFont(NORMAL_FONT_STYLE);
                numberOfSeatsJTextField.setFont(NORMAL_FONT_STYLE);
                engineCapacityJTextField.setFont(NORMAL_FONT_STYLE);
                makeJTextField.setFont(NORMAL_FONT_STYLE);
                modelJTextField.setFont(NORMAL_FONT_STYLE);
                yearJTextField.setFont(NORMAL_FONT_STYLE);
                ownerIdJComboBox.setFont(NORMAL_FONT_STYLE);
                vehicleJButton.setFont(NORMAL_FONT_STYLE);
                addModeJButton.setFont(NORMAL_FONT_STYLE);
                clearJButton.setFont(NORMAL_FONT_STYLE);   
        }
        
        
        
        /**
         * Assigns an item listener to the selectVehicleTypeJComboBox, and action listeners to JButtons
         */
        private void setActionListeners()
        {
                selectVehicleTypeJComboBox.addItemListener(this);
                vehicleJButton.addActionListener(this);
                addModeJButton.addActionListener(this);
                clearJButton.addActionListener(this);
        }
        
        
        
        /**
         * Sets the layout of the JPanel
         */
        private void setLayout()
        {
                setLayout(new BorderLayout());
                add(vehicleJPanel);
                vehicleJPanel.setLayout(vehicleLayout);
                
                vehicleLayout.setHorizontalGroup(vehicleLayout.createSequentialGroup()
                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP)
                                .addGroup(vehicleLayout.createSequentialGroup()
                                .addGroup(vehicleLayout.createParallelGroup()
                                        .addComponent(vehicleJButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addModeJButton, GroupLayout.Alignment.CENTER, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(clearJButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                .addGroup(vehicleLayout.createSequentialGroup()
                                .addGroup(vehicleLayout.createParallelGroup()
                                        .addComponent(vehicleJLabel)
                                        .addComponent(selectVehicleTypeJLabel)
                                        .addComponent(enterVehicleDetailsJLabel)
                                        .addComponent(loadCapacityJLabel)
                                        .addComponent(numberOfSeatsJLabel)
                                        .addComponent(engineCapacityJLabel)
                                        .addComponent(makeJLabel)
                                        .addComponent(modelJLabel)
                                        .addComponent(yearJLabel)
                                        .addComponent(ownerIdJLabel))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                                        .addGroup(vehicleLayout.createSequentialGroup()
                                        .addGroup(vehicleLayout.createParallelGroup()
                                                .addComponent(selectVehicleTypeJComboBox)
                                                .addComponent(loadCapacityJTextField)
                                                .addComponent(numberOfSeatsJTextField)
                                                .addComponent(engineCapacityJTextField)
                                                .addComponent(makeJTextField)
                                                .addComponent(modelJTextField)
                                                .addComponent(yearJTextField)
                                                .addComponent(ownerIdJComboBox))))))
                                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP));
                // Creates a merged column spanning the width of the JPanel, and splits the remainder of the JPanel into two columns. A small gap is added between columns. Additionally, border gaps are added to the left and right to keep swing components away from the edge of the window.

                vehicleLayout.setVerticalGroup(vehicleLayout.createSequentialGroup()
                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(vehicleJLabel))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(selectVehicleTypeJLabel)
                                .addComponent(selectVehicleTypeJComboBox))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(enterVehicleDetailsJLabel))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(loadCapacityJLabel)
                                .addComponent(loadCapacityJTextField))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(numberOfSeatsJLabel, GroupLayout.Alignment.CENTER)
                                .addComponent(numberOfSeatsJTextField))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(engineCapacityJLabel)
                                .addComponent(engineCapacityJTextField))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(makeJLabel)
                                .addComponent(makeJTextField))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(modelJLabel)
                                .addComponent(modelJTextField))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(yearJLabel)
                                .addComponent(yearJTextField))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(ownerIdJLabel)
                                .addComponent(ownerIdJComboBox))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(vehicleLayout.createSequentialGroup()
                        .addGroup(vehicleLayout.createParallelGroup(BASELINE)
                                .addComponent(vehicleJButton)
                                .addComponent(addModeJButton)
                                .addComponent(clearJButton)))))))))))))
                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP));
                // The JPanel is split into 11 rows. A small gap is added between rows, large gaps are added to push buttons towards the bottom of the window, and a border gap is added to the top and bottom to keep swing components away from the edge of the window
        }
      
        
        
        /**
         * Sets common fields visible when a private and corporate owner type is selected. Additionally, the add mode button is made visible if edit mode is currently enabled
         */
        private void setCommonFieldsVisible()
        {
                enterVehicleDetailsJLabel.setVisible(true);
                engineCapacityJLabel.setVisible(true);
                makeJLabel.setVisible(true);
                modelJLabel.setVisible(true);
                yearJLabel.setVisible(true);
                ownerIdJLabel.setVisible(true);
                engineCapacityJTextField.setVisible(true);
                makeJTextField.setVisible(true);
                modelJTextField.setVisible(true);
                yearJTextField.setVisible(true);
                ownerIdJComboBox.setVisible(true);
                vehicleJButton.setVisible(true);
                clearJButton.setVisible(true);    
                if(getEditMode() == true)
                        addModeJButton.setVisible(true);
        }
                
             
        
        /**
         * Sets common fields invisible when a private and corporate owner type has not been selected
         */
        private void setCommonFieldsInvisible()
        {
                enterVehicleDetailsJLabel.setVisible(false);
                engineCapacityJLabel.setVisible(false);
                makeJLabel.setVisible(false);
                modelJLabel.setVisible(false);
                yearJLabel.setVisible(false);
                ownerIdJLabel.setVisible(false);
                engineCapacityJTextField.setVisible(false);
                makeJTextField.setVisible(false);
                modelJTextField.setVisible(false);
                yearJTextField.setVisible(false);
                ownerIdJComboBox.setVisible(false);
                vehicleJButton.setVisible(false);
                clearJButton.setVisible(false);  
                addModeJButton.setVisible(false);
        }
        
        
        
        /**
         * Sets number of seats fields invisible when a light vehicle type has not been selected
         */
        private void setLightVehicleFieldsInvisible()
        {
                numberOfSeatsJLabel.setVisible(false);
                numberOfSeatsJTextField.setVisible(false); 
        }
        
        
        
        /**
         * Sets load capacity fields invisible when a heavy vehicle type has not been selected
         */
        private void setHeavyVehicleFieldsInvisible()
        {
                loadCapacityJLabel.setVisible(false);
                loadCapacityJTextField.setVisible(false);
        }
        
        
        /**
         * Sets all relevant swing components invisible when a motorcycle, light vehicle, or heavy vehicle has not been selected
         */
        private void setEntryAreaInvisible()
        {
                setCommonFieldsInvisible(); 
                setHeavyVehicleFieldsInvisible();
                setLightVehicleFieldsInvisible();
        }
        
        
        
        /**
         * Receives input from the itemStateChanged method to set various swing components visible or invisible based on whether a motorcycle, light vehicle, heavy vehicle, or no vehicle has been selected using the selectOwnerTypeJComboBox
         */
        private void SelectVehicleType()
        {
                if(selectVehicleTypeJComboBox.getSelectedIndex() == NO_VEHICLE_SELECTED)
                {
                        setEntryAreaInvisible();           
                }
                
                if(selectVehicleTypeJComboBox.getSelectedIndex() == MOTORCYCLE)
                {
                        setCommonFieldsVisible(); 
                        setLightVehicleFieldsInvisible();
                        setHeavyVehicleFieldsInvisible();
                }
                
                if(selectVehicleTypeJComboBox.getSelectedIndex() == LIGHT_VEHICLE)
                {
                        setCommonFieldsVisible(); 
                        numberOfSeatsJLabel.setVisible(true);
                        numberOfSeatsJTextField.setVisible(true);
                        setHeavyVehicleFieldsInvisible();
                }
                                
                if(selectVehicleTypeJComboBox.getSelectedIndex() == HEAVY_VEHICLE)
                {
                        setCommonFieldsVisible(); 
                        loadCapacityJLabel.setVisible(true);
                        loadCapacityJTextField.setVisible(true);
                        setLightVehicleFieldsInvisible();
                }
                
        }

        
        
        /**
         * Generates a dialog box with a yes or no option, and assigns the result of the user's selection to the confirmDialogResponse instance field
         */
        private void generateConfirmDialog(String messageText)
        {
               confirmDialogResponse = JOptionPane.showConfirmDialog(null, messageText, PROGRAM_TITLE, JOptionPane.YES_NO_OPTION);
        }

        
        
        /**
         * Generates an error message dialog containing the specified text
         */ 
        private void generateErrorDialog(String messageText)
        {
                JOptionPane.showMessageDialog(null, messageText, PROGRAM_TITLE, JOptionPane.ERROR_MESSAGE); 
        }
        
        
        
        /**
         * Generates an error message dialog which also sets the focus of the JPanel to the specified JComponent, with most swing components inheriting from super class JComponent, and therefore are JComponents
         */ 
        private void generateDataValidationErrorDialog(JComponent fieldObject, String messageText)
        {
                generateErrorDialog(messageText);
                fieldObject.requestFocus();
                return;  
        }
        
        
        
        /**
         * Generates an error message dialog and highlights the first number of seats field swing component found to contain an error during data validation
         */ 
        private void generateNumberOfSeatsFieldDataValidationErrorDialog()
        {
                generateDataValidationErrorDialog(numberOfSeatsFieldObjectJComponentContainer, numberOfSeatsErrorDialogStringBuilder.toString()); 
        }
        
        
        
        /**
         * Generates an error message dialog and highlights the first load capacity field swing component found to contain an error during data validation
         */ 
        private void generateLoadCapacityFieldDataValidationErrorDialog()
        {
                generateDataValidationErrorDialog(loadCapacityFieldObjectJComponentContainer, loadCapacityErrorDialogStringBuilder.toString()); 
        }
        
        
        
        /**
         * Generates an error message dialog and highlights the first common field swing component found to contain an error during data validation
         */ 
        private void generateCommonFieldDataValidationErrorDialog()
        {
                generateDataValidationErrorDialog(commonFieldObjectJComponentContainer, commonErrorDialogStringBuilder.toString()); 
        }
        
        

        /**
         * Accesses the first erroneously entered swing component found in data validation
         */ 
        private JComponent getCommonFieldObjectJComponentContainer()
        {
                return commonFieldObjectJComponentContainer;
        }
        
        
        
        /**
         * Data validation JComponent container
         * @return fills a JComponent container with the first swing component found to contain an error during data validation
         */ 
        private JComponent addCommonFieldObjectToJComponentContainer(JComponent fieldObject)
        {
                if(commonFieldObjectJComponentContainer == null)
                {
                        commonFieldObjectJComponentContainer = fieldObject;
                }
                
                return commonFieldObjectJComponentContainer;
        }
                
        

        /**
         * Accesses the text of the first erroneously entered swing component found in data validation
         */ 
        private StringBuilder getCommonErrorDialogStringBuilder()
        {
                return commonErrorDialogStringBuilder;
        }
        
        
        
        /**
         * Data validation StringBuilder
         * @return fills a StringBuider with the error message text related to the first swing component found to contain an error during data validation
         */  
        private StringBuilder addCommonErrorDialogToStringBuilder(String errorDialog)
        {
                if(commonErrorDialogStringBuilder.length() == 0)
                {
                        commonErrorDialogStringBuilder.append(errorDialog);
                }
                
                return commonErrorDialogStringBuilder;
        }


        
        /**
         * Performs data validation checks for common vehicle fields and stores the first swing component and related error message text found to contain an error during data validation
         */ 
        private void validateCommonVehicleFields()
        {
                commonFieldObjectJComponentContainer = null;
                commonErrorDialogStringBuilder.setLength(0);
                int integerTestField;
                double doubleTestField;
                boolean isFieldLargerThanZero;
                integerTestField = 0;
                doubleTestField = 0;
                isFieldLargerThanZero = false;

                try
                {
                        doubleTestField = Double.parseDouble(engineCapacityJTextField.getText());
                }
                
                catch(NumberFormatException e)
                {
                        addCommonFieldObjectToJComponentContainer(engineCapacityJTextField);
                        addCommonErrorDialogToStringBuilder("You must enter an engine capacity.\n\n"
                                + "This must be a number.");
                }
            
                try
                {
                        isFieldLargerThanZero = Double.parseDouble(engineCapacityJTextField.getText()) > 0;
                }
                
                catch(NumberFormatException e)
                {
                        addCommonFieldObjectToJComponentContainer(engineCapacityJTextField);
                        addCommonErrorDialogToStringBuilder("The engine capacity should be larger than zero");
                }
                
                if(makeJTextField.getText().compareTo("") == 0)
                {
                        addCommonFieldObjectToJComponentContainer(makeJTextField);
                        addCommonErrorDialogToStringBuilder("You must enter a make for the vehicle");
                }
                                    
                else if(modelJTextField.getText().compareTo("") == 0)
                {
                        addCommonFieldObjectToJComponentContainer(modelJTextField);
                        addCommonErrorDialogToStringBuilder("You must enter a model for the vehicle");
                }

                else if(yearJTextField.getText().compareTo("") == 0)
                {
                        addCommonFieldObjectToJComponentContainer(yearJTextField);
                        addCommonErrorDialogToStringBuilder("You must enter a year of production for the vehicle");
                }
                
                else if(yearJTextField.getText().length() != 4)
                {
                        addCommonFieldObjectToJComponentContainer(yearJTextField);
                        addCommonErrorDialogToStringBuilder("The year of production should contain four characters");
                }
                
                try
                {
                        integerTestField = Integer.parseInt(yearJTextField.getText());
                }
                
                catch(NumberFormatException e)
                {
                        addCommonFieldObjectToJComponentContainer(yearJTextField);
                        addCommonErrorDialogToStringBuilder("The year of production should be a number");
                }

                if(ownerIdJComboBox.getSelectedIndex() == 0 && getEditMode() == false)
                {
                        addCommonFieldObjectToJComponentContainer(ownerIdJComboBox);
                        addCommonErrorDialogToStringBuilder("You must select the license number of the vehicle owner.\n\n"
                                + "Alternatively, the license number you are searching for\n"
                                + "may not be listed because it is already assigned to another\n"
                                + "vehicle; as per the business rule that only one owner can\n"
                                + "have one vehicle.");
                }
                
        }
                
        

        /**
         * Accesses the first erroneously entered swing component found in data validation
         */ 
        private JComponent getNumberOfSeatsFieldObjectJComponentContainer()
        {
                return numberOfSeatsFieldObjectJComponentContainer;
        }
      
        
        
        /**
         * Data validation JComponent container
         * @return fills a JComponent container with the first swing component found to contain an error during data validation
         */        
        private JComponent numberOfSeatsFieldObjectToJComponentContainer(JComponent fieldObject)
        {
                if(numberOfSeatsFieldObjectJComponentContainer == null)
                {
                        numberOfSeatsFieldObjectJComponentContainer = fieldObject;
                }
                
                return numberOfSeatsFieldObjectJComponentContainer;
        }        
        
        

        /**
         * Accesses the text of the first erroneously entered swing component found in data validation
         */ 
        private StringBuilder getNumberOfSeatsErrorDialogStringBuilder()
        {
                return numberOfSeatsErrorDialogStringBuilder;
        }
        
        
        
        /**
         * Data validation StringBuilder
         * @return fills a StringBuider with the error message text related to the first swing component found to contain an error during data validation
         */
        private StringBuilder addNumberOfSeatsErrorDialogToStringBuilder(String errorDialog)
        {
                if(numberOfSeatsErrorDialogStringBuilder.length() == 0)
                {
                        numberOfSeatsErrorDialogStringBuilder.append(errorDialog);
                }
                
                return numberOfSeatsErrorDialogStringBuilder;
        }

        
        
        /**
         * Performs data validation checks for date of birth fields and stores the first swing component and related error message text found to contain an error during data validation
         */        
        private void validateNumberOfSeatsField()
        {
                numberOfSeatsFieldObjectJComponentContainer = null;
                numberOfSeatsErrorDialogStringBuilder.setLength(0);
                int numberOfSeats;
                boolean isNumberOfSeatsLargerThanZero;
                numberOfSeats = 0;
                isNumberOfSeatsLargerThanZero = false;
                
                try
                {
                        numberOfSeats = Integer.parseInt(numberOfSeatsJTextField.getText());
                }
                
                catch(NumberFormatException e)
                {
                        numberOfSeatsFieldObjectToJComponentContainer(numberOfSeatsJTextField);
                        addNumberOfSeatsErrorDialogToStringBuilder("You must enter a number of seats");
                }
            
                try
                {
                        isNumberOfSeatsLargerThanZero = Integer.parseInt(numberOfSeatsJTextField.getText()) > 0;
                }
                
                catch(NumberFormatException e)
                {
                        numberOfSeatsFieldObjectToJComponentContainer(numberOfSeatsJTextField);
                        addNumberOfSeatsErrorDialogToStringBuilder("The number of seats should be larger than zero");
                }
                
                

        }
                
        
        
        /**
         * Accesses the first erroneously entered swing component found in data validation
         */ 
        private JComponent getLoadCapacityFieldObjectJComponentContainer()
        {
                return loadCapacityFieldObjectJComponentContainer;
        }
        
        
        /**
         * Data validation JComponent container
         * @return fills a JComponent container with the first swing component found to contain an error during data validation
         */         
        private JComponent loadCapacityFieldObjectToJComponentContainer(JComponent fieldObject)
        {
                if(loadCapacityFieldObjectJComponentContainer == null)
                {
                        loadCapacityFieldObjectJComponentContainer = fieldObject;
                }
                
                return loadCapacityFieldObjectJComponentContainer;
        }
        
        

        /**
         * Accesses the text of the first erroneously entered swing component found in data validation
         */ 
        private StringBuilder getLoadCapacityErrorDialogStringBuilder()
        {
                return loadCapacityErrorDialogStringBuilder;
        }        
        
        
        
        /**
         * Data validation StringBuilder
         * @return fills a StringBuider with the error message text related to the first swing component found to contain an error during data validation
         */
        private StringBuilder addLoadCapacityErrorDialogToStringBuilder(String errorDialog)
        {
                if(loadCapacityErrorDialogStringBuilder.length() == 0)
                {
                        loadCapacityErrorDialogStringBuilder.append(errorDialog);
                }
                
                return loadCapacityErrorDialogStringBuilder;
        }

        
        
        /**
         * Performs data validation checks for ABN fields and stores the first swing component and related error message text found to contain an error during data validation
         */        
        private void validateLoadCapacityField()
        {
                loadCapacityFieldObjectJComponentContainer = null;
                loadCapacityErrorDialogStringBuilder.setLength(0);
                int loadCapacity;
                boolean isLoadCapacityLargerThanZero;
                loadCapacity = 0;
                isLoadCapacityLargerThanZero = false;
                
                try
                {
                        loadCapacity = Integer.parseInt(loadCapacityJTextField.getText());
                }
                
                catch(NumberFormatException e)
                {
                        loadCapacityFieldObjectToJComponentContainer(loadCapacityJTextField);
                        addLoadCapacityErrorDialogToStringBuilder("You must enter a load capacity");
                }
            
                try
                {
                        isLoadCapacityLargerThanZero = Integer.parseInt(loadCapacityJTextField.getText()) > 0;
                }
                
                catch(NumberFormatException e)
                {
                        loadCapacityFieldObjectToJComponentContainer(loadCapacityJTextField);
                        addLoadCapacityErrorDialogToStringBuilder("The load capacity should be larger than zero");
                }

        }
                
        
        /**
         * Receives an input from the generateNumberPlateValues method, rounding the input down to 0 decimal places
         * @param number input received from the generateNumberPlateValues method
         * @return returns the rounded down result casted as an integer
         */
        public int roundDown(double number)
        { 
               number = Math.floor(number); 
               return (int)number;
        }
        
        
        
        /**
         * Generates an accumulating total of combinations by multiplying the preceding combinationAccummulatingTotal by the current combination value for the number character place. e.g. 10; 100 = 10 x 10; 1000 = 100 x 10; 26000 = 1000 x 26; 676000 = 26000 x 26; 6760000 = 676000 x 10;
         */
        private void generateCombinationAccummulatingTotal()
        {
                combinationAccummulatingTotal[0] = combinationValues[0];
                for(int accumulationIndex = 1; accumulationIndex < numberOfCharacterIndexesInNumberPlate; accumulationIndex++)
                        combinationAccummulatingTotal[accumulationIndex] = combinationAccummulatingTotal[accumulationIndex - 1] * combinationValues[accumulationIndex];
        }
        
        
        
        /**
         * Generates the generateMaximumNumberPlaceValues by subtracting 1 from the CombinationAccummulatingTotal, important in the calculation of the minimumNumberPlaceValues. example iterations are as follows: 9 = 10 - 1; 99 = 100 - 1; 999 = 1000 - 1; 25999 = 26000 - 1; 675999 = 676000 - 1; 6759999 = 6760000 - 1;
         */
        private void generateMaximumNumberPlaceValues()
        {
                for(int numberPlateCharacterIndex = 0; numberPlateCharacterIndex < numberOfCharacterIndexesInNumberPlate; numberPlateCharacterIndex++)
                        maximumNumberPlaceValues[numberPlateCharacterIndex] = combinationAccummulatingTotal[numberPlateCharacterIndex] - 1;
        }
        
        
        
        /**
         * Generates the minimumNumberPlaceValues by adding 1 to the previous maximumNumberPlaceValues index. These will be used to create the flippedMinimumPlaceValues. Example iterations are as follows: 1; 10 = 9 + 1; 100 = 99 + 1; 1000 = 999 + 1; 26000 = 25999 + 1; 676000 = 675999 + 1;
         */
        private void generateMinimumNumberPlaceValues()
        {
                minimumNumberPlaceValues[0] = 1;
                for(int numberPlateCharacterIndex = 1; numberPlateCharacterIndex < numberOfCharacterIndexesInNumberPlate; numberPlateCharacterIndex++)
                        minimumNumberPlaceValues[numberPlateCharacterIndex] = maximumNumberPlaceValues[numberPlateCharacterIndex - 1] + 1;
        }
        
        
        /**
         * The flippedMinimumIndexes are used to calculate the minimum whole number which can be entered into each number place. The flippedMinimumIndexes are calculated by reading the minimumNumberPlaceValues from right to left, last index to first index. For instance 676000; 26000; 1000; 100; 10; 1
         */
        private void generateFlippedMinimumIndexes()
        {
                for(int flippedMinimumIndex = 0; flippedMinimumIndex < numberOfCharacterIndexesInNumberPlate; flippedMinimumIndex++)
                        flipppedMinimumNumberPlaceValues[flippedMinimumIndex] = minimumNumberPlaceValues[numberOfCharacterIndexesInNumberPlate - 1 - flippedMinimumIndex];
        }
        
        
        
        /**
         * Calculates the quantityInNumberPlace essentially using long division. For instance how many times does the remainder (in some cases the starting integer) fit into the flippedNumberPlaceValues without resolving to a decimal place? For instance with the starting integer 521000, 521000 goes into the flippedMinimumNumbrPlace of 676000 0 times with a remainder of 521000;
         * 521000 goes into 26000 20 times with a remainder of 1000; Lastly, 1000 goes into 1000 1 time with 0 remainder
         */
        private void generateNumberPlateValues(int currentNumberPlate)
        {
                remainder[0] = currentNumberPlate;
                
                quantityInNumberPlace[0] = roundDown(remainder[0] / flipppedMinimumNumberPlaceValues[0]);
                amount = flipppedMinimumNumberPlaceValues[0] * quantityInNumberPlace[0];
                if(1 < numberOfCharacterIndexesInNumberPlate)
                        remainder[1] = remainder[0] - amount;
               
                for (int calculationIndex = 0; calculationIndex < numberOfCharacterIndexesInNumberPlate; calculationIndex++)
                {
                        quantityInNumberPlace[calculationIndex] = roundDown(remainder[calculationIndex] / flipppedMinimumNumberPlaceValues[calculationIndex]);
                        amount = flipppedMinimumNumberPlaceValues[calculationIndex] * quantityInNumberPlace[calculationIndex];
                        if(calculationIndex + 1 < numberOfCharacterIndexesInNumberPlate)
                                remainder[calculationIndex + 1] = remainder[calculationIndex] - amount;
                }

        }
        
        
        
        /**
         * Assembles all the prerequisite integers used to calculated the flippedMinimumIndexes
         */
        private void assignNumberPlateArrays()
        {
                generateCombinationAccummulatingTotal();
                generateMaximumNumberPlaceValues();
                generateMinimumNumberPlaceValues();
                generateFlippedMinimumIndexes();
        }
        
        
        
        /**
         * Once the long division in the generateNumberPlateValues method has been performed, the quantityInNumberPlace values need to be organised into how they should appear as number plates. For instance following the planned number plate design for September 2020 in Queensland for light and heavy vehicles, there are three digits, then two letters, then one digit.
         * Because the quantityInNumberPlace values are for the minimumNumbrPlaceValues in flipped/reverse order, they need to be rearranged with smallest indexes in the largest position and largest indexes in the smallest position. E.g. 3,4,5,1,2,0 represents the 000AA0 format for September 2020.
         */
        private void organiseLightAndHeavyVehicleNumberPlateValues()
        {
                characterIndexesInNumberPlate.append(quantityInNumberPlace[3]).append(quantityInNumberPlace[4]).append(quantityInNumberPlace[5]).append(integerToUpperCaseAlphatbeticalCharacterConversion(quantityInNumberPlace[1])).append(integerToUpperCaseAlphatbeticalCharacterConversion(quantityInNumberPlace[2])).append(quantityInNumberPlace[0]);
        }
        
        
        
        /**
         * Similar to the organiseLightAndHeavyVehicleNumberPlateValues method, motorcycle number plates also need to be organised. However, motorcycles tend to have five characters rather than six with the format being 2,3,4,0,1 or 000Aa
         */
        private void organiseMotorcycleNumberPlateValues()
        {
                characterIndexesInNumberPlate.append(quantityInNumberPlace[2]).append(quantityInNumberPlace[3]).append(quantityInNumberPlace[4]).append(integerToUpperCaseAlphatbeticalCharacterConversion(quantityInNumberPlace[0]-1)).append(integerToLowerCaseAlphatbeticalCharacterConversion(quantityInNumberPlace[1]-1));
        }
        
        
        
        /**
         * Receives various characters from the organiseLightAndHeavyVehicleNumberPlateValues and the organiseMotorcycleNumberPlateValues methods which need to be converted into letters rather than numbers
         * @param number the number retrieved from the organiseLightAndHeavyVehicleNumberPlateValues and the organiseMotorcycleNumberPlateValues methods
         * @return returns the input number as a upper case character 
         */
        public String integerToUpperCaseAlphatbeticalCharacterConversion(int number)
        { 
                String [] result;
                result = new String [] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; 
                return result[number]; 
        } 
                
    
        
        /**
         * Receives characters from the organiseMotorcycleNumberPlateValues method which need to be converted into letters rather than numbers
         * @param number the number retrieved from the organiseMotorcycleNumberPlateValues method
         * @return returns the input number as a lower case character 
         */
        public String integerToLowerCaseAlphatbeticalCharacterConversion(int number)
        { 
                String [] result;
                result = new String [] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}; 
                return result[number]; 
        } 
   
    
    
        /**
         * Utilises the numberOfCharacterIndexesInNumberPlate field to update the for loop limits across many number plate calculation methods.
         * The combination values for the number plate can be input. For instance, the number plate format 000AA0 has the possibilities of 0-9 which is 10 possible combination values while A-Z has 26 possible combination values for instance.
         * The assignNumberPlateArrays method is used to calculate the flippedMinimumNumberPlaceValues.
         * The generateNumberPlateValues utilises long division to fit each current numberplate into each flippedMinimumNumberPlaceValue how many times in order to generate quantityInNumberPlace values
         * The organiseLightAndHeavyVehicleNumberPlateValues organises the quantityInNumberPlace values into the appropriate order and converts numbers into letters as needed
         * The string contained in the StringBuilder is output to a temporary variable while the StringBuilder is emptied to make way for new number plates.
         * The corresponding current number plate integer is incremented.
         * Lastly, the temporary string holding the number plate is output to the calling method.
         * @return returns the number plate as a string from the temporary variable
         */
        public String generateLightAndHeavyVehicleNumberPlate()
        {
                numberOfCharacterIndexesInNumberPlate = 6;
                String numberPlateText;
        
                combinationValues[0] = 10;
                combinationValues[1] = 10;
                combinationValues[2] = 10;
                combinationValues[3] = 26;
                combinationValues[4] = 26;
                combinationValues[5] = 10;
                
                assignNumberPlateArrays();
                generateNumberPlateValues(currentLightAndHeavyVehicleNumberPlate);
                
                organiseLightAndHeavyVehicleNumberPlateValues();
                
                numberPlateText = characterIndexesInNumberPlate.toString();
                
                characterIndexesInNumberPlate.setLength(0);
                
                currentLightAndHeavyVehicleNumberPlate++;
                
                return numberPlateText;
        }   
                
        
        /**
         * Utilises the numberOfCharacterIndexesInNumberPlate field to update the for loop limits across many number plate calculation methods.
         * The combination values for the number plate can be input. For instance, the number plate format 000Aa has the possibilities of 0-9 which is 10 possible combination values while a-z has 26 possible combination values for instance.
         * The assignNumberPlateArrays method is used to calculate the flippedMinimumNumberPlaceValues.
         * The generateNumberPlateValues utilises long division to fit each current numberplate into each flippedMinimumNumberPlaceValue how many times in order to generate quantityInNumberPlace values
         * The organiseMotorcycleNumberPlateValues organises the quantityInNumberPlace values into the appropriate order and converts numbers into letters as needed
         * The string contained in the StringBuilder is output to a temporary variable while the StringBuilder is emptied to make way for new number plates.
         * The corresponding current number plate integer is incremented.
         * Lastly, the temporary string holding the number plate is output to the calling method.
         * @return returns the number plate as a string from the temporary variable
         */
        public String generateMotorcycleNumberPlate()
        {
                numberOfCharacterIndexesInNumberPlate = 5;
                String numberPlateText;

                combinationValues[0] = 10;
                combinationValues[1] = 10;
                combinationValues[2] = 10;
                combinationValues[3] = 26;
                combinationValues[4] = 26;

                assignNumberPlateArrays();

                generateNumberPlateValues(currentMotorcycleNumberPlate);
                
                organiseMotorcycleNumberPlateValues();
                
                numberPlateText = characterIndexesInNumberPlate.toString();
                
                characterIndexesInNumberPlate.setLength(0);
                
                currentMotorcycleNumberPlate++;
                
                return numberPlateText;
        } 
        
        
        
        /**
         * Retrieves the current number plate number from the values stored at the ownerSearchReferenceIndex index in the vehicleArray
         * @return returns the numberplate ready for output to the addPrivateOwner and addCorporateOwner methods
         */
        private String getNumberPlate()
        {
                return MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex()).getPlateNumber();
        }
        
        
        
        /**
         * If while editing the current vehicle, and the vehicle remains selected as either a light or heavy vehicle, retain the current number plate and decrement the currentLightAndHeavyVehicleNumberPlate due to the increment which occurred while calling the generateLightAndHeavyVehicleNumberPlate method. Otherwise adopt a light/heavy number plate and discard the current motorcycle number plate.
         * @return returns the same number plate if the vehicle remains of a similar type otherwise discard the current number plate and assign a new light or heavy vehicle number plate. Discarding the motorcycle number plate is necessary as we do not know whether decrementing the current motorcycle number plate upon changing the vehicle type could end up reassigning the current number plate
         * to an already assigned number plate, which would create duplicate motorcycle number plates.
         */
        private String checkLightAndHeavyVehicleNumberPlate()
        {
                Vehicle objectTypeValue;
                String currentNumberPlate;
                String generatedNumberPlate;
                objectTypeValue = MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex());
                currentNumberPlate = getNumberPlate();
                generatedNumberPlate = generateLightAndHeavyVehicleNumberPlate();
                
                if(objectTypeValue instanceof LightVehicle || objectTypeValue instanceof HeavyVehicle)
                {
                        currentLightAndHeavyVehicleNumberPlate--;
                        return currentNumberPlate; 
                }
                else
                        return generatedNumberPlate;
        }
        
        
        /**
         * If while editing the current vehicle, and the vehicle remains selected as a motorcycle, retain the current number plate and decrement the currentMotorcycleNumberPlate due to the increment which occurred while calling the generateMotorcycleVehicleNumberPlate method. Otherwise adopt a motorcycle number plate and discard the current light/heavy vehicle number plate.
         * @return returns the same number plate if the vehicle remains of a similar type otherwise discard the current number plate and assign a new motorcycle number plate. Discarding the light/heavy vehicle number plate is necessary as we do not know whether decrementing the current light/heavy vehicle number plate upon changing the vehicle type could end up reassigning the current number plate
         * to an already assigned number plate, which would create duplicate light/heavy vehicle number plates.
         */
        private String checkMotorcycleNumberPlate()
        {
                String currentNumberPlate;
                String generatedNumberPlate;
                currentNumberPlate = getNumberPlate();
                generatedNumberPlate = generateMotorcycleNumberPlate();
                if(currentNumberPlate.length() == generatedNumberPlate.length())
                {
                        currentMotorcycleNumberPlate--;
                        return currentNumberPlate;
                }
                else 
                        return generatedNumberPlate;
        }
        
        
        
        /**
         * Clears the contents of JTextFields and JComboBoxes
         */
        private static void clear()
        {
                resetJComboBox();
                loadCapacityJTextField.setText("");
                numberOfSeatsJTextField.setText("");
                engineCapacityJTextField.setText("");
                makeJTextField.setText("");
                modelJTextField.setText("");
                yearJTextField.setText("");
                ownerIdJComboBox.setSelectedIndex(0);
        }
        
        
        
        /**
         * Clears the contents of JTextFields and JComboBoxes
         */
        private static void resetGUIAndShowNewVehicleHeading()
        {
                SearchJPanel.newVehicle();
                clear();
        }
        
        
        
        /**
         * Clears the contents of JTextFields and JComboBoxes in the VehicleJPanel, appends edited vehicle details to the searchJPanel class, and switches the focus to the SearchJPanel
         */
        private static void resetGUIAndShowEditVehicleHeading()
        {
                SearchJPanel.editVehicle();
                clear();
        }

        
        
        /**
         * Clears the contents of the selectVehicleTypeJComboBox and updates the owneridJcomboBox through the itemStateChangd method
         */
        public static void resetJComboBox()
        {
                int selection;
                selection = selectVehicleTypeJComboBox.getSelectedIndex();
                if(getEditMode() == false)
                        selectVehicleTypeJComboBox.setSelectedIndex(0);
                else
                {
                        selectVehicleTypeJComboBox.setSelectedIndex(0);
                        selectVehicleTypeJComboBox.setSelectedIndex(selection);
                }
        }
      
        
        
        /**
         * Retrieves the number of seats from a JTextField
         * @return outputs the retrieved number of seats to the addLightVehicle and the acceptChangesLightVehicle methods
         */
        private int getNumberOfSeats()
        {
                numberOfSeats = Integer.parseInt(numberOfSeatsJTextField.getText());
                return numberOfSeats;
        }
        
        
        
        /**
         * Sets the number of seats JTextField to the number of seats stored in the vehicleSearchReferenceIndex index in the vehicleArray
         */
        public static void setNumberOfSeats()
        {
                lightVehicleArray.add((LightVehicle) MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex()));
                numberOfSeatsJTextField.setText(lightVehicleArray.get(0).getNumberOfSeats() + "");
                lightVehicleArray.remove(0);
        }
        
        
        
        /**
         * Retrieves the lead capacity from a JTextField
         * @return outputs the retrieved load capacity to the addHeavyVehicle and the acceptChangesHeavytVehicle methods
         */
        private int getLoadCapacity()
        {
                loadCapacity = Integer.parseInt(loadCapacityJTextField.getText());
                return loadCapacity;
        }

        
        
        /**
         * Sets the load capacity JTextField to the load capacity stored in the vehicleSearchReferenceIndex index in the vehicleArray
         */
        public static void setLoadCapacity()
        {
                heavyVehicleArray.add((HeavyVehicle) MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex()));
                loadCapacityJTextField.setText(heavyVehicleArray.get(0).getLoadCapacity() + "");
                heavyVehicleArray.remove(0);
        }
        
        
        
        /**
         * Retrieves the engine capacity from a JTextField
         * @return outputs the retrieved engine capacity to the addMotorcycle, addLightVehicle, addHeavyVehicle, acceptChangesMotorcycle, acceptChangesLightVehicle, and the acceptChangesHeavyVehicle methods
         */
        private double getEngineCapacity()
        {
                engineCapacity = Double.parseDouble(engineCapacityJTextField.getText());
                return engineCapacity;
        }    
        
        
        
        /**
         * Sets the engine capacity JTextField to the engine capacity stored in the vehicleSearchReferenceIndex index in the vehicleArray
         */
        private static void setEngineCapacity()
        {
                engineCapacityJTextField.setText(MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex()).getEngineCapacity() + "");
        } 
        
        
        
        /**
         * Retrieves the make from a JTextField
         * @return outputs the retrieved make to the addMotorcycle, addLightVehicle, addHeavyVehicle, acceptChangesMotorcycle, acceptChangesLightVehicle, and the acceptChangesHeavyVehicle methods
         */
        private String getMake()
        {
                make = makeJTextField.getText();
                return make;
        }   
        
        
        
        /**
         * Sets the make JTextField to the make stored in the vehicleSearchReferenceIndex index in the vehicleArray
         */
        private static void setMake()
        {
                makeJTextField.setText(MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex()).getMake());
        }      
        
        
        
        /**
         * Retrieves the model from a JTextField
         * @return outputs the retrieved model to the addMotorcycle, addLightVehicle, addHeavyVehicle, acceptChangesMotorcycle, acceptChangesLightVehicle, and the acceptChangesHeavyVehicle methods
         */
        private String getModel()
        {
                model = modelJTextField.getText();
                return model;
        }
        
        
        
        /**
         * Sets the model JTextField to the model stored in the vehicleSearchReferenceIndex index in the vehicleArray
         */
        private static void setModel()
        {
                modelJTextField.setText(MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex()).getModel());
        } 
        
        
        
        /**
         * Retrieves the year from a JTextField
         * @return outputs the retrieved year to the addMotorcycle, addLightVehicle, addHeavyVehicle, acceptChangesMotorcycle, acceptChangesLightVehicle, and the acceptChangesHeavyVehicle methods
         */
        private int getYear()
        {
                year = Integer.parseInt(yearJTextField.getText());
                return year;
        }
        
        
        
        /**
         * Sets the year JTextField to the year stored in the vehicleSearchReferenceIndex index in the vehicleArray
         */
        private static void setYear()
        {
                yearJTextField.setText(MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex()).getYear() + "");
        }
        
        
        
        /**
         * Retrieves the ownerId from a JComboBox
         * @return outputs the retrieved ownerId to the addMotorcycle, addLightVehicle, addHeavyVehicle, acceptChangesMotorcycle, acceptChangesLightVehicle, and the acceptChangesHeavyVehicle methods
         */
        private int getOwnerId()
        {
                ownerId = Integer.parseInt(ownerIdJComboBox.getItemAt(ownerIdJComboBox.getSelectedIndex()));
                return ownerId;
        }     
        
        
        /**
         * Empty the ownerIdStringBuilder at the start of each iteration, and then tests whether the license number of the owner also corresponds to at least one ownerId stored at the vehicleSearchReferenceIndex index in the vehicleArray. If so, append the index to the StringBuilder, increasing the length of the string contained in the StringBuilder above 0.
         * @param indexToSearch index retrieved from the setOwnerId method
         * @return returns the length of the text stored in the StringBuilder
         */
        private int searchForOwnerID(int indexToSearch)
        {
                ownerIdStringBuilder.setLength(0);
                int vehicleArraySize;
                vehicleArraySize = MotorVehicleRegistrationFrame.vehicleArray.size();
                for (int searchIndex = 0; searchIndex < vehicleArraySize; ++searchIndex)
                        if(indexToSearch == MotorVehicleRegistrationFrame.vehicleArray.get(searchIndex).getOwnerId())
                                ownerIdStringBuilder.append(indexToSearch);
                return ownerIdStringBuilder.length();
        }

        
        
        /**
         * Enforces referential integrity and business rules by allowing users to only select from a range of valid ownerIds whilst also ensuring that an owner cannot have more than one vehicle by not including them in the ownerIdJComboBox, if an owner has at least one vehicle.
         * However, to ensure that users can always select the original ownerId assigned to a vehicle while editing, the ownerId at the vehicleSearchReferenceIndex index in the vehicleArray is always appended to index one of the ownerIdJComboBox while in edit mode
         */
        private void setOwnerId()
        {
                int ownerArraySize;
                int vehicleArraySize;
                ownerArraySize = MotorVehicleRegistrationFrame.ownerArray.size();
                vehicleArraySize = MotorVehicleRegistrationFrame.vehicleArray.size();
                AddOwnerIdStartingElements();
                for (int searchIndex = 0; searchIndex < ownerArraySize; ++searchIndex)
                {
                        if(vehicleArraySize == 0)
                                ownerIdJComboBox.addItem(MotorVehicleRegistrationFrame.ownerArray.get(searchIndex).getId() + ""); 
                        else
                        {
                                if(searchForOwnerID(searchIndex) == 0)
                                        ownerIdJComboBox.addItem(MotorVehicleRegistrationFrame.ownerArray.get(searchIndex).getId() + ""); 
                        }
                }
                if(getEditMode() == true)
                        ownerIdJComboBox.setSelectedIndex(OWNER_ID_REFERENCE_INDEX);
        }
 
        
        
        /**
         * Adds a "..." item as the first item in the JComboBox so the validateCommonVehicleFields methods can check whether essentially nothing has been selected as an ownerId.
         * Alternatively, if edit mode is enabled, the first items in the JComboBox is the ownerId stored at the vehicleSearchReferenceIndex in the vehicle array. Data validation checking 
         * at index 0 is also temporarily disabled.
         */
        private void AddOwnerIdStartingElements()
        {
                ownerIdJComboBox.removeAllItems();
                if(getEditMode() == false)
                        ownerIdJComboBox.addItem("...");
                else if(getEditMode() == true)
                        ownerIdJComboBox.addItem(MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex()).getOwnerId() + "");
        }
        
        
        
        /**
         * Utilises modularity to validate common fields, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, motorcycle details are entered into an array list. Next, JTextFields are set to zero length strings, and JComboBoxes are set to the first index "...". The tab is then switched to the SearchJpanel which displays the newly added vehicle details.
         * This helps to avoid needing to create a JTextArea to display newly added details in the VehicleJPanel, all the while providing users a visual clue that data was successfully entered.
         */
        private void addMotorcycle()
	{   
                validateCommonVehicleFields();
                
                if(getCommonFieldObjectJComponentContainer() != null && getCommonErrorDialogStringBuilder().length() > 0)
                        generateCommonFieldDataValidationErrorDialog();

                else
                {
                        MotorVehicleRegistrationFrame.vehicleArray.add( new Motorcycle (generateMotorcycleNumberPlate(), getEngineCapacity(), getMake(), getModel(), getYear(), getOwnerId()));
                        resetGUIAndShowNewVehicleHeading();
                        return;
                }

	}
        
        
        
        /**
         * Utilises modularity to validate common fields, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, light vehicle details are entered into an array list. Next, JTextFields are set to zero length strings, and JComboBoxes are set to the first index "...". The tab is then switched to the SearchJpanel which displays the newly added vehicle details.
         * This helps to avoid needing to create a JTextArea to display newly added details in the VehicleJPanel, all the while providing users a visual clue that data was successfully entered.
         */
        private void addLightVehicle()
	{   
                validateNumberOfSeatsField();
                validateCommonVehicleFields();
                
                if(getNumberOfSeatsFieldObjectJComponentContainer() != null && getNumberOfSeatsErrorDialogStringBuilder().length() > 0)
                        generateNumberOfSeatsFieldDataValidationErrorDialog();
                
                else if(getCommonFieldObjectJComponentContainer() != null && getCommonErrorDialogStringBuilder().length() > 0)
                        generateCommonFieldDataValidationErrorDialog();

                else
                {
                        MotorVehicleRegistrationFrame.vehicleArray.add( new LightVehicle (generateLightAndHeavyVehicleNumberPlate(), getNumberOfSeats(), getEngineCapacity(), getMake(), getModel(), getYear(), getOwnerId()));
                        resetGUIAndShowNewVehicleHeading();
                        return;
                }

	}
        
        
        
        /**
         * Utilises modularity to validate common fields, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, heavy vehicle details are entered into an array list. Next, JTextFields are set to zero length strings, and JComboBoxes are set to the first index "...". The tab is then switched to the SearchJpanel which displays the newly added vehicle details.
         * This helps to avoid needing to create a JTextArea to display newly added details in the VehicleJPanel, all the while providing users a visual clue that data was successfully entered.
         */
        private void addHeavyVehicle()
	{   
                validateLoadCapacityField();
                validateCommonVehicleFields();
                
                if(getLoadCapacityFieldObjectJComponentContainer() != null && getLoadCapacityErrorDialogStringBuilder().length() > 0)
                        generateLoadCapacityFieldDataValidationErrorDialog();
                
                else if(getCommonFieldObjectJComponentContainer() != null && getCommonErrorDialogStringBuilder().length() > 0)
                        generateCommonFieldDataValidationErrorDialog();

                else
                {
                        MotorVehicleRegistrationFrame.vehicleArray.add( new HeavyVehicle (generateLightAndHeavyVehicleNumberPlate(), getLoadCapacity(), getEngineCapacity(), getMake(), getModel(), getYear(), getOwnerId()));
                        resetGUIAndShowNewVehicleHeading();
                        return;
                }

	}
        
        
        
        /**
         * Sets common text fields to the values stored in the vehicleSearchReferenceIndex index in the vehicleArray
         */
        private static void setCommonFields()
        {
                VehicleJPanel.setEngineCapacity();
                VehicleJPanel.setMake();
                VehicleJPanel.setModel();
                VehicleJPanel.setYear();
        }
        
        
        
        /**
         * Sets the selectvehicleTypeJComboBox to select motorcycle from the JComboBox
         */
        private static void setMotorcycleFields()
        {
                selectVehicleTypeJComboBox.setSelectedIndex(MOTORCYCLE);
        }
        
        
        
        /**
         * Sets number of seats fields to the values stored in the vehicleSearchReferenceIndex index in the vehicleArray, and sets the selectVehicleTypeJComboBox to select light vehicle from the JComboBox
         */
        private static void setLightVehicleFields()
        {
                VehicleJPanel.setNumberOfSeats();
                selectVehicleTypeJComboBox.setSelectedIndex(LIGHT_VEHICLE);
        }
        
        
        
        /**
         * Sets load capacity fields to the values stored in the vehicleSearchReferenceIndex index in the vehicleArray, and sets the selectVehicleTypeJComboBox to select heavy vehicle from the JComboBox
         */
        private static void setHeavyVehicleFields()
        {
                VehicleJPanel.setLoadCapacity();
                selectVehicleTypeJComboBox.setSelectedIndex(HEAVY_VEHICLE);
        }
        
        
        
        /**
         * Determines whether the ownerSearchReferenceIndex index in the ownerArray refers to a corporate or private owner, and then sets the corresponding fields
         */
        private static void setFieldsBasedOnVehicleObjctType()
        {
                Vehicle objectTypeValue;
                objectTypeValue = MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex());
                setCommonFields();
                
                if(objectTypeValue instanceof Motorcycle)
                        setMotorcycleFields(); 
                else if(objectTypeValue instanceof LightVehicle)
                        setLightVehicleFields();
                else
                        setHeavyVehicleFields();
        }
        
        
        
        /**
         * Sets the tab to edit mode by setting the editMode variable to true, relabelling relevant JLabels, changing the tab name, and setting fields and text areas to the values stored in the vehicleSearchReferenceIndex index in the vehicleArray. The add mode button is also added to transition back to add mode
         */
        public static void editMode()
        {
                vehicleJLabel.setText("Edit Owner");
                enterVehicleDetailsJLabel.setText("Edit Owner Details");
                vehicleJButton.setText("Accept Changes");
                addModeJButton.setVisible(true);
                editMode = true;
                MotorVehicleRegistrationFrame.resetVehiclePanelJComboBox();
                MotorVehicleRegistrationFrame.showEditVehicleTab();
                setFieldsBasedOnVehicleObjctType();
        }
        
        
        
        /**
         * Accesses the editMode variable
         * @return returns whether or not edit mode has been enabled
         */
        public static boolean getEditMode()
        {
            return editMode;
        }
        
        
        
        /**
         * Sets the tab to add mode by setting the editMode variable to false, relabelling relevant JLabels, changing the tab name, and setting fields and text areas to zero length strings. The add mode button is also hidden to prevent the user from transitioning back to add mode when add mode is already enabled.
         * Lastly, links to the vehicleSearchReferenceIndex index of the array list are severed to prevent the user from re-editing or deleting the current index, without performing another search.
         */
        private static void addMode()
        {
                vehicleJLabel.setText("Add Vehicle");
                enterVehicleDetailsJLabel.setText("Enter Vehicle Details");
                vehicleJButton.setText("Add Vehicle");
                addModeJButton.setVisible(false);
                editMode = false;
                clear();
                MotorVehicleRegistrationFrame.showAddVehicleTab();
                MotorVehicleRegistrationFrame.showSearchTab();
                SearchJPanel.resetSearchAndReferenceIndexes();
        }
        
        

        /**
         * Utilises modularity to validate common fields and common fields, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, motorcycle details are entered into an array list, overwriting the vehicleSearchReferenceIndex index. Next, JTextFields are set to zero length strings, and JComboBoxes are set to the first index "...". The tab is then switched to the SearchJpanel which displays the newly edited vehicle details.
         * Lastly, the VehicleJpanel is switched to add mode, to allow the entry of new vehicles into the vehicleArray.
         */
        private void acceptChangesMotorcycle()
	{ 
                validateCommonVehicleFields();
                
                if(getCommonFieldObjectJComponentContainer() != null && getCommonErrorDialogStringBuilder().length() > 0)
                        generateCommonFieldDataValidationErrorDialog();
                
                else
                {
                        MotorVehicleRegistrationFrame.vehicleArray.set(SearchJPanel.getVehicleSearchReferenceIndex(), new Motorcycle (checkMotorcycleNumberPlate(), getEngineCapacity(), getMake(), getModel(), getYear(), getOwnerId()));
                        resetGUIAndShowEditVehicleHeading();
                        addMode();
                }

	}
        
        
        
        /**
         * Utilises modularity to validate common fields and common fields, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, light vehicle details are entered into an array list, overwriting the vehicleSearchReferenceIndex index. Next, JTextFields are set to zero length strings, and JComboBoxes are set to the first index "...". The tab is then switched to the SearchJpanel which displays the newly edited vehicle details.
         * Lastly, the VehicleJpanel is switched to add mode, to allow the entry of new vehicles into the vehicleArray.
         */
        private void acceptChangesLightVehicle()
	{ 
                validateNumberOfSeatsField();
                validateCommonVehicleFields();
                        
                if(getNumberOfSeatsFieldObjectJComponentContainer() != null && getNumberOfSeatsErrorDialogStringBuilder().length() > 0)
                        generateNumberOfSeatsFieldDataValidationErrorDialog();
                
                else if(getCommonFieldObjectJComponentContainer() != null && getCommonErrorDialogStringBuilder().length() > 0)
                        generateCommonFieldDataValidationErrorDialog();
                
                else
                {
                        MotorVehicleRegistrationFrame.vehicleArray.set(SearchJPanel.getVehicleSearchReferenceIndex(), new LightVehicle (checkLightAndHeavyVehicleNumberPlate(), getNumberOfSeats(), getEngineCapacity(), getMake(), getModel(), getYear(), getOwnerId()));
                        resetGUIAndShowEditVehicleHeading();
                        addMode();
                }

	}
        
        
        
        /**
         * Utilises modularity to validate common fields and common fields, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, heavy vehicle details are entered into an array list, overwriting the vehicleSearchReferenceIndex index. Next, JTextFields are set to zero length strings, and JComboBoxes are set to the first index "...". The tab is then switched to the SearchJpanel which displays the newly edited vehicle details.
         * Lastly, the VehicleJpanel is switched to add mode, to allow the entry of new vehicles into the vehicleArray.
         */
        private void acceptChangesHeavyVehicle()
	{ 
                validateLoadCapacityField();
                validateCommonVehicleFields();
                        
                if(getLoadCapacityFieldObjectJComponentContainer() != null && getLoadCapacityErrorDialogStringBuilder().length() > 0)
                        generateLoadCapacityFieldDataValidationErrorDialog();
                
                else if(getCommonFieldObjectJComponentContainer() != null && getCommonErrorDialogStringBuilder().length() > 0)
                        generateCommonFieldDataValidationErrorDialog();
                
                else
                {
                        MotorVehicleRegistrationFrame.vehicleArray.set(SearchJPanel.getVehicleSearchReferenceIndex(), new HeavyVehicle (checkLightAndHeavyVehicleNumberPlate(), getLoadCapacity(), getEngineCapacity(), getMake(), getModel(), getYear(), getOwnerId()));
                        resetGUIAndShowEditVehicleHeading();
                        addMode();
                }

	}
                
        
        
        /**
         * getActionCommand is used to provide modularity to the vehicle button, allowing the function of the button to be changed by relabelling the JButton in the addMode and editMode methods rather than having two separate JButtons for adding and editing owner records.
         * Next, the user's selection from the selectOwnerTypeJComboBox enables the application to determine which type of owner to add or edit.
         * The add mode button, switches the JPanel to edit mode. However, with the risk of severing the connection with the ownerSearchReferenceIndex index of the ownerAray, a confirmation dialog box is generated to ask whether or not the user wants this to occur.
         * Lastly, the clear button replaces fields and text areas in the VehicleJpanel with zero length strings, and sets the selectVehicleTypeJComboBox to index 0, hiding most cells.
         */  
        @Override
        public void actionPerformed(ActionEvent e)
        {
                String command = e.getActionCommand();
                Object source = e.getSource();

                if(command.equals("Add Vehicle"))
                {
                        if(selectVehicleTypeJComboBox.getSelectedIndex() == MOTORCYCLE)
                        {
                               addMotorcycle();
                        }
                   
                        if(selectVehicleTypeJComboBox.getSelectedIndex() == LIGHT_VEHICLE)
                        {
                               addLightVehicle();
                        }
                       
                        if(selectVehicleTypeJComboBox.getSelectedIndex() == HEAVY_VEHICLE)
                        {
                               addHeavyVehicle();
                        }
                } 
                
                if(command.equals("Accept Changes"))
                {
                        if(selectVehicleTypeJComboBox.getSelectedIndex() == MOTORCYCLE)
                        {
                               acceptChangesMotorcycle();
                        }
                   
                        if(selectVehicleTypeJComboBox.getSelectedIndex() == LIGHT_VEHICLE)
                        {
                               acceptChangesLightVehicle();
                        }
                       
                        if(selectVehicleTypeJComboBox.getSelectedIndex() == HEAVY_VEHICLE)
                        {
                               acceptChangesHeavyVehicle();
                        }
                } 
                
                else if(source == addModeJButton)
                {
                        generateConfirmDialog("You are currently in edit mode, would you like to return to the add mode?\n\n"
                                + "No further edits can be performed if you return to add mode.");
                        if(confirmDialogResponse == JOptionPane.YES_OPTION)
                                addMode();
                        else
                                return;
                }
                
                else if(source == clearJButton)
                {
                        generateConfirmDialog("Are you sure you wish to clear text from the fields in this tab?");
                        if(confirmDialogResponse == JOptionPane.YES_OPTION)
                                clear();
                        else
                                return;
                }

        }
        
        
        
        /**
         * This event connects to the selectVehicleTypeJComboBox which determines which fields and text areas within the panel to be made visible and not visible. 
         */
        @Override
        public void itemStateChanged(ItemEvent e)
	{
		Object source = e.getSource();
                
                if(source == selectVehicleTypeJComboBox)
                {
                        setOwnerId();
                        SelectVehicleType();
                }
        }
                
}
