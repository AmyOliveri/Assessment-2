
package assignmnent_2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DisplayAccident extends JPanel implements ActionListener{
    //private JLabel boxLabel;
    private JLabel selectVehicle;
    private JComboBox selectBox;
    private JTextArea box;
    private JButton showButton;
    
    public DisplayAccident(){
    selectBox = new JComboBox();
    selectVehicle = new JLabel("Select vehicle: ");
    box = new JTextArea();
    box.setText("Accident Information...");
    showButton = new JButton("Show Accidents");
   
    setLayout(new GridLayout(2,2));
    add(selectVehicle);
    add(selectBox);
    add(box);
    add(showButton);
    
    }
@Override
    public void actionPerformed(ActionEvent e) {
         if (e.getActionCommand().equals("Save")) {
         Object source = e.getSource();
            if (source == showButton) {
                
            }
            }
    }
    }
    

