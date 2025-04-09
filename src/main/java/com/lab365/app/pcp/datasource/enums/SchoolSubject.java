package com.lab365.app.pcp.datasource.enums;

public enum SchoolSubject {
    MATHEMATICS("Matemática"),
    HISTORY("História"),
    CHEMISTRY("Química"),
    PHYSICS("Física"),
    ENGLISH("Inglês"),
    GEOGRAPHY("Geografia"),
    BIOLOGY("Biologia"),
    PORTUGUESE("Português"),
    LITERATURE("Literatura"),
    PHILOSOPHY("Filosofia"),
    SOCIOLOGY("Sociologia"),
    PHYSICAL_EDUCATION("Educação Física"),
    ARTS("Artes"),
    SPANISH("Espanhol"),
    COMPUTER_SCIENCE("Informática"),
    TECHNOLOGY("Tecnologia"),
    SCIENCE("Ciências"),
    MUSIC("Música"),
    THEATER("Teatro");

    private final String name;

    SchoolSubject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
