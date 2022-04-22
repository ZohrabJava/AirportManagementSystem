package service;

import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class DBInput {
    public static String dateCompil(String date) {
        return date.replace(" ", "T");
    }

    public static void addressInput(Set<Address> addresses) {

        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;
        try {
            statement = connection.createStatement();
            for (Address address : addresses) {
                query = "INSERT INTO address ( country, city)" +
                        " VALUES ('" + address.getCountry().replace("'", "") + "' ,'" + address.getCity().replace("'", "") + "');";
                statement.execute(query);
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

    }

    public static String dateConvert(String date) {
        String[] dates = date.split("/");
        if (Integer.parseInt(dates[1]) < 10) {
            dates[1] = "0" + dates[1];
        }
        if (Integer.parseInt(dates[0]) < 10) {
            dates[0] = "0" + dates[0];
        }
        date = dates[2] + "-" + dates[0] + "-" + dates[1];
        return date;
    }

    public static void companyImput(Set<Company> companies) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();


            for (Company company : companies) {

                query = "INSERT INTO company ( founding_date, company_name)" +
                        " VALUES ('" + company.getFoundingDate() + "' ,'" + company.getCompanyName() + "');";

                statement.execute(query);
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
    }

    public static void passengerInput(Set<Passenger> passengers) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();
            for (Passenger passenger : passengers) {
                query = "INSERT INTO passenger ( passenger_name, passenger_phone,address_id)" +
                        " VALUES ('" + passenger.getPassengerName() + "' ,'" + passenger.getPassengerPhone() + "' ,'" + passenger.getAddressId() + "');";
                statement.execute(query);
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
    }

    public static void tripInput(Set<Trip> trips) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();
            for (Trip trip : trips) {
                query = "INSERT INTO trip (trip_number,company_id,plane,town_from,town_to,time_out,time_in)" +
                        " VALUES ('" + trip.getTripNumber() + "' ,'" + trip.getCompanyId() + "' ,'"
                        + trip.getPlane() + "' ,'" + trip.getTownFrom() + "' ,'" + trip.getTownTo() +
                        "' ,'" + trip.getTimeOut() + "' ,'" + trip.getTimeIn() + "');";
                statement.execute(query);
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
    }

    public static void passInTripInput(Set<PassInTrip> passInTrips) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();


            for (PassInTrip passInTrip : passInTrips) {

                query = "INSERT INTO pass_in_trip " +
                        " VALUES ('" + passInTrip.getTripId() + "' ,'" + passInTrip.getPsgId() +
                        "' ,'" + passInTrip.getDate() + "' ,'" + passInTrip.getPlace() + "');";
                statement.execute(query);
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
    }

}
