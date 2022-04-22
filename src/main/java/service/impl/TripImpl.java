package service.impl;

import model.Passenger;
import model.Trip;
import service.DatabaseConnectionService;
import service.TripInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

public class TripImpl implements TripInterface {

    public static String dateCompil(String date) {
        return date.replace(" ", "T");
    }

    @Override
    public Trip getById(long id) {
        Trip trip = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();
            query = "SELECT * from trip " +
                    "where trip_number='" + id + "'";

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                trip = new Trip(rs.getInt("trip_number"),
                        rs.getInt("company_id"), rs.getString("plane"),
                        rs.getString("town_from"), rs.getString("town_to"),
                        rs.getTimestamp("time_out").toLocalDateTime(),
                        rs.getTimestamp("time_in").toLocalDateTime());
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
        return trip;
    }

    @Override
    public Set<Trip> getAll() {
        Trip trip;
        Set<Trip> trips = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            trips = new HashSet<>();
            statement = connection.createStatement();
            query = "SELECT * from trip ";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                trip = new Trip(rs.getInt("trip_number"),
                        rs.getInt("company_id"), rs.getString("plane"),
                        rs.getString("town_from"), rs.getString("town_to"),
                        rs.getTimestamp("time_out").toLocalDateTime(),
                        rs.getTimestamp("time_in").toLocalDateTime());
                trips.add(trip);
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
        return trips;
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {

        Trip trip;
        Set<Trip> trips = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            trips = new LinkedHashSet<>();
            statement = connection.createStatement();
            query = "SELECT * from trip " +
                    "ORDER BY " + sort + " " +
                    "LIMIT " + offset + " ," + perPage + " ";
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                trip = new Trip(rs.getInt("trip_number"),
                        rs.getInt("company_id"), rs.getString("plane"),
                        rs.getString("town_from"), rs.getString("town_to"),
                        rs.getTimestamp("time_out").toLocalDateTime(),
                        rs.getTimestamp("time_in").toLocalDateTime());
                trips.add(trip);
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
        return trips;
    }

    @Override
    public void save(Trip trip) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();
            query = "INSERT INTO trip(trip_number, company_id, plane, town_from, town_to, time_out, time_in) " +
                    "VALUE ('" + trip.getTripNumber() + "' , '" + trip.getCompanyId() +
                    "' , '" + trip.getPlane() + "' , '" + trip.getTownFrom() + "' , '" + trip.getTownTo() +
                    "' , '" + trip.getTimeOut() + "' , '" + trip.getTimeIn() + "')"
            ;
            System.out.println(query);
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
    public void update(Trip trip, int id) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {

            statement = connection.createStatement();
            query = "UPDATE  trip   " +
                    "Set company_id='" + trip.getCompanyId() +
                    "' ,plane= '" + trip.getPlane() +
                    "' ,town_from= '" + trip.getTownFrom() +
                    "' ,town_to= '" + trip.getTownTo() +
                    "' ,time_out= '" + trip.getTimeOut() +
                    "' ,time_in= '" + trip.getTimeIn() + "'" +
                    "WHERE trip_number= '" + id + "'";
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
    public void delete(long tripId) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {

            statement = connection.createStatement();
            query = " DELETE " +
                    " FROM pass_in_trip " +
                    "WHERE trip_id = (SELECT trip_number " +
                    "from trip " +
                    "where trip_number = " + tripId + ")";
            statement.execute(query);
            query = "DELETE " +
                    "FROM trip " +
                    "where  trip_number =" + tripId;
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
    public List<Trip> getTripsFrom(String city) {
        Trip trip;
        List<Trip> trips = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            trips = new ArrayList<>();
            statement = connection.createStatement();
            query = "SELECT * from trip " +
                    "WHERE town_from='"+city+"'";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                trip = new Trip(rs.getInt("trip_number"),
                        rs.getInt("company_id"), rs.getString("plane"),
                        rs.getString("town_from"), rs.getString("town_to"),
                        rs.getTimestamp("time_out").toLocalDateTime(),
                        rs.getTimestamp("time_in").toLocalDateTime());
                trips.add(trip);
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
        return trips;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        Trip trip;
        List<Trip> trips = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            trips = new ArrayList<>();
            statement = connection.createStatement();
            query = "SELECT * from trip " +
                    "WHERE town_to='"+city+"'";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                trip = new Trip(rs.getInt("trip_number"),
                        rs.getInt("company_id"), rs.getString("plane"),
                        rs.getString("town_from"), rs.getString("town_to"),
                        rs.getTimestamp("time_out").toLocalDateTime(),
                        rs.getTimestamp("time_in").toLocalDateTime());
                trips.add(trip);
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
        return trips;
    }
}
