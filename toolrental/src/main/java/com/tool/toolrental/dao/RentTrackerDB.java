package com.tool.toolrental.dao;

import com.tool.toolrental.model.RentTracker;
import com.tool.toolrental.utils.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.tool.toolrental.constants.DBConstants.jdbcUrl;

public class RentTrackerDB {

    /**
     * id
     * toolId
     * renterId
     * clerkId
     * rentalDays
     * checkoutDate
     * chargeDays
     * preDiscountCharge
     * discountPercent
     * discountAmount
     * finalCharge
     */

    private static final Logger log = LoggerFactory.getLogger(RenterDB.class);

    String insertSQL = "INSERT INTO rentTracker (" +
            "toolId," +
            "renterId," +
            "clerkId," +
            "rentalDays," +
            "checkoutDate," +
            "chargeDays," +
            "preDiscountCharge," +
            "discountPercent," +
            "discountAmount," +
            "finalCharge" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void Initialize(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            Initialize(connection);
        }catch(Exception e){
            log.error("Issue with making DB connection");
        }
    }


    public void Initialize(Connection connection){

        try(Statement statement = connection.createStatement()) {
            // Create the rentTracker table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS rentTracker ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "toolId VARCHAR(255),"
                    + "renterId VARCHAR(255), "
                    + "clerkId VARCHAR(255), "
                    + "rentalDays VARCHAR(255),"
                    + "checkoutDate VARCHAR(255),"
                    + "chargeDays VARCHAR(255),"
                    + "preDiscountCharge VARCHAR(255),"
                    + "discountPercent VARCHAR(255),"
                    + "discountAmount VARCHAR(255),"
                    + "finalCharge VARCHAR(255))";
            statement.execute(createTableSQL);

            PrefillData(connection);
        }catch (Exception e){
            log.error("Issue with creating rent tracking Table", e);
        }
    }

    private void PrefillData(Connection connection){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            // Example data to insert
            List<RentTracker> rentTrackerList = new ArrayList<>();

            rentTrackerList.add(new RentTracker(
                    "LADS",
                    1,
                    2,
                    "4",
                    LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                    "2",
                    "3.98",
                    "25",
                    String.valueOf(3.98*.25),
                    String.valueOf(3.98*.75)));

            // Insert each entry using the prepared statement
            for (RentTracker rentTrack : rentTrackerList) {
                preparedStatement.setString(1,  rentTrack.getToolId());
                preparedStatement.setInt(   2,  rentTrack.getRenterId());
                preparedStatement.setInt(   3,  rentTrack.getClerkId());
                preparedStatement.setString(4,  rentTrack.getRentalDays());
                preparedStatement.setString(5,  rentTrack.getCheckoutDate());
                preparedStatement.setString(6,  rentTrack.getChargeDays());
                preparedStatement.setString(7,  rentTrack.getPreDiscountCharge());
                preparedStatement.setString(8,  rentTrack.getDiscountPercent());
                preparedStatement.setString(9,  rentTrack.getDiscountAmount());
                preparedStatement.setString(10, rentTrack.getFinalCharge());
                preparedStatement.addBatch(); // Add to batch
            }

            // Execute batch insert
            int[] affectedRecords = preparedStatement.executeBatch();
            log.debug(String.format("Inserted records: %s", affectedRecords.length));

        } catch (SQLException e) {
            log.error("Issue with initializing base Renter list", e);
        }
    }

    public List<RentTracker> getRentersList(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the rentTracker table
            Statement statement = connection.createStatement();
            String querySQL = "SELECT * FROM rentTracker";
            ResultSet resultSet = statement.executeQuery(querySQL);

            // Map ResultSet to List of rentTracker objects
            ResultSetMapper<RentTracker> mapper = new ResultSetMapper<>();
            List<RentTracker> rentTrackers = mapper.map(resultSet, RentTracker.class);

            return rentTrackers;
        }catch(Exception e){
            log.error("Issue with converting Renters list");
        }
        return null;
    }

    public boolean insertNewRentTracker(String toolId, Integer renterId, Integer clerkId, String rentalDays, String checkoutDate, String chargeDays, String preDiscountCharge, String discountPercent, String discountAmount, String finalCharge) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the rentTracker table
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            RentTracker rentTrack = new RentTracker(
                    toolId,
                    renterId,
                    clerkId,
                    rentalDays,
                    checkoutDate,
                    chargeDays,
                    preDiscountCharge,
                    discountPercent,
                    discountAmount,
                    finalCharge);
            preparedStatement.setString(1,  rentTrack.getToolId());
            preparedStatement.setInt(   2,  rentTrack.getRenterId());
            preparedStatement.setInt(   3,  rentTrack.getClerkId());
            preparedStatement.setString(4,  rentTrack.getRentalDays());
            preparedStatement.setString(5,  rentTrack.getCheckoutDate());
            preparedStatement.setString(6,  rentTrack.getChargeDays());
            preparedStatement.setString(7,  rentTrack.getPreDiscountCharge());
            preparedStatement.setString(8,  rentTrack.getDiscountPercent());
            preparedStatement.setString(9,  rentTrack.getDiscountAmount());
            preparedStatement.setString(10, rentTrack.getFinalCharge());

            preparedStatement.execute();

            return true;
        }catch(Exception e){
            log.error("Issue adding new RentTracker.", e);
            return false;
        }
    }
}
