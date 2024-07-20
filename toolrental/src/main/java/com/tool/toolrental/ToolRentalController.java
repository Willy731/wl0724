package com.tool.toolrental;

import com.tool.database.*;
import com.tool.utils.PaymentUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Tool selectedTool;
    private LocalDate checkoutDate;
    private int rentalDays;
    private int discount;
    private ChargeDB chargeDB;
    private List<Charge> charges;
    private Charge chargeObj;
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
    protected void onToolSelectionChange() {
        selectedTool = toolPicker.getValue();
        updateSummaryLabels();
    }

    @FXML
    protected void onCheckoutDateChange() {
        checkoutDate = checkoutDatePicker.getValue();
        updateSummaryLabels();
    }

    @FXML
    protected void onDiscountChange() {
        discount = discountSpinner.getValue();
        updateSummaryLabels();
    }

    @FXML
    protected void onRentalDaysChange() {
        rentalDays = rentalDaySpinner.getValue();
        updateSummaryLabels();
    }

    private void updateSummaryLabels(){
        if(selectedTool!=null && checkoutDate!=null && rentalDays>0){

            PaymentUtils paymentUtils = new PaymentUtils();
            Optional<Charge> toolCharge = charges.stream()
                    .filter(charge -> (charge.getToolType().equals(selectedTool.getToolType())
                            && charge.getLevel().equals("1"))).findFirst();
            if(toolCharge.isPresent()){
                chargeObj = toolCharge.get();
            }else{
                return;
            }

            LocalDate startDate = checkoutDatePicker.getValue();
            LocalDate endDate = checkoutDatePicker.getValue().plusDays(rentalDaySpinner.getValue());

            int chargeDays = paymentUtils.countChargeDays(startDate, endDate, chargeObj);
            preDiscountLabel.setText(paymentUtils.format(paymentUtils.calculateRate(chargeObj, chargeDays)));
            discountAmountLabel.setText(paymentUtils.format(paymentUtils.calculateDiscount(chargeObj, chargeDays,discount)));
            finalChargeLabel.setText(paymentUtils.format(paymentUtils.calculateAmountOwed(chargeObj, chargeDays,discount)));

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

        chargeDB = new ChargeDB();
        charges = chargeDB.getChargesList();
        rentalDaySpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                rentalDays = rentalDaySpinner.getValue();
                updateSummaryLabels();
            }
        });
        discountSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                discount = discountSpinner.getValue();
                updateSummaryLabels();
            }
        });
    }
    private void initCheckoutDate(){
        checkoutDatePicker.setValue(LocalDate.now());
        checkoutDate = checkoutDatePicker.getValue();
    }
    private void initRentalDaySpinner(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactory.setValue(1);
        rentalDaySpinner.setValueFactory(valueFactory);
        rentalDays = rentalDaySpinner.getValue();
    }

    private void initDiscountSpinner(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        valueFactory.setValue(0);
        discountSpinner.setValueFactory(valueFactory);
        discount = discountSpinner.getValue();
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