package no.jaflaten.dev.mvcwithconfigurationclass.example.repository;

import no.jaflaten.dev.mvcwithconfigurationclass.example.entities.UtilityServlet;
import org.hibernate.annotations.NotFound;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface UtilityServletRepository extends CrudRepository<UtilityServlet, UUID> {
    @NotFound
    @Override
    Set<UtilityServlet> findAll();

}
