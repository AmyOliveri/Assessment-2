/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: HeavyVehicle.java
Purpose: HeavyVehicle class, database subclass of the vehicle class used to store heavy vehicles with load capacities
Date: 10 August 2020
*/
package assignmnent_2;

/**
 *
 * @author aread
 */
public class HeavyVehicle extends Vehicle {

        private String plateNumber;
        private int loadCapacity;

        public HeavyVehicle (String plateNumber, int loadCapacity, double engineCapacity, String make, String model, int year, int ownerId)
        {
                super(plateNumber, engineCapacity, make, model, year, ownerId);
                this.plateNumber = plateNumber;
                this.loadCapacity = loadCapacity;
        }
   
    
    
        public HeavyVehicle()
        {
                this("000AA0", 23000, 6, "Isuzu", "FVM 23O-3OO 6x2", 1999, 0);
        }
    
    

        HeavyVehicle(HeavyVehicle heavyVehicle)
        { // Start of copy constructor for vehicle class
                this.plateNumber = heavyVehicle.plateNumber;
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

        
        
        public int getLoadCapacity()
        {
                return loadCapacity;
        }

        
        
        public void setLoadCapacity(int loadCapacity)
        {
                this.loadCapacity = loadCapacity;
        }

        public String getType()
        {
            return "Heavy Vehicle";
        }
        
        @Override
        public String toString()
        {
                return super.toString() + ", Load Capacity = " + loadCapacity;
        }
        
}
