package com.tool.toolrental;

import com.tool.database.ToolRentalDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolRentalApplication extends Application {
    private static final Logger log = LoggerFactory.getLogger(ToolRentalApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ToolRentalApplication.class.getResource("tool-rental-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());//, 320, 240);
        stage.setTitle("Tool Rental Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        ToolRentalDB toolRentalDB = new ToolRentalDB();
        toolRentalDB.Initialize();

        //Run the DB server to check on it.
        try {
            // Start the web server for H2 Console
            Server server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("H2 Console started at: http://localhost:8082");
        } catch (Exception e) {
            log.debug("Issue Running the Server: ", e);
        }

        launch();
    }
}