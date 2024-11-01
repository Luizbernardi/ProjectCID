package com.br.cid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.cid.model.Cuidador;

@Repository
public interface CuidadorRepository  extends JpaRepository<Cuidador, Long> {
    
}
