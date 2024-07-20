package com.tool.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.tool.database.constants.DBConstants.jdbcUrl;

public class ClerkDB {
    private static final Logger log = LoggerFactory.getLogger(ClerkDB.class);

    String insertSQL = "INSERT INTO clerk (firstName,\n" +
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
            String createTableSQL = "CREATE TABLE IF NOT EXISTS clerk ("
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
            log.error("Issue with creating Clerk Table", e);
        }
    }
    private void PrefillData(Connection connection){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            // Example data to insert
            List<Clerk> clerkList = new ArrayList<>();

            clerkList.add(new Clerk("Sue", "Bob", "Over There Rd" , "Plymouth",
                    "MA", "02360", "5081444444", ""));
            clerkList.add(new Clerk("Frank", "Williams", "2 Not Main Street" , "Plymouth",
                    "MA", "02360", "5083333333", ""));
            clerkList.add(new Clerk("Bill", "Hernandez", "3 Main Street" , "Plymouth",
                    "MA", "02360", "5081566666", "4085151515"));

            // Insert each entry using the prepared statement
            for (Clerk clerk : clerkList) {
                preparedStatement.setString(1, (String) clerk.getFirstName());
                preparedStatement.setString(2, (String) clerk.getLastName());
                preparedStatement.setString(3, (String) clerk.getStreetAddress());
                preparedStatement.setString(4, (String) clerk.getCity());
                preparedStatement.setString(5, (String) clerk.getState());
                preparedStatement.setString(6, (String) clerk.getZipcode());
                preparedStatement.setString(7, (String) clerk.getMobilePhoneNumber());
                preparedStatement.setString(8, (String) clerk.getHomePhoneNumber());
                preparedStatement.addBatch(); // Add to batch
            }

            // Execute batch insert
            int[] affectedRecords = preparedStatement.executeBatch();
            log.debug(String.format("Inserted records: %s", affectedRecords.length));

        } catch (SQLException e) {
            log.error("Issue with initializing base Clerk list", e);
        }
    }

    public List<Clerk> getClerksList(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the tools table
            Statement statement = connection.createStatement();
            String querySQL = "SELECT * FROM clerk";
            ResultSet resultSet = statement.executeQuery(querySQL);

            // Map ResultSet to List of Tool objects
            ResultSetMapper<Clerk> mapper = new ResultSetMapper<>();
            List<Clerk> clerks = mapper.map(resultSet, Clerk.class);

            // Print the tools
            for (Clerk clerk : clerks) {
                log.debug(clerk.toString());
            }
            return clerks;
        }catch(Exception e){
            log.error("Issue with converting Clerks list");
        }
        return null;
    }

    public boolean insertNewClerk(String firstName, String lastName, String streetAddress, String city, String state, String zipcode, String mobilePhoneNumber, String homePhoneNumber) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the tools table
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            Clerk clerk = new Clerk(firstName,
                    lastName,
                    streetAddress,
                    city,
                    state,
                    zipcode,
                    mobilePhoneNumber,
                    homePhoneNumber);
            preparedStatement.setString(1, (String) clerk.getFirstName());
            preparedStatement.setString(2, (String) clerk.getLastName());
            preparedStatement.setString(3, (String) clerk.getStreetAddress());
            preparedStatement.setString(4, (String) clerk.getCity());
            preparedStatement.setString(5, (String) clerk.getState());
            preparedStatement.setString(6, (String) clerk.getZipcode());
            preparedStatement.setString(7, (String) clerk.getMobilePhoneNumber());
            preparedStatement.setString(8, (String) clerk.getHomePhoneNumber());

            preparedStatement.execute();

            return true;
        }catch(Exception e){
            log.error("Issue adding new Clerk.", e);
            return false;
        }
    }
}
