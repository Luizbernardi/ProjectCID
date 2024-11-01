package com.br.cid.model;

import com.br.cid.enums.Perfil;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cuidador {

    @Enumerated(EnumType.STRING)
    private Perfil perfil = Perfil.CUIDADOR;
    
}
