package com.br.cid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.cid.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
}
