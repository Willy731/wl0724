package com.tool.database.constants;

public final class DBConstants {
    private DBConstants() {
        throw new UnsupportedOperationException("This constants class cannot be instantiated");
    }
    // URL for the H2 database
    public static final String jdbcUrl = "jdbc:h2:./DBs/toolRental";
}
