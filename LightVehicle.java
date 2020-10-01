/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: LightVehicle.java
Purpose: LightVehicle class, database subclass of the vehicle class, used to store corporate vehicles and their number of seats
Date: 10 August 2020
*/
package assignmnent_2;

public class LightVehicle extends Vehicle {

        private String plateNumber;
        private int numberOfSeats;

        public LightVehicle (String plateNumber, int numberOfSeats, double engineCapacity, String make, String model, int year, int ownerId)
        {
                super(plateNumber, engineCapacity, make, model, year, ownerId);
                this.plateNumber = plateNumber;
                this.numberOfSeats = numberOfSeats;
        }
   
    
    
        public LightVehicle()
        {
                this("000AA0", 4, 3.5, "Toyota", "camry", 2016, 0);
        }
    
    

        LightVehicle(LightVehicle lightVehicle)
        {
                this.plateNumber = lightVehicle.plateNumber;
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
        
        

        public int getNumberOfSeats()
        {
                return numberOfSeats;
        }
        
        

        public void setNumberOfSeats(int numberOfSeats)
        {
                this.numberOfSeats = numberOfSeats;
        }

        public String getType()
        {
            return "Light Vehicle";
        }
        
        @Override
        public String toString()
        {
                return super.toString() + ", Number of Seats = " + numberOfSeats;
        }
        
}
