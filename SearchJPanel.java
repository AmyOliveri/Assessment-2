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
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
// Group layout constants and the entire awt, awt event, and swing libraries are imported to reduce the number of import statements required.

/**
 * The SearchJPanel class allows users to list all owners and vehicles, view information for specific owners and vehicles, and also facilitates edit and delete functionality collects user input for motorcycles, light and heavy vehicles, outputs data to array lists, performs data validation, retrieves data from array lists, and allows motorcycles, light and heavy vehicles to be edited 
 * @author aread
 */
public class SearchJPanel extends JPanel implements ActionListener, ItemListener
{
    
        private static final int OWNER = 1;
        private static final int VEHICLE = 2;
        // Based on the searchList, static constants are declared and initialised to avoid writing magic numbers for determining which item has been selected from the searchCategoryJComboBox     
    
        private final int BUTTON_WIDTH;
        private final int BORDER_GAP;
        private final int FIELD_HORIZONTAL_GAP;
        private final int FIELD_VERTICAL_GAP;
        private final int LARGE_FIELD_VERTICAL_GAP;
        // Constants are declared to adjust the gaps between the rows and columns within the set layout method
        
        private int confirmDialogResponse;
        // Declared an instance field to check the user's response to various dialog boxes
        
        private int ownerArraySize;
        private int vehicleArraySize;
        // Declared the ownerArraySize and vehicleArraySize integers as instance fields to minimise the duplication of delcaration statements throughout the class
        
        private static int ownerInstancesFoundInSearch;
        private static int vehicleInstancesFoundInSearch;
        // Declared instance fields to calculate and store the number of instances found in each search, ensuring that edit and dlete functions can only take place if one instance is selected
        
        public static int ownerSearchReferenceIndex;
        public static int vehicleSearchReferenceIndex;
        // Declared instance fields to store the index selected in owner and vehicle searches
        
        private JComponent searchErrorObjectJComponentContainer;
        private JComponent editAndDeleteErrorJComponentContainer;
        private StringBuilder searchErrorDialogStringBuilder;
        private StringBuilder editAndDeleteErrorDialogStringBuilder;
        // Declared StringBuilders and JComponents to store error messages and references to the relevent swing components which are found to contain errors during data validation. StringBuilders are declared here and their lengths sets to 0 as needed in order to minimise the number of objects being created as well as to minimise the number of declaration and initialisation statements
        
        private final String PROGRAM_TITLE;
        // Declared the program title constant as an instance field
        
        private final Font NORMAL_FONT_STYLE;
        private final Font HEADINGTWO_FONT_STYLE;
        // Declared normal and heading fonts for use in swing components
        
        private final Color WHITE_COLOUR;
        // Declared a white colout to change the background colour of JComboBoxes
        
        private JPanel searchJPanel;
        // Declared the JPanel to contain swing components
        
        private JLabel searchJLabel;
        private JLabel searchCategoryNameJLabel;
        private JLabel searchTextJLabel;
        // Declared JLables for the GUI
        
        private String[] searchList;  
        // Declared a string array to contain a list of search options
        
        private static JComboBox<String> searchCategoryJComboBox;
        private static JComboBox<String> searchTextJComboBox;
        // Declared JComboBoxes to select search categories and to select values
 
        private JButton listAllJButton; 
        private JButton searchJButton; 
        private JButton searchClearJButton; 
        private JButton editJButton;
        private JButton deleteJButton;
        // Declared JButtons for the GUI
        
        private static JTextArea searchJTextArea;
        private JScrollPane searchJScrollPane;
        // Declared a JTextArea accompanied with a JSCrollBar to faciliate a scrollable display of added, edited, and searched owners and vehicles
        
        private GroupLayout searchLayout;
        // Declared the Group Layout for the setLayout method
       
        
        
