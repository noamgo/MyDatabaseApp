package com.example.mymenu;

public enum Colors {
    BLACK("#000000"),
    WHITE("#FFFFFF"),
    GRAY("#808080"),
    RED("#FF0000"),
    YELLOW("#FFFF00"),
    BLUE("#0000FF"),
    GREEN("#008000");

    private final String hexCode;

    Colors(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getHexCode() {
        return hexCode;
    }
}

