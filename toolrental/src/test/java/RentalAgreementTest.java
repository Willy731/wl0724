import com.tool.toolrental.constants.ToolStatus;
import com.tool.toolrental.constants.ToolType;
import com.tool.toolrental.agreements.RentalAgreement;
import com.tool.toolrental.dao.ClerkDB;
import com.tool.toolrental.dao.RenterDB;
import com.tool.toolrental.model.Clerk;
import com.tool.toolrental.model.Renter;
import com.tool.toolrental.model.Tool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalAgreementTest {

    private List<Renter> renterList;
    private List<Clerk> clerkList;
    private RentalAgreement agreement;

    @BeforeEach
    public void setUp() {
        renterList = RenterDB.testRentersList();
        clerkList = ClerkDB.testClerksListWithId();
    }

    @Test
    /**
     * ToolCode:         JAKR,
     * Checkout Date:    9/3/15,
     * Rental Days:      5,
     * Percent Discount: 101%
     *
     * Results:
     *      finalCharge = "$ 0.00"
     *      discountAmount = "$ 8.97"
     *      preDiscountCharge = "$ 8.97"
     *      chargeDays = 3
     *      discountPercent = 100
     */
    public void test1() {
        String desiredToolCode = "JAKR";
        Tool tool = new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid",
                ToolStatus.AVAILABLE, "NEW", null);

        LocalDate testCheckoutDate = LocalDate.of(2015, 9, 3);
        int testRentalDays = 5;
        int testPercentDiscount = 101;

        agreement = new RentalAgreement();
        agreement.setClerk(clerkList.get(0));
        agreement.setRenter(renterList.getFirst());
        agreement.setSelectedTool(tool);
        agreement.setRentalDays(testRentalDays);
        agreement.setCheckOutDate(testCheckoutDate);
        agreement.setDiscountPercent(testPercentDiscount);
        agreement.setDate(testCheckoutDate);

        assertEquals(agreement.getToolCode(), desiredToolCode,"Tool Code not Correct");
        assertEquals(agreement.getCheckOutDate(), testCheckoutDate, "Checkout Date not Correct");
        assertEquals(agreement.getDiscountPercent(), 100, "Discount Percent not Correct");
        assertEquals(agreement.getFinalCharge(), "$ 0.00", "Final Charge not Correct");
        assertEquals(agreement.getDiscountAmount(), "$ 8.97", "Discount Amount not Correct");
        assertEquals(agreement.getPreDiscountCharge(), "$ 8.97", "PreDiscount Charge not Correct");
        assertEquals(agreement.getChargeDays(), 3, "Charge Days not Correct");
    }


    @Test
    /**
     * ToolCode:         LADW,
     * Checkout Date:    7/2/20,
     * Rental Days:      3,
     * Percent Discount: 10%
     *
     * Results
     *      finalCharge = "$ 7.16"
     *      discountAmount = "$ 0.80"
     *      preDiscountCharge = "$ 7.96"
     *      chargeDays = 4
     */
    public void test2() {
        String desiredToolCode = "LADW";
        Tool tool = new Tool("LADW", ToolType.LADDER, "Werner",
                ToolStatus.AVAILABLE, "USED", null );
        LocalDate testCheckoutDate = LocalDate.of(2020, 7, 20);
        int testRentalDays = 3;
        int testPercentDiscount = 10;



        agreement = new RentalAgreement();
        agreement.setClerk(clerkList.get(0));
        agreement.setRenter(renterList.getFirst());
        agreement.setSelectedTool(tool);
        agreement.setRentalDays(testRentalDays);
        agreement.setCheckOutDate(testCheckoutDate);
        agreement.setDiscountPercent(testPercentDiscount);
        agreement.setDate(testCheckoutDate);
        assertEquals(agreement.getToolCode().toString(), desiredToolCode,"Tool Code not Correct");
        assertEquals(agreement.getCheckOutDate(), testCheckoutDate, "Checkout Date not Correct");
        assertEquals(agreement.getDiscountPercent(), testPercentDiscount, "Discount Percent not Correct");
        assertEquals(agreement.getFinalCharge(), "$ 7.16", "Final Charge not Correct");
        assertEquals(agreement.getDiscountAmount(), "$ 0.80", "Discount Amount not Correct");
        assertEquals(agreement.getPreDiscountCharge(), "$ 7.96", "PreDiscount Charge not Correct");
        assertEquals(agreement.getChargeDays(), 4, "Charge Days not Correct");
    }


    @Test
    /**
     * ToolCode:         CHNS,
     * Checkout Date:    7/2/15,
     * Rental Days:      5,
     * Percent Discount: 25%
     *
     * Results
     *      finalCharge = "$ 4.47"
     *      discountAmount = "$ 1.49"
     *      preDiscountCharge = "$ 5.96"
     *      chargeDays = 4
     */
    public void test3() {
        String desiredToolCode = "CHNS";
        Tool tool = new Tool("CHNS",ToolType.CHAINSAW, "Stihl",
                ToolStatus.AVAILABLE , "NEW", null);
        LocalDate testCheckoutDate = LocalDate.of(2015, 7, 2);
        int testRentalDays = 5;
        int testPercentDiscount = 25;



        agreement = new RentalAgreement();
        agreement.setClerk(clerkList.get(0));
        agreement.setRenter(renterList.getFirst());
        agreement.setSelectedTool(tool);
        agreement.setRentalDays(testRentalDays);
        agreement.setCheckOutDate(testCheckoutDate);
        agreement.setDiscountPercent(testPercentDiscount);
        agreement.setDate(testCheckoutDate);
        assertEquals(agreement.getToolCode().toString(), desiredToolCode,"Tool Code not Correct");
        assertEquals(agreement.getCheckOutDate(), testCheckoutDate, "Checkout Date not Correct");
        assertEquals(agreement.getDiscountPercent(), testPercentDiscount, "Discount Percent not Correct");
        assertEquals(agreement.getFinalCharge(), "$ 4.47", "Final Charge not Correct");
        assertEquals(agreement.getDiscountAmount(), "$ 1.49", "Discount Amount not Correct");
        assertEquals(agreement.getPreDiscountCharge(), "$ 5.96", "PreDiscount Charge not Correct");
        assertEquals(agreement.getChargeDays(), 4, "Charge Days not Correct");
    }



    @Test
    /**
     * ToolCode:         JAKD,
     * Checkout Date:    9/3/15,
     * Rental Days:      6,
     * Percent Discount: 0%
     *
     * Results
     *      finalCharge = "$ 11.96"
     *      discountAmount = "$ 0.00"
     *      preDiscountCharge = "$ 11.96"
     *      chargeDays = 4
     */
    public void test4() {
        String desiredToolCode = "JAKD";
        Tool tool = new Tool("JAKD", ToolType.JACKHAMMER, "DeWalt",
                ToolStatus.AVAILABLE, "NEW", null);
        LocalDate testCheckoutDate = LocalDate.of(2015, 9, 3);
        int testRentalDays = 6;
        int testPercentDiscount = 0;



        agreement = new RentalAgreement();
        agreement.setClerk(clerkList.get(0));
        agreement.setRenter(renterList.getFirst());
        agreement.setSelectedTool(tool);
        agreement.setRentalDays(testRentalDays);
        agreement.setCheckOutDate(testCheckoutDate);
        agreement.setDiscountPercent(testPercentDiscount);
        agreement.setDate(testCheckoutDate);
        assertEquals(agreement.getToolCode().toString(), desiredToolCode,"Tool Code not Correct");
        assertEquals(agreement.getCheckOutDate(), testCheckoutDate, "Checkout Date not Correct");
        assertEquals(agreement.getDiscountPercent(), testPercentDiscount, "Discount Percent not Correct");
        assertEquals(agreement.getFinalCharge(), "$ 11.96", "Final Charge not Correct");
        assertEquals(agreement.getDiscountAmount(), "$ 0.00", "Discount Amount not Correct");
        assertEquals(agreement.getPreDiscountCharge(), "$ 11.96", "PreDiscount Charge not Correct");
        assertEquals(agreement.getChargeDays(), 4, "Charge Days not Correct");
    }


    @Test
    /**
     * ToolCode:         JAKR,
     * Checkout Date:    7/2/15,
     * Rental Days:      9,
     * Percent Discount: 0%
     *
     *      Results
     *      finalCharge = "$ 17.94"
     *      discountAmount = "$ 0.00"
     *      preDiscountCharge = "$ 17.94"
     *      chargeDays = 6
     */
    public void test5() {
        String desiredToolCode = "JAKR";
        Tool tool = new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid",
                ToolStatus.AVAILABLE, "NEW", null);
        LocalDate testCheckoutDate = LocalDate.of(2015, 7, 2);
        int testRentalDays = 9;
        int testPercentDiscount = 0;



        agreement = new RentalAgreement();
        agreement.setClerk(clerkList.get(0));
        agreement.setRenter(renterList.getFirst());
        agreement.setSelectedTool(tool);
        agreement.setRentalDays(testRentalDays);
        agreement.setCheckOutDate(testCheckoutDate);
        agreement.setDiscountPercent(testPercentDiscount);
        agreement.setDate(testCheckoutDate);
        assertEquals(agreement.getToolCode().toString(), desiredToolCode,"Tool Code not Correct");
        assertEquals(agreement.getCheckOutDate(), testCheckoutDate, "Checkout Date not Correct");
        assertEquals(agreement.getDiscountPercent(), testPercentDiscount, "Discount Percent not Correct");
        assertEquals(agreement.getFinalCharge(), "$ 17.94", "Final Charge not Correct");
        assertEquals(agreement.getDiscountAmount(), "$ 0.00", "Discount Amount not Correct");
        assertEquals(agreement.getPreDiscountCharge(), "$ 17.94", "PreDiscount Charge not Correct");
        assertEquals(agreement.getChargeDays(), 6, "Charge Days not Correct");
    }


    @Test
    /**
     * ToolCode:         JAKR,
     * Checkout Date:    7/2/20,
     * Rental Days:      4,
     * Percent Discount: 50%
     *
     * Results
     *      finalCharge = "$ 2.99"
     *      discountAmount = "$ 2.99"
     *      preDiscountCharge = "$ 5.98"
     *      chargeDays = 2
     */
    public void test6() {
        String desiredToolCode = "JAKR";
        Tool tool = new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid",
                ToolStatus.AVAILABLE, "NEW", null);
        LocalDate testCheckoutDate = LocalDate.of(2020, 7, 2);
        int testRentalDays = 4;
        int testPercentDiscount = 50;



        agreement = new RentalAgreement();
        agreement.setClerk(clerkList.get(0));
        agreement.setRenter(renterList.getFirst());
        agreement.setSelectedTool(tool);
        agreement.setRentalDays(testRentalDays);
        agreement.setCheckOutDate(testCheckoutDate);
        agreement.setDiscountPercent(testPercentDiscount);
        agreement.setDate(testCheckoutDate);
        assertEquals(agreement.getToolCode().toString(), desiredToolCode,"Tool Code not Correct");
        assertEquals(agreement.getCheckOutDate(), testCheckoutDate, "Checkout Date not Correct");
        assertEquals(agreement.getDiscountPercent(), testPercentDiscount, "Discount Percent not Correct");
        assertEquals(agreement.getFinalCharge(), "$ 2.99", "Final Charge not Correct");
        assertEquals(agreement.getDiscountAmount(), "$ 2.99", "Discount Amount not Correct");
        assertEquals(agreement.getPreDiscountCharge(), "$ 5.98", "PreDiscount Charge not Correct");
        assertEquals(agreement.getChargeDays(), 2, "Charge Days not Correct");
    }
}