/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: OwnerJPanel.java
Purpose: OwnerJpanel class to act as a GUI for adding and editing owners
Date: 10 August 2020
*/
package assignmnent_2;
// Labelling the package name for the java project

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
// Group layout constants and the entire awt, awt event, and swing libraries are imported to reduce the number of import statements required.


/**
 * The Owner JPanel class collects user input for private and corporate owners, outputs data to array lists, performs data validation, retrieves data from array lists, and allows private and corporate owners to be edited 
 * @author aread
 */
public class OwnerJPanel extends JPanel implements ActionListener, ItemListener
{
        private final int NO_OWNER_SELECTED;
        private static final int CORPORATE_OWNER = 1;
        private static final int PRIVATE_OWNER = 2;
        // Based on the selectOwnerTypeJComboBoxList, constants are declared and static constants are declared and initialised to avoid writing magic numbers for determining which item has been selected from the selectOwnerTypeJComboBox
        
        private final int BUTTON_WIDTH;
        private final int BORDER_GAP;
        private final int FIELD_HORIZONTAL_GAP;
        private final int FIELD_VERTICAL_GAP;
        private final int LARGE_FIELD_VERTICAL_GAP;
        private final int JEXTFIELD_COLUMN_WIDTH;
        // Constants are declared to adjust the gaps between the rows and columns within the set layout method
        
        private int confirmDialogResponse;
        // Declared an instance field to check the user's response to various dialog boxes
        
        private int licenseNumber;
        // Declared owners' license number instance field
        
        public static boolean editMode;
        // Declared an instance field to check whether or not edit mode is activated
        
        private String firstName;
        private String lastName;
        private String address;
        private String phoneNumber;
        // Declared instance fields to store the text contents of swing components
        
        private String [] selectOwnerTypeJComboBoxList;
        // Declared a instance field to store a list of items for the selectOwnerTypeJComboBox
        
        private String [] digits;
        // Declared a instance field to store a list of digit characters to confirm whether or not the first and last names contain digits
        
        private StringBuilder numberFinderStringBuilder;
        // Declared a StringBuilder to store the number of digits found within the first name and last name swing components
        
        private final int MAX_DATE_OF_BIRTH_PANELS;
        private static int currentDateOfBirthJPanel;
        private static DateOfBirthJPanel [] dateOfBirthArray;
        // Declared an object array for the Date Of Birth class
        
        private final int MAX_ABN_PANELS;
        private static int currentABNJPanel;
        private static ABNJPanel [] aBNArray;
        // Declared an object array for the Date Of Birth class
        
        private final String PROGRAM_TITLE;
        // Declared the program title constant as an instance field
        
        private JComponent commonFieldObjectJComponentContainer;
        private StringBuilder commonErrorDialogStringBuilder;
        private JComponent dateOfBirthFieldObjectJComponentContainer;
        private StringBuilder dateOfBirthErrorDialogStringBuilder;
        private JComponent aBNFieldObjectJComponentContainer;
        private StringBuilder aBNErrorDialogStringBuilder;
        // Declared StringBuilders and JComponents to store error messages and references to the relevent swing components which are found to contain errors during data validation. StringBuilders are declared here and their lengths sets to 0 as needed in order to minimise the number of objects being createdas well as to minimise the number of declaration and initialisation statements
        
        private final Font NORMAL_FONT_STYLE;
        private final Font HEADINGTWO_FONT_STYLE;
        // Declared normal and heading fonts for use in swing components
        
        private final Color WHITE_COLOUR;
        // Declared a white colout to change the background colour of JComboBoxes
    
        private JPanel ownerJPanel;
        // Declared the JPanel to contain swing components
        
        private static JLabel ownerJLabel;
        // Declared the owner title label for the GUI 
        
        private JLabel selectOwnerTypeJLabel;
        private static JComboBox<String> selectOwnerTypeJComboBox;
        // Declared the label and JComboBox for selecting between private and corporate owners
        
        private static JLabel enterOwnerDetailsJLabel;
        private JLabel aBNJLabel; 
        private JLabel dateOfBirthJLabel;
        private JLabel firstNameJLabel;
        private JLabel lastNameJLabel;
        private JLabel addressJLabel;
        private JLabel phoneNumberJLabel;
        // Declared JLables for the GUI
 
        private static JTextField firstNameJTextField;
        private static JTextField lastNameJTextField;
        private static JTextArea addressJTextArea;
        private JScrollPane addressScrollPane;
        private static JTextField phoneNumberJTextField;
        // Declared instance fields to contain user input for private and corporate owners
        
        private static JButton ownerJButton;
        private static JButton addModeJButton;
        private JButton clearJButton;
        // Declared the JButtons for the frame
        
        private GroupLayout ownerLayout;
        // Declared the Group Layout for the setLayout method

