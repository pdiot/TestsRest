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
import com.monapp.entity.Logement;
import com.monapp.entity.Views;

@CrossOrigin
@RestController
public class LogementController {

	@Autowired
	LogementDAO logDAO;
	
	@GetMapping("/api/logements")
	@JsonView (Views.LogementWithAll.class)
	public ResponseEntity<List<Logement>> findAll() {
		List<Logement> logements = logDAO.findAll();
		
		return new ResponseEntity<List<Logement>>(logements, HttpStatus.OK);
	}
	
	
	@GetMapping("/api/logements/{id}") 
	@JsonView (Views.LogementWithAll.class)
	public ResponseEntity<Logement> findOne(@PathVariable("id") Integer id) {
		Logement logement = logDAO.findByPrimaryKey(id);
		
		if (logement == null) {
			return new ResponseEntity<>(logement, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Logement>(logement, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/logements/{id}")
	@JsonView (Views.LogementWithAll.class)
	public ResponseEntity<Logement> delete(@PathVariable("id") Integer id) {
		Logement logement = logDAO.findByPrimaryKey(id);
		
		if (logement == null) {
			return new ResponseEntity<>(logement, HttpStatus.NOT_FOUND);
		}
		logDAO.delete(logement);
		return new ResponseEntity<Logement>(logement, HttpStatus.OK);
	}

	
}
