
package assignmnent_2;
/*
Student Name: Amy Oliveri
Student Number: 12095536
Assignment 2 COIT11134 OOP
*/
import java.util.ArrayList;

public class Accident {
    //variables used in accident
    String accidentID;
    String location;
    String date;
    String comment;

    //declaring the arraylist for the accident arraylist
    ArrayList<String> accidentArray;
    
    //default constructor for an accident object
    public Accident(){
    this("0", "", "", "", new ArrayList<String>());
    }
    
    //parameterised constructor
    public Accident(String accidentID, String location, String date, String comment, ArrayList<String>accidentArray) {
        this.accidentID = accidentID;
        this.location = location;
        this.date = date;
        this.comment = comment;
        this.accidentArray = accidentArray;
    }

    // Start of copy constructor for accident class
    Accident(Accident accident) { 
        this.accidentID = accident.accidentID;
        this.location = accident.location;
        this.date = accident.date;
        this.comment = accident.comment;
        this.accidentArray = accident.accidentArray;
    }

    Accident(String type, String accidentID, String location, String date, String comment, ArrayList<String> vehicles_involved) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    //accessor and mutator afor accident ID
    public String getAccidentID() {
        return accidentID;
    }

    public void setAccidentID(String accidentID) {
        this.accidentID = accidentID;
    }

    //accessor and mutator for location variable
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //accessor and mutator for date variable 
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //accessor and mutator for comment variable
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    //toString for accident array
    @Override
    public String toString() {
        return "Accident ID = " + accidentID + ", location = " + location + ", Date = " + date + ", Comments = " + comment + ", Vehicles: " + accidentArray;
    }

}
