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
import com.app.dto.Email;
import com.app.dto.ResponseDTO;
import com.app.dto.ResponseListDTO;
import com.app.dto.TrainingDetails;
import com.app.pojos.Training;
import com.app.pojos.User;
import com.app.repository.TrainingRepository;
import com.app.repository.UserRepository;
import com.app.service.MailService;

@RestController
@RequestMapping("/trainings")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TrainingController {
	
	@Autowired
	private TrainingRepository trainingRepo;
	@Autowired 
	private UserRepository userRepo;
	@Autowired 
	private MailService mailService;
	@GetMapping
	public ResponseEntity<?> getAllTraningDetails() {
		List<Training>training = trainingRepo.findAll();
	
		return ResponseEntity.ok(training);
	}
	@GetMapping("/{trainingId}")
	public ResponseEntity<?> getTrainingDetails(@PathVariable int trainingId) {
		System.out.println("in get Tech dtls " + trainingId);
		Optional<Training> optional = trainingRepo.findById(trainingId);
		if (optional.isPresent())
	//		return new ResponseEntity<>(optional.get(), HttpStatus.OK);
			return new ResponseEntity<>(new ResponseDTO("success","Details Found",optional.get()),HttpStatus.OK);
		// invalid id
		return new ResponseEntity<>(new ResponseDTO("error","Training id not Found",null),HttpStatus.NOT_FOUND);

	}
	@PostMapping
	public ResponseEntity<?> addTechDetails(@RequestBody Training t) {
		System.out.println("in add training " + t);
		return new ResponseEntity<>(trainingRepo.save(t), HttpStatus.CREATED);
	}








@PutMapping("/{trainingId}")
public ResponseEntity<?> updateTrainingDetails(@PathVariable int trainingId, @RequestBody Training training) {
	System.out.println("in update training " + trainingId + " " + training);
	Optional<Training> optional =trainingRepo.findById(trainingId);
	if (optional.isPresent()) {
		Training existingtraining = optional.get();// DETACHED
		System.out.println("existing techno " + existingtraining);
		existingtraining.setEmpid(training.getEmpid());
		existingtraining.setTechnology_id(training.getTechnology_id());
		existingtraining.setTrainer_id(training.getTrainer_id());
		existingtraining.setNo_of_trainee(training.getNo_of_trainee());
		existingtraining.setProject_mgr_status(training.getProject_mgr_status());
		existingtraining.setTraning_cor_status(training.getTraning_cor_status());
		existingtraining.setStartDate(training.getStartDate());
		existingtraining.setEndDate(training.getEndDate());
		existingtraining.setComment(training.getComment());
		trainingRepo.save(existingtraining);
		return new ResponseEntity<>(new ResponseDTO("success","Request updated",null), HttpStatus.OK);
		
	} else
		return new ResponseEntity<>(new ResponseDTO("error","Request updatation failed",null), HttpStatus.NOT_FOUND);

}

@PutMapping("/approve-by-training-cor/{trainingId}")
public ResponseEntity<?> approvedByTrainingCoordinator(@PathVariable int trainingId) {
	System.out.println("in update training " + trainingId );
	Optional<Training> optional =trainingRepo.findById(trainingId);
	if (optional.isPresent()) {
		Training existingtraining = optional.get();// DETACHED
		System.out.println("existing techno " + existingtraining);
		existingtraining.setProject_mgr_status("approved");
		existingtraining.setTraning_cor_status("approved");
		trainingRepo.save(existingtraining);
		Optional<User> u=userRepo.findById(existingtraining.getEmpid());
		if (optional.isPresent())
		{
			User user=u.get();
			Email email =new Email();
			email.setDestEmail(user.getEmail());
			System.out.println(user.getEmail());
			email.setSubject("Training Approval");
			email.setMessage("Your request has been approved");
			mailService.sendEmail(email);
			
		}
		
		
		return new ResponseEntity<>(new ResponseDTO("success","Request Approved",null), HttpStatus.OK);
		
	} else
		return new ResponseEntity<>(new ResponseDTO("error","Training id not found",null), HttpStatus.NOT_FOUND);


}


@PutMapping("/reject-by-training-cor/{trainingId}")
public ResponseEntity<?> rejectedByTrainingCoordinator(@PathVariable int trainingId) {
	System.out.println("in update training " + trainingId );
	Optional<Training> optional =trainingRepo.findById(trainingId);
	if (optional.isPresent()) {
		Training existingtraining = optional.get();// DETACHED
		System.out.println("existing techno " + existingtraining);
		
		existingtraining.setTraning_cor_status("rejected");
		trainingRepo.save(existingtraining);
		Optional<User> u=userRepo.findById(existingtraining.getEmpid());
		if (optional.isPresent())
		{
			User user=u.get();
			Email email =new Email();
			email.setDestEmail(user.getEmail());
			email.setSubject("Training Rejection");
			email.setMessage("Your request has been rejected");
			mailService.sendEmail(email);
			
		}
		return new ResponseEntity<>(new ResponseDTO("success","Request rejected",null), HttpStatus.OK);
		
	} else
		return new ResponseEntity<>(new ResponseDTO("error","Training id not found",null), HttpStatus.NOT_FOUND);


}
@PutMapping("/reject-by-project-mgr/{trainingId}")
public ResponseEntity<?> rejectedByProjectManager(@PathVariable int trainingId) {
	System.out.println("in update training " + trainingId );
	Optional<Training> optional =trainingRepo.findById(trainingId);
	if (optional.isPresent()) {
		Training existingtraining = optional.get();// DETACHED
		System.out.println("existing techno " + existingtraining);
		
		existingtraining.setProject_mgr_status("rejected");
		trainingRepo.save(existingtraining);
		
			
			Email email =new Email();
			email.setDestEmail("madhurikale50@gmail.com");
			email.setSubject("Training Rejection");
			email.setMessage("Training request has been rejected");
			mailService.sendEmail(email);
			
		
		return new ResponseEntity<>(new ResponseDTO("success","Request rejected",null), HttpStatus.OK);
		
	} else
		return new ResponseEntity<>(new ResponseDTO("error","Training id not found",null), HttpStatus.NOT_FOUND);


}

@DeleteMapping("/{trainingId}")
public ResponseEntity<?> deleteTechDetails(@PathVariable  int trainingId) {
	System.out.println("in delete training " + trainingId);
	// check if user exists
	Optional<Training> optional = trainingRepo.findById(trainingId);
	if (optional.isPresent()) {
		trainingRepo.deleteById(trainingId);
		return new ResponseEntity<>(new ResponseDTO("success","Technoogy rec deleted with ID " + trainingId,null), HttpStatus.OK);
	} else
		 throw new ResourceNotFoundException("training ID Invalid : rec deletion failed");
	

}
	
	
@GetMapping("/requested-training")
public ResponseEntity<?> getRequestedTraining() {
	System.out.println("in get Tech dtls " );
	List<TrainingDetails> trainingDetails=trainingRepo.getTrainingDetails();
	//return new ResponseEntity<>(new ResponseDTO("success","Training found " + trainingId,trainingDetails), HttpStatus.OK);
	if(trainingDetails!=null)
	{
		return new ResponseEntity<>(new ResponseListDTO("success","Training found ",trainingDetails), HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<>(new ResponseListDTO("success","Training found ",null), HttpStatus.NOT_FOUND);

	}
}

@GetMapping("/pending-training/{empid}")
public ResponseEntity<?> getPendingRequestedTraining(@PathVariable  int empid) {
	System.out.println("in get Tech dtls " );
	List<TrainingDetails> trainingDetails=trainingRepo.getPendingTrainingDetails(empid);
	//return new ResponseEntity<>(new ResponseDTO("success","Training found " + trainingId,trainingDetails), HttpStatus.OK);
	if(trainingDetails.size()>0)
	{
		return new ResponseEntity<>(new ResponseListDTO("success","Training found ",trainingDetails), HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<>(new ResponseDTO("error","Training not found ",null), HttpStatus.NOT_FOUND);

	}
}
@GetMapping("/requested-training/{trainingId}")
public ResponseEntity<?> getRequestedTraining(@PathVariable  int trainingId) {
	System.out.println("in get Tech dtls " );
	TrainingDetails trainingDetails=trainingRepo.getTrainingDetailsById(trainingId);
	//return new ResponseEntity<>(new ResponseDTO("success","Training found " + trainingId,trainingDetails), HttpStatus.OK);
	if(trainingDetails!=null)
	{
		return new ResponseEntity<>(new ResponseDTO("success","Training found ",trainingDetails), HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<>(new ResponseDTO("error","Training not found ",null), HttpStatus.NOT_FOUND);

	}
}

@GetMapping("/training-history/{empid}")
public ResponseEntity<?> getProjectManagerHistory(@PathVariable  int empid) {
	System.out.println("in get Tech dtls " );
	List<TrainingDetails> trainingDetails=trainingRepo.getTrainingHistory(empid);
	//return new ResponseEntity<>(new ResponseDTO("success","Training found " + trainingId,trainingDetails), HttpStatus.OK);
	if(trainingDetails.size()>0)
	{
		return new ResponseEntity<>(new ResponseListDTO("success","Training found ",trainingDetails), HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<>(new ResponseListDTO("error","Training not found ",null), HttpStatus.NOT_FOUND);

	}
}

@GetMapping("/training-history")
public ResponseEntity<?> getTrainingCoordinatorHistory() {
	System.out.println("in get Tech dtls " );
	List<TrainingDetails> trainingDetails=trainingRepo.getTrainingHistory();
	//return new ResponseEntity<>(new ResponseDTO("success","Training found " + trainingId,trainingDetails), HttpStatus.OK);
	if(trainingDetails.size()>0)
	{
		return new ResponseEntity<>(new ResponseListDTO("success","Training found ",trainingDetails), HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<>(new ResponseListDTO("error","Training not found ",null), HttpStatus.NOT_FOUND);

	}
}

}
