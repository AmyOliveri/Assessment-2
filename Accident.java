
package assignmnent_2;

import java.util.ArrayList;

public class Accident {

    String accidentID;
    String location;
    String date;
    String comment;

    ArrayList<String> accidentArray;
    
    public Accident(){
    this("0", "", "", "", new ArrayList<String>());
    }
    
    public Accident(String accidentID, String location, String date, String comment, ArrayList<String>accidentArray) {
        this.accidentID = accidentID;
        this.location = location;
        this.date = date;
        this.comment = comment;
        this.accidentArray = accidentArray;
    }

    Accident(Accident accident) { // Start of copy constructor for accident class
        this.accidentID = accident.accidentID;
        this.location = accident.location;
        this.date = accident.date;
        this.comment = accident.comment;
        this.accidentArray = accident.accidentArray;
    }

    public String getAccidentID() {
        return accidentID;
    }

    public void setAccidentID(String accidentID) {
        this.accidentID = accidentID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Accident ID = " + accidentID + ", location = " + location + ", Date = " + date + ", Comments = " + comment + ", Vehicles: " + accidentArray;
    }
/*
    public static void main(String[] args) {
        ArrayList<String> vehicles = new ArrayList<String>();
        vehicles.add("PQR123");
        vehicles.add("ASD456");
        Accident accident1 = new Accident("1234567", "Cairns", "090102", "minor", vehicles);
        Accident accident2 = new Accident(accident1);
        System.out.println(accident1);
        System.out.println(accident2);
        
        new GUI();
    }
*/
}
