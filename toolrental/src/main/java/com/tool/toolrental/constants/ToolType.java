package com.tool.toolrental.constants;

public enum ToolType {

    JACKHAMMER("JACKHAMMER"),
    LADDER("LADDER"),
    CHAINSAW("CHAINSAW");

    private final String toolType;
    private ToolType(String toolType) {
        this.toolType = toolType;
    }

    @Override
    public String toString() {
        return toolType;
    }
}
