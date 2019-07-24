package com.book.builer;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20 , nullable = false, unique = true)
	private String user_id;
	
	@Column(length = 200 , nullable = false)
	private String user_pw;
	
	@Column
	private LocalDateTime reg_date; 
	
	@Builder
	public User(String user_id,String user_pw) {
		this.user_id=user_id;
		this.user_pw=user_pw;
		this.reg_date = LocalDateTime.now();
	}
}
