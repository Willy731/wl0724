package com.tool.toolrental.controllers;

//
public class ToolController {
//    private static final Logger log = LoggerFactory.getLogger(ToolController.class);
//
//    public void initialize(){
//        ListView myListView;
//
//        try{
//
//            TableView<Tool> tableView = new TableView<>();
//            tableView.setItems(getToolList());
//
//            TableColumn<Tool, String> toolCodeColumn = new TableColumn<>("Tool Code");
//            toolCodeColumn.setCellValueFactory(new PropertyValueFactory<>("toolCode"));
//
//            TableColumn<Tool, String> toolTypeColumn = new TableColumn<>("Tool Type");
//            toolTypeColumn.setCellValueFactory(new PropertyValueFactory<>("toolType"));
//
//            TableColumn<Tool, String> brandColumn = new TableColumn<>("Brand");
//            brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
//
//            TableColumn<Tool, String> rentalStatusColumn = new TableColumn<>("Rental Status");
//            rentalStatusColumn.setCellValueFactory(new PropertyValueFactory<>("rentalStatus"));
//
//            TableColumn<Tool, String> conditionStatusColumn = new TableColumn<>("Condition Status");
//            conditionStatusColumn.setCellValueFactory(new PropertyValueFactory<>("conditionStatus"));
//
//            TableColumn<Tool, String> rentalTrackingCodeColumn = new TableColumn<>("Rental Tracking Code");
//            rentalTrackingCodeColumn.setCellValueFactory(new PropertyValueFactory<>("rentalTrackingCode"));
//
//            tableView.getColumns().add(toolCodeColumn);
//            tableView.getColumns().add(toolTypeColumn);
//            tableView.getColumns().add(brandColumn);
//            tableView.getColumns().add(rentalStatusColumn);
//            tableView.getColumns().add(conditionStatusColumn);
//            tableView.getColumns().add(rentalTrackingCodeColumn);
//        } catch ( Exception e) {
//            log.error("Error occurred retrieving Tools", e);
//        }
//    }
//
//    private ObservableList<Tool> getToolList() {
//        ObservableList<Tool> tools = FXCollections.observableArrayList();
//
//        ToolsDB toolsDB = new ToolsDB();
//        tools.addAll(toolsDB.getToolsList());
//
//        return tools;
//    }

}
