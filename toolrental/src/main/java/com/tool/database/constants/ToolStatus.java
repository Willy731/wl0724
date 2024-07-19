package com.tool.database.constants;

public enum ToolStatus {
    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED"),
    RENTED("RENTED"),
    OVERDUE("OVERDUE"),
    PENDING_INSPECTION("PENDING_INSPECTION"),
    PENDING_REPAIR("PENDING_REPAIR");

    private final String printableText;

    private ToolStatus(String printableText) {
        this.printableText = printableText;
    }

    @Override
    public String toString() {
        return printableText;
    }
}
