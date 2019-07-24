package com.book.controller.login;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.repository.UserRepository;

@RestController
public class LoginController {
	
	@Resource
	UserRepository userRepo;

	@PostMapping("/com/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest request, ModelMap model,@RequestParam Map<String,Object> paramMap) {
		String userId=(String)paramMap.get("user_id");
		String userPw=(String)paramMap.get("user_pw");
		String returnVal ="SUCCESS";
		String getPw = userRepo.findByPw(userId);
		
		boolean check= new BCryptPasswordEncoder().matches(userPw,getPw);
		if(!check) {
			return returnVal ="FAIL";
		}
		return returnVal;
		
	}
}
