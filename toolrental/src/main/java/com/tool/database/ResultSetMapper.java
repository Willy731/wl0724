package com.tool.database;

import com.tool.database.constants.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMapper<T> {

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
                        field.set(instance, value);
                    }
                }
                results.add(instance);
            }
        } catch (Exception e) {
            throw new SQLException("Error mapping ResultSet to object", e);
        }

        return results;
    }
}
