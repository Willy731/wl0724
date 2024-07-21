module com.tool.toolrental {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;
    requires com.h2database;
    requires kernel;
    requires layout;
    requires java.desktop;


    opens com.tool.toolrental to javafx.fxml;
    exports com.tool.toolrental;
    opens com.tool.toolrental.controllers to javafx.fxml;
    exports com.tool.toolrental.controllers;
    opens com.tool.toolrental.dao to javafx.fxml;
    exports com.tool.toolrental.dao;
    opens com.tool.toolrental.agreements to javafx.fxml;
    exports com.tool.toolrental.agreements;
    opens com.tool.toolrental.constants to javafx.fxml;
    exports com.tool.toolrental.constants;
    opens com.tool.toolrental.model to javafx.fxml;
    exports com.tool.toolrental.model;

}