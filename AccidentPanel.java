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

    //private labels and inputs and comboboxes for the accident panel of the tabbed frame
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

    //accident panel constructor
    public AccidentPanel() {

        idLabel = new JLabel("Accident ID: ");
        idInput = new JTextField(20);

        locationLabel = new JLabel("Location: ");
        locationInput = new JTextField(20);

        //array of days of the month for the dropdown
        Integer[] daysOfMonth = new Integer[32];
        int day = 1;
        for (int i = 0; i < 31; i++) {
            daysOfMonth[i] = day;
            day++;
        }

        //array of months in the year
        Integer[] monthsOfYear = new Integer[13];
        int month = 1;
        for (int i = 0; i < 12; i++) {
            monthsOfYear[i] = month;
            month++;
        }

        //array of years 
        Integer[] years = new Integer[2050];
        int year = 2000;
        for (int i = 0; i < 2050; i++) {
            years[i] = year;
            year++;
        }

        //labels and jcomboboxes which contains arrays for days, months and years
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

        //add all labels, textfields and comboboxes
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

        //buttons to save and clear
        add(saveButton);
        add(clearButton);

        //action listeners to control what happens when the buttons are clicked
        saveButton.addActionListener(this);
        clearButton.addActionListener(this);

    }

    //dropdown which contains the array of vehicle number plates to use in the view accident panel
    public void regoDropdown() {
        int arraySize = MotorVehicleRegistrationFrame.vehicleArray.size();
        for (int searchIndex = 0; searchIndex < arraySize; ++searchIndex) {
            rego.addItem(MotorVehicleRegistrationFrame.vehicleArray.get(searchIndex).getPlateNumber() + "");
        }
    }

    //method to ensure that the accident id input isn't empty
    public void validateAccidentID() {
        if (idInput.equals("")) {
            JOptionPane.showInputDialog("You must input a 7 digit accident ID");
        }
    }

    //making sure that the accident id is a number - if not, return a JOptionPane with a warning
    public boolean isAccidentIDNumber() {
        try {
            Integer.parseInt(idInput.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showInputDialog("Accident ID must be a 7 digit number");
            return false;
        }
        return true;
    }

    
    //action listeners
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            Object source = e.getSource();
            if (source == saveButton) {
                validateAccidentID();
                isAccidentIDNumber();
                ArrayList<String> vehicles = new ArrayList<String>();
                Accident myAccident = new Accident(this.idInput.getText(), this.locationInput.getText(),
                        this.dateInput.getText(), this.commentInput.getText(), vehicles);
                MotorVehicleRegistrationFrame.accidents.add(myAccident);
            }
        }
        if (e.getActionCommand().equals("Clear")) {
            Object source = e.getSource();
            if (source == clearButton) {
                idInput.setText("");
                locationInput.setText("");
                dateInput.setText("");
                commentInput.setText("");
            }
        }
    }
}
