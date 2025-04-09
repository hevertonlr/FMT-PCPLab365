package com.lab365.app.pcp.datasource.enums;

public enum CivilStateEnum {
    SINGLE("Solteiro(a)"),
    MARRIED("Casado(a)"),
    DIVORCED("Divorciado(a)"),
    WIDOWED("Viúvo(a)"),
    SEPARATED("Separado(a)");

    private final String name;

    CivilStateEnum(String name) {
        this.name = name;
    }
}
