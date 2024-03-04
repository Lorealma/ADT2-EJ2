package com.example.segundoejercicio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select c from Cliente c where c.estado= 'activo' and c.total > :cantidad ")
    public List<String> getClienteByEstadoAndTotal();

    @Query("select sum(c.total) from Cliente c")
    Double calcularTotalVentas();

    @Query("select avg(c.total) from Cliente c where c.estado= 'activo'")
    Double calcularPromedioVentas();

    @Query("select count(c) from Cliente c where c.total>0 and c.estado = 'inactivo'")
    Long contarTotalMayorCeroInactivos();

}
