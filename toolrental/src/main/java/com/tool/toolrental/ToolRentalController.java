package com.tool.toolrental;

import com.tool.database.*;
import com.tool.utils.PaymentUtils;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ToolRentalController implements Initializable {
    @FXML
    private ComboBox<Tool> toolPicker;

    @FXML
    private DatePicker checkoutDatePicker;

    @FXML
    private Spinner<Integer> rentalDaySpinner;

    @FXML
    private Spinner<Integer> discountSpinner;

    @FXML
    private ComboBox<Renter> renterComboBox;

    @FXML
    private ComboBox<Clerk> clerkComboBox;

    @FXML private Label preDiscountLabel;
    @FXML private Label discountAmountLabel;
    @FXML private Label finalChargeLabel;

    @FXML
    protected void onSubmitButtonClick() {
        /*
        Print the agreement and write to the DB
         */
    }

    /**
     * id
     * toolId
     * renterId
     * clerkId
     * rentalDays
     * checkoutDate
     * chargeDays
     * preDiscountCharge    preDiscountLabel
     * discountPercent
     * discountAmount       discountAmountLabel
     * finalCharge          finalChargeLabel
     */
    @FXML
    protected void onSelectionChange() {
        if(toolPicker.getValue()!=null
                && checkoutDatePicker.getValue()!=null
                && rentalDaySpinner.getValue()!=null
                && discountSpinner.getValue()!=null){
            PaymentUtils paymentUtils = new PaymentUtils();
            ChargeDB chargeDB = new ChargeDB();

            Charge chargeObj;

            Optional<Charge> toolCharge = chargeDB.getChargesList().stream()
                    .filter(charge -> (charge.getToolType().equals(toolPicker.getValue().getToolType())
                                        && charge.getLevel().equals("1"))).findFirst();
            if(toolCharge.isPresent()){
                chargeObj = toolCharge.get();
            }else{
                return;
            }
//            Charge charge = chargeDB.getCharge(toolPicker.getValue().getToolType(), "1");
            LocalDate startDate = checkoutDatePicker.getValue();
            LocalDate endDate = checkoutDatePicker.getValue().plusDays(rentalDaySpinner.getValue());

            int chargeDays = paymentUtils.countChargeDays(startDate, endDate, chargeObj);
            preDiscountLabel.setText(paymentUtils.format(paymentUtils.calculateRate(chargeObj, chargeDays)));
        }else{
            log.error("else");
        }

    }

    private static final Logger log = LoggerFactory.getLogger(ToolController.class);


    private ObservableList<Tool> getToolList() {
        ToolsDB toolsDB = new ToolsDB();
        List<Tool> toolList = toolsDB.getToolsList();
        ObservableList<Tool> tools = FXCollections.observableArrayList(toolList);

        return tools;
    }
    private ObservableList<Renter> getRenterList() {
        RenterDB renterDB = new RenterDB();
        List<Renter> renterList = renterDB.getRentersList();
        ObservableList<Renter> renters = FXCollections.observableArrayList(renterList);

        return renters;
    }
    private ObservableList<Clerk> getClerksList() {
        ClerkDB clerkDB = new ClerkDB();
        List<Clerk> clerkList = clerkDB.getClerksList();
        ObservableList<Clerk> clerks = FXCollections.observableArrayList(clerkList);

        return clerks;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTools();
        initRentalDaySpinner();
        initDiscountSpinner();
        initCheckoutDate();
        initRenters();
        initClerks();
    }
    private void initCheckoutDate(){
        checkoutDatePicker.setValue(LocalDate.now());
    }
    private void initRentalDaySpinner(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        valueFactory.setValue(1);
        rentalDaySpinner.setValueFactory(valueFactory);
    }

    private void initDiscountSpinner(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        valueFactory.setValue(0);
        discountSpinner.setValueFactory(valueFactory);
    }

    private void initTools(){
        try{

            toolPicker.getItems().addAll(getToolList());

            // Set a custom StringConverter to display the Tool objects in a readable format
            toolPicker.setConverter(new StringConverter<Tool>() {
                @Override
                public String toString(Tool tool) {
                    return tool != null ? tool.toString() : "A";
                }

                @Override
                public Tool fromString(String string) {
                    // This method is not needed for our use case, so it can return null
                    return null;
                }
            });

        } catch ( Exception e) {
            log.error("Error occurred adding Tools", e);
        }
    }

    private void initRenters(){
        try{

            renterComboBox.getItems().addAll(getRenterList());

            // Set a custom StringConverter to display the renter objects in a readable format
            renterComboBox.setConverter(new StringConverter<Renter>() {
                @Override
                public String toString(Renter renter) {
                    return renter != null ? renter.toString() : "";
                }

                @Override
                public Renter fromString(String string) {
                    // This method is not needed for our use case, so it can return null
                    return null;
                }
            });

        } catch ( Exception e) {
            log.error("Error occurred adding Renters", e);
        }
    }


    private void initClerks(){
        try{

            clerkComboBox.getItems().addAll(getClerksList());

            // Set a custom StringConverter to display the renter objects in a readable format
            clerkComboBox.setConverter(new StringConverter<Clerk>() {
                @Override
                public String toString(Clerk clerk) {
                    return clerk != null ? clerk.toString() : "";
                }

                @Override
                public Clerk fromString(String string) {
                    // This method is not needed for our use case, so it can return null
                    return null;
                }
            });

        } catch ( Exception e) {
            log.error("Error occurred adding Clerks", e);
        }
    }
}