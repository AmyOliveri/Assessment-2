/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: ABNJPanel.java
Purpose: ABNJpanel class to store the ABN fields for the Owner JPanel Class
Date: 10 August 2020
*/
package assignmnent_2;
// Labelling the package name for the java project

import java.awt.*;
import java.util.ArrayList;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import javax.swing.*;
// Array lists, group layout constants and the entire awt and swing libraries are imported to reduce the number of import statements required.


/**
 * The ABNJPanel class displays the ABN boxes visible within the Owner JPanel class, gets the text contained in the ABN boxes, and also sets the text in the ABN boxes when edit mode in the Owner JPanel class is activated
 * @author aread
 */
public class ABNJPanel extends JPanel
{
        private final int FIELD_HORIZONTAL_GAP;
        private final int FIELD_VERTICAL_GAP;
        // Constants are declared to adjust the gaps between the rows and columns within the set layout method
        
        private final int JEXTFIELD_TWO_DIGIT_COLUMN_WIDTH;
        private final int JEXTFIELD_THREE_DIGIT_COLUMN_WIDTH;
        // Constants are declared to set the preferred widths of JTextFields within the set layout method
    
        private final Font NORMAL_FONT_STYLE;
        // A constant is introduced to set the font of swing components within the JPanel
        
        private static JPanel aBNJPanel; 
        
        private static JTextField firstABNJTextField;
        private static JTextField secondABNJTextField;
        private static JTextField thirdABNJTextField;
        private static JTextField fourthABNJTextField;
        // Four JTextFields are declared to store corporate owners' ABN number across four boxes
        
        private static ArrayList<CorporateOwner> aBNArray;
        // An array list is introduced to cast the output of the owner array from super class owner to subclass CorporateOwner
        
        private GroupLayout ABNLayout;
        // A group layout is declared to arrange the swing elements on screen

        
        
    /**
     * The default constructor is used to initialise the constants and swing components declared as fields
     */
    public ABNJPanel()
        {
                FIELD_HORIZONTAL_GAP = 12;
                FIELD_VERTICAL_GAP = 5;
                JEXTFIELD_TWO_DIGIT_COLUMN_WIDTH = 20;
                JEXTFIELD_THREE_DIGIT_COLUMN_WIDTH = 27;
            
                NORMAL_FONT_STYLE = new Font("Arial", 4, 12);
                
                aBNJPanel = new JPanel();
                
                firstABNJTextField = new JTextField();
                secondABNJTextField = new JTextField();
                thirdABNJTextField = new JTextField();
                fourthABNJTextField = new JTextField();
                
                aBNArray = new ArrayList<>();
                
                setFontStyles();
 
                ABNLayout = new GroupLayout(aBNJPanel);

                setLayout();
        }
        
    
        
