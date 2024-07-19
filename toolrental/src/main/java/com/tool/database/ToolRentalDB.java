package com.tool.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.tool.database.constants.DBConstants.jdbcUrl;

public class ToolRentalDB {
    private static final Logger log = LoggerFactory.getLogger(ToolRentalDB.class);
    public void Initialize(){

        // Establish a connection
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Create a statement object
            Statement statement = connection.createStatement();

            // Create the tools table
            ToolsDB toolsDB = new ToolsDB();
            toolsDB.Initialize(connection);

            log.info("Database initialized successfully.");
            statement.close();
        } catch (SQLException e) {
            log.error("Error occurred with DB creation and initialization", e);
        }
    }
}
