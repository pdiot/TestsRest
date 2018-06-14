package com.monapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.monapp.dao.*;
import com.monapp.entity.Maison;
import com.monapp.entity.Views;

@CrossOrigin
@RestController
public class MaisonController {

	@Autowired
	MaisonDAO maiDAO;
	
	@GetMapping("/api/maisons")
	@JsonView (Views.MaisonWithClient.class)
	public ResponseEntity<List<Maison>> findAll() {
		List<Maison> maisons = maiDAO.findAll();
		
		return new ResponseEntity<List<Maison>>(maisons, HttpStatus.OK);
	}
	
	
	@GetMapping("/api/maisons/{id}") 
	@JsonView (Views.MaisonWithClient.class)
	public ResponseEntity<Maison> findOne(@PathVariable("id") Integer id) {
		Maison maison = maiDAO.findByPrimaryKey(id);
		
		if (maison == null) {
			return new ResponseEntity<>(maison, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Maison>(maison, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/maisons/{id}")
	@JsonView (Views.MaisonWithClient.class)
	public ResponseEntity<Maison> delete(@PathVariable("id") Integer id) {
		Maison maison = maiDAO.findByPrimaryKey(id);
		
		if (maison == null) {
			return new ResponseEntity<>(maison, HttpStatus.NOT_FOUND);
		}
		maiDAO.delete(maison);
		return new ResponseEntity<Maison>(maison, HttpStatus.OK);
	}
	
	@PostMapping("/api/maisons")
	@JsonView (Views.MaisonWithClient.class)
	public ResponseEntity<Maison> create(@RequestBody Maison maison) {
		
		if (maison.getId() > 0) {
			return new ResponseEntity<Maison>(maison, HttpStatus.BAD_REQUEST);
		}
		
		maiDAO.save(maison);
		
		return new ResponseEntity<Maison>(maison, HttpStatus.OK);
		
	}
	
	@PutMapping("/api/maisons")
	@JsonView (Views.MaisonWithClient.class)
	public ResponseEntity<Maison> update(@RequestBody Maison maison) {
		Maison cl = maiDAO.findByPrimaryKey(maison.getId());
		
		if (cl == null) {
			cl = maiDAO.save(maison);
		} else {
			cl = maiDAO.update(maison);
		}
		
		return new ResponseEntity<Maison>(cl, HttpStatus.OK);
	}
	
}
