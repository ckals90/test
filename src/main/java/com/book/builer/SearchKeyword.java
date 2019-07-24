package com.book.builer;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class SearchKeyword {
	@Id
	@Column(length = 20 , nullable = false, unique = false)
	private String keyword;
	
	@Column
	private int cnt;
	
	@Builder
	public SearchKeyword(String keyword,int cnt) {
		this.keyword=keyword;
		this.cnt=cnt;
	}
}
