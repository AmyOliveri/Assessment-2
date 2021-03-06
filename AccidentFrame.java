/*
Student Name: Amy Oliveri
Student Number: 12095536
Assignment 2 COIT11134 OOP
*/

package assignmnent_2;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class AccidentFrame extends JFrame {
  
    //creating accident panel called accidentPanel
   public static AccidentPanel accidentPanel;
   
   //adding the accidentPanel to the accidentFrame with the title "Queensland Road and Transport Authority"
   public AccidentFrame(){
       super("Motor Vehicle Registration Application");
       accidentPanel = new AccidentPanel();
       JTabbedPane tabbedPane = new JTabbedPane();
       tabbedPane.addTab("Add new accident", accidentPanel); 
       tabbedPane.addTab("View all accidents", new DisplayAccident());
       JLabel titleLabel=new JLabel("Queensland Road and Transport Authority");
       
       this.add(tabbedPane);
       
   }
}
