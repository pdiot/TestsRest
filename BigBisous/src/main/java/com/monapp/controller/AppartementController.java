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
import com.monapp.entity.Appartement;
import com.monapp.entity.Views;

@CrossOrigin
@RestController
public class AppartementController {

	@Autowired
	AppartementDAO appDAO;
	
	@GetMapping("/api/appartements")
	@JsonView (Views.AppartementWithAll.class)
	public ResponseEntity<List<Appartement>> findAll() {
		List<Appartement> appartements = appDAO.findAll();
		
		return new ResponseEntity<List<Appartement>>(appartements, HttpStatus.OK);
	}
	
	
	@GetMapping("/api/appartements/{id}") 
	@JsonView (Views.AppartementWithAll.class)
	public ResponseEntity<Appartement> findOne(@PathVariable("id") Integer id) {
		Appartement appartement = appDAO.findByPrimaryKey(id);
		
		if (appartement == null) {
			return new ResponseEntity<>(appartement, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Appartement>(appartement, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/appartements/{id}")
	@JsonView (Views.AppartementWithAll.class)
	public ResponseEntity<Appartement> delete(@PathVariable("id") Integer id) {
		Appartement appartement = appDAO.findByPrimaryKey(id);
		
		if (appartement == null) {
			return new ResponseEntity<>(appartement, HttpStatus.NOT_FOUND);
		}
		appDAO.delete(appartement);
		return new ResponseEntity<Appartement>(appartement, HttpStatus.OK);
	}
	
	@PostMapping("/api/appartements")
	@JsonView (Views.AppartementWithAll.class)
	public ResponseEntity<Appartement> create(@RequestBody Appartement appartement) {
		
		if (appartement.getId() > 0) {
			return new ResponseEntity<Appartement>(appartement, HttpStatus.BAD_REQUEST);
		}
		
		appDAO.save(appartement);
		
		return new ResponseEntity<Appartement>(appartement, HttpStatus.OK);
		
	}
	
	@PutMapping("/api/appartements")
	@JsonView (Views.AppartementWithAll.class)
	public ResponseEntity<Appartement> update(@RequestBody Appartement appartement) {
		Appartement cl = appDAO.findByPrimaryKey(appartement.getId());
		
		if (cl == null) {
			cl = appDAO.save(appartement);
		} else {
			cl = appDAO.update(appartement);
		}
		
		return new ResponseEntity<Appartement>(cl, HttpStatus.OK);
	}
	
}
