package com.tool.toolrental.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileOpener {
    private static final Logger log = LoggerFactory.getLogger(FileOpener.class);
    public static void OpenFile(String pdfPath){
        File file = new File(pdfPath);

        // Check if the Desktop API is supported
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                // Check if the file exists
                if (file.exists()) {
                    // Open the file with the default application
                    desktop.open(file);
                    log.info("Opening file with default application: " + pdfPath);
                } else {
                    log.error("File not found: " + pdfPath);
                }
            } catch (IOException e) {
                log.error("Error opening file", e);
            }
        } else {
            log.error("Desktop API is not supported on this platform.");
        }
    }
}
