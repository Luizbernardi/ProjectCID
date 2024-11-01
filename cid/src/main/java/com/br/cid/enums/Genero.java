package com.br.cid.enums;

public enum Genero {
    MASCULINO("Masculino"), FEMININO("Feminino"), OUTRO("Outro");

    private String genero;

    Genero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
