package com.tool.database;

import com.tool.database.constants.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMapper<T> {

    private static final Logger log = LoggerFactory.getLogger(ResultSetMapper.class);
    public List<T> map(ResultSet resultSet, Class<T> clazz) throws SQLException {
        List<T> results = new ArrayList<>();

        try {
            while (resultSet.next()) {
                T instance = clazz.getDeclaredConstructor().newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column column = field.getAnnotation(Column.class);
                        String columnName = column.name();
                        Object value = resultSet.getObject(columnName);
                        field.setAccessible(true);
                        try {
                            field.setAccessible(true);
                            if (field.getType().equals(LocalDate.class) && value instanceof java.sql.Date) {
                                // Convert java.sql.Date to java.time.LocalDate
                                field.set(instance, ((java.sql.Date) value).toLocalDate());
                            } else if (field.getType().equals(boolean.class) && value instanceof String) {
                                // Convert java.sql.Date to java.time.LocalDate
                                field.set(instance, Boolean.valueOf((String)value));
                            } else if (field.getType().isEnum()) {
                                // Special handling for enums
                                Method setter = clazz.getMethod("set" + capitalize(field.getName()), String.class);
                                setter.invoke(instance, value);
                            } else {
                                field.set(instance, value);
                            }
                        }catch(Exception e){
                            log.error("field conversion issue: ", e);
                        }
                    }
                }
                results.add(instance);
            }
        } catch (Exception e) {
            throw new SQLException("Error mapping ResultSet to object", e);
        }

        return results;
    }
    // Utility method to capitalize the first letter of the field name
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
