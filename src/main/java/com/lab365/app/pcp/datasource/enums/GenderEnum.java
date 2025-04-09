package com.lab365.app.pcp.datasource.enums;

public enum GenderEnum {
    MALE("Masculino"),
    FEMALE("Feminino"),
    NONBINARY("Não-Binário"),
    GENDERQUEER("Gênero Queer"),
    GENDERFLUID("Gênero Fluido"),
    AGENDER("Agênero"),
    BIGENDER("Bigênero"),
    TWOSPIRIT("Dois Espíritos"),
    OTHER("Outro");

    public final String name;

    GenderEnum(String name){
        this.name = name;
    }
}
