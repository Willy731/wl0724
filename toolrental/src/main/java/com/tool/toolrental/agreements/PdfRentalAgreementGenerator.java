package com.tool.toolrental.agreements;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import com.tool.toolrental.utils.FileOpener;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;

public class PdfRentalAgreementGenerator {
    private static final Logger log = LoggerFactory.getLogger(PdfRentalAgreementGenerator.class);

    public static void generatePdf(RentalAgreement agreement, String dest) {
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
            document.add(new Paragraph("Rental Agreement").setBold().setFontSize(28));
            document.add(new Paragraph("Date: " + agreement.getDate().format(dateTimeFormatter)));
            document.add(new Paragraph("Clerk ID: " + agreement.getClerkIdNumber()));
            document.add(new Paragraph("Care and Treatment of Tools:").setBold().setFontSize(20));
            document.add(new Paragraph("The renter agrees to take all reasonable measures to ensure the proper care" +
                    " and treatment of the rented tools. The renter acknowledges responsibility for using the tools in " +
                    "accordance with their intended purpose and in a manner that prevents unnecessary damage or wear. " +
                    "To the best of their ability, the renter will maintain the tools in good condition, returning them " +
                    "in the same state as when received, except for normal wear and tear." ));

            Table table = new Table(2);
            table.addCell("Renter Name");
            table.addCell(agreement.getRenterName());
            table.addCell("Renter Contact Number");
            table.addCell(agreement.getRenterContactNumber());
            table.addCell("Tool Code");
            table.addCell(agreement.getToolCode());
            table.addCell("Tool Type");
            table.addCell(agreement.getToolType());
            table.addCell("Tool Brand");
            table.addCell(agreement.getToolBrand());
            table.addCell("Rental Days");
            table.addCell(String.valueOf(agreement.getRentalDays()));
            table.addCell("Check Out Date");
            table.addCell(agreement.getCheckOutDate().format(dateTimeFormatter));
            table.addCell("Due Date");
            table.addCell(agreement.getDueDate().format(dateTimeFormatter));
            table.addCell("Daily Rental Charge");
            table.addCell(String.format("$%.2f", agreement.getDailyRentalCharge()));
            table.addCell("Charge Days");
            table.addCell(String.valueOf(agreement.getChargeDays()));
            table.addCell("Pre-discount Charge");
            table.addCell( agreement.getPreDiscountCharge());
            table.addCell("Discount Percent");
            table.addCell(String.format("%.2f%%", agreement.getDiscountPercent()));
            table.addCell("Discount Amount");
            table.addCell( agreement.getDiscountAmount());
            table.addCell("Final Charge");
            table.addCell( agreement.getFinalCharge());

            document.add(table);

            document.add(new Paragraph("\n\nSignature: ___________________________   Date __________"));

            document.close();
            log.info("PDF created at: " + dest);
        } catch (FileNotFoundException e) {
            log.error("Issue Generating the agreement pdf", e);
        }
    }


    public static void printAggreement(RentalAgreement rentalAgreement) {
        /*
        Print the agreement and write to the DB
         */
        if (rentalAgreement.getRenter() == null) {
            showErrorAlert("Data Incomplete", "Missing Data.\n\n- Renter\n\nUse the Renter Dropdown to select\"");
            return;
        }
        if (rentalAgreement.getClerk() == null) {
            showErrorAlert("Data Incomplete", "Missing Data.\n\n- Clerk\n\nUse the Clerk Dropdown to select\"");
            return;
        }
        if (rentalAgreement.getSelectedTool() == null) {
            showErrorAlert("Data Incomplete", "Missing Data.\n\n- Tool\n\nUse the Tool Dropdown to select");
            return;
        }
        try {
            String pdfPath = "rental_agreement" + rentalAgreement.getDate().toString() + ".pdf";
            PdfRentalAgreementGenerator.generatePdf(rentalAgreement, pdfPath);

            FileOpener.OpenFile(pdfPath);
        }catch (Exception e){
            log.error("issue creating and opening pdf", e);
        }
    }

    private static void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

