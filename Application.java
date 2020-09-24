/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: Application.java
Purpose: Application class to contain the main method forthe application and to facilitate the universal exit functionality contained in MtorVehicleRegistrationFrame
Date: 10 August 2020
*/
package assignmnent_2;

/**
 *
 * @author aread
 */
public class Application
{
        public static void main(String[] args)
        {

                MotorVehicleRegistrationFrame myApp=new MotorVehicleRegistrationFrame();
                myApp.setVisible(true);
                myApp.setSize(880, 500);
                myApp.setResizable(false);
                myApp.setDefaultCloseOperation(MotorVehicleRegistrationFrame.DO_NOTHING_ON_CLOSE);
        }
    
}
