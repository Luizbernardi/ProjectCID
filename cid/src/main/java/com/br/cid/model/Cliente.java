package com.br.cid.model;

import com.br.cid.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente extends User {
    
    @Enumerated(EnumType.STRING)
    private Perfil perfil = Perfil.CLIENTE;

}
