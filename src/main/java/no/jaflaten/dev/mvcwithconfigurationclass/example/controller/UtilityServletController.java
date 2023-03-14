package no.jaflaten.dev.mvcwithconfigurationclass.example.controller;

import lombok.RequiredArgsConstructor;
import no.jaflaten.dev.mvcwithconfigurationclass.example.entities.UtilityServlet;
import no.jaflaten.dev.mvcwithconfigurationclass.example.service.UtilityServletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/utility/servlet")
@RequiredArgsConstructor
public class UtilityServletController {

    private final UtilityServletService service;

    @GetMapping(value = "/")
    public ResponseEntity<Set<UtilityServlet>> findAll() {
        Set<UtilityServlet> servlets = service.findAllUtilityServlets();
        return servlets.size() > 0 ? ResponseEntity.ok().body(servlets) : ResponseEntity.noContent().build();
    }
}
