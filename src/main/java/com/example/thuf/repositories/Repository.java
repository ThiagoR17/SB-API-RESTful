package com.example.thuf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.thuf.models.Productmodel;




public interface Repository extends JpaRepository<Productmodel, UUID> {
    
    
}
