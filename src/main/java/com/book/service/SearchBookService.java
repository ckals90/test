package com.book.service;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.book.dto.SearchKeywordSaveRequestDto;
import com.book.dto.UserHistorySaveRequestDto;
import com.book.repository.SearchKeywordRepository;
import com.book.repository.UserHistoryRepository;

@Service
public class SearchBookService {
	
	@Resource
	UserHistoryRepository userHistoryRepo;
	@Resource
	SearchKeywordRepository keywordRepo;
	
	public HttpURLConnection searchForKaKao(HttpURLConnection conn) throws ProtocolException {
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "KakaoAK 50ec4effd25cf0e8ae7f8fc4bf355430");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		return conn;
	}
	public HttpURLConnection searchForNaver(HttpURLConnection conn) throws ProtocolException {
		conn.setRequestMethod("GET");
		conn.setRequestProperty("X-Naver-Client-Id", "Ap0vAnBF405AEOYqc9mw");
		conn.setRequestProperty("X-Naver-Client-Secret", "uiEdOgH7fS");
		return conn;
	}
	
	public void insertKeywordHistory(String userId,String keyword) {
		UserHistorySaveRequestDto hisDto = new UserHistorySaveRequestDto();
		hisDto.setUser_id(userId);
		hisDto.setKeyword(keyword);
		userHistoryRepo.save(hisDto.toEntity());
		
		if(StringUtils.isEmpty(keywordRepo.findByCount(keyword))) {
			SearchKeywordSaveRequestDto keywordDto = new SearchKeywordSaveRequestDto();
			keywordDto.setKeyword(keyword);
			keywordDto.setCnt(1);
			keywordRepo.save(keywordDto.toEntity());
		}else {
			keywordRepo.updateCount(keyword);
		}
	}
	
	public List<Map<String,Object>>selectTop(){
		return keywordRepo.selectTop();
	}
	
	public List<Map<String,Object>>selectHistory(String userId){
		return userHistoryRepo.findByUserId(userId);
	}
}
