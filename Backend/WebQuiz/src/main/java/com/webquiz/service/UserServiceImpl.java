package com.webquiz.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webquiz.dto.QuizDTO;
import com.webquiz.dto.QuizTopicDTO;
import com.webquiz.dto.ScoreDTO;
import com.webquiz.dto.UserDTO;
import com.webquiz.entity.QuizQuestions;
import com.webquiz.entity.QuizTopic;
import com.webquiz.entity.Score;
import com.webquiz.entity.UserEntity;
import com.webquiz.exception.WebQuizException;
import com.webquiz.repository.QuizTopicRepository;
import com.webquiz.repository.ScoreRepository;
import com.webquiz.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private QuizTopicRepository qrepo;
	
	@Autowired
	private ScoreRepository scoreRepo;
	
	
	@Override
	public void loginUser(String email, String password) throws WebQuizException{
		
		Optional<UserEntity> op = userRepo.findByEmail(email);
		UserEntity ue = op.orElseThrow(()->new WebQuizException("SERVICE.USER NOT FOUND"));
		UserDTO uDto = new UserDTO();
		uDto.setPassword(ue.getPassword());
		if(!uDto.getPassword().equals(password)) {
			throw new WebQuizException("Incorrect Password");
		}
		
	}

	@Override
	public Integer adduser(UserDTO user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());
		userEntity.setPhoneNo(user.getPhoneNo());
		UserEntity ur = userRepo.save(userEntity);
		return ur.getUserId();
	}

	@Override
	public UserDTO getUser(String userEmail) {
		Optional<UserEntity> op = userRepo.findByEmail(userEmail);
		UserEntity ue = op.orElseThrow();
		UserDTO udto = new UserDTO();
		udto.setUserId(ue.getUserId());
		udto.setEmail(ue.getEmail());
		udto.setPhoneNo(ue.getPhoneNo());
		return udto;
	}

	@Override
	public List<UserDTO> getAllUser() {
		Iterable<UserEntity> allUser = userRepo.findAll();
		List<UserDTO> userdto = new ArrayList<>();
		allUser.forEach(user->{
			UserDTO ud = new UserDTO();
			ud.setUserId(user.getUserId());
			ud.setEmail(user.getEmail());
			ud.setPhoneNo(user.getPhoneNo());
			userdto.add(ud);
		});
		return userdto;
	}

	@Override
	public void updateUser(Integer userId,UserDTO userdto) throws Exception {
		Optional<UserEntity> optional = userRepo.findById(userId);
		UserEntity ue = optional.orElseThrow(()->new Exception());
		ue.setEmail(userdto.getEmail());
		userRepo.save(ue);
	}

	@Override
	public void deleteUser(String email) {
		Optional<UserEntity> optional = userRepo.findByEmail(email);
		UserEntity ue = optional.orElseThrow();
		userRepo.deleteById(ue.getUserId());
		
	}

	@Override
	public String addQuiz(QuizTopicDTO qtDTO) throws WebQuizException {
		Optional<QuizTopic> ita = qrepo.findByQuizId(qtDTO.getQuizId());
		if(!ita.isEmpty()) {
			return "Change the QuizeId";
		}
		
		QuizTopic qt = new QuizTopic();
		qt.setQuizId(qtDTO.getQuizId());
		qt.setQuizTopic(qtDTO.getQuizTopic());
		
		List<QuizQuestions> qq = new ArrayList<QuizQuestions>();
		for(QuizDTO q:qtDTO.getQuizDTO()) {
			QuizQuestions qqs = new QuizQuestions();
			qqs.setAnsKey(q.getAnsKey());
			qqs.setQuestion(q.getQuestion());
			qqs.setQ1(q.getQ1());
			qqs.setQ2(q.getQ2());
			qqs.setQ3(q.getQ3());
			qqs.setQ4(q.getQ4());
			qqs.setQ5(q.getQ5());
			qq.add(qqs);
		}
		qt.setQuiza(qq);
		qrepo.save(qt);
		return qt.getQuizId();
	}

	@Override
	public QuizTopicDTO getQuiz(String quizId) throws WebQuizException{
		Optional<QuizTopic> ita = qrepo.findByQuizId(quizId);
		QuizTopic qp = ita.orElseThrow(()->new WebQuizException("No Questions is there."));
		QuizTopicDTO qdto = new QuizTopicDTO();
		qdto.setQuizId(qp.getQuizId());
		qdto.setQuizTopic(qp.getQuizTopic());
		List<QuizDTO> lq = new ArrayList<QuizDTO>();
		for(QuizQuestions qt:qp.getQuiza()) {
			QuizDTO qd = new QuizDTO();
			qd.setQuestion(qt.getQuestion());
			qd.setQ1(qt.getQ1());
			qd.setQ2(qt.getQ2());
			qd.setQ3(qt.getQ3());
			qd.setQ4(qt.getQ4());
			qd.setQ5(qt.getQ5());
			qd.setAnsKey(qt.getAnsKey());
			lq.add(qd);
		}
		qdto.setQuizDTO(lq);
		return qdto;
	}

	@Override
	public Integer calculateScore(ScoreDTO qt) throws WebQuizException {
		
//		Optional<QuizTopic> op = qrepo.findByQuizId(qtdto.getQuizId());
//		QuizTopic qt = op.orElseThrow(()->new WebQuizException("Not Found"));
//		List<QuizQuestions> qkey = qt.getQuiza();
//		List<QuizDTO> qans = qtdto.getQuizDTO(); 
//		int s =0;
//		for(int i=0;i<qkey.size();i++) {
//				if(qkey.get(i).getAnsKey().equals(qans.get(i).getAnsKey())) {
//					s++;
//				}
//			
//		}
		
		Score sdto = new Score(); 
		sdto.setQuizId(qt.getQuizId());
		sdto.setQuizScore(qt.getQuizScore());
		sdto.setUserEmail(qt.getUserEmail());
		scoreRepo.save(sdto);
		return sdto.getQuizScore();
	}

	@Override
	public List<QuizTopicDTO> getAllQuiz() throws WebQuizException {
		Iterable<QuizTopic> it = qrepo.findAll();
		List<QuizTopic> list = new ArrayList<QuizTopic>();
		
		it.forEach(list::add);
		if(list.isEmpty()) {
			throw new WebQuizException("No Quiz found");
		}
		List<QuizTopicDTO> qtDto = new ArrayList<>();
		for(QuizTopic q:list) {
			QuizTopicDTO qt = new QuizTopicDTO();
			qt.setQuizId(q.getQuizId());
			qt.setQuizTopic(q.getQuizTopic());
			qtDto.add(qt);
		}
		return qtDto;
	}
	
	
	
	
	
	
	
	
}
