# Tool Rental Application

## Overview

The Tool Rental Application is a Java-based system that allows users to manage tool rentals. Users can enter rental information through a graphical interface, and the application will generate a PDF of the rental agreement, which can be printed for records.

## Features

- User-friendly GUI for entering rental data
- Generates a rental agreement in PDF format
- Includes preloaded Renter information and Clerk Information for recording in the rental agreement.
- A DB server for browsing the HW DB will run while the application is run
  - Can be reached at: http://localhost:8082


## Prerequisites

- Java 11 or higher
- Maven
- A PDF viewer installed on your system


Some Notes:
- I chose to have the data for this held in an H2 DB. There is a Prefill that is running to load each Table with data.
  - The Tables have unique keys that prevent some duplicate values from being entered.
- There is Generated HolidayCalendar Table to hold Holidays. The idea is for an established application to allow new holidays or changing holidays to be added to the table.
