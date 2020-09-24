/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: Owner.java
Purpose: Owner class, database super for private and corporate owners
Date: 10 August 2020
*/
package assignmnent_2;

/**
 *
 * @author aread
 */
public class Owner
{

        private int id;
        private String firstName; // The engine capacity of the vehicle in litres
        private String lastName;
        private String address;
        private String phoneNumber; // 

        public Owner(int id, String firstName, String lastName, String address, String phoneNumber)
        {
                this.id = id;
                this.firstName = firstName;
                this.lastName = lastName;
                this.address = address;
                this.phoneNumber = phoneNumber;
        }
   
    
    
        public Owner()
        { // Start of the default constructor for the Rectangle class, which initialises the paramatised constructor
                this(0, "Sam", "Sample", "27 Latrobe Street \n East Brisbane Qld 4670", "(07) 3821 5849"); // Initialised the default constructor
        } // End of the default constructor
    
    

        Owner(Owner owner)
        { // Start of copy constructor for vehicle class
                this.id = owner.id;
                this.firstName = owner.firstName;
                this.lastName = owner.lastName;
                this.address = owner.address;
                this.phoneNumber = owner.phoneNumber;
        } 

        
        
        public int getId()
        {
                return id;
        }

        
        
        public void setId(int id)
        {
                this.id = id;
        }

        
        
        public String getFirstName()
        {
                return firstName;
        }
        
        

        public void setFirstName(String firstName)
        {
                this.firstName = firstName;
        }

        
        
        public String getLastName()
        {
                return lastName;
        }

        
        
        public void setLastName(String lastName)
        {
                this.lastName = lastName;
        }

        
        
        public String getAddress()
        {
                return address;
        }

        
        
        public void setAddress(String address)
        {
                this.address = address;
        }

    
    
        public String getPhoneNumber()
        {
                return phoneNumber;
        }

        
        
        public void setPhoneNumber(String phoneNumber)
        {
                this.phoneNumber = phoneNumber;
        }

        
        
        @Override
        public String toString()
        {
                return "License Number = " + id + ", First Name = " + firstName + ", Last Name = " + lastName + ", Address = " + address + ", Phone Number = " + phoneNumber;
        }

} // End of the vehicle class

