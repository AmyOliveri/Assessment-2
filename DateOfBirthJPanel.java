/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: CorporateOwner.java
Purpose: CorporateOwner class, database subclass of the owner, used to stoe corporate owners with ABN fields
Date: 10 August 2020
*/
package assignmnent_2;

import java.awt.*;
import java.util.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import javax.swing.*;
// Group layout constants and the entire awt, utility, and swing libraries are imported to reduce the number of import statements required.

/**
 *
 * @author aread
 */
public class DateOfBirthJPanel extends JPanel
{
        private int FIELD_HORIZONTAL_GAP;
        private int FIELD_VERTICAL_GAP;
        private int COMBO_BOX_WIDTH;
        private int MAX_NUMBER_OF_DAYS;
        private int MAX_NUMBER_OF_MONTHS;
        private int MIN_NUMBER_OF_YEARS;
        private static int currentYear;
    
        private String [] monthList;  
        
        private static StringBuilder dateOfBirthTextGetterStringBuilder;
        private static StringBuilder dateOfBirthTextExtractorStringBuilder;
        
        private final Font NORMAL_FONT_STYLE;
        
        private final Color WHITE_COLOUR;
    
        private JPanel dateOfBirthJPanel;
        private JLabel dateOfBirthDayJLabel;
        private JLabel dateOfBirthMonthJLabel;  
        private JLabel dateOfBirthYearJLabel;  
        private static JComboBox<String> dateOfBirthDayJComboBox;
        private static JComboBox<String> dateOfBirthMonthJComboBox;
        private static JComboBox<String> dateOfBirthYearJComboBox;
        
        private static ArrayList<PrivateOwner> dateOfBithArray;
        
        private javax.swing.GroupLayout dateOfBirthLayout;

        public DateOfBirthJPanel()
        {
                MAX_NUMBER_OF_DAYS = 31+1;
                MAX_NUMBER_OF_MONTHS = 12+1;
                MIN_NUMBER_OF_YEARS = 1930-1;
            
                FIELD_HORIZONTAL_GAP = 12;
                FIELD_VERTICAL_GAP = 5;
                COMBO_BOX_WIDTH = 96;
                
                currentYear = 2020;
                
                dateOfBirthTextGetterStringBuilder = new StringBuilder();
                dateOfBirthTextExtractorStringBuilder = new StringBuilder();
            
                NORMAL_FONT_STYLE = new Font("Arial", 4, 12);
                
                WHITE_COLOUR = new Color(255,255,255);
                
                dateOfBirthJPanel = new JPanel();
                
                dateOfBirthDayJLabel = new JLabel("Day: ");
                dateOfBirthMonthJLabel = new JLabel("Month: ");
                dateOfBirthYearJLabel = new JLabel("Year: ");
                
                dateOfBirthDayJComboBox = new JComboBox<>();
                dateOfBirthMonthJComboBox = new JComboBox<>();
                dateOfBirthYearJComboBox = new JComboBox<>();
                
                dateOfBithArray = new ArrayList<>();
                
                setDateOfBirthComboBoxListConstants();
                
                setFontStyles();
                
                setBackgroundColours();
 
                dateOfBirthLayout = new javax.swing.GroupLayout(dateOfBirthJPanel);

                setLayouts();
        }
        
        
        
        private void setBackgroundColours()
        {
                dateOfBirthDayJComboBox.setBackground(WHITE_COLOUR);
                dateOfBirthMonthJComboBox.setBackground(WHITE_COLOUR);
                dateOfBirthYearJComboBox.setBackground(WHITE_COLOUR);
        }
        
        
        
        private void setFontStyles()
        {
                dateOfBirthDayJLabel.setFont(NORMAL_FONT_STYLE);
                dateOfBirthMonthJLabel.setFont(NORMAL_FONT_STYLE);
                dateOfBirthYearJLabel.setFont(NORMAL_FONT_STYLE);
                dateOfBirthDayJComboBox.setFont(NORMAL_FONT_STYLE);
                dateOfBirthMonthJComboBox.setFont(NORMAL_FONT_STYLE);
                dateOfBirthYearJComboBox.setFont(NORMAL_FONT_STYLE);
        }
        
        
        
