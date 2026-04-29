package com.franquicias.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.franquicias.api.model.Franquicia;

@Repository
public interface FranquiciaRepository  extends JpaRepository<Franquicia, Long>{

}
