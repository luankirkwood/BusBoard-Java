package training.busboard.web;

import training.busboard.Arrivals;
import java.util.ArrayList;

public class BusInfo {
    private final String postcode;
    ArrayList<Arrivals> arrivals = new ArrayList<>();

    public BusInfo(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public ArrayList<Arrivals> getArrivals() { return arrivals; }

//    public ArrayList newArrivals() {
//        arrivals.add(new Arrivals("Station", "487", "Destination", 300));
//
//        return arrivals;
//    }
}
