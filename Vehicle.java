/*
Programmer: Alex Read
Course: Object Orientated Programming COIT11134 T120
File: Vehicle.java
Purpose: Vehicle class, database super class for motorcycles, light, and heavy vehicles
Date: 10 August 2020
*/
package assignmnent_2;

/**
 *
 * @author aread
 */
public class Vehicle
{

        private String plateNumber;
        private double engineCapacity; // The engine capacity of the vehicle in litres
        private String make;
        private String model;
        private int year; // 
        private int ownerId; // 10 digit alpha-numeric license number

    
    
        public Vehicle(String plateNumber, double engineCapacity, String make, String model, int year, int ownerId)
        {
                this.plateNumber = plateNumber;
                this.engineCapacity = engineCapacity;
                this.make = make;
                this.model = model;
                this.year = year;
                this.ownerId = ownerId;
        }
   
    
    
        public Vehicle()
        { // Start of the default constructor for the Rectangle class, which initialises the paramatised constructor
                this("000AA0", 3.5, "Toyota", "camry", 2016, 0); // Initialised the default constructor
        } // End of the default constructor
    
    

        Vehicle(Vehicle vehicle)
        { // Start of copy constructor for vehicle class
                this.plateNumber = vehicle.plateNumber;
                this.engineCapacity = vehicle.engineCapacity;
                this.make = vehicle.make;
                this.model = vehicle.model;
                this.year = vehicle.year;
                this.ownerId = vehicle.ownerId;
        } 

    
    
        public String getPlateNumber()
        {
                return plateNumber;
        }

    
    
        public void setPlateNumber(String plateNumber)
        {
                this.plateNumber = plateNumber;
        }

    
    
        public double getEngineCapacity()
        {
                return engineCapacity;
        }

    
    
        public void setEngineCapacity(double engineCapacity)
        {
                this.engineCapacity = engineCapacity;
        }

    
    
        public String getMake()
        {
                return make;
        }

    
    
        public void setMake(String make)
        {
                this.make = make;
        }

    
    
        public String getModel()
        {
                return model;
        }

    
    
        public void setModel(String model)
        {
                this.model = model;
        }

    
    
        public int getYear()
        {
                return year;
        }

    
    
        public void setYear(int year)
        {
                this.year = year;
        }

    
    
        public int getOwnerId()
        {
                return ownerId;
        }

    
    
        public void setOwnerId(int ownerId)
        {
                this.ownerId = ownerId;
        }

        public String getType()
        {
            return "Vehicle";
        }
        
        @Override
        public String toString()
        {
                return "Number Plate = " + plateNumber + ", Engine Capacity = " + engineCapacity + ", Make = " + make + ", Model = " + model + ", Year = " + year + ", License Number = " + ownerId;
        }
    
} // End of the vehicle class
