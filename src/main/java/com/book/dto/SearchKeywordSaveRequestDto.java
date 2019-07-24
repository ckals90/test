package com.book.dto;



import com.book.builer.SearchKeyword;
import com.book.builer.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchKeywordSaveRequestDto {
	private String keyword;
	private int cnt;
	
	public SearchKeyword toEntity() {
		return SearchKeyword.builder()
				.keyword(keyword)
				.cnt(cnt)
				.build();
	}
}
