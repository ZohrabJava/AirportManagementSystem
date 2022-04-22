import model.*;
import service.DBInput;
import service.fileRider.*;
import service.impl.CompanyImpl;
import service.impl.PassengerImpl;
import service.impl.TripImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class conect {
    public static void main(String[] args) {
//        Set<Address> addresses= AddressReader.addressesRead();
//        for(Address ad:addresses){
//            System.out.println(ad);
//        }
//        System.out.println(addresses.size());

//        Set<Company> companies= CompanyReader.companyReader();
//        for (Company ad : companies) {
//            System.out.println(ad);
//        }
//        System.out.println(companies.size());

//        DBInput.addressInput(AddressReader.addressesRead());

//        System.out.println(CompanyReader.companyReader());
//        DBInput.companyImput(CompanyReader.companyReader());


//        DBInput.passengerInput(PassengerReader.passengerReader());
//        System.out.println(TripReader.tripReader());

//        DBInput.tripInput(TripReader.tripReader());

//        System.out.println(PassInTripReader.PassInTripReader());
//        DBInput.passInTripInput(PassInTripReader.PassInTripReader());

//        CompanyImpl company=new CompanyImpl();
//        System.out.println(company.getById(55));
//        for(Company comp:company.getAll()){
//            System.out.println(comp);
//        }

//        for(Company comp:company.get(10,2,"company_name")){
//            System.out.println(comp);
//        }

//        Company com=new Company(LocalDate.parse("2005-05-29"),"Mxo");
//        company.update(com,"Sis");

//        company.delete(4);

        PassengerImpl passenger = new PassengerImpl();

//        System.out.println(passenger.getById(15));

//        for (Passenger comp : passenger.getAll()) {
//            System.out.println(comp);
//        }

//        for (Passenger pass : passenger.get(10, 2, "passenger_name")) {
//            System.out.println(pass);
//        }
        Passenger pas=new Passenger("Mxo","256246",25);
//        passenger.update(pas,1001);
//        passenger.delete(37);

//        List<Passenger> passengers=passenger.getPassengersOfTrip(7771);
//        for (Passenger p:passengers){
//            System.out.println(p);
//    }
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));

//        System.out.println( "1a".substring(1,2));
//        System.out.println(passenger.autoPlace(1182));
//        System.out.println(Character.toString('1'+1));

        //Trip test
//        Trip trip=new TripImpl().getById(1182);

//        System.out.println(trip);
//        Set<Trip> trips=new TripImpl().getAll();
//        for (Trip t:trips){
//            System.out.println(t);
//        }

//        Set<Trip> trips=new TripImpl().get(5,2,"plane");
//        for (Trip t:trips){
//            System.out.println(t);
//        }

        Trip tr=new Trip(2358,20,"Boying",
                "Armenia","Russia",LocalDateTime.parse( "2022-04-22T14:00"),
                LocalDateTime.parse( "2022-04-22T15:00"));
        TripImpl trip=new TripImpl();
//        trip.save(tr);
//        trip.update(tr,1100);
//        passenger.registerTrip(tr,pas);
//        passenger.cancelTrip(1002,2358);
//            trip.delete(2358);
        List<Trip> townfrom=trip.getTripsFrom("Paris");
        for (Trip t:townfrom){
            System.out.println(t);
        }
    }
}