        /**
         * The default constructor is used to initialise the constants, variables, arrays, StringBuilders, fonts, colours and swing components declared as instance fields
         * Additionally, fonts, action listeners, and layouts are initialised in separate methods to improve the readability of the code
         */
        public OwnerJPanel()
        {
                NO_OWNER_SELECTED = 0;
                BUTTON_WIDTH = 130;
                BORDER_GAP = 12;
                FIELD_HORIZONTAL_GAP = 18;
                FIELD_VERTICAL_GAP = 5;
                LARGE_FIELD_VERTICAL_GAP = 24;
                JEXTFIELD_COLUMN_WIDTH = 40;
                confirmDialogResponse = 0;
                licenseNumber = 0;
                editMode = false;
                
                selectOwnerTypeJComboBoxList = new String [] {"...", "Corporate", "Private"}; 
                digits = new String [] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                MAX_DATE_OF_BIRTH_PANELS = 1;
                currentDateOfBirthJPanel = 0;
                dateOfBirthArray = new DateOfBirthJPanel[MAX_DATE_OF_BIRTH_PANELS];
                MAX_ABN_PANELS = 1;
                currentABNJPanel = 0;
                aBNArray = new ABNJPanel[MAX_ABN_PANELS];                
                numberFinderStringBuilder = new StringBuilder();
                commonErrorDialogStringBuilder = new StringBuilder();
                aBNErrorDialogStringBuilder = new StringBuilder();
                dateOfBirthErrorDialogStringBuilder = new StringBuilder();
                
                PROGRAM_TITLE = "Motor Vehicle Registration Application";
                
                NORMAL_FONT_STYLE = new Font("Arial", 4, 12);
                HEADINGTWO_FONT_STYLE = new Font("Arial", 1, 14);
                
                WHITE_COLOUR = new Color(255,255,255);
                
                ownerJPanel = new JPanel();
                
                ownerJLabel = new JLabel("Add Owner");
                selectOwnerTypeJLabel = new JLabel("Select owner type: ");
                enterOwnerDetailsJLabel = new JLabel("Enter Owner Details");
                aBNJLabel = new JLabel("ABN: ");
                dateOfBirthJLabel = new JLabel("Date of Birth: ");
                dateOfBirthArray[currentDateOfBirthJPanel] = new DateOfBirthJPanel();
                firstNameJLabel = new JLabel("First Name: ");
                lastNameJLabel = new JLabel("Last Name: ");
                addressJLabel = new JLabel("Address: ");
                phoneNumberJLabel = new JLabel("Phone Number: ");
                
                selectOwnerTypeJComboBox = new JComboBox<>(selectOwnerTypeJComboBoxList);
                selectOwnerTypeJComboBox.setBackground(WHITE_COLOUR);
                aBNArray[currentABNJPanel] = new ABNJPanel();
                firstNameJTextField = new JTextField(JEXTFIELD_COLUMN_WIDTH);
                lastNameJTextField = new JTextField(JEXTFIELD_COLUMN_WIDTH);
                addressJTextArea = new JTextArea(null, "", 6, 5);
                addressScrollPane = new JScrollPane(addressJTextArea);
                addressScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                addressScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                phoneNumberJTextField = new JTextField(JEXTFIELD_COLUMN_WIDTH);
                
                ownerJButton = new JButton("Add Owner");
                addModeJButton = new JButton("Add Mode");
                clearJButton = new JButton("Clear");
                
                addModeJButton.setVisible(false);
                
                setFontStyles();
                
                ownerLayout = new GroupLayout(ownerJPanel);

                setLayout();

                setActionListeners();
                
                setEntryAreaInvisible();
        }
        
        
        
        /**
         * Assigns fonts to swing components
         */
        private void setFontStyles()
        {
                ownerJLabel.setFont(HEADINGTWO_FONT_STYLE);
                selectOwnerTypeJLabel.setFont(NORMAL_FONT_STYLE);
                enterOwnerDetailsJLabel.setFont(HEADINGTWO_FONT_STYLE);
                aBNJLabel.setFont(NORMAL_FONT_STYLE);
                dateOfBirthJLabel.setFont(NORMAL_FONT_STYLE);
                firstNameJLabel.setFont(NORMAL_FONT_STYLE);
                lastNameJLabel.setFont(NORMAL_FONT_STYLE);
                addressJLabel.setFont(NORMAL_FONT_STYLE);
                phoneNumberJLabel.setFont(NORMAL_FONT_STYLE);
                selectOwnerTypeJComboBox.setFont(NORMAL_FONT_STYLE);
                firstNameJTextField.setFont(NORMAL_FONT_STYLE);
                lastNameJTextField.setFont(NORMAL_FONT_STYLE);
                addressJTextArea.setFont(NORMAL_FONT_STYLE);
                phoneNumberJTextField.setFont(NORMAL_FONT_STYLE);
                ownerJButton.setFont(NORMAL_FONT_STYLE);
                addModeJButton.setFont(NORMAL_FONT_STYLE);
                clearJButton.setFont(NORMAL_FONT_STYLE);
        }
        
        
        
