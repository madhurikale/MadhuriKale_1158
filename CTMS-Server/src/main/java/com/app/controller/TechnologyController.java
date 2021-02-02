package com.app.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cust_excs.ResourceNotFoundException;
import com.app.dto.ResponseDTO;
import com.app.dto.ResponseListDTO;
import com.app.pojos.Technology;
import com.app.repository.TechnologyRepository;

@RestController
@RequestMapping("/technologies")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TechnologyController {

	@Autowired
	private TechnologyRepository techRepo;
	

	@GetMapping
	public ResponseEntity<?> getAllTechnologiesDetails() {
		List<Technology>technologies = techRepo.findAll();
	
		return new ResponseEntity<>(new ResponseListDTO("success","details found",technologies),HttpStatus.OK);
	}
	
	
	
	@GetMapping("/{techid}")
	public ResponseEntity<?> getTechDetails(@PathVariable int techid) {
		System.out.println("in get Tech dtls " + techid);
		Optional<Technology> optional = techRepo.findById(techid);
		if (optional.isPresent())
	//		return new ResponseEntity<>(optional.get(), HttpStatus.OK);
			return new ResponseEntity<>(new ResponseDTO("success","details found",optional.get()),HttpStatus.OK);
		// invalid id
		
		return new ResponseEntity<>(new ResponseDTO("error","details not found",null),HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<?> addTechDetails(@RequestBody Technology t) {
		System.out.println("in add Technology " + t);
		return new ResponseEntity<>(new ResponseDTO("success","technology added successfully",techRepo.save(t)),HttpStatus.CREATED);

	}








@PutMapping("/{techId}")
public ResponseEntity<?> updateUserDetails(@PathVariable int techId, @RequestBody Technology technology) {
	System.out.println("in update technology " + techId + " " + technology);
	Optional<Technology> optional =techRepo.findById(techId);
	if (optional.isPresent()) {
		Technology existingTechnology = optional.get();// DETACHED
		System.out.println("existing techno " + existingTechnology);
		//existingTechnology.setName(technology.getName());
		
		return new ResponseEntity<>(techRepo.save(existingTechnology), HttpStatus.OK);
		
	} else
		throw new ResourceNotFoundException("Technology ID Invalid");

}

@DeleteMapping("/{techId}")
public ResponseEntity<?> deleteTechDetails(@PathVariable  int techId) {
	System.out.println("in delete Technology " + techId);
	// check if user exists
	Optional<Technology> optional = techRepo.findById(techId);
	if (optional.isPresent()) {
		techRepo.deleteById(techId);
		return new ResponseEntity<>(new ResponseDTO("success","Technoogy rec deleted with ID " + techId,null), HttpStatus.OK);
	} else
		 throw new ResourceNotFoundException("Technology ID Invalid : rec deletion failed");
	

}
	
}
