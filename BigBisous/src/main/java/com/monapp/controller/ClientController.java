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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.monapp.dao.*;
import com.monapp.entity.Client;
import com.monapp.entity.Logement;
import com.monapp.entity.Views;

@CrossOrigin
@RestController
public class ClientController {

	@Autowired
	ClientDAO cliDAO;
	
	@Autowired
	LogementDAO logDAO;
	
	@GetMapping("/api/clients")
	@JsonView (Views.ClientWithLogement.class)
	public ResponseEntity<List<Client>> findAll() {
		List<Client> clients = cliDAO.findAll();
		
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}
	
	
	@GetMapping("/api/clients/{id}") 
	@JsonView (Views.ClientWithLogement.class)
	public ResponseEntity<Client> findOne(@PathVariable("id") Integer id) {
		Client client = cliDAO.findByPrimaryKey(id);
		
		if (client == null) {
			return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/clients/{id}")
	@JsonView (Views.ClientWithLogement.class)
	public ResponseEntity<Client> delete(@PathVariable("id") Integer id) {
		Client client = cliDAO.findByPrimaryKey(id);
		
		if (client == null) {
			return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
		}
		cliDAO.delete(client);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	@PostMapping("/api/clients")
	@JsonView (Views.ClientWithLogement.class)
	public ResponseEntity<Client> create(@RequestBody Client client) {
		
		if (client.getId() > 0) {
			return new ResponseEntity<Client>(client, HttpStatus.BAD_REQUEST);
		}
		
		cliDAO.save(client);
		
		return new ResponseEntity<Client>(client, HttpStatus.OK);
		
	}
	
	@PutMapping("/api/clients")
	@JsonView (Views.ClientWithLogement.class)
	public ResponseEntity<Client> update(@RequestBody Client client) {
		Client cl = cliDAO.findByPrimaryKey(client.getId());
		
		if (cl == null) {
			cl = cliDAO.save(client);
		} else {
			cl = cliDAO.update(client);
		}
		
		return new ResponseEntity<Client>(cl, HttpStatus.OK);
	}
	
	@PutMapping("/api/clients/{id_client}/logement/{id_logement}")
	@JsonView (Views.ClientWithLogement.class)
	public ResponseEntity<Client> update(@PathVariable("id_client") Integer id_client, @PathVariable("id_logement") Integer id_logement) {

		Client cl = cliDAO.findByPrimaryKey(id_client);
		
		Logement log = logDAO.findByPrimaryKey(id_logement);
		
		if (cl == null || log == null) {
			return new ResponseEntity<Client>(cl, HttpStatus.NOT_FOUND);
		}
		
		cl.ajouterLogement(log);
		logDAO.update(log);
		cliDAO.update(cl);
		
		return new ResponseEntity<Client>(cl, HttpStatus.OK);
		
	}
	
}
