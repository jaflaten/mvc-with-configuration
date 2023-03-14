package no.jaflaten.dev.mvcwithconfigurationclass.example.service;

import lombok.RequiredArgsConstructor;
import no.jaflaten.dev.mvcwithconfigurationclass.example.entities.UtilityServlet;
import no.jaflaten.dev.mvcwithconfigurationclass.example.repository.UtilityServletRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UtilityServletService {
    private final UtilityServletRepository repository;

    public Set<UtilityServlet> findAllUtilityServlets() {
        return repository.findAll();
    }
}
