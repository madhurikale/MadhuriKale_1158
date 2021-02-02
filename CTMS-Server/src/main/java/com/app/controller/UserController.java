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
import com.app.dto.Login;
import com.app.dto.ResponseDTO;
import com.app.dto.ResponseListDTO;
import com.app.pojos.User;
import com.app.repository.UserRepository;
import com.app.service.MailService;
import com.app.dto.Email;
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {

	@Autowired
	private UserRepository userRepo;
	@Autowired 
	private MailService mailService;
	@GetMapping
	public ResponseEntity<?> getAllUserDetails() {
		List<User>users = userRepo.findAll();
	//	return new ResponseEntity<>(users, HttpStatus.OK);
		return new ResponseEntity<>(new ResponseListDTO("success","User Found",users),HttpStatus.OK);//status code : 200 , body : list of emps
	}

	
	@GetMapping("/{user_id}")
	public ResponseEntity<?> getEmpDetails(@PathVariable int user_id) {
		System.out.println("in get user dtls " + user_id);
		Optional<User> optional = userRepo.findById(user_id);
		if (optional.isPresent())
	//		return new ResponseEntity<>(optional.get(), HttpStatus.OK);
			return ResponseEntity.ok(optional.get());
		// invalid id
		ErrorResponse resp = new ErrorResponse("User Id Invalid", "");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}
	@PostMapping
	public ResponseEntity<?> addUserDetails(@RequestBody User u) {
		System.out.println("in add User " + u);
		User u1=userRepo.save(u);
		Email email =new Email();
		email.setDestEmail(u.getEmail());
		System.out.println(u.getEmail());
		email.setSubject("TMS Registration");
		email.setMessage("Your Registration  has been Done Successfully\n Please Check  Your Login Details Mentioned Below\n Email:"+u.getEmail()+"\nPassword::"+u.getPassword());
		mailService.sendEmail(email);
		return new ResponseEntity<>(u1, HttpStatus.CREATED);
	}








@PutMapping("/{userId}")
public ResponseEntity<?> updateUserDetails(@PathVariable int userId, @RequestBody User user) {
	System.out.println("in update user " + userId + " " + user);
	// check if user exists
	Optional<User> optional =userRepo.findById(userId);
	if (optional.isPresent()) {
		// user id valid : update the same
		User existingUser = optional.get();// DETACHED
		System.out.println("existing user " + existingUser);
		//existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		// update detached POJO
		return new ResponseEntity<>(userRepo.save(existingUser), HttpStatus.OK);
		// save or update (insert: transient(value of ID : default
		// or non default value BUT existing on DB -- update
	} else
		throw new ResourceNotFoundException("User ID Invalid");

}

@DeleteMapping("/{userId}")
public ResponseEntity<?> deleteEmpDetails(@PathVariable  int userId) {
	System.out.println("in delete user " + userId);
	// check if user exists
	Optional<User> optional = userRepo.findById(userId);
	if (optional.isPresent()) {
		userRepo.deleteById(userId);
		return new ResponseEntity<>(new ResponseDTO("success","User rec deleted with ID " + userId,null), HttpStatus.OK);
	} else
		 throw new ResourceNotFoundException("User ID Invalid : rec deletion failed");
	//	throw new RuntimeException("my own err mesg");

}
@PostMapping("/login")
public ResponseEntity<?> validateUserCredentials(@RequestBody Login login)
{
	User u=userRepo.validateUser(login.getEmail(), login.getPassword());
	if(u==null)
	{
		return new ResponseEntity<>(new ResponseDTO("error","Invalid Credentials",null), HttpStatus.OK);
	}
	
	else
	{
		return new ResponseEntity<>(new ResponseDTO("success","Login Succesfully",u), HttpStatus.OK);
	}
}
}
