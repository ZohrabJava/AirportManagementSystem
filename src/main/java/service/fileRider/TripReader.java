package service.fileRider;

import model.PassInTrip;
import model.Trip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TripReader {
    public static String dateCompil(String date){
        return date.replace(" ","T");
    }
    public static Set<Trip> tripReader() {
        Set<Trip> trip = null;
        try {
            trip = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/trips.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                element=element.replace("'","");
                arr = element.split(",");

                Trip tr = new Trip( Integer.parseInt( arr[0]),Integer.parseInt(arr[1]),
                        arr[2],arr[3],arr[4], LocalDateTime.parse( dateCompil( arr[5])),
                        LocalDateTime.parse( dateCompil(arr[6])));
                trip.add(tr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trip;
    }
}
