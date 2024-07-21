package com.tool.toolrental.dao;

import com.tool.toolrental.constants.ToolStatus;
import com.tool.toolrental.constants.ToolType;
import com.tool.toolrental.model.Tool;
import com.tool.toolrental.utils.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

import static com.tool.toolrental.constants.DBConstants.jdbcUrl;

public class ToolsDB {
    private static final Logger log = LoggerFactory.getLogger(ToolsDB.class);

    String insertSQL = "INSERT INTO tools (toolCode, toolType, brand, rentalStatus, conditionStatus, rentalTrackingCode) VALUES (?, ?, ?, ?, ?, ?)";

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
            String createTableSQL = "CREATE TABLE IF NOT EXISTS tools ("
                    + "toolCode VARCHAR(255) PRIMARY KEY, "
                    + "toolType VARCHAR(255), "
                    + "brand VARCHAR(255), "
                    + "rentalStatus VARCHAR(255),"
                    + "conditionStatus VARCHAR(255),"
                    + "rentalTrackingCode VARCHAR(255))";
            statement.execute(createTableSQL);

            PrefillData(connection);
            getToolsList();
        }catch (Exception e){
            log.error("Issue with creating Tools Table", e);
        }
    }
    private void PrefillData(Connection connection){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            // Example data to insert
            List<Tool> toolsList = testToolsList();

            // Insert each entry using the prepared statement
            for (Tool tool : toolsList) {
                preparedStatement.setString(1, (String) tool.getToolCode());
                preparedStatement.setString(2, (String) tool.getToolType().toString());
                preparedStatement.setString(3, (String) tool.getBrand());
                preparedStatement.setString(4, (String) tool.getRentalStatus().toString());
                preparedStatement.setString(5, (String) tool.getConditionStatus());
                preparedStatement.setString(6, (String) tool.getRentalTrackingCode());
                preparedStatement.addBatch(); // Add to batch
            }

            // Execute batch insert
            int[] affectedRecords = preparedStatement.executeBatch();
            log.debug(String.format("Inserted records: %s", affectedRecords.length));

        } catch (SQLException e) {
            log.error("Issue with initializing base tool list", e);
        }
    }

    public List<Tool> getToolsList(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the tools table
            Statement statement = connection.createStatement();
            String querySQL = "SELECT * FROM tools WHERE RENTALSTATUS = 'AVAILABLE'";
            ResultSet resultSet = statement.executeQuery(querySQL);

            // Map ResultSet to List of Tool objects
            ResultSetMapper<Tool> mapper = new ResultSetMapper<>();
            List<Tool> tools = mapper.map(resultSet, Tool.class);

            return tools;
        }catch(Exception e){
            log.error("Issue with converting Tools list");
        }
        return null;
    }

    public boolean insertNewTool(String toolCode, ToolType toolType, String brand, ToolStatus rentalStatus, String conditionStatus, String rentalTrackingCode) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the tools table
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            Tool tool = new Tool(toolCode, toolType, brand, rentalStatus , conditionStatus, rentalTrackingCode);
            preparedStatement.setString(1, (String) tool.getToolCode());
            preparedStatement.setString(2, (String) tool.getToolType().toString());
            preparedStatement.setString(3, (String) tool.getBrand());
            preparedStatement.setString(4, (String) tool.getRentalStatus().toString());
            preparedStatement.setString(5, (String) tool.getConditionStatus());
            preparedStatement.setString(6, (String) tool.getRentalTrackingCode());

            preparedStatement.execute();

            return true;
        }catch(Exception e){
            log.error("Issue adding new tool.", e);
            return false;
        }
    }

    public static List<Tool> testToolsList(){

        List<Tool> toolsList = new ArrayList<>();
        toolsList.add(new Tool("CHNS", ToolType.CHAINSAW, "Stihl", ToolStatus.AVAILABLE , "NEW", null));
        toolsList.add(new Tool("LADW", ToolType.LADDER, "Werner", ToolStatus.AVAILABLE, "USED", null      ));
        toolsList.add(new Tool("JAKD", ToolType.JACKHAMMER, "DeWalt", ToolStatus.AVAILABLE, "NEW", null));
        toolsList.add(new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid", ToolStatus.AVAILABLE, "NEW", null));
        toolsList.add(new Tool("LADS", ToolType.LADDER, "Stihl", ToolStatus.RENTED, "USED", null      ));
        return toolsList;
    }
}
