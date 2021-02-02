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
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Trainer;
import com.app.repository.TrainerRepository;

@RestController
@RequestMapping("/trainers")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TrainerController {
	
	@Autowired
	private TrainerRepository trainerRepo;
	
	@GetMapping
	public ResponseEntity<?> getAllTechnologiesDetails() {
		List<Trainer>technologies = trainerRepo.findAll();
	
		return ResponseEntity.ok(technologies);
	}
	
	
	
	@GetMapping("/{trainerId}")
	public ResponseEntity<?> getTrainerDetails(@PathVariable int trainerId) {
		System.out.println("in get Tech dtls " + trainerId);
		Optional<Trainer> optional = trainerRepo.findById(trainerId);
		if (optional.isPresent())
	//		return new ResponseEntity<>(optional.get(), HttpStatus.OK);
			return ResponseEntity.ok(optional.get());
		// invalid id
		ErrorResponse resp = new ErrorResponse("trainer Id Invalid", "");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}
	@PostMapping
	public ResponseEntity<?> addTechDetails(@RequestBody Trainer t) {
		System.out.println("in add trainer " + t);
		return new ResponseEntity<>(new ResponseDTO("success","Trainer Added succesfully",trainerRepo.save(t)), HttpStatus.CREATED);
	}








@PutMapping("/{trainerId}")
public ResponseEntity<?> updateUserDetails(@PathVariable int trainerId, @RequestBody Trainer trainer) {
	System.out.println("in update trainer " + trainerId + " " + trainer);
	Optional<Trainer> optional =trainerRepo.findById(trainerId);
	if (optional.isPresent()) {
		Trainer existingtrainer = optional.get();// DETACHED
		System.out.println("existing techno " + existingtrainer);
		
		
		return new ResponseEntity<>(trainerRepo.save(existingtrainer), HttpStatus.OK);
		
	} else
		throw new ResourceNotFoundException("trainer ID Invalid");

}

@DeleteMapping("/{trainerId}")
public ResponseEntity<?> deleteTechDetails(@PathVariable  int trainerId) {
	System.out.println("in delete trainer " + trainerId);
	// check if user exists
	Optional<Trainer> optional = trainerRepo.findById(trainerId);
	if (optional.isPresent()) {
		trainerRepo.deleteById(trainerId);
		return new ResponseEntity<>(new ResponseDTO("success","Technoogy rec deleted with ID " + trainerId,null), HttpStatus.OK);
	} else
		 throw new ResourceNotFoundException("trainer ID Invalid : rec deletion failed");
	

}
	
	
	
	
}