        /**
         * Assigns an item listener to the selectOwnerTypeJComboBox, and action listeners to JButtons
         */
        private void setActionListeners()
        {
                selectOwnerTypeJComboBox.addItemListener(this);
                ownerJButton.addActionListener(this);
                addModeJButton.addActionListener(this);
                clearJButton.addActionListener(this);
        }

        
        /**
         * Sets the layout of the JPanel
         */
        private void setLayout()
        {
                setLayout(new BorderLayout());
                add(ownerJPanel);
                ownerJPanel.setLayout(ownerLayout);

                ownerLayout.setHorizontalGroup(ownerLayout.createSequentialGroup()
                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP)
                                .addGroup(ownerLayout.createSequentialGroup()
                                .addGroup(ownerLayout.createParallelGroup()
                                        .addComponent(ownerJButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addModeJButton, GroupLayout.Alignment.CENTER, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(clearJButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                .addGroup(ownerLayout.createSequentialGroup()
                                .addGroup(ownerLayout.createParallelGroup()
                                        .addComponent(ownerJLabel)
                                        .addComponent(selectOwnerTypeJLabel)
                                        .addComponent(enterOwnerDetailsJLabel)
                                        .addComponent(aBNJLabel)
                                        .addComponent(dateOfBirthJLabel)
                                        .addComponent(firstNameJLabel)
                                        .addComponent(lastNameJLabel)
                                        .addComponent(addressJLabel)
                                        .addComponent(phoneNumberJLabel))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                                        .addGroup(ownerLayout.createSequentialGroup()
                                        .addGroup(ownerLayout.createParallelGroup()
                                                .addComponent(selectOwnerTypeJComboBox)
                                                .addComponent(aBNArray[currentABNJPanel])
                                                .addComponent(dateOfBirthArray[currentDateOfBirthJPanel])
                                                .addComponent(firstNameJTextField)
                                                .addComponent(lastNameJTextField)
                                                .addComponent(addressScrollPane)
                                                .addComponent(phoneNumberJTextField))))))
                                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP));
                // Creates a merged column spanning the width of the JPanel, and splits the remainder of the JPanel into two columns. Border gaps are added to the left and right to keep swing components away from the edge of the window

                ownerLayout.setVerticalGroup(ownerLayout.createSequentialGroup()
                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP)
                        .addGroup(ownerLayout.createSequentialGroup()
                        .addGroup(ownerLayout.createParallelGroup(BASELINE)
                                .addComponent(ownerJLabel))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(ownerLayout.createSequentialGroup()
                        .addGroup(ownerLayout.createParallelGroup(BASELINE)
                                .addComponent(selectOwnerTypeJLabel)
                                .addComponent(selectOwnerTypeJComboBox))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(ownerLayout.createSequentialGroup()
                        .addGroup(ownerLayout.createParallelGroup(BASELINE)
                                .addComponent(enterOwnerDetailsJLabel))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(ownerLayout.createSequentialGroup()
                        .addGroup(ownerLayout.createParallelGroup(BASELINE)
                                .addComponent(aBNJLabel)
                                .addComponent(aBNArray[currentABNJPanel]))
                        .addGroup(ownerLayout.createSequentialGroup()
                        .addGroup(ownerLayout.createParallelGroup(BASELINE)
                                .addComponent(dateOfBirthJLabel, GroupLayout.Alignment.CENTER)
                                .addComponent(dateOfBirthArray[currentDateOfBirthJPanel]))
                        .addGroup(ownerLayout.createSequentialGroup()
                        .addGroup(ownerLayout.createParallelGroup(BASELINE)
                                .addComponent(firstNameJLabel)
                                .addComponent(firstNameJTextField))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)
                        .addGroup(ownerLayout.createSequentialGroup()
                        .addGroup(ownerLayout.createParallelGroup(BASELINE)
                                .addComponent(lastNameJLabel)
                                .addComponent(lastNameJTextField))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)
                        .addGroup(ownerLayout.createSequentialGroup()
                        .addGroup(ownerLayout.createParallelGroup(BASELINE)
                                .addComponent(addressJLabel)
                                .addComponent(addressScrollPane))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)
                        .addGroup(ownerLayout.createSequentialGroup()
                        .addGroup(ownerLayout.createParallelGroup(BASELINE)
                                .addComponent(phoneNumberJLabel)
                                .addComponent(phoneNumberJTextField))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(ownerLayout.createSequentialGroup()
                        .addGroup(ownerLayout.createParallelGroup(BASELINE)
                                .addComponent(ownerJButton)
                                .addComponent(addModeJButton)
                                .addComponent(clearJButton))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)))))))))))
                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP));
                // The JPanel is split into 10 rows. The dateOfBirthJLabel is vertically aligned to the centre to align the label with the DateOfBirthJPanel. Additionally, a border gap is added to the top and bottom to keep swing components away from the edge of the window
        }
        
        
        
        /**
         * Sets common fields visible when a private and corporate owner type is selected. Additionally, the add mode button is made visible if edit mode is currently enabled
         */
        private void setCommonFieldsVisible()
        {
                enterOwnerDetailsJLabel.setVisible(true);
                firstNameJLabel.setVisible(true);
                firstNameJTextField.setVisible(true);
                lastNameJLabel.setVisible(true);
                lastNameJTextField.setVisible(true);
                addressJLabel.setVisible(true);
                addressScrollPane.setVisible(true);
                phoneNumberJLabel.setVisible(true);
                phoneNumberJTextField.setVisible(true);
                ownerJButton.setVisible(true);
                clearJButton.setVisible(true);   
                if(getEditMode() == true)
                        addModeJButton.setVisible(true);
        }
                
               
        
        /**
         * Sets common fields invisible when a private and corporate owner type has not been selected
         */
        private void setCommonFieldsInvisible()
        {
                enterOwnerDetailsJLabel.setVisible(false);
                firstNameJLabel.setVisible(false);
                firstNameJTextField.setVisible(false);
                lastNameJLabel.setVisible(false);
                lastNameJTextField.setVisible(false);
                addressJLabel.setVisible(false);
                addressScrollPane.setVisible(false);
                phoneNumberJLabel.setVisible(false);
                phoneNumberJTextField.setVisible(false);
                ownerJButton.setVisible(false);
                clearJButton.setVisible(false);   
                addModeJButton.setVisible(false);
        }
        
        
        
        /**
         * Sets ABN fields invisible when a corporate owner type has not been selected
         */
        private void setCorporateOwnerFieldsInvisible()
        {
                aBNJLabel.setVisible(false);
                aBNArray[currentABNJPanel].setVisible(false);
        }
        
        
        
        /**
         * Sets date of birth fields invisible when a private owner type has not been selected
         */
        private void setPrivateOwnerFieldsInvisible()
        {
                dateOfBirthJLabel.setVisible(false);
                dateOfBirthArray[currentDateOfBirthJPanel].setVisible(false); 
        }
        
        
        
        /**
         * Sets all relevant swing components invisible when a private and corporate owner type has not been selected
         */
        private void setEntryAreaInvisible()
        {
                setCommonFieldsInvisible(); 
                setCorporateOwnerFieldsInvisible();
                setPrivateOwnerFieldsInvisible();
        }
        
        
        
        /**
         * Receives input from the itemStateChanged method to set various swing components visible or invisible based on whether a corporate, private, or no owner has been selected using the selectOwnerTypeJComboBox
         */
        private void SelectOwnerType()
        {
                if(selectOwnerTypeJComboBox.getSelectedIndex() == NO_OWNER_SELECTED)
                {
                        setEntryAreaInvisible();           
                }

                if(selectOwnerTypeJComboBox.getSelectedIndex() == CORPORATE_OWNER)
                {
                        setCommonFieldsVisible(); 
                        aBNJLabel.setVisible(true);
                        aBNArray[currentABNJPanel].setVisible(true);
                        setPrivateOwnerFieldsInvisible();
                }
                                
                if(selectOwnerTypeJComboBox.getSelectedIndex() == PRIVATE_OWNER)
                {
                        setCommonFieldsVisible(); 
                        setCorporateOwnerFieldsInvisible();
                        dateOfBirthJLabel.setVisible(true);
                        dateOfBirthArray[currentDateOfBirthJPanel].setVisible(true);
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
         * Generates an error message dialog and highlights the first date of birth field swing component found to contain an error during data validation
         */ 
        private void generateDateOfBirthFieldDataValidationErrorDialog()
        {
                generateDataValidationErrorDialog(dateOfBirthFieldObjectJComponentContainer, dateOfBirthErrorDialogStringBuilder.toString()); 
        }
        
        
        /**
         * Generates an error message dialog and highlights the first ABN field swing component found to contain an error during data validation
         */ 
        private void generateABNFieldDataValidationErrorDialog()
        {
                generateDataValidationErrorDialog(aBNFieldObjectJComponentContainer, aBNErrorDialogStringBuilder.toString()); 
        }
        
        
        /**
         * Generates an error message dialog and highlights the first common field swing component found to contain an error during data validation
         */ 
        private void generateCommonFieldDataValidationErrorDialog()
        {
                generateDataValidationErrorDialog(commonFieldObjectJComponentContainer, commonErrorDialogStringBuilder.toString()); 
        }
        
        

        /**
         * @return returns a StringBuilder containing the number of digits found in the first and last name swing components
         */ 
        private StringBuilder findNumbersInText(JTextField fieldObject)
        {
                numberFinderStringBuilder.setLength(0);

                for(int numberIndex = 0; numberIndex < digits.length; numberIndex++)
                {
                        if(fieldObject.getText().contains(digits[numberIndex]))
                        {
                                numberFinderStringBuilder.append(digits[numberIndex]);     
                        }
                        
                }
                
                return numberFinderStringBuilder;
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
         * Performs data validation checks for common owner fields and stores the first swing component and related error message text found to contain an error during data validation
         */ 
        private void validateCommonOwnerFields()
        {
                
                commonFieldObjectJComponentContainer = null;
                commonErrorDialogStringBuilder.setLength(0);
                if(firstNameJTextField.getText().compareTo("") == 0)
                {
                        addCommonFieldObjectToJComponentContainer(firstNameJTextField);
                        addCommonErrorDialogToStringBuilder("You must enter a first name");
                }
                
                else if(findNumbersInText(firstNameJTextField).length() > 0)
                {
                        addCommonFieldObjectToJComponentContainer(firstNameJTextField);
                        addCommonErrorDialogToStringBuilder("The first name must not contain a number");
                }
                
                else if(lastNameJTextField.getText().compareTo("") == 0)
                {
                        addCommonFieldObjectToJComponentContainer(lastNameJTextField);
                        addCommonErrorDialogToStringBuilder("You must enter a last name");
                }
                                    
                else if(findNumbersInText(lastNameJTextField).length() > 0)
                {
                        addCommonFieldObjectToJComponentContainer(lastNameJTextField);
                        addCommonErrorDialogToStringBuilder("The last name must not contain a number");
                }
                
                else if(addressJTextArea.getText().compareTo("") == 0)
                {
                        addCommonFieldObjectToJComponentContainer(addressJTextArea);
                        addCommonErrorDialogToStringBuilder("You must enter an address");
                }

                else if(phoneNumberJTextField.getText().compareTo("") == 0)
                {
                        addCommonFieldObjectToJComponentContainer(phoneNumberJTextField);
                        addCommonErrorDialogToStringBuilder("You must enter a phone number");
                }

        }
        
        

        /**
         * Accesses the first erroneously entered swing component found in data validation
         */ 
        private JComponent getDateOfBirthFieldObjectJComponentContainer()
        {
                return dateOfBirthFieldObjectJComponentContainer;
        }
     
        
        
        /**
         * Data validation JComponent container
         * @return fills a JComponent container with the first swing component found to contain an error during data validation
         */ 
        private JComponent addDateOfBirthFieldObjectToJComponentContainer(JComponent fieldObject)
        {
                if(dateOfBirthFieldObjectJComponentContainer == null)
                {
                        dateOfBirthFieldObjectJComponentContainer = fieldObject;
                }
                
                return dateOfBirthFieldObjectJComponentContainer;
        }
        
        

        /**
         * Accesses the text of the first erroneously entered swing component found in data validation
         */ 
        private StringBuilder getDateOfBirthErrorDialogStringBuilder()
        {
                return dateOfBirthErrorDialogStringBuilder;
        }
        
        
        
        /**
         * Data validation StringBuilder
         * @return fills a StringBuider with the error message text related to the first swing component found to contain an error during data validation
         */
        private StringBuilder addDateOfBirthErrorDialogToStringBuilder(String errorDialog)
        {
                if(dateOfBirthErrorDialogStringBuilder.length() == 0)
                {
                        dateOfBirthErrorDialogStringBuilder.append(errorDialog);
                }
                
                return dateOfBirthErrorDialogStringBuilder;
        }

        
        
        /**
         * Performs data validation checks for date of birth fields and stores the first swing component and related error message text found to contain an error during data validation
         */
        private void validateDateOfBirthField()
        {
                dateOfBirthFieldObjectJComponentContainer = null;
                dateOfBirthErrorDialogStringBuilder.setLength(0);
                
                if(dateOfBirthArray[currentDateOfBirthJPanel].dateOfBirthDayIndexSelected() == 0)
                {
                        addDateOfBirthFieldObjectToJComponentContainer(dateOfBirthArray[currentDateOfBirthJPanel].getDateOfBirthDayJComboBox());
                        addDateOfBirthErrorDialogToStringBuilder("You must enter a day of birth");
                }
            
                else if(dateOfBirthArray[currentDateOfBirthJPanel].dateOfBirthMonthIndexSelected() == 0)
                {
                        addDateOfBirthFieldObjectToJComponentContainer(dateOfBirthArray[currentDateOfBirthJPanel].getDateOfBirthMonthJComboBox());
                        addDateOfBirthErrorDialogToStringBuilder("You must enter a month of birth");
                }
                
                if(dateOfBirthArray[currentDateOfBirthJPanel].dateOfBirthYearIndexSelected() == 0)
                {
                        addDateOfBirthFieldObjectToJComponentContainer(dateOfBirthArray[currentDateOfBirthJPanel].getDateOfBirthYearJComboBox());
                        addDateOfBirthErrorDialogToStringBuilder("You must enter a year of birth");
                }
        }
        
        
        
        /**
         * Accesses the first erroneously entered swing component found in data validation
         */ 
        private JComponent getABNFieldObjectJComponentContainer()
        {
                return aBNFieldObjectJComponentContainer;
        }
        
        
        
        /**
         * Data validation JComponent container
         * @return fills a JComponent container with the first swing component found to contain an error during data validation
         */ 
        private JComponent addABNFieldObjectToJComponentContainer(JComponent fieldObject)
        {
                if(aBNFieldObjectJComponentContainer == null)
                {
                        aBNFieldObjectJComponentContainer = fieldObject;
                }
                
                return aBNFieldObjectJComponentContainer;
        }
        
        

        /**
         * Accesses the text of the first erroneously entered swing component found in data validation
         */ 
        private StringBuilder getABNErrorDialogStringBuilder()
        {
                return aBNErrorDialogStringBuilder;
        }
        
        
        
        /**
         * Data validation StringBuilder
         * @return fills a StringBuider with the error message text related to the first swing component found to contain an error during data validation
         */
        private StringBuilder addABNErrorDialogToStringBuilder(String errorDialog)
        {
                if(aBNErrorDialogStringBuilder.length() == 0)
                {
                        aBNErrorDialogStringBuilder.append(errorDialog);
                }
                
                return aBNErrorDialogStringBuilder;
        }

        
        
        /**
         * Performs data validation checks for ABN fields and stores the first swing component and related error message text found to contain an error during data validation
         */
        private void validateABNField()
        {
                aBNFieldObjectJComponentContainer = null;
                aBNErrorDialogStringBuilder.setLength(0);
                int aBNInteger;
                aBNInteger = 0;
                
                if(aBNArray[currentABNJPanel].getFirstABNJTextFieldText().length() != 2)
                {
                        addABNFieldObjectToJComponentContainer(aBNArray[currentABNJPanel].getFirstABNJTextField());
                        addABNErrorDialogToStringBuilder("The first ABN box should contain two digits");
                }
            
                try
                {
                        aBNInteger = Integer.parseInt(aBNArray[currentABNJPanel].getFirstABNJTextFieldText());
                }
                
                catch(NumberFormatException e)
                {
                        addABNFieldObjectToJComponentContainer(aBNArray[currentABNJPanel].getFirstABNJTextField());
                        addABNErrorDialogToStringBuilder("The first ABN box should contain two digits");
                }
                
                if(aBNArray[currentABNJPanel].getSecondABNJTextFieldText().length() != 3)
                {
                        addABNFieldObjectToJComponentContainer(aBNArray[currentABNJPanel].getSecondABNJTextField());
                        addABNErrorDialogToStringBuilder("The second ABN box should contain three digits");
                }
            
                try
                {
                        aBNInteger = Integer.parseInt(aBNArray[currentABNJPanel].getSecondABNJTextFieldText());
                }
                
                catch(NumberFormatException e)
                {
                        addABNFieldObjectToJComponentContainer(aBNArray[currentABNJPanel].getSecondABNJTextField());
                        addABNErrorDialogToStringBuilder("The second ABN box should contain three digits");
                }
                
                if(aBNArray[currentABNJPanel].getThirdABNJTextFieldText().length() != 3)
                {
                        addABNFieldObjectToJComponentContainer(aBNArray[currentABNJPanel].getThirdABNJTextField());
                        addABNErrorDialogToStringBuilder("The third ABN box should contain three digits");
                }
            
                try
                {
                        aBNInteger = Integer.parseInt(aBNArray[currentABNJPanel].getThirdABNJTextFieldText());
                }
                
                catch(NumberFormatException e)
                {
                        addABNFieldObjectToJComponentContainer(aBNArray[currentABNJPanel].getThirdABNJTextField());
                        addABNErrorDialogToStringBuilder("The third ABN box should contain three digits");
                }
                
                if(aBNArray[currentABNJPanel].getFourthABNJTextFieldText().length() != 3)
                {
                        addABNFieldObjectToJComponentContainer(aBNArray[currentABNJPanel].getFourthABNJTextField());
                        addABNErrorDialogToStringBuilder("The fourth ABN box should contain three digits");
                }
            
                try
                {
                        aBNInteger = Integer.parseInt(aBNArray[currentABNJPanel].getFourthABNJTextFieldText());
                }
                
                catch(NumberFormatException e)
                {
                        addABNFieldObjectToJComponentContainer(aBNArray[currentABNJPanel].getFourthABNJTextField());
                        addABNErrorDialogToStringBuilder("The fourth ABN box should contain three digits");
                }
        }
        
        
        
        /**
         * Clears the contents of the selectOwnerTypeJComboBox
         */
        public static void resetJComboBox()
        {
                selectOwnerTypeJComboBox.setSelectedIndex(0);
        }
        
        
        
        /**
         * Clears the contents of JTextFields and JComboBoxes
         */
        private static void clear()
        {
                resetJComboBox();
                aBNArray[currentABNJPanel].resetABNJTextFields();
                dateOfBirthArray[currentDateOfBirthJPanel].resetDateOfBirthJComboBoxes();
                firstNameJTextField.setText("");
                lastNameJTextField.setText("");
                addressJTextArea.setText("");
                phoneNumberJTextField.setText("");
        }
        
        
        
        /**
         * Clears the contents of JTextFields and JComboBoxes in the OwnerJPanel and also clears JComboBoxes in the VehicleJPanel class and the SearchJPanel class
         */
        private static void resetGUI()
        {
                clear();
                MotorVehicleRegistrationFrame.resetJComboBoxes();
        }
        
        
        
        /**
         * Clears the contents of JTextFields and JComboBoxes in the OwnerJPanel, clears JComboBoxes in the VehicleJPanel and the SearchJPanel, appends new owner details to the searchJPanel class, and switches the focus to the SearchJPanel
         */
        private static void resetGUIAndShowNewOwnerHeading()
        {
                SearchJPanel.newOwner();
                resetGUI();
        }
        
        
        
        /**
         * Clears the contents of JTextFields and JComboBoxes in the OwnerJPanel, clears JComboBoxes in the VehicleJPanel and the SearchJPanel, appends edited owner details to the searchJPanel class, and switches the focus to the SearchJPanel
         */
        private static void resetGUIAndShowEditOwnerHeading()
        {
                SearchJPanel.editOwner();
                resetGUI();
        }

        
        
        /**
         * Retrieves the license number from the license number field, stores the license number in a variable ready for output into the addPrivateOwner and addCorporateOwner methods, and increments the license number field to prevent a duplication of license numbers
         * @return returns the license number ready for output to the addPrivateOwner and addCorporateOwner methods
         */
        private int generateNewLicenseNumber()
        {
                    int license = licenseNumber;
                    licenseNumber++;
                    return license;
        }
            

        /**
         * Retrieves the day, month, and year entered into the date of birth fields accessible through the dateOfBirthArray array
         * @return Concatenates the retrieved day, month, and year, ready for output to the addPrivateOwner and acceptChangesPrivateOwner  methods
         */
        private String calculateDateOfBirth()
        {
                String day;
                String month;
                String year;
                
                day = dateOfBirthArray[currentDateOfBirthJPanel].getDay();
                month = dateOfBirthArray[currentDateOfBirthJPanel].getMonth();
                year = dateOfBirthArray[currentDateOfBirthJPanel].getYear();
                
                return day + "/" + month + "/" + year;
        }
        
        
        /**
         * Retrieves the contents of the ABN boxes accessible through the aBNArray array and adds fixed spacing between characters. This simplifies data validation and user input, with users not needing to add add spaces between characters, and for data validation processes not needing to check for differentiating numbers of spaces between characters 
         * @return Concatenates the text contained in the ABN boxes to form an ABN, ready for output to the addCorporateOwner and acceptChangesCorporateOwner methods
         * Conveniently, retrieving the ABN number from a series of JTextFields in the ABNJPanel provides users a neatly spaced tends to simplify data validation and user input, with users not needing to add add spaces between characters, and for data validation processes to check for varying numbers of spaces 
         */
        private String calculateABN()
        {
                String aBNText;
                
                aBNText = aBNArray[currentABNJPanel].getFirstABNJTextFieldText() + " " + aBNArray[currentABNJPanel].getSecondABNJTextFieldText() + " " + aBNArray[currentABNJPanel].getThirdABNJTextFieldText() + " " + aBNArray[currentABNJPanel].getFourthABNJTextFieldText();
                
                return aBNText;
        }

         
        /**
         * Retrieves the first name from a JTextField
         * @return outputs the retrieved first name to the addPrivateOwner, AddCorporateOwner, acceptChangesPrivateOwner, and the acceptChangesCorporateOwner methods
         */
        private String getFirstName()
        {
                firstName = firstNameJTextField.getText();
                return firstName;
        }   
        
        
        
        /**
         * Sets the first name JTextField to the first name stored in the ownerSearchReferenceIndex index in the ownerArray
         */
        private static void setFirstName()
        {
                firstNameJTextField.setText(MotorVehicleRegistrationFrame.ownerArray.get(SearchJPanel.getOwnerSearchReferenceIndex()).getFirstName());
        } 


        
        /**
         * Retrieves the last name from a JTextField
         * @return outputs the retrieved last name to the addPrivateOwner, AddCorporateOwner, acceptChangesPrivateOwner, and the acceptChangesCorporateOwner methods
         */
        private String getLastName()
        {
                lastName = lastNameJTextField.getText();
                return lastName;
        }  
        
        
        
        /**
         * Sets the last name JTextField to the last name stored in the ownerSearchReferenceIndex index in the ownerArray
         */
        private static void setLastName()
        {
                lastNameJTextField.setText(MotorVehicleRegistrationFrame.ownerArray.get(SearchJPanel.getOwnerSearchReferenceIndex()).getLastName());
        } 


        
        /**
         * Retrieves the address from a JTextArea
         * @return outputs the retrieved address to the addPrivateOwner, AddCorporateOwner, acceptChangesPrivateOwner, and the acceptChangesCorporateOwner methods
         */
        private String getAddress()
        {
                address = addressJTextArea.getText();
                return address;
        }     
        
        
        
        /**
         * Sets the address JTextArea to the address stored in the ownerSearchReferenceIndex index in the ownerArray
         */
        private static void setAddress()
        {
                addressJTextArea.setText(MotorVehicleRegistrationFrame.ownerArray.get(SearchJPanel.getOwnerSearchReferenceIndex()).getAddress());
        } 
        

        /**
         * Retrieves the phone number from a JTextField
         * @return outputs the retrieved phone number to the addPrivateOwner, AddCorporateOwner, acceptChangesPrivateOwner, and the acceptChangesCorporateOwner methods
         */
        private String getPhoneNumber()
        {
                phoneNumber = phoneNumberJTextField.getText();
                return phoneNumber;
        }  
        
        
        
        /**
         * Sets the address JTextArea to the address stored in the ownerSearchReferenceIndex index in the ownerArray
         */
        private static void setPhoneNumber()
        {
                phoneNumberJTextField.setText(MotorVehicleRegistrationFrame.ownerArray.get(SearchJPanel.getOwnerSearchReferenceIndex()).getPhoneNumber());
        } 
        

        /**
         * Utilises modularity to validate date of birth fields and common fields, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, private owner details are entered into an array list. Next, JTextFields are set to zero length strings, and JComboBoxes are set to the first index "...". The tab is then switched to the SearchJpanel which displays the newly added owner details.
         * This helps to avoid needing to create a JTextArea to display newly added details in the OwnerJPanel, all the while providing users a visual clue that data was successfully entered.
         */
        private void addPrivateOwner()
	{   
                validateCommonOwnerFields();
                validateDateOfBirthField();

                if(getDateOfBirthFieldObjectJComponentContainer() != null && getDateOfBirthErrorDialogStringBuilder().length() > 0)
                        generateDateOfBirthFieldDataValidationErrorDialog();
                
                else if(getCommonFieldObjectJComponentContainer() != null && getCommonErrorDialogStringBuilder().length() > 0)
                        generateCommonFieldDataValidationErrorDialog();

                else
                {
                        MotorVehicleRegistrationFrame.ownerArray.add( new PrivateOwner (generateNewLicenseNumber(), calculateDateOfBirth(), getFirstName(), getLastName(), getAddress(), getPhoneNumber()));
                        resetGUIAndShowNewOwnerHeading();
                }

	}
        
        
        
        /**
         * Utilises modularity to validate ABN fields and common fields, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, corporate owner details are entered into an array list. Next, JTextFields are set to zero length strings, and JComboBoxes are set to the first index "...". The tab is then switched to the SearchJpanel which displays the newly added owner details.
         * This helps to avoid needing to create a JTextArea to display newly added details in the OwnerJPanel, all the while providing users a visual clue that data was successfully entered.
         */
        private void addCorporateOwner()
	{   
                validateABNField();
                validateCommonOwnerFields();
                
                if(getABNFieldObjectJComponentContainer() != null && getABNErrorDialogStringBuilder().length() > 0)
                        generateABNFieldDataValidationErrorDialog();

                else if(getCommonFieldObjectJComponentContainer() != null && getCommonErrorDialogStringBuilder().length() > 0)
                        generateCommonFieldDataValidationErrorDialog();

                else
                {
                        MotorVehicleRegistrationFrame.ownerArray.add( new CorporateOwner (generateNewLicenseNumber(), calculateABN(), getFirstName(), getLastName(), getAddress(), getPhoneNumber()));
                        resetGUIAndShowNewOwnerHeading();
                }

	}
        
        
        
        /**
         * Sets common text fields and text areas to the values stored in the ownerSearchReferenceIndex index in the ownerArray
         */
        private static void setCommonFields()
        {
                OwnerJPanel.setFirstName();
                OwnerJPanel.setLastName();
                OwnerJPanel.setAddress();
                OwnerJPanel.setPhoneNumber();
        }
        
        
        
        /**
         * Sets ABN fields to the values stored in the ownerSearchReferenceIndex index in the ownerArray, and sets the selectOwnerTypeJComboBox to select corporate owner from the JComboBox
         */
        private static void setCorporateOwnerFields()
        {
                ABNJPanel.setABN();
                selectOwnerTypeJComboBox.setSelectedIndex(CORPORATE_OWNER);
        }
        
        
        
        /**
         * Sets date of birth fields to the values stored in the ownerSearchReferenceIndex index in the ownerArray, and sets the selectOwnerTypeJComboBox to select private owner from the JComboBox
         */
        private static void setPrivateOwnerFields()
        {
                DateOfBirthJPanel.setDateOfBirth();
                selectOwnerTypeJComboBox.setSelectedIndex(PRIVATE_OWNER);
        }
        
        
        
        /**
         * Determines whether the ownerSearchReferenceIndex index in the ownerArray refers to a corporate or private owner, and then sets the corresponding fields
         */
        private static void setFieldsBasedOnOwnerObjctType()
        {
                Owner objectTypeValue;
                objectTypeValue = MotorVehicleRegistrationFrame.ownerArray.get(SearchJPanel.getOwnerSearchReferenceIndex());
                
                setCommonFields();
                
                if(objectTypeValue instanceof CorporateOwner)
                        setCorporateOwnerFields(); 
                else
                        setPrivateOwnerFields();
        }
        
        
        
        /**
         * Sets the tab to edit mode by setting the editMode variable to true, relabelling relevant JLabels, changing the tab name, and setting fields and text areas to the values stored in the ownerSearchReferenceIndex index in the ownerArray. The add mode button is also added to transition back to add mode
         */
        public static void editMode()
        {
                ownerJLabel.setText("Edit Owner");
                enterOwnerDetailsJLabel.setText("Edit Owner Details");
                ownerJButton.setText("Accept Changes");
                addModeJButton.setVisible(true);
                editMode = true;
                MotorVehicleRegistrationFrame.showEditOwnerTab();
                setFieldsBasedOnOwnerObjctType();
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
         * Lastly, links to the ownerSearchReferenceIndex index of the array list are severed to prevent the user from re-editing or deleting the current index, without performing another search.
         */
        private static void addMode()
        {
                ownerJLabel.setText("Add Owner");
                enterOwnerDetailsJLabel.setText("Enter Owner Details");
                ownerJButton.setText("Add Owner");
                addModeJButton.setVisible(false);
                editMode = false;
                clear();
                MotorVehicleRegistrationFrame.showAddOwnerTab();
                MotorVehicleRegistrationFrame.showSearchTab();
                SearchJPanel.resetSearchAndReferenceIndexes();
        }
        
        
        
        /**
         * Gets the current license number value from the getownerSearchReferenceIndex index in the ownerArray to prevent the license number from being incremented during editing
         */
        private int getLicenseNumber()
        {
                return MotorVehicleRegistrationFrame.ownerArray.get(SearchJPanel.getOwnerSearchReferenceIndex()).getId();
        }
        
        
        /**
         * Utilises modularity to validate date of birth fields and common fields, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, private owner details are entered into an array list, overwriting the ownerSearchReferenceIndex index. Next, JTextFields are set to zero length strings, and JComboBoxes are set to the first index "...". The tab is then switched to the SearchJpanel which displays the newly edited owner details.
         * Lastly, the OwnerJpanel is switched to add mode, to allow the entry of new owners into the ownerArray.
         */
        private void acceptChangesPrivateOwner()
	{ 
                validateDateOfBirthField();
                validateCommonOwnerFields();
                        
                if(getDateOfBirthFieldObjectJComponentContainer() != null && getDateOfBirthErrorDialogStringBuilder().length() > 0)
                        generateDateOfBirthFieldDataValidationErrorDialog();
                
                else if(getCommonFieldObjectJComponentContainer() != null && getCommonErrorDialogStringBuilder().length() > 0)
                        generateCommonFieldDataValidationErrorDialog();
                
                else
                {
                        MotorVehicleRegistrationFrame.ownerArray.set(SearchJPanel.getOwnerSearchReferenceIndex(), new PrivateOwner (getLicenseNumber(), calculateDateOfBirth(), getFirstName(), getLastName(), getAddress(), getPhoneNumber()));
                        resetGUIAndShowEditOwnerHeading();
                        addMode();
                }

	}
        
        
        
        /**
         * Utilises modularity to validate ABN fields and common fields, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, corporate owner details are entered into an array list, overwriting the getownerSearchReferenceIndex index. Next, JTextFields are set to zero length strings, and JComboBoxes are set to the first index "...". The tab is then switched to the SearchJpanel which displays the newly edited owner details.
         * Lastly, the OwnerJpanel is switched to add mode, to allow the entry of new owners into the ownerArray.
         */
        private void acceptChangesCorporateOwner()
	{ 
                validateABNField();
                validateCommonOwnerFields();
                        
                if(getABNFieldObjectJComponentContainer() != null && getABNErrorDialogStringBuilder().length() > 0)
                        generateABNFieldDataValidationErrorDialog();
                
                else if(getCommonFieldObjectJComponentContainer() != null && getCommonErrorDialogStringBuilder().length() > 0)
                        generateCommonFieldDataValidationErrorDialog();
                
                else
                {
                        MotorVehicleRegistrationFrame.ownerArray.set(SearchJPanel.getOwnerSearchReferenceIndex(), new CorporateOwner (getLicenseNumber(), calculateABN(), getFirstName(), getLastName(), getAddress(), getPhoneNumber()));
                        resetGUIAndShowEditOwnerHeading();
                        addMode();
                }

	}
        
        
        
        /**
         * This event connects to the selectOwnerTypeJComboBox which determines which fields and text areas within the panel to be made visible and not visible. 
         */
        @Override
        public void itemStateChanged(ItemEvent e)
	{
		Object source = e.getSource();
                
                if(source == selectOwnerTypeJComboBox)
                        SelectOwnerType();
        }
        
        
        
        /**
         * getActionCommand is used to provide modularity to the owner button, allowing the function of the button to be changed by relabelling the JButton in the addMode and editMode methods rather than having two separate JButtons for adding and editing owner records.
         * Next, the user's selection from the selectOwnerTypeJComboBox enables the application to determine which type of owner to add or edit.
         * The add mode button, switches the JPanel to edit mode. However, with the risk of severing the connection with the ownerSearchReferenceIndex index of the ownerAray, a confirmation dialog box is generated to ask whether or not the user wants this to occur.
         * Lastly, the clear button replaces fields and text areas in the OwnerJpanel with zero length strings, and sets the selectOwnerTypeJComboBox to index 0, hiding most cells.
         */  
        @Override
        public void actionPerformed(ActionEvent e)
        {
                String command = e.getActionCommand();
                Object source = e.getSource();

                if(command.equals("Add Owner"))
                {
                        if(selectOwnerTypeJComboBox.getSelectedIndex() == CORPORATE_OWNER)
                        {
                                addCorporateOwner();
                        }
                   
                        else if(selectOwnerTypeJComboBox.getSelectedIndex() == PRIVATE_OWNER)
                        {
                                addPrivateOwner();
                        }
                } 
                
                if(command.equals("Accept Changes"))
                {
                        if(selectOwnerTypeJComboBox.getSelectedIndex() == CORPORATE_OWNER)
                        {
                                acceptChangesCorporateOwner();
                        }
                    
                        else if(selectOwnerTypeJComboBox.getSelectedIndex() == PRIVATE_OWNER)
                        {
                                acceptChangesPrivateOwner();
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
                
}
