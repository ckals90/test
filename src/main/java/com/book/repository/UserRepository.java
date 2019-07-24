package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.book.builer.User;


public interface UserRepository extends JpaRepository<User,Long>{
	@Query(value="SELECT COUNT(*) FROM USER WHERE USER_ID = ?1", nativeQuery = true)
    String findByUserId(String user_id);
	
	@Query(value="SELECT USER_PW FROM USER WHERE USER_ID= ?1", nativeQuery = true)
	String findByPw(String user_id);
}
