package com.tool.toolrental.dao;

import com.tool.toolrental.model.Renter;
import com.tool.toolrental.utils.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.tool.toolrental.constants.DBConstants.jdbcUrl;

public class RenterDB {
    private static final Logger log = LoggerFactory.getLogger(RenterDB.class);

    String insertSQL = "INSERT INTO renter (firstName,\n" +
            "lastName," +
            "streetAddress," +
            "city," +
            "state," +
            "zipcode," +
            "mobilePhoneNumber," +
            "homePhoneNumber" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public void Initialize(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            Initialize(connection);
        }catch(Exception e){
            log.error("Issue with making DB connection");
        }
    }


    public void Initialize(Connection connection){

        try(Statement statement = connection.createStatement()) {
            // Create the tools table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS renter ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "firstName VARCHAR(255),"
                    + "lastName VARCHAR(255), "
                    + "streetAddress VARCHAR(255), "
                    + "city VARCHAR(255),"
                    + "state VARCHAR(255),"
                    + "zipcode VARCHAR(255),"
                    + "mobilePhoneNumber VARCHAR(255),"
                    + "homePhoneNumber VARCHAR(255),"
                    + "UNIQUE (firstName, lastName, mobilePhoneNumber))";
            statement.execute(createTableSQL);

            PrefillData(connection);
        }catch (Exception e){
            log.error("Issue with creating Renter Table", e);
        }
    }
    private void PrefillData(Connection connection){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            // Example data to insert
            List<Renter> renterList = testRentersList();

            // Insert each entry using the prepared statement
            for (Renter renter : renterList) {
                preparedStatement.setString(1, (String) renter.getFirstName());
                preparedStatement.setString(2, (String) renter.getLastName());
                preparedStatement.setString(3, (String) renter.getStreetAddress());
                preparedStatement.setString(4, (String) renter.getCity());
                preparedStatement.setString(5, (String) renter.getState());
                preparedStatement.setString(6, (String) renter.getZipcode());
                preparedStatement.setString(7, (String) renter.getMobilePhoneNumber());
                preparedStatement.setString(8, (String) renter.getHomePhoneNumber());
                preparedStatement.addBatch(); // Add to batch
            }

            // Execute batch insert
            int[] affectedRecords = preparedStatement.executeBatch();
            log.debug(String.format("Inserted records: %s", affectedRecords.length));

        } catch (SQLException e) {
            log.error("Issue with initializing base Renter list", e);
        }
    }

    public List<Renter> getRentersList(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the tools table
            Statement statement = connection.createStatement();
            String querySQL = "SELECT * FROM renter";
            ResultSet resultSet = statement.executeQuery(querySQL);

            // Map ResultSet to List of Tool objects
            ResultSetMapper<Renter> mapper = new ResultSetMapper<>();
            List<Renter> renters = mapper.map(resultSet, Renter.class);

            return renters;
        }catch(Exception e){
            log.error("Issue with converting Renters list");
        }
        return null;
    }

    public boolean insertNewRenter(String firstName, String lastName, String streetAddress, String city, String state, String zipcode, String mobilePhoneNumber, String homePhoneNumber) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the tools table
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            Renter renter = new Renter(firstName,
                    lastName,
                    streetAddress,
                    city,
                    state,
                    zipcode,
                    mobilePhoneNumber,
                    homePhoneNumber);
            preparedStatement.setString(1, (String) renter.getFirstName());
            preparedStatement.setString(2, (String) renter.getLastName());
            preparedStatement.setString(3, (String) renter.getStreetAddress());
            preparedStatement.setString(4, (String) renter.getCity());
            preparedStatement.setString(5, (String) renter.getState());
            preparedStatement.setString(6, (String) renter.getZipcode());
            preparedStatement.setString(7, (String) renter.getMobilePhoneNumber());
            preparedStatement.setString(8, (String) renter.getHomePhoneNumber());

            preparedStatement.execute();

            return true;
        }catch(Exception e){
            log.error("Issue adding new Renter.", e);
            return false;
        }
    }

    public static List<Renter> testRentersList(){

        List<Renter> renterList = new ArrayList<>();

        renterList.add(new Renter("Billy", "Thornton", "123 A Street" , "Boston",
                "MA", "02360", "5081231568", ""));
        renterList.add(new Renter("Cheryl", "Bozeman", "234 Streetville Drive" , "Boston",
                "MA", "02360", "5084444444", ""));
        renterList.add(new Renter("William", "Lucas", "456 Worth Drive" , "Boston",
                "MA", "02360", "8501652368", "4561234568"));

        return renterList;
    }
}