        /**
         * The default constructor is used to initialise the constants, variables, arrays, StringBuilders, fonts, colours and swing components declared as instance fields
         * Additionally, fonts, action listeners, and layouts are initialised in separate methods to improve the readability of the code
         */
        public SearchJPanel()
        {
                BUTTON_WIDTH = 130;
                BORDER_GAP = 12;
                FIELD_HORIZONTAL_GAP = 170;
                FIELD_VERTICAL_GAP = 5;
                LARGE_FIELD_VERTICAL_GAP = 24;
                ownerArraySize = MotorVehicleRegistrationFrame.ownerArray.size();
                vehicleArraySize = MotorVehicleRegistrationFrame.vehicleArray.size();
                confirmDialogResponse = 0;
                ownerInstancesFoundInSearch = 0;
                vehicleInstancesFoundInSearch = 0;
                ownerSearchReferenceIndex = 0;
                vehicleSearchReferenceIndex = 0;
                
                searchErrorDialogStringBuilder = new StringBuilder();
                editAndDeleteErrorDialogStringBuilder = new StringBuilder();
                
                PROGRAM_TITLE = "Motor Vehicle Registration Application";
        
                NORMAL_FONT_STYLE = new Font("Arial", 4, 12);
                HEADINGTWO_FONT_STYLE = new Font("Arial", 1, 14);
        
                WHITE_COLOUR = new Color(255,255,255);
        
                searchJPanel = new JPanel();
        
                searchJLabel = new JLabel("Search Owners and Vehicles");
                searchCategoryNameJLabel = new JLabel("Search for: ");
                searchTextJLabel = new JLabel("Select a record to search: ");
        
                searchList = new String [] {"...", "Owner by License Number", "Vehicle by Number Plate"}; 
                searchCategoryJComboBox = new JComboBox<>(searchList);
                searchTextJComboBox = new JComboBox<>(); 
                searchTextJComboBox.addItem("...");
                
                listAllJButton = new JButton("List All");
                searchJButton = new JButton("Start Search");
                searchClearJButton = new JButton("Clear Search");
                editJButton = new JButton("Edit");
                deleteJButton = new JButton("Delete");
        
                searchJTextArea = new JTextArea();
                searchJScrollPane = new JScrollPane();
        
                searchCategoryJComboBox.setBackground(WHITE_COLOUR);
                searchTextJComboBox.setBackground(WHITE_COLOUR);
                
                searchJTextArea = new JTextArea(null, "", 6, 5);
                searchJTextArea.setEditable(false);
                searchJScrollPane = new JScrollPane(searchJTextArea);
                searchJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                searchJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                
                setFontStyles();
                
                setActionListeners();
 
                searchLayout = new GroupLayout(searchJPanel);

                setLayout();
        }
        
        
        
        /**
         * Assigns fonts to swing components
         */
        private void setFontStyles()
        {
                searchJLabel.setFont(HEADINGTWO_FONT_STYLE);
                searchCategoryNameJLabel.setFont(NORMAL_FONT_STYLE);
                searchTextJLabel.setFont(NORMAL_FONT_STYLE);
                searchCategoryJComboBox.setFont(NORMAL_FONT_STYLE);
                searchTextJComboBox.setFont(NORMAL_FONT_STYLE);
                listAllJButton.setFont(NORMAL_FONT_STYLE);
                searchJButton.setFont(NORMAL_FONT_STYLE);
                searchClearJButton.setFont(NORMAL_FONT_STYLE);
                editJButton.setFont(NORMAL_FONT_STYLE);
                deleteJButton.setFont(NORMAL_FONT_STYLE);
                searchJTextArea.setFont(NORMAL_FONT_STYLE);
        }
        
        
        
