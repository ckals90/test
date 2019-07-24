package com.book.controller;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.dto.UserSaveRequestDto;
import com.book.repository.UserRepository;

import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class UserController {
	
	private UserRepository userRepository;
	private final String SUCCESS ="SUCCESS";
	private final String FAIL = "FAIL";
	
	@PostMapping("/com/insertUser")
	public String insertUser(@RequestParam Map<String,String> map) {
		UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto();
		String user_id=map.get("user_id");
		userSaveRequestDto.setUser_id(user_id);
		userSaveRequestDto.setUser_pw(new BCryptPasswordEncoder().encode(map.get("user_pw")));
		if(idDuplCheck(user_id)==0) {
			userRepository.save(userSaveRequestDto.toEntity());
			return this.SUCCESS;
		}
		return this.FAIL;
	}
	
	@GetMapping("/com/idDuplCheck")
	public String checkDuplicationUserId(@RequestParam("user_id")String user_id) {
		if(idDuplCheck(user_id)==0) {
			return this.SUCCESS;
		}
		return this.FAIL;
	}
	
	public int idDuplCheck(String id) {
		return Integer.parseInt(userRepository.findByUserId(id));
	}
}
