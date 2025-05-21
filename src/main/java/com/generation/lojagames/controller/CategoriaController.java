package com.generation.lojagames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagames.model.Categoria;
import com.generation.lojagames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	 @Autowired
	    private CategoriaRepository repository;

	    @GetMapping
	    public ResponseEntity<List<Categoria>> getAll() {
	        return ResponseEntity.ok(repository.findAll());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
	        return repository.findById(id)
	                .map(resp -> ResponseEntity.ok(resp))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public ResponseEntity<Categoria> post(@RequestBody Categoria categoria) {
	        return ResponseEntity.status(201).body(repository.save(categoria));
	    }

	    @PutMapping
	    public ResponseEntity<Categoria> put(@RequestBody Categoria categoria) {
	        return ResponseEntity.ok(repository.save(categoria));
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        repository.deleteById(id);
	    }

}
