package service.impl;

import model.Company;
import model.Passenger;
import service.CompanyInterface;
import service.DatabaseConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class CompanyImpl implements CompanyInterface {
    @Override
    public Company getById(long id) {
        Company company = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();
            query = "SELECT * from company " +
                    "where company_id='" + id + "'";

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                company = new Company(rs.getDate("founding_date"),
                        rs.getString("company_name"));
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
        return company;
    }

    @Override
    public Set<Company> getAll() {
        Company company;
        Set<Company> companies = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            companies = new HashSet<>();
            statement = connection.createStatement();
            query = "SELECT * from company ";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                company = new Company(rs.getDate("founding_date"),
                        rs.getString("company_name"));
                companies.add(company);
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
        return companies;

    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {
        Company company;
        Set<Company> companies = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            companies = new LinkedHashSet<>();
            statement = connection.createStatement();
            query = "SELECT * from company " +
                    "WHERE company_id>'" + offset * perPage + "' and company_id<='" + (offset * perPage + perPage) + "'" +
                    "ORDER BY " + sort + "";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                company = new Company(rs.getDate("founding_date"),
                        rs.getString("company_name"));
                companies.add(company);
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
        return companies;
    }

    @Override
    public void save(Company company) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {
            statement = connection.createStatement();
            query = "INSERT INTO company (founding_date,company_name)   " +
                    "VALUE ('" + company.getFoundingDate() + "' , '" + company.getCompanyName() + "')"
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
    public void update(Company company, String companyName) {

        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {

            statement = connection.createStatement();
            query = "UPDATE  company   " +
                    "Set founding_date='" + company.getFoundingDate() + "' ,company_name= '" + company.getCompanyName() + "'" +
                    "WHERE company_name= '" + companyName + "'";
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
    public void delete(long companyId) {

        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        String query;

        Statement statement = null;

        try {

            statement = connection.createStatement();
            query = " DELETE " +
                    " FROM pass_in_trip " +
                    "WHERE trip_id in (SELECT trip_number " +
                    "from trip " +
                    "where company_id = '" + companyId + "')";
            statement.execute(query);
            query = "DELETE " +
                    "FROM trip " +
                    "where  company_id =" + companyId;
            statement.execute(query);
            query = "DELETE " +
                    "FROM company " +
                    "where company_id=" + companyId;

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
