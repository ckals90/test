package com.book.dto;



import com.book.builer.UserHistory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserHistorySaveRequestDto {
	private String user_id;
	private String keyword;
	
	public UserHistory toEntity() {
		return UserHistory.builder()
				.user_id(user_id)
				.keyword(keyword)
				.build();
	}
}
