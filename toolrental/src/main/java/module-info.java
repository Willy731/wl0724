module com.tool.toolrental {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;
    requires com.h2database;


    opens com.tool.toolrental to javafx.fxml;
    exports com.tool.toolrental;
}