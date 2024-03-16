package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persons;
import com.example.demo.service.PersonService;

@RestController
@CrossOrigin
public class BasicController {

	@Autowired
	private PersonService personService;
	
	
	@GetMapping("/all")
	public List<Persons> getAll() {
		
		return personService.findAll();
	}
	
	@PostMapping(path = "/set")
	public Persons addPerson(@RequestBody Persons person) {
		return personService.savePerson(person);
	}	
}
