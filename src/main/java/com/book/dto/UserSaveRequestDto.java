package com.book.dto;



import com.book.builer.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveRequestDto {
	private String user_id;
	private String user_pw;
	
	public User toEntity() {
		return User.builder()
				.user_id(user_id)
				.user_pw(user_pw)
				.build();
	}
}
