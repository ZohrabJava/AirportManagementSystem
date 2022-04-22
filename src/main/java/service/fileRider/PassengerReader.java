package service.fileRider;

import com.mysql.cj.xdevapi.Result;
import model.Company;
import model.Passenger;
import service.DatabaseConnectionService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PassengerReader {
    public static int addressIdSearch(String[] arr) {
        int addresId=0;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "SELECT address_id " +
                            "FROM address " +
                            "WHERE country = ? AND city = ?;"
            );

            statement.setString(1, arr[2].replace("'", ""));
            statement.setString(2, arr[3].replace("'", ""));

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                addresId = rs.getInt("address_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return addresId;
    }

    public static Set<Passenger> passengerReader() {
        Set<Passenger> passengers = null;
        try {
            passengers = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/passengers.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                element=element.replace("'","");
                arr = element.split(",");
                Passenger pas = new Passenger(arr[0], arr[1],addressIdSearch(arr));
                passengers.add(pas);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passengers;
    }
}
