package edu.wpi.cs3733.D23.teamQ.db.obj;

public class Sign {

    int kiosk;
    String date;
    String destination;
    String direction;

    public void sign(int kiosk, String date, String destination, String direction){
        this.kiosk = kiosk;
        this.date = date;
        this.destination = destination;
        this.direction = direction;
    }
}
