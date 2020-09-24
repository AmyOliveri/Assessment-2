/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: LightVehicle.java
Purpose: Motorcycle class, database subclass of the vehicle class, used to store motorcycles
Date: 10 August 2020
*/
package assignmnent_2;

public class Motorcycle extends Vehicle {

        private String plateNumber;

        public Motorcycle (String plateNumber, double engineCapacity, String make, String model, int year, int ownerId)
        {
                super(plateNumber, engineCapacity, make, model, year, ownerId);
                this.plateNumber = plateNumber;
        }
   
    
    
        public Motorcycle()
        { // Start of the default constructor for the Rectangle class, which initialises the paramatised constructor
                this("000Ta", 3.5, "Suzuki", "GSX-R125", 2017, 0); // Initialised the default constructor
        } // End of the default constructor
    
    

        Motorcycle(Motorcycle motorcycle)
        { // Start of copy constructor for vehicle class
                this.plateNumber = motorcycle.plateNumber;
        } 

        
        
        @Override
        public String getPlateNumber()
        {
                return plateNumber;
        }

        
        
        @Override
        public void setPlateNumber(String plateNumber)
        {
                this.plateNumber = plateNumber;
        }

        
        
        @Override
        public String toString()
        {
                return super.toString();
        }

}
