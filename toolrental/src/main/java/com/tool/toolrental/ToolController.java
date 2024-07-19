package com.tool.toolrental;

import com.tool.database.ToolRentalDB;
import com.tool.database.ToolsDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.tool.database.constants.DBConstants.jdbcUrl;

public class ToolController {
    private static final Logger log = LoggerFactory.getLogger(ToolController.class);

//    try(true){
//        ToolsDB toolsDB = new ToolsDB();
//        toolsDB.getToolsList();
//
//        log.info("Database initialized successfully.");
//    } catch ( Exception e) {
//        log.error("Error occurred with DB creation and initialization", e);
//    }
}
