package com.tool.toolrental.dao;

import com.tool.toolrental.constants.ToolType;
import com.tool.toolrental.model.Charge;
import com.tool.toolrental.utils.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.tool.toolrental.constants.DBConstants.jdbcUrl;

public class ChargeDB {    
    /*
    toolType
    level
    dailyCharge
    weekdayCharge
    weekendCharge
    holidayCharge
    baseCharge
     */
    private static final Logger log = LoggerFactory.getLogger(ChargeDB.class);

    String insertSQL = "INSERT INTO charge (" +
            "toolType,\n" +
            "level," +
            "dailyCharge," +
            "weekdayCharge," +
            "weekendCharge," +
            "holidayCharge," +
            "baseCharge" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?)";

    public void Initialize(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            Initialize(connection);
        }catch(Exception e){
            log.error("Issue with making DB connection");
        }
    }


    public void Initialize(Connection connection){

        try(Statement statement = connection.createStatement()) {
            // Create the table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS charge ("
                    + "toolType VARCHAR(255),"
                    + "level VARCHAR(255),"
                    + "dailyCharge VARCHAR(255), "
                    + "weekdayCharge VARCHAR(255), "
                    + "weekendCharge VARCHAR(255),"
                    + "holidayCharge VARCHAR(255),"
                    + "baseCharge VARCHAR(255),"
                    + "UNIQUE (toolType, level))";
            statement.execute(createTableSQL);

            PrefillData(connection);
        }catch (Exception e){
            log.error("Issue with creating Charge Table", e);
        }
    }
    private void PrefillData(Connection connection){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            // Example data to insert
            List<Charge> chargeList = testChargeList();

            // Insert each entry using the prepared statement
            for (Charge charge : chargeList) {
                preparedStatement.setString( 1,  charge.getToolType().toString());
                preparedStatement.setString( 2,  charge.getLevel());
                preparedStatement.setString( 3,  charge.getDailyCharge());
                preparedStatement.setBoolean(4,  charge.getWeekdayCharge());
                preparedStatement.setBoolean(5,  charge.getWeekendCharge());
                preparedStatement.setBoolean(6,  charge.getHolidayCharge());
                preparedStatement.setString( 7,  charge.getBaseCharge());
                preparedStatement.addBatch(); // Add to batch
            }

            // Execute batch insert
            int[] affectedRecords = preparedStatement.executeBatch();
            log.debug(String.format("Inserted records: %s", affectedRecords.length));

        } catch (SQLException e) {
            log.error("Issue with initializing base Charge list", e);
        }
    }
    public Charge getCharge(ToolType toolType, String level){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the table
            String querySQL = "SELECT * FROM charge WHERE toolType = ? AND level = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            preparedStatement.setString(1, toolType.toString());
            preparedStatement.setString(2, level);

            ResultSet resultSet = preparedStatement.getResultSet();

            // Map ResultSet to List of objects
            ResultSetMapper<Charge> mapper = new ResultSetMapper<>();
            List<Charge> charges = mapper.map(resultSet, Charge.class);

            return charges.getFirst();
        }catch(Exception e){
            log.error("Issue selecting one charge");
        }
        return null;
    }
    public static List<Charge> getChargesList(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the table
            Statement statement = connection.createStatement();
            String querySQL = "SELECT * FROM charge";
            ResultSet resultSet = statement.executeQuery(querySQL);

            // Map ResultSet to List of objects
            ResultSetMapper<Charge> mapper = new ResultSetMapper<>();
            List<Charge> charges = mapper.map(resultSet, Charge.class);

            return charges;
        }catch(Exception e){
            log.error("Issue with converting Charges list");
        }
        return null;
    }

    public boolean insertNewCharge(ToolType toolType, String level, String dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge, String baseCharge) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the table
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            Charge charge = new Charge(toolType,
                    level,
                    dailyCharge,
                    weekdayCharge,
                    weekendCharge,
                    holidayCharge,
                    baseCharge);
            preparedStatement.setString( 1,  charge.getToolType().toString());
            preparedStatement.setString( 2,  charge.getLevel());
            preparedStatement.setString( 3,  charge.getDailyCharge());
            preparedStatement.setBoolean(4,  charge.getWeekdayCharge());
            preparedStatement.setBoolean(5,  charge.getWeekendCharge());
            preparedStatement.setBoolean(6,  charge.getHolidayCharge());
            preparedStatement.setString( 7,  charge.getBaseCharge());

            preparedStatement.execute();

            return true;
        }catch(Exception e){
            log.error("Issue adding new Charge.", e);
            return false;
        }
    }

    public static List<Charge> testChargeList(){

        List<Charge> chargeList = new ArrayList<>();

        chargeList.add(new Charge(
                ToolType.JACKHAMMER,
                "1",
                "2.99",
                true,
                false,
                false,
                "0.00"));
        chargeList.add(new Charge(
                ToolType.CHAINSAW,
                "1",
                "1.49",
                true,
                false,
                true,
                "0.00"));
        chargeList.add(new Charge(
                ToolType.LADDER,
                "1",
                "1.99",
                true,
                true,
                false,
                "0.00"));
        return chargeList;
    }
}
