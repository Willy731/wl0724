package com.tool.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tool.database.constants.DBConstants.jdbcUrl;


public class HolidayCalendarDB {    /*
    holidayName
    observedDate
     */
    private static final Logger log = LoggerFactory.getLogger(HolidayCalendarDB.class);

    String insertSQL = "INSERT INTO holidayCalendar (holidayName, observedDate) VALUES (?, ?)";

    public void Initialize(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            Initialize(connection);
        }catch(Exception e){
            log.error("Issue with making DB connection");
        }
    }

    public void Initialize(Connection connection){

        try(Statement statement = connection.createStatement()) {
            // Create the holidayCalendar table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS holidayCalendar ("
                    + "holidayName VARCHAR(255), "
                    + "observedDate DATE,"
                    + "UNIQUE (holidayName, observedDate))";
            statement.execute(createTableSQL);

            PrefillData(connection);
        }catch (Exception e){
            log.error("Issue with creating HolidayCalendars Table", e);
        }
    }
    private void PrefillData(Connection connection){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            // Example data to insert
            List<HolidayCalendar> holidayCalendarList = new ArrayList<>();
            holidayCalendarList.add(new HolidayCalendar("Independence Day", LocalDate.of(2024,7,4)));
            holidayCalendarList.add(new HolidayCalendar("LaborDay", LocalDate.of(2024,8,2)));


            // Insert each entry using the prepared statement
            for (HolidayCalendar holidayCalendar : holidayCalendarList) {
                preparedStatement.setString(1, (String) holidayCalendar.getHolidayName());
                preparedStatement.setDate(2, java.sql.Date.valueOf(holidayCalendar.getObservedDate()));
                preparedStatement.addBatch(); // Add to batch
            }

            // Execute batch insert
            int[] affectedRecords = preparedStatement.executeBatch();
            log.debug(String.format("Inserted records: %s", affectedRecords.length));

        } catch (SQLException e) {
            log.error("Issue with initializing base holidayCalendar list", e);
        }
    }

    public List<HolidayCalendar> getHolidayCalendarsList(LocalDate startDate, LocalDate endDate){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Query the holidayCalendar table
            String querySQL = "SELECT * FROM holidayCalendar";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySQL);
//            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
//            ResultSet resultSet = preparedStatement.getResultSet();

            // Map ResultSet to List of HolidayCalendar objects
            ResultSetMapper<HolidayCalendar> mapper = new ResultSetMapper<>();
            List<HolidayCalendar> holidays = mapper.map(resultSet, HolidayCalendar.class);

            // Print the holidayCalendar
            for (HolidayCalendar holiday : holidays) {
                log.info(holiday.toString());
            }
            return filterEventsByDateRange(holidays,startDate, endDate);
        }catch(Exception e){
            log.error("Issue with converting HolidayCalendars list");
        }
        return null;
    }

    public static List<HolidayCalendar> filterEventsByDateRange(List<HolidayCalendar> days, LocalDate startDate, LocalDate endDate) {
        return days.stream()
                .filter(event -> !event.getObservedDate().isBefore(startDate) && !event.getObservedDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    public boolean insertNewHolidayCalendar(String holidayName, LocalDate observedDate) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, holidayName);
            preparedStatement.setDate(2, java.sql.Date.valueOf(observedDate));

            preparedStatement.execute();

            return true;
        }catch(Exception e){
            log.error("Issue adding new holidayCalendar.", e);
            return false;
        }
    }
}