        private void setLayouts()
        {
                setLayout(new BorderLayout());            
            
                add(dateOfBirthJPanel);
            
                dateOfBirthJPanel.setLayout(dateOfBirthLayout);
                dateOfBirthLayout.setHorizontalGroup(dateOfBirthLayout.createSequentialGroup()
                        .addGroup(dateOfBirthLayout.createSequentialGroup()
                        .addGroup(dateOfBirthLayout.createParallelGroup()
                                .addComponent(dateOfBirthDayJLabel))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                        .addGroup(dateOfBirthLayout.createSequentialGroup()
                        .addGroup(dateOfBirthLayout.createParallelGroup()
                                .addComponent(dateOfBirthDayJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, COMBO_BOX_WIDTH, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                        .addGroup(dateOfBirthLayout.createSequentialGroup()
                        .addGroup(dateOfBirthLayout.createParallelGroup()
                                .addComponent(dateOfBirthMonthJLabel))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                        .addGroup(dateOfBirthLayout.createSequentialGroup()
                        .addGroup(dateOfBirthLayout.createParallelGroup()
                                .addComponent(dateOfBirthMonthJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, COMBO_BOX_WIDTH, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                        .addGroup(dateOfBirthLayout.createSequentialGroup()
                        .addGroup(dateOfBirthLayout.createParallelGroup()
                                .addComponent(dateOfBirthYearJLabel))
                                .addGap(FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP, FIELD_HORIZONTAL_GAP)
                        .addGroup(dateOfBirthLayout.createSequentialGroup()
                        .addGroup(dateOfBirthLayout.createParallelGroup()
                                .addComponent(dateOfBirthYearJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, COMBO_BOX_WIDTH, javax.swing.GroupLayout.PREFERRED_SIZE)))))))));

                dateOfBirthLayout.setVerticalGroup(dateOfBirthLayout.createSequentialGroup()
                        .addGroup(dateOfBirthLayout.createSequentialGroup()
                        .addGroup(dateOfBirthLayout.createParallelGroup(BASELINE)
                                .addComponent(dateOfBirthDayJLabel)
                                .addComponent(dateOfBirthDayJComboBox)
                                .addComponent(dateOfBirthMonthJLabel)
                                .addComponent(dateOfBirthMonthJComboBox)
                                .addComponent(dateOfBirthYearJLabel)
                                .addComponent(dateOfBirthYearJComboBox)))
                                .addGap(FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP, FIELD_VERTICAL_GAP));
        }


        
        private void setDateOfBirthComboBoxListConstants()
        { // Start of the end method, which when called, sets the focus of the window to the customerNameField, and ends the method
                String dayNumberFormat = "0";
                String monthNumberFormat = "0";
                dateOfBirthDayJComboBox.addItem("...");
                for(int currentDay = 1; currentDay < MAX_NUMBER_OF_DAYS; currentDay++)
                {
                        if(currentDay > 9)
                                dayNumberFormat = "";
                        dateOfBirthDayJComboBox.addItem(dayNumberFormat + Integer.toString(currentDay));
                }
                dateOfBirthMonthJComboBox.addItem("...");
                for(int currentMonth = 1; currentMonth < MAX_NUMBER_OF_MONTHS; currentMonth++)
                {
                        if(currentMonth > 9)
                                monthNumberFormat = "";
                        dateOfBirthMonthJComboBox.addItem(monthNumberFormat + Integer.toString(currentMonth));
                }
                dateOfBirthYearJComboBox.addItem("...");
                for(int year = currentYear; year > MIN_NUMBER_OF_YEARS; year--)
                        dateOfBirthYearJComboBox.addItem(Integer.toString(year));
        } // End of the end method
        
        
        
        public int dateOfBirthDayIndexSelected()
        {
                return dateOfBirthDayJComboBox.getSelectedIndex();
        }

        
        
        public int dateOfBirthMonthIndexSelected()
        {
                return dateOfBirthMonthJComboBox.getSelectedIndex();
        }

        
        
        public int dateOfBirthYearIndexSelected()
        {
                return dateOfBirthYearJComboBox.getSelectedIndex();
        }

        
        
        public String getDay()
        {
                return dateOfBirthDayJComboBox.getItemAt(dateOfBirthDayIndexSelected());
        }

        
        
        public String getMonth()
        {
                return dateOfBirthMonthJComboBox.getItemAt(dateOfBirthMonthIndexSelected());
        }

        
        
        public String getYear()
        {
                return dateOfBirthYearJComboBox.getItemAt(dateOfBirthYearIndexSelected());
        }

        
        
        public static String extractDateOfBirthValuesFromText()
        {
                String extractedString;
                dateOfBithArray.add((PrivateOwner) MotorVehicleRegistrationFrame.ownerArray.get(SearchJPanel.ownerSearchReferenceIndex));
                extractedString = dateOfBithArray.get(0).getDateOfBirth() + "";
                return extractedString;
        }
        
        
        
        public static String extractDay()
        {
                int firstIndex;
                int lastIndex;
                String dayText;
                firstIndex = 0;
                lastIndex = 2;
                dayText = extractDateOfBirthValuesFromText().substring(firstIndex, lastIndex);
                return dayText;
        }
        
        
        
        public static void setDay()
        {
                dateOfBirthDayJComboBox.setSelectedIndex(Integer.parseInt(extractDay()));
        }
        
        
        
        public static String extractMonth()
        {
                int firstIndex;
                int lastIndex;
                String monthText;
                firstIndex = 3;
                lastIndex = 5;
                monthText = extractDateOfBirthValuesFromText().substring(firstIndex, lastIndex);
                return monthText;
        }
        
        
        
        public static void setMonth()
        {
                dateOfBirthMonthJComboBox.setSelectedIndex(Integer.parseInt(extractMonth()));
        }
        
        
        
        public static String extractYear()
        {
                int firstIndex;
                int lastIndex;
                String yearText;
                firstIndex = 6;
                lastIndex = 10;
                yearText = extractDateOfBirthValuesFromText().substring(firstIndex, lastIndex);
                return yearText;
        }
        
        
        
        public static void setYear()
        {
                dateOfBirthYearJComboBox.setSelectedIndex(currentYear - Integer.parseInt(extractYear()) + 1);
        }
        
        
        
        public static void setDateOfBirth()
        {
                setDay();
                setMonth();
                setYear();
        }


        
        
        public JComboBox<String> getDateOfBirthDayJComboBox()
        {
                return dateOfBirthDayJComboBox;
        }

        
        
        public JComboBox<String> getDateOfBirthMonthJComboBox()
        {
                return dateOfBirthMonthJComboBox;
        }

    
    
        public JComboBox<String> getDateOfBirthYearJComboBox()
        {
                return dateOfBirthYearJComboBox;
        }

    
    
        public void resetDateOfBirthJComboBoxes()
        {
                dateOfBirthDayJComboBox.setSelectedIndex(0);
                dateOfBirthMonthJComboBox.setSelectedIndex(0);
                dateOfBirthYearJComboBox.setSelectedIndex(0);
        }        

}
