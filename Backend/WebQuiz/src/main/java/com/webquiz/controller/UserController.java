package com.webquiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.webquiz.dto.QuizTopicDTO;
import com.webquiz.dto.ScoreDTO;
import com.webquiz.dto.UserDTO;
import com.webquiz.entity.QuizQuestions;
import com.webquiz.exception.WebQuizException;
import com.webquiz.service.UserService;

@RestController
@CrossOrigin(value="http://localhost:4200/")
@RequestMapping(value = "/webquiz")
public class UserController {

	@Autowired
	private UserService us;
	
//	@Autowired
//	private Environment environment;
	@CrossOrigin(origins ="'http://127.0.0.1:5500")
	@PostMapping(value="/login")
	public ResponseEntity<String> login(@RequestBody UserDTO user) throws WebQuizException{
		us.loginUser(user.getEmail(), user.getPassword());
		return new ResponseEntity<>("Login Successful",HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{userID}")
	public ResponseEntity<UserDTO> user(@PathVariable String userID){
		UserDTO userData = us.getUser(userID);
		return new ResponseEntity<>(userData,HttpStatus.OK);
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<UserDTO> ud = new ArrayList<>();
		ud=us.getAllUser();
		return new ResponseEntity<>(ud,HttpStatus.OK);
	}
	
	@PostMapping(value= "/registerUser")
	public ResponseEntity<Integer> registration(@RequestBody UserDTO user){
		int res = us.adduser(user);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@PutMapping(value="/userUpdate/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable Integer userId,@RequestBody UserDTO userdto) throws Exception{
		us.updateUser(userId, userdto);
		return new ResponseEntity<>("User SUccessfully updated",HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{userEmail}")
	public ResponseEntity<String> deleteUser(@PathVariable String userEmail){
		us.deleteUser(userEmail);
		return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
	}
	
	@PostMapping("/addquiz")
	public ResponseEntity<String> addquiz(@RequestBody QuizTopicDTO qtDTO) throws WebQuizException{
		
		return new ResponseEntity<String>(us.addQuiz(qtDTO),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getQuiz/{quizId}")
	public ResponseEntity<QuizTopicDTO> getQuiz(@PathVariable String quizId) throws WebQuizException{
		
		QuizTopicDTO qdto= us.getQuiz(quizId);
		
		return new ResponseEntity<>(qdto,HttpStatus.OK);
		
	}
	
	@PostMapping("/quizscore")
	public ResponseEntity<String> calScore(@RequestBody ScoreDTO sdto) throws WebQuizException{
		int s = us.calculateScore(sdto);
		String res = "Your Score is : "+s;
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	
	@GetMapping("/allQuizs")
	public ResponseEntity<List<QuizTopicDTO>> getAllQuizes() throws WebQuizException{
		List<QuizTopicDTO> res = us.getAllQuiz();
		
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
