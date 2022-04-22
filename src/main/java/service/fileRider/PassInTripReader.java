package service.fileRider;

import model.PassInTrip;
import model.Passenger;
import service.DBInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PassInTripReader {
    public static String dateCompil(String date){
        return date.replace(" ","T");
    }
    public static Set<PassInTrip> PassInTripReader() {
        Set<PassInTrip> pit = null;
        try {
            pit = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/pass_in_trip.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                element=element.replace("'","");
                arr = element.split(",");
                PassInTrip pi = new PassInTrip(Integer.parseInt( arr[0]),Integer.parseInt( arr[1]),
                        LocalDateTime.parse(dateCompil( arr[2])),arr[3]);
                pit.add(pi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pit;
    }
}
