/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: PrivateOwner.java
Purpose: Private owner class, database subclass of the owner class, used to enter private owners with date of births
Date: 10 August 2020
*/
package assignmnent_2;

/**
 *
 * @author aread
 */
public class PrivateOwner extends Owner
{

        private String dateOfBirth;

        public PrivateOwner (int id, String dateOfBirth, String firstName, String lastName, String address, String phoneNumber)
        {
                super(id, firstName, lastName, address, phoneNumber);
                this.dateOfBirth = dateOfBirth;
        }
   
    
    
        public PrivateOwner()
        { // Start of the default constructor for the Rectangle class, which initialises the paramatised constructor
                this(0, "29/07/2020", "Sam", "Sample", "27 Latrobe Street \n East Brisbane Qld 4670", "(07) 3821 5849"); // Initialised the default constructor
        } // End of the default constructor
    
    

        PrivateOwner(PrivateOwner privateOwner)
        { // Start of copy constructor for vehicle class
                this.dateOfBirth = privateOwner.dateOfBirth;
        }  

        
        
        public String getDateOfBirth()
        {
                return dateOfBirth;
        }

        
        
        public void setDateOfBirth(String dateOfBirth)
        {
                this.dateOfBirth = dateOfBirth;
        }

        
        
        @Override
        public String toString()
        {
                return super.toString() + ", Date of Birth = " + dateOfBirth;
        }

}
