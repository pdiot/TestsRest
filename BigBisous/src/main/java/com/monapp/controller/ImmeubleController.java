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
import com.monapp.entity.Client;
import com.monapp.entity.Immeuble;
import com.monapp.entity.Logement;
import com.monapp.entity.Views;

@CrossOrigin
@RestController
public class ImmeubleController {

	@Autowired
	ImmeubleDAO immDAO;

	@Autowired
	LogementDAO logDAO;
	
	@GetMapping("/api/immeubles")
	@JsonView (Views.ImmeubleWithAppartement.class)
	public ResponseEntity<List<Immeuble>> findAll() {
		List<Immeuble> immeubles = immDAO.findAll();
		
		return new ResponseEntity<List<Immeuble>>(immeubles, HttpStatus.OK);
	}
	
	
	@GetMapping("/api/immeubles/{id}") 
	@JsonView (Views.ImmeubleWithAppartement.class)
	public ResponseEntity<Immeuble> findOne(@PathVariable("id") Integer id) {
		Immeuble immeuble = immDAO.findByPrimaryKey(id);
		
		if (immeuble == null) {
			return new ResponseEntity<>(immeuble, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Immeuble>(immeuble, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/immeubles/{id}")
	@JsonView (Views.ImmeubleWithAppartement.class)
	public ResponseEntity<Immeuble> delete(@PathVariable("id") Integer id) {
		Immeuble immeuble = immDAO.findByPrimaryKey(id);
		
		if (immeuble == null) {
			return new ResponseEntity<>(immeuble, HttpStatus.NOT_FOUND);
		}
		immDAO.delete(immeuble);
		return new ResponseEntity<Immeuble>(immeuble, HttpStatus.OK);
	}
	
	@PostMapping("/api/immeubles")
	@JsonView (Views.ImmeubleWithAppartement.class)
	public ResponseEntity<Immeuble> create(@RequestBody Immeuble immeuble) {
		
		if (immeuble.getId() > 0) {
			return new ResponseEntity<Immeuble>(immeuble, HttpStatus.BAD_REQUEST);
		}
		
		immDAO.save(immeuble);
		
		return new ResponseEntity<Immeuble>(immeuble, HttpStatus.OK);
		
	}
	
	@PutMapping("/api/immeubles")
	@JsonView (Views.ImmeubleWithAppartement.class)
	public ResponseEntity<Immeuble> update(@RequestBody Immeuble immeuble) {
		Immeuble cl = immDAO.findByPrimaryKey(immeuble.getId());
		
		if (cl == null) {
			cl = immDAO.save(immeuble);
		} else {
			cl = immDAO.update(immeuble);
		}
		
		return new ResponseEntity<Immeuble>(cl, HttpStatus.OK);
	}
	
	@PutMapping("/api/immeubles/{id_immeuble}/logement/{id_logement}")
	@JsonView (Views.ClientWithLogement.class)
	public ResponseEntity<Immeuble> update(@PathVariable("id_immeuble") Integer id_immeuble, @PathVariable("id_logement") Integer id_logement) {

		Immeuble im = immDAO.findByPrimaryKey(id_immeuble);
		
		Logement log = logDAO.findByPrimaryKey(id_logement);
		
		if (im  == null || log == null) {
			return new ResponseEntity<Immeuble>(im, HttpStatus.NOT_FOUND);
		}
		
		if (log instanceof Appartement) {
			im.ajouterAppartement((Appartement)log);
			logDAO.update(log);
			
			return new ResponseEntity<Immeuble>(im, HttpStatus.OK);
		} else {
			return new ResponseEntity<Immeuble>(im, HttpStatus.NOT_ACCEPTABLE);
		}
		
		
		
	}
	
	
}
