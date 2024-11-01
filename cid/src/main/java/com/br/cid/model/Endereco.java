package com.br.cid.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String cep;

    private String uf;

    private String estado;
    
    private String localidade;

    private String bairro;
    
    @OneToMany(mappedBy = "endereco")
    private List<Cuidador> cuidador;

    @OneToMany(mappedBy = "endereco")
    private List<Cliente> cliente;

    @OneToMany(mappedBy = "endereco")
    private List<Admin> admin;
    
}
