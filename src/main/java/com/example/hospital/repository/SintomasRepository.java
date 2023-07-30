package com.example.hospital.repository;

import com.example.hospital.model.Sintomas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SintomasRepository extends CrudRepository<Sintomas, Long> {
}
