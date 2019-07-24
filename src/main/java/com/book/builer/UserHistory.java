package com.book.builer;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class UserHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20 , nullable = false)
	private String user_id;
	
	@Column(length = 200 , nullable = false)
	private String keyword;
	
	@Column
	private LocalDateTime reg_date; 
	
	@Builder
	public UserHistory(String user_id,String keyword) {
		this.user_id=user_id;
		this.keyword=keyword;
		this.reg_date=LocalDateTime.now();
	}
}
