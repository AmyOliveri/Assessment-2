 /*
Student Name: Amy Oliveri
Student Number: 12095536
Assignment 2 COIT11134 OOP
*/
package assignmnent_2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AccidentPanel extends JPanel implements ActionListener { 
    
    private JLabel idLabel;
    private JLabel locationLabel;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;
    private JLabel regoLabel;
    private JLabel commentLabel;
    
    private JTextField idInput;
    private JTextField locationInput;
    private JTextField dateInput;
    private JComboBox rego;
    private JTextField commentInput;
    
    private JComboBox dayInput;
    private JComboBox monthInput;
    private JComboBox yearInput;
    
    JButton saveButton = new JButton("Save");
    JButton clearButton = new JButton("Clear");
    
    //private GroupLayout GUILayout;
    
    public AccidentPanel(){
    
    idLabel = new JLabel("Accident ID: ");
    idInput = new JTextField(20);
    
    locationLabel = new JLabel("Location: ");
    locationInput = new JTextField(20);
    
    Integer[] daysOfMonth = new Integer[32]; 
int day=1;
for(int i=0;i<31;i++){
    daysOfMonth[i]= day;
    day++;
}
    
 Integer[] monthsOfYear = new Integer[13]; 
int month=1;
for(int i=0;i<12;i++){
    monthsOfYear[i]= month;
    month++;
}

Integer[] years = new Integer[2050]; 
int year=2000;
for(int i=0;i<2050;i++){
    years[i]= year;
    year++;
}

    dayLabel = new JLabel("Day: ");
    monthLabel = new JLabel("Month: ");
    yearLabel = new JLabel("Year: ");
    
    dayInput = new JComboBox(daysOfMonth);
    monthInput = new JComboBox(monthsOfYear);
    yearInput = new JComboBox(years);
    
    regoLabel = new JLabel("Registration: ");
   
    rego = new JComboBox();
    regoDropdown();
   
    commentLabel = new JLabel("Comments: ");
    commentInput = new JTextField(20);
    
    setLayout(new GridLayout(8, 2));
    add(idLabel);
    add(idInput);
    add(locationLabel);
    add(locationInput);
    add(dayLabel);
    add(dayInput);
    add(monthLabel);
    add(monthInput);
    add(yearLabel);
    add(yearInput);
    add(regoLabel);
    add(rego);
    add(commentLabel);
    add(commentInput);
    
    add(saveButton);
    add(clearButton);
    
    saveButton.addActionListener(this);
    clearButton.addActionListener(this);
   
    }
   
    public void regoDropdown(){
    int arraySize = MotorVehicleRegistrationFrame.vehicleArray.size();
        for (int searchIndex = 0; searchIndex < arraySize; ++searchIndex)
        {
            rego.addItem(MotorVehicleRegistrationFrame.vehicleArray.get(searchIndex).getPlateNumber()+ "");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
         Object source = e.getSource();
            if (source == saveButton) {
                ArrayList<String> vehicles = new ArrayList<String>();
                //populate vehicles from main ArrayList
                Accident myAccident = new Accident(this.idInput.getText(), this.locationInput.getText(), 
                        this.dateInput.getText(), this.commentInput.getText(), vehicles);
                AccidentFrame.accidents.add(myAccident);
            }   
        }
        if (e.getActionCommand().equals("Clear")) {
            Object source = e.getSource();
            if (source == clearButton) {
                idInput.setText("");
                locationInput.setText("");
                dateInput.setText("");
               // regoInput.setText("");
                commentInput.setText("");
        }
    }
   }
}
  