        /**
         * Assigns an item listener to the searchCategoryJComboBox, and action listeners to JButtons
         */
        private void setActionListeners()
        {
                searchCategoryJComboBox.addItemListener(this);
                listAllJButton.addActionListener(this);
                searchJButton.addActionListener(this);
                searchClearJButton.addActionListener(this);
                editJButton.addActionListener(this);
                deleteJButton.addActionListener(this);
        }
        

        
        /**
         * Sets the layout of the JPanel
         */
        private void setLayout()
        {
                setLayout(new BorderLayout());
                add(searchJPanel);
                searchJPanel.setLayout(searchLayout);
                
                searchLayout.setHorizontalGroup(searchLayout.createSequentialGroup()
                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP)
                                .addGroup(searchLayout.createSequentialGroup()
                                .addGroup(searchLayout.createParallelGroup()
                                        .addComponent(searchJScrollPane, GroupLayout.Alignment.CENTER)
                                .addGroup(searchLayout.createSequentialGroup()
                                .addGroup(searchLayout.createParallelGroup()
                                        .addComponent(searchJLabel)
                                        .addComponent(searchCategoryNameJLabel)
                                        .addComponent(searchTextJLabel)
                                        .addComponent(listAllJButton, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editJButton, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                                        .addGroup(searchLayout.createSequentialGroup()
                                        .addGroup(searchLayout.createParallelGroup()
                                                .addComponent(searchCategoryJComboBox)
                                                .addComponent(searchTextJComboBox)
                                                .addComponent(searchJButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchClearJButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(deleteJButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE))))))
                                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP));
                // Creates a merged column spanning the width of the JPanel, and splits the remainder of the JPanel into two columns. A small gap is added between columns. Additionally, border gaps are added to the left and right to keep swing components away from the edge of the window.

                searchLayout.setVerticalGroup(searchLayout.createSequentialGroup()
                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP)
                        .addGroup(searchLayout.createSequentialGroup()
                        .addGroup(searchLayout.createParallelGroup(BASELINE)
                                .addComponent(searchJLabel))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(searchLayout.createSequentialGroup()
                        .addGroup(searchLayout.createParallelGroup(BASELINE)
                                .addComponent(searchCategoryNameJLabel)
                                .addComponent(searchCategoryJComboBox))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)
                        .addGroup(searchLayout.createSequentialGroup()
                        .addGroup(searchLayout.createParallelGroup(BASELINE)
                                .addComponent(searchTextJLabel)
                                .addComponent(searchTextJComboBox))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(searchLayout.createSequentialGroup()
                        .addGroup(searchLayout.createParallelGroup(BASELINE)
                                .addComponent(listAllJButton)
                                .addComponent(searchJButton)
                                .addComponent(searchClearJButton))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(searchLayout.createSequentialGroup()
                        .addGroup(searchLayout.createParallelGroup(BASELINE)
                                .addComponent(searchJScrollPane))
                                .addGap(LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP, LARGE_FIELD_VERTICAL_GAP)
                        .addGroup(searchLayout.createSequentialGroup()
                        .addGroup(searchLayout.createParallelGroup(BASELINE)
                                .addComponent(editJButton)
                                .addComponent(deleteJButton))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP)))))))
                        .addGap(BORDER_GAP, BORDER_GAP, BORDER_GAP));
                // The JPanel is split into 6 rows. A large gap is added between rows, and a border gap is added to the top and bottom to keep swing components away from the edge of the window
        }
        
        
        
        /**
         * Accesses the index of the searched owner
         */
        public static int getOwnerSearchReferenceIndex()
        {
                return SearchJPanel.ownerSearchReferenceIndex;
        }
        
        
        
        /**
         * Accesses the index of the searched vehicle
         */
        public static int getVehicleSearchReferenceIndex()
        {
                return SearchJPanel.vehicleSearchReferenceIndex;
        }


        
        /**
         * When a selection is made from the searchCategoryJComboBox, the itemStateChanged method activates, and if a search for licenses is selected, all license numbers from the owner array are added into the searchTextJComboBox
         */
        private void licenseSearch(int arraySize)
        {
                for (int searchIndex = 0; searchIndex < arraySize; ++searchIndex)
                        searchTextJComboBox.addItem(MotorVehicleRegistrationFrame.ownerArray.get(searchIndex).getId() + ""); 
        }
      

        /**
         * When a selection is made from the searchCategoryJComboBox, the itemStateChanged method activates, and if a search for vehicles is selected, all plate numbers from the owner array are added into the searchTextJComboBox
         */
        private void vehicleSearch(int arraySize)
        {
                for (int searchIndex = 0; searchIndex < arraySize; ++searchIndex)
                        searchTextJComboBox.addItem(MotorVehicleRegistrationFrame.vehicleArray.get(searchIndex).getPlateNumber()+ ""); 
        }

        
        
        /**
         * Sets the JTextArea to a zero length string
         */        
        public static void resetSearchJTextArea()
        {
                searchJTextArea.setText("");
        }        
        
        
        
        /**
         * Sets the searchCategoryJComboBox to an unselected state "..."
         */ 
        public static void resetJComboBox()
        {
                searchCategoryJComboBox.setSelectedIndex(0);
        }        
        
        
        
        /**
         * Sets the instances found and reference indexes to 0. 
         * Coordinates with the validateEditAndDelete method to prevent the editing or deleting of indexes from the owner and vehicle arrays 
         */ 
        public static void resetSearchAndReferenceIndexes()
        {
                ownerInstancesFoundInSearch = 0;
                vehicleInstancesFoundInSearch = 0;
                ownerSearchReferenceIndex = 0;
                vehicleSearchReferenceIndex = 0;
        }
                
        
        /**
         * Sets the JComboBoxes to unselected states and sets the JTextArea to a zero length string
         */ 
        public static void clear()
        {
                searchCategoryJComboBox.setSelectedIndex(0);
                searchTextJComboBox.setSelectedIndex(0);
                resetSearchJTextArea();
        }     
        
        
        /**
         * Severs connections to searched owners and vehicles and then displays all owners and vehicles within the JTextArea
         */ 
        private void listAll()
        {
                ownerArraySize = MotorVehicleRegistrationFrame.ownerArray.size();
                vehicleArraySize = MotorVehicleRegistrationFrame.vehicleArray.size();
                clear();
                resetSearchAndReferenceIndexes();
                displayOwnerHeading();
                for (int listIndex = 0; listIndex < ownerArraySize; ++listIndex)
                        searchJTextArea.append(MotorVehicleRegistrationFrame.ownerArray.get(listIndex).toString() + "\n");
                searchJTextArea.append("\n");
                displayVehicleHeading();
                for (int listIndex = 0; listIndex < vehicleArraySize; ++listIndex)
                        searchJTextArea.append(MotorVehicleRegistrationFrame.vehicleArray.get(listIndex).toString() + "\n");
        }
        
        
        
        /**
         * Severs connections to searched owners and vehicles, displays all owners and vehicles within the JTextArea, updates the selectVehicleTypeJComboBox in the vehicleJpanel, and then displays the search tab
         */ 
        private void resetSearchPanelAfterDeletion()
	{ 
                listAll();
                MotorVehicleRegistrationFrame.resetVehiclePanelJComboBox();
                MotorVehicleRegistrationFrame.showSearchTab();
	}
        
        
        
        /**
         * Adds an owners title to the JTeatArea, particularly used in conjunction with the listAll method
         */ 
        public static void displayOwnerHeading()
        {
                searchJTextArea.append("Owners: \n"); 
        }
        
        
        
        /**
         * Adds an vehicles title to the JTeatArea, particularly used in conjunction with the listAll method
         */
        public static void displayVehicleHeading()
        {
                searchJTextArea.append("Vehicles: \n"); 
        }
        
        
        
        /**
         * Displays the search tab, sets the JComboBoxes to unselected states and sets the JTextArea to a zero length string
         */ 
        public static void updateSearchTab()
        {
                MotorVehicleRegistrationFrame.showSearchTab();
                clear();
        }
        
        
        
        /**
         * Clears the search tab, resets JComboBoxes, adds a new owner heading to the JTextArea and then adds values from the newest added index of the owner array to the JTextArea. Particularly used in conjunction with add owner methods in the OwnerJpanel
         */
        public static void newOwner()
        {
               updateSearchTab();
               searchJTextArea.append("New Owner: \n"); 
               searchJTextArea.append(MotorVehicleRegistrationFrame.ownerArray.get(MotorVehicleRegistrationFrame.ownerArray.size()-1).toString());
               resetSearchAndReferenceIndexes();
        }
        
        
        
        /**
         * Clears the search tab, resets JComboBoxes, adds an edited owner heading to the JTextArea and then adds values from the newest added index of the owner array to the JTextArea. Particularly used in conjunction with accept changes methods in the OwnerJpanel
         */
        public static void editOwner()
        {
               updateSearchTab();
               searchJTextArea.append("Edited Owner: \n"); 
               searchJTextArea.append(MotorVehicleRegistrationFrame.ownerArray.get(MotorVehicleRegistrationFrame.ownerArray.size()-1).toString());
               resetSearchAndReferenceIndexes();
        }
        
        
        
        /**
         * Clears the search tab, resets JComboBoxes, adds a vehicle heading to the JTextArea and then adds values from the newest added index of the vehicle array to the JTextArea. Particularly used in conjunction with add vehicle methods in the VehicleJpanel
         */
        public static void newVehicle()
        {
               updateSearchTab();
               searchJTextArea.append("New Vehicle: \n"); 
               searchJTextArea.append(MotorVehicleRegistrationFrame.vehicleArray.get(MotorVehicleRegistrationFrame.vehicleArray.size()-1).toString());
               resetSearchAndReferenceIndexes();
        }
        
        
        
        /**
         * Clears the search tab, resets JComboBoxes, adds a vehicle heading to the JTextArea and then adds values from the newest added index of the vehicle array to the JTextArea. Particularly used in conjunction with accept changes methods in the VehicleJpanel
         */
        public static void editVehicle()
        {
               updateSearchTab();
               searchJTextArea.append("Edited Vehicle: \n"); 
               searchJTextArea.append(MotorVehicleRegistrationFrame.vehicleArray.get(SearchJPanel.getVehicleSearchReferenceIndex()).toString());
               resetSearchAndReferenceIndexes();
        }
        
        
        
        /**
         * Generates a dialog box with a yes or no option, and assigns the result of the user's selection to the confirmDialogResponse instance field
         */
        private void generateConfirmDialog(String messageText)
        {
               confirmDialogResponse = JOptionPane.showConfirmDialog(null, messageText, PROGRAM_TITLE, JOptionPane.YES_NO_OPTION);
        }
        
        
        
        /**
         * Generates a dialog box asking the user whether or not to edit a record. The dialog box presents a yes or no option, and assigns the result of the user's selection to the confirmDialogResponse instance field
         */
        private void generateConfirmEditDialogForTab(String tabName)
        {
                generateConfirmDialog("Are you sure you wish to edit the selected " + tabName + " record?\n\n"
                            + "This will cause the " + tabName + " tab to switch to edit mode, which\n"
                            + "will replace any text currently contained in the " + tabName + " tab.");
        }
        
        
        /**
         * Generates a dialog box asking the user whether or not to delete a record. The dialog box presents a yes or no option, and assigns the result of the user's selection to the confirmDialogResponse instance field
         */
        private void generateConfirmDeleteDialog()
        {
               confirmDialogResponse = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete the selected record?", PROGRAM_TITLE, JOptionPane.YES_NO_OPTION);
        }


        
        /**
         * Generates an error message dialog containing the specified text
         */
        private void generateErrorDialog(String messageText)
        {
                JOptionPane.showMessageDialog(null, messageText, PROGRAM_TITLE, JOptionPane.ERROR_MESSAGE); 
        }
        
        
        
        /**
         * Generates an error message dialog indicating that no instance of the specified search term were found
         */
        private void generateNotFoundErrorDialog(int instancesFoundInSearch, String searchItem)
        {
                if(instancesFoundInSearch == 0)
                        generateErrorDialog(searchItem + " not found");
        }
        
        
        
        /**
         * Works in conjunction with the actionPerformed method to generate an error message dialog indicating that no other indexes of the selected array can be edited or deleted while the edit mode for the relevant class is enabled.
         * The dialog box also provides users options on how to exit edit mode for the relevant class.
         */
        private void generateEditModeDialogForTab(String tabName)
        {
                generateErrorDialog("The " + tabName + " tab is currently in edit mode.\n\n"
                            + "Only one " + tabName + " can be edited or deleted at a time.\n"
                            + "To edit or delete another " + tabName + ", please complete\n"
                            + "the editing in the " + tabName + " tab and click on the\n"
                            + "accept changes button. Alternatively, return to the\n"
                            + tabName + " tab and click on the add mode button to exit edit\n"
                            + "mode without accepting changes.");
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
         * Generates a specialised error message dialog for when the searchTextJComboBox is essentially in an unselected state "...".
         * The method also sets the focus of the JPanel to the searchTextJComboBox JComponent
         * While the generation of this dialog box during general operation is unlikely, it exists mainly for testing and quality control processes.
         */
        private void noInstancesSelectedInSearch()
        {
                generateDataValidationErrorDialog(searchTextJComboBox, "Please select a record using the search button");
        }
        
             
        
        /**
         * Generates a specialised error message dialog for when more than one instance are selected to be edited or deleted.
         * The method also sets the focus of the JPanel to the searchTextJComboBox JComponent.
         * While the generation of this dialog box during general operation is unlikely, it operates as a measure of last resort to prevent unexpected data loss arising attempting to select duplicate license plates for instance.
         */
        private void multipleInstancesSelectedInSearch()
        {
                generateDataValidationErrorDialog(searchTextJComboBox, "More than record has ben selected in the search.\nTo use this button, Please refine your search to one record.");
        }
        
        
        
        /**
         * Generates an error message dialog and highlights the first load capacity field swing component found to contain an error during data validation
         */ 
        private void generateSearchErrorDataValidationDialog()
        {
                generateDataValidationErrorDialog(searchErrorObjectJComponentContainer, searchErrorDialogStringBuilder.toString()); 
        }
        
                
        
        /**
         * Generates an error message dialog and highlights the first number of seats field swing component found to contain an error during data validation
         */ 
        private void generateEditAndDeleteErrorDataValidationDialog()
        {
                generateDataValidationErrorDialog(editAndDeleteErrorJComponentContainer, editAndDeleteErrorDialogStringBuilder.toString());
        }


        
        /**
         * Accesses the first erroneously entered swing component found in data validation
         */ 
        private JComponent getSearchErrorObjectJComponentContainer()
        {
                return searchErrorObjectJComponentContainer;
        }
        
        
        
        /**
         * Data validation JComponent container
         * @return fills a JComponent container with the first swing component found to contain an error during data validation
         */ 
        private JComponent searchErrorObjectToJComponentContainer(JComponent fieldObject)
        {
                if(searchErrorObjectJComponentContainer == null)
                {
                        searchErrorObjectJComponentContainer = fieldObject;
                }
                
                return searchErrorObjectJComponentContainer;
        }


        
        /**
         * Accesses the first erroneously entered swing component found in data validation
         */ 
        private StringBuilder getSearchErrorDialogStringBuilder()
        {
                return searchErrorDialogStringBuilder;
        }
        
        
        
        /**
         * Data validation StringBuilder
         * @return fills a StringBuider with the error message text related to the first swing component found to contain an error during data validation
         */ 
        private StringBuilder addSearchErrorDialogToStringBuilder(String errorDialog)
        {
                if(searchErrorDialogStringBuilder.length() == 0)
                {
                        searchErrorDialogStringBuilder.append(errorDialog);
                }
                
                return searchErrorDialogStringBuilder;
        }

        
        
        /**
         * Performs data validation checks for search fields and stores the first swing component and related error message text found to contain an error during data validation
         */         
        private void validateSearch()
        {
                searchErrorObjectJComponentContainer = null;
                searchErrorDialogStringBuilder.setLength(0);                
                
                if(searchCategoryJComboBox.getSelectedIndex() == 0) 
                {
                        searchErrorObjectToJComponentContainer(searchCategoryJComboBox);
                        addSearchErrorDialogToStringBuilder("You must select what to search for");
                }
                
                if(searchTextJComboBox.getSelectedIndex() == 0) 
                {
                        searchErrorObjectToJComponentContainer(searchTextJComboBox);
                        addSearchErrorDialogToStringBuilder("You must select a record to search for.\n\n"
                                + "Alternatively, more records need to be added.");
                }

        } 


        
        /**
         * Accesses the first erroneously entered swing component found in data validation
         */ 
        private JComponent getEditAndDeleteErrorJComponentContainer()
        {
                return editAndDeleteErrorJComponentContainer;
        }
        
        
        
        /**
         * Data validation JComponent container
         * @return fills a JComponent container with the first swing component found to contain an error during data validation
         */ 
        private JComponent editAndDeleteErrorToJComponentContainer(JComponent fieldObject)
        {
                if(editAndDeleteErrorJComponentContainer == null)
                {
                        editAndDeleteErrorJComponentContainer = fieldObject;
                }
                
                return editAndDeleteErrorJComponentContainer;
        }


        
        /**
         * Accesses the first erroneously entered swing component found in data validation
         */ 
        private StringBuilder geEditAndDeleteErrorDialogStringBuilder()
        {
                return editAndDeleteErrorDialogStringBuilder;
        }
        
        
        
        /**
         * Data validation StringBuilder
         * @return fills a StringBuider with the error message text related to the first swing component found to contain an error during data validation
         */ 
        private StringBuilder addEditAndDeleteErrorDialogToStringBuilder(String errorDialog)
        {
                if(editAndDeleteErrorDialogStringBuilder.length() == 0)
                {
                        editAndDeleteErrorDialogStringBuilder.append(errorDialog);
                }
                
                return editAndDeleteErrorDialogStringBuilder;
        }

        
        
        /**
         * Performs data validation checks for editing and deletion operations, and then stores the first swing component and related error message text found to contain an error during data validation
         */         
        private void validateEditAndDelete()
        {
                editAndDeleteErrorJComponentContainer = null;
                editAndDeleteErrorDialogStringBuilder.setLength(0); 
                
                if(ownerInstancesFoundInSearch == 0 && vehicleInstancesFoundInSearch == 0) 
                {
                        editAndDeleteErrorToJComponentContainer(searchCategoryJComboBox);
                        addEditAndDeleteErrorDialogToStringBuilder("To edit or delete a record, a record must be selected.\n"
                                + "To do this, use the start search button.");
                }
                
                if(ownerInstancesFoundInSearch > 1 || vehicleInstancesFoundInSearch > 1) 
                {
                        editAndDeleteErrorToJComponentContainer(searchCategoryJComboBox);
                        addEditAndDeleteErrorDialogToStringBuilder("More than record has ben selected in the search.\n\n"
                                + "To use this button, Please refine your search to one record.");
                }

        }
        
        
        /**
         * Collects the ownerSearchReferenceIndex from the calling method
         * @return returns the calling variable to the calling method
         */
        private int collectOwnerSearchReferenceIndex(int instanceIndex)
        {
                ownerSearchReferenceIndex = instanceIndex;
                return instanceIndex;
        }
        
        
        
        /**
         * Collects the vehicleSearchReferenceIndex from the calling method
         * @return returns the calling variable to the calling method
         */
        private int collectVehicleSearchReferenceIndex(int instanceIndex)
        {
                vehicleSearchReferenceIndex = instanceIndex;
                return instanceIndex;
        }
        
        
        
        /**
         * Collects and then increments the ownerInstancesFoundInSearch from the calling method
         * @return returns the calling variable to the calling method
         */
        private String collectAndIincrementOwnerInstancesFoundInSearch(String instanceText)
        {
                String testString = instanceText;
                if(testString.compareTo("") != 0)
                        ++ownerInstancesFoundInSearch;
                return instanceText;
        }
        
        
        
        /**
         * Collects and then increments the vehicleInstancesFoundInSearch from the calling method
         * @return returns the calling variable to the calling method
         */
        private String collectAndIncrementVehicleInstancesFoundInSearch(String instanceText)
        {
                String testString = instanceText;
                if(testString.compareTo("") != 0)
                        ++vehicleInstancesFoundInSearch;
                return instanceText;
        }
        
        
        
        /**
         * Utilises modularity to validate the search function, creating error dialogs which discuss the first error found, and subsequently highlighting the swing components which contain errors to provide users an opportunity to correct data entry errors.
         * If no error are found, a search is performed. The search function adds every value from the selected array which matches the search criteria to the JTextArea. The number of search instances are counted, and each index which is found subsequently replaces
         * the corresponding ownerSearchReferenceIndex and vehicleSearchReferenceIndex. while the information output to the JTextArea may be useful to the user. The collected instance numbers and the most recently acquired search reference index of the corresponding type
         * is used as an input for editing and deleting functions.
         */
        private void search()
	{
                validateSearch();
                
                if(getSearchErrorObjectJComponentContainer() != null && getSearchErrorDialogStringBuilder().length() > 0)
                        generateSearchErrorDataValidationDialog();
            
                else
                {
                           
                        int license;
                        String numberPlate;
                        license = 0;
                        
                        ownerArraySize = MotorVehicleRegistrationFrame.ownerArray.size();
                        vehicleArraySize = MotorVehicleRegistrationFrame.vehicleArray.size(); 
                        resetSearchAndReferenceIndexes();
                        resetSearchJTextArea();
                        
                        if(searchCategoryJComboBox.getSelectedIndex() == OWNER)
                        {
                                license = Integer.parseInt(searchTextJComboBox.getItemAt(searchTextJComboBox.getSelectedIndex()));
                                displayOwnerHeading();
                                for (int searchIndex = 0; searchIndex < ownerArraySize; ++searchIndex)
                                        if(MotorVehicleRegistrationFrame.ownerArray.get(searchIndex).getId() == license)
                                                searchJTextArea.append(collectAndIincrementOwnerInstancesFoundInSearch(MotorVehicleRegistrationFrame.ownerArray.get(collectOwnerSearchReferenceIndex(searchIndex)).toString()));
                                
                                generateNotFoundErrorDialog(ownerInstancesFoundInSearch, license + "");
                                return;
                        }
                        
                        if(searchCategoryJComboBox.getSelectedIndex() == VEHICLE)
                        {
                                numberPlate = searchTextJComboBox.getItemAt(searchTextJComboBox.getSelectedIndex());
                                displayVehicleHeading();
                                for (int searchIndex = 0; searchIndex < vehicleArraySize; ++searchIndex)
                                        if(MotorVehicleRegistrationFrame.vehicleArray.get(searchIndex).getPlateNumber().equalsIgnoreCase(numberPlate))
                                                searchJTextArea.append(collectAndIncrementVehicleInstancesFoundInSearch(MotorVehicleRegistrationFrame.vehicleArray.get(collectVehicleSearchReferenceIndex(searchIndex)).toString()));
                                                                
                                generateNotFoundErrorDialog(vehicleInstancesFoundInSearch, numberPlate);
                                return;
                        }
                                
                }  
                        
        } 
        
        
        
        /**
         * Works alongside the actionPerformed method to delete the currently selected ownerSearchReferenceIndex if particular criteria are met
         */
        private void deleteOwner()
	{ 
                MotorVehicleRegistrationFrame.ownerArray.remove(ownerSearchReferenceIndex);
                resetSearchPanelAfterDeletion();
	}
                
        
        
        /**
         * Works alongside the actionPerformed method to delete the currently selected vehicleSearchReferenceIndex if particular criteria are met
         */
        private void deleteVehicle()
	{ 
                MotorVehicleRegistrationFrame.vehicleArray.remove(vehicleSearchReferenceIndex);
                resetSearchPanelAfterDeletion();
	}
        
        
        /**
         * This event connects to the searchCategoryJComboBox and the searchTextJComboBox with a selection from the searchCategoryJComboBox JComboBox populating the searchTextJComboBox JComboBox with relevant search values
         * This minimises the effort for the user in entering an acceptable search term, while ensuring that the user can always select a valid and searchable value. 
         */
        @Override
        public void itemStateChanged(ItemEvent e)
	{
		Object source = e.getSource();
                
                if(source == searchCategoryJComboBox)
                {
                ownerArraySize = MotorVehicleRegistrationFrame.ownerArray.size();
                vehicleArraySize = MotorVehicleRegistrationFrame.vehicleArray.size();
                searchTextJComboBox.removeAllItems();
                searchTextJComboBox.addItem("...");
                
                if(searchCategoryJComboBox.getSelectedIndex() == OWNER)
                        licenseSearch(ownerArraySize);
                if(searchCategoryJComboBox.getSelectedIndex() == VEHICLE)
                        vehicleSearch(vehicleArraySize);
                }
                        
        }

        
        
        /**
         * When clicked the search button lists all owners and vehicles.
         * The search clear button sets the JComboBoxes to an unselected state "..." and replaces the JTextArea with a zero length string
         * The search button will display a selected owner or vehicle based on the user's selection from the searchCategoryJComboBox and the searchTextJComboBox. Additionally, the search button collects the number of instances found and assigns the respective search reference index to the most recently found index.
         * If the instances found variable is equal to 1, editing and deletion functionality becomes available through the respective edit and delete buttons
         * Due to the risks of data loss, with the edit owner panel technically replacing the add owner panel, and the delete function being capable of deleting data, users are asked question to confirm the action
         */ 
        @Override
        public void actionPerformed(ActionEvent e)
        {
                Object source = e.getSource();
            
                if(source == listAllJButton)
                        listAll();
               
                if(source == searchClearJButton)
                {
                        generateConfirmDialog("Are you sure you wish to clear search details?");
                        if(confirmDialogResponse == JOptionPane.YES_OPTION)
                        {
                                clear();
                                resetSearchAndReferenceIndexes();
                        }
                        else
                                return;
                }
                
                if(source == searchJButton)
                        search();
                
                if(source == editJButton)
                {
                        validateEditAndDelete();
                
                        if(getEditAndDeleteErrorJComponentContainer() != null && geEditAndDeleteErrorDialogStringBuilder().length() > 0)
                                generateEditAndDeleteErrorDataValidationDialog();
                        else
                        {
                                if(ownerInstancesFoundInSearch > vehicleInstancesFoundInSearch)
                                {
                                        if(OwnerJPanel.editMode == true)
                                        {
                                                generateEditModeDialogForTab("owner");
                                                return;
                                        }
                                        else
                                        {
                                                generateConfirmEditDialogForTab("owner");
                                                if(confirmDialogResponse == JOptionPane.YES_OPTION)
                                                {
                                                        OwnerJPanel.editMode();
                                                        return;
                                                }
                                                else
                                                {
                                                        return;
                                                }
                                                
                                        }
                                }                                
                                                
                                if(vehicleInstancesFoundInSearch > ownerInstancesFoundInSearch)
                                {
                                        if(VehicleJPanel.editMode == true)
                                        {
                                                generateEditModeDialogForTab("vehicle");
                                                return;
                                        }
                                        else
                                        {
                                                generateConfirmEditDialogForTab("vehicle");
                                                if(confirmDialogResponse == JOptionPane.YES_OPTION)
                                                {
                                                        VehicleJPanel.editMode();
                                                        return;
                                                }
                                                else
                                                {
                                                        return;
                                                }
                                                
                                        }
                                        
                                }

                        }
                
                }
                
                if(source == deleteJButton)
                {
                        validateEditAndDelete();
                
                        if(getEditAndDeleteErrorJComponentContainer() != null && geEditAndDeleteErrorDialogStringBuilder().length() > 0)
                                generateEditAndDeleteErrorDataValidationDialog();
                        else
                        {
                                if(ownerInstancesFoundInSearch > vehicleInstancesFoundInSearch)
                                {
                                        if(OwnerJPanel.editMode == true)
                                        {
                                                generateEditModeDialogForTab("owner");
                                                return;
                                        }
                                        else
                                        {
                                                generateConfirmDeleteDialog();
                                                if(confirmDialogResponse == JOptionPane.YES_OPTION)
                                                {
                                                        deleteOwner();
                                                        return;
                                                }
                                                else
                                                {
                                                        return;
                                                }
                                                
                                        }
                                }                                
                                                
                                if(vehicleInstancesFoundInSearch > ownerInstancesFoundInSearch)
                                {
                                        if(VehicleJPanel.editMode == true)
                                        {
                                                generateEditModeDialogForTab("vehicle");
                                                return;
                                        }
                                        else
                                        {
                                                generateConfirmDeleteDialog();
                                                if(confirmDialogResponse == JOptionPane.YES_OPTION)
                                                {
                                                        deleteVehicle();
                                                        return;
                                                }
                                                else
                                                {
                                                        return;
                                                }
                                                
                                        }
                                        
                                }

                        }
                                
                }
                        
        }
                
}

