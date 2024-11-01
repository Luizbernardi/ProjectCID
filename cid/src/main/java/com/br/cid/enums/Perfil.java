package com.br.cid.enums;

public enum Perfil {
    ADMIN("Admin"),CUIDADOR("Cuidador"),CLIENTE("Cliente");

    private String perfil;

    Perfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    
    
}