        /**
         * The setFontStyles method sets the normal font style to the JTextFields
         */
        private void setFontStyles()
        {
                firstABNJTextField.setFont(NORMAL_FONT_STYLE);
                secondABNJTextField.setFont(NORMAL_FONT_STYLE);
                thirdABNJTextField.setFont(NORMAL_FONT_STYLE);
                fourthABNJTextField.setFont(NORMAL_FONT_STYLE);
        }
        
        
        /**
         * The setLayout method utilises the border and group layout to present the swing components
         */
        private void setLayout()
        {        
                setLayout(new BorderLayout());
                setAlignmentX(aBNJPanel.LEFT_ALIGNMENT);
                add(aBNJPanel, BorderLayout.WEST);
                // Sets the JPanel contents to be left aligned

                aBNJPanel.setLayout(ABNLayout);
                ABNLayout.setHorizontalGroup(ABNLayout.createSequentialGroup()
                        .addGroup(ABNLayout.createSequentialGroup()
                        .addGroup(ABNLayout.createParallelGroup()
                                .addComponent(firstABNJTextField, javax.swing.GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, JEXTFIELD_TWO_DIGIT_COLUMN_WIDTH, GroupLayout.PREFERRED_SIZE))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                        .addGroup(ABNLayout.createSequentialGroup()
                        .addGroup(ABNLayout.createParallelGroup()
                                .addComponent(secondABNJTextField, javax.swing.GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, JEXTFIELD_THREE_DIGIT_COLUMN_WIDTH, GroupLayout.PREFERRED_SIZE))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                        .addGroup(ABNLayout.createSequentialGroup()
                        .addGroup(ABNLayout.createParallelGroup()
                                .addComponent(thirdABNJTextField, javax.swing.GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, JEXTFIELD_THREE_DIGIT_COLUMN_WIDTH, GroupLayout.PREFERRED_SIZE))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                        .addGroup(ABNLayout.createSequentialGroup()
                        .addGroup(ABNLayout.createParallelGroup()
                                .addComponent(fourthABNJTextField, javax.swing.GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, JEXTFIELD_THREE_DIGIT_COLUMN_WIDTH, GroupLayout.PREFERRED_SIZE)))))));
                // Four columns are introduced, each containing an ABN box separated by a small gap
                
                
                ABNLayout.setVerticalGroup(ABNLayout.createSequentialGroup()
                        .addGroup(ABNLayout.createSequentialGroup()
                        .addGroup(ABNLayout.createParallelGroup(BASELINE)
                                .addComponent(firstABNJTextField)
                                .addComponent(secondABNJTextField)
                                .addComponent(thirdABNJTextField)
                                .addComponent(fourthABNJTextField)))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP));
                // One row is used to present all fout ABN boxes. Storing the FIELD_VERTICAL_GAP, gaps within JPanels tends to minimise space differences which occur when swing components are set as not visible within the Owner JPanel class
        }

        
        
        /**
         * Getter for the first ABN box
         * @return returns the contents of the first ABN box as a string
         */
        public String getFirstABNJTextFieldText()
        {
                return firstABNJTextField.getText();
        }

        
        
        /**
         * Getter for the second ABN box
         * @return returns the contents of the second ABN box as a string
         */
        public String getSecondABNJTextFieldText()
        {
                return secondABNJTextField.getText();
        }

        
        
        /**
         * Getter for the third ABN box
         * @return returns the contents of the third ABN box as a string
         */
        public String getThirdABNJTextFieldText()
        {
                return thirdABNJTextField.getText();
        }

        
        
        /**
         * Getter for the fourth ABN box
         * @return returns the contents of the fourth ABN box as a string
         */
        public String getFourthABNJTextFieldText()
        {
                return fourthABNJTextField.getText();
        }

        
        
        /**
         * 
         * @return returns the object reference to the first ABN box for data validation within the Owner JPanel class
         */
        public JTextField getFirstABNJTextField()
        {
                return firstABNJTextField;
        }

        
        
        /**
         * 
         * @return returns the object reference to the second ABN box for data validation within the Owner JPanel class
         */
        public JTextField getSecondABNJTextField()
        {
                return secondABNJTextField;
        }

        
        
        /**
         * 
         * @return returns the object reference to the third ABN box for data validation within the Owner JPanel class
         */
        public JTextField getThirdABNJTextField()
        {
                return thirdABNJTextField;
        }

        
        
        /**
         * 
         * @return returns the object reference to the fourth ABN box for data validation within the Owner JPanel class
         */
        public JTextField getFourthABNJTextField()
        {
                return fourthABNJTextField;
        }

        
    
        /**
         * 
         * Empties the JTextFields
         */
        public void resetABNJTextFields()
        {
                firstABNJTextField.setText("");
                secondABNJTextField.setText("");
                thirdABNJTextField.setText("");
                fourthABNJTextField.setText("");
        }    
        
        
        
        /**
          * Casts the owner array from super class Owner to subclass CorporateOwner in order to access the Australian Business Number getter 
          * @return returns the string extracted from the owner array at the ownerSearchReferenceIndex
          */
        public static String extractABNValuesFromText()
        {
                String extractedString;
                aBNArray.add((CorporateOwner) MotorVehicleRegistrationFrame.ownerArray.get(SearchJPanel.ownerSearchReferenceIndex));
                extractedString = aBNArray.get(0).getAustralianBusinessNumber() + "";
                return extractedString;
        }
        
        
        
        /**
          * Works with the edit mode of the Owner JPanel class to extract a fixed-width sub-string from the first two digits of the ABN, which will be output to the first ABN box 
          * @return returns the first sub-string of the extracted ABN
          */
        public static String extractFirstABNJTextField()
        {
                int firstIndex;
                int lastIndex;
                String firstBoxText;
                firstIndex = 0;
                lastIndex = 2;
                firstBoxText = extractABNValuesFromText().substring(firstIndex, lastIndex);
                return firstBoxText;
        }
        
        
        
        /**
          * Sets the first ABN box to contain the first two characters from the ABN extracted from the extractFirstABNJTextField method
          * 
          */
        public static void setFirstABNJTextField()
        {
                firstABNJTextField.setText(extractFirstABNJTextField());
        }
        
        
        
        /**
          * Works with the edit mode of the Owner JPanel class to extract a fixed-width sub-string from the second three digits of the ABN, which will be output to the second ABN box 
          * @return returns the ssecond sub-string of the extracted ABN
          */
        public static String extractSecondABNJTextField()
        {
                int firstIndex;
                int lastIndex;
                String secondBoxText;
                firstIndex = 3;
                lastIndex = 6;
                secondBoxText = extractABNValuesFromText().substring(firstIndex, lastIndex);
                return secondBoxText;
        }
        
        
        
        /**
          * Sets the second ABN box to contain the second three characters from the ABN extracted from the extractSecondABNJTextField method
          * 
          */
        public static void setSecondABNJTextField()
        {
                secondABNJTextField.setText(extractSecondABNJTextField());
        }
        
        
        
        /**
          * Works with the edit mode of the Owner JPanel class to extract a fixed-width sub-string from the third three digits of the ABN, which will be output to the third ABN box 
          * @return returns the third sub-string of the extracted ABN
          */
        public static String extractThirdABNJTextField()
        {
                int firstIndex;
                int lastIndex;
                String thirdBoxText;
                firstIndex = 7;
                lastIndex = 10;
                thirdBoxText = extractABNValuesFromText().substring(firstIndex, lastIndex);
                return thirdBoxText;
        }
        
        
        
        /**
          * Sets the third ABN box to contain the third three characters from the ABN extracted from the extractThirdABNJTextField method
          * 
          */
        public static void setThirdABNJTextField()
        {
                thirdABNJTextField.setText(extractThirdABNJTextField());
        }
        
        
        
        /**
          * Works with the edit mode of the Owner JPanel class to extract a fixed-width sub-string from the fourth three digits of the ABN, which will be output to the fourth ABN box 
          * @return returns the fourth sub-string of the extracted ABN
          */
        public static String extractFourthABNJTextField()
        {
                int firstIndex;
                int lastIndex;
                String fourthBoxText;
                firstIndex = 11;
                lastIndex = 14;
                fourthBoxText = extractABNValuesFromText().substring(firstIndex, lastIndex);
                return fourthBoxText;
        }
        
        
        
        /**
          * Sets the fourth ABN box to contain the fourth three characters from the ABN extracted from the extractFourthABNJTextField method
          * 
          */
        public static void setFourthABNJTextField()
        {
                fourthABNJTextField.setText(extractFourthABNJTextField());
        }        
        
        
        
        /**
          * Sets the text of all four ABN boxes
          * 
          */
        public static void setABN()
        {
                setFirstABNJTextField();
                setSecondABNJTextField();
                setThirdABNJTextField();
                setFourthABNJTextField();
        }

}
