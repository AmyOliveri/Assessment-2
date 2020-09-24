/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: CorporateOwner.java
Purpose: CorporateOwner class, database subclass of the owner class, used to store corporate owners with ABN fields
Date: 10 August 2020
*/
package assignmnent_2;

/**
 *
 * @author aread
 */
public class CorporateOwner extends Owner
{

        private String australianBusinessNumber;

        public CorporateOwner (int id, String australianBusinessNumber, String firstName, String lastName, String address, String phoneNumber)
        {
                super(id, firstName, lastName, address, phoneNumber);
                this.australianBusinessNumber = australianBusinessNumber;
        }
   
    
    
        public CorporateOwner()
        {
                this(0, "0", "Sam", "Sample", "27 Latrobe Street \n East Brisbane Qld 4670", "(07) 3821 5849");
        }
    
    

        CorporateOwner(CorporateOwner corporateOwner)
        {
                this.australianBusinessNumber = corporateOwner.australianBusinessNumber;
        }  

        
        
        public String getAustralianBusinessNumber()
        {
                return australianBusinessNumber;
        }
        
        

        public void setAustralianBusinessNumber(String australianBusinessNumber)
        {
                this.australianBusinessNumber = australianBusinessNumber;
        }

        
        
        @Override
        public String toString()
        {
                return super.toString() + ", Australian Business Number = " + australianBusinessNumber;
        }

}
