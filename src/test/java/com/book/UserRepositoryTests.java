package com.book;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.book.builer.User;
import com.book.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	UserRepository userRepository;
	
	@After
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	@Test
	public void testUserRepository(){
		User user = User.builder().user_id("testId").user_pw("testPw").build();
		userRepository.save(user);
		
		List<User> userList = userRepository.findAll();
		
		User userA = userList.get(0);
		assertThat(userA.getUser_id(), is("testId"));
		assertThat(userA.getUser_pw(), is("testPw"));
	}
}