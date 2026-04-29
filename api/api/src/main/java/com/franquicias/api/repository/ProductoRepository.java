package com.franquicias.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.franquicias.api.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

     @Query("""
        SELECT p FROM Producto p
        WHERE p.sucursal.franquicia.id = :franquiciaId
        AND p.stock = (
            SELECT MAX(p2.stock)
            FROM Producto p2
            WHERE p2.sucursal = p.sucursal
        )
    """)
    List<Producto> findTopProductosPorFranquicia(
        @Param("franquiciaId") Long franquiciaId);
}
