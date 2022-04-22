package service.impl;

import model.Company;
import model.Passenger;
import model.Trip;
import service.DatabaseConnectionService;
import service.PassengerInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PassengerImpl implements PassengerInterface {
    public String autoPlace(int tripNumber) {
        String place = "1a";
        Set<String> places = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();
            places = new HashSet<>();
            query = "SELECT place " +
                    "from pass_in_trip " +
                    "where trip_id=" + tripNumber +
                    " order by place";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                places.add(rs.getString("place"));
            }
            while (places.contains(place)) {
                if (place.substring(0, 1).charAt(0) < '6') {
                    place = Character.toString(place.charAt(0) + 1) + place.charAt(1);
                } else {
                    place = "1" + Character.toString(place.charAt(1) + 1);
                }
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
        return place;
    }

    @Override
    public Passenger getById(long id) {
        Passenger passenger = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();
            query = "SELECT * from passenger " +
                    "where passenger_id='" + id + "'";

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                passenger = new Passenger(rs.getString("passenger_name"),
                        rs.getString("passenger_phone"), rs.getInt("address_id"));
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
        return passenger;
    }

    @Override
    public Set<Passenger> getAll() {
        Passenger passenger;
        Set<Passenger> passengers = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            passengers = new HashSet<>();
            statement = connection.createStatement();
            query = "SELECT * from passenger ";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                passenger = new Passenger(rs.getString("passenger_name"),
                        rs.getString("passenger_phone"), rs.getInt("address_id"));
                passengers.add(passenger);
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
        return passengers;

    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {

        Passenger passenger;
        Set<Passenger> passengers = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            passengers = new LinkedHashSet<>();
            statement = connection.createStatement();
            query = "SELECT * from passenger " +
                    "WHERE passenger_id>'" + offset * perPage + "' and passenger_id<='" + (offset * perPage + perPage) + "' " +
                    "ORDER BY " + sort + " ";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                passenger = new Passenger(rs.getString("passenger_name"),
                        rs.getString("passenger_phone"), rs.getInt("address_id"));
                passengers.add(passenger);
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
        return passengers;
    }

    @Override
    public void save(Passenger passenger) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();
            query = "INSERT INTO passenger (passenger_name,passenger_phone,address_id)   " +
                    "VALUE ('" + passenger.getPassengerName() + "' , '" + passenger.getPassengerPhone() + "' , '" + passenger.getAddressId() + "')"
            ;

            statement.execute(query);
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

    @Override
    public void update(Passenger passenger, int id) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {

            statement = connection.createStatement();
            query = "UPDATE  passenger   " +
                    "Set passenger_name='" + passenger.getPassengerName() +
                    "' ,passenger_phone= '" + passenger.getPassengerPhone() +
                    "' ,address_id= '" + passenger.getAddressId() + "'" +
                    "WHERE passenger_id= '" + id + "'";
            ;

            statement.execute(query);
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

    @Override
    public void delete(long passengerId) {

        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {

            statement = connection.createStatement();
            query = " DELETE " +
                    " FROM pass_in_trip " +
                    "WHERE psg_id = (SELECT passenger_id " +
                    "from passenger " +
                    "where passenger_id = '" + passengerId + "')";
            statement.execute(query);
            query = "DELETE " +
                    "FROM passenger " +
                    "where  passenger_id =" + passengerId;
            statement.execute(query);
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

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        Passenger passenger;
        List<Passenger> passengers = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            passengers = new LinkedList<>();
            statement = connection.createStatement();
            query = "select passenger_name,passenger_phone,address_id " +
                    "from passenger " +
                    "join pass_in_trip pit on passenger.passenger_id = pit.psg_id " +
                    "where trip_id =" + tripNumber;

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                passenger = new Passenger(rs.getString("passenger_name"),
                        rs.getString("passenger_phone"), rs.getInt("address_id"));
                passengers.add(passenger);
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

        return passengers;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();

            query = "SELECT passenger_id " +
                    "from    passenger " +
                    "where passenger.passenger_name='" + passenger.getPassengerName()+"'";

            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                save(passenger);
            }
            query = "SELECT trip_number " +
                    "from    trip " +
                    "where trip_number=" + trip.getTripNumber();
             rs = statement.executeQuery(query);
            if (!rs.next()) {
                TripImpl tr =new TripImpl();
                tr.save (trip);
            }
            query = "INSERT INTO pass_in_trip () " +
                    "VALUES ("+trip.getTripNumber() +", (SELECT passenger_id " +
                    "from    passenger " +
                    "where passenger.passenger_name='" + passenger.getPassengerName() + "'), " +
                    "LOCALTIME, '" + autoPlace(trip.getTripNumber()) + "')";
            ;

            statement.execute(query);
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

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {

            statement = connection.createStatement();

            query = "DELETE " +
                    "FROM pass_in_trip " +
                    "where  trip_id =" + tripNumber+" and psg_id = "+passengerId+"";

            statement.execute(query);
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
