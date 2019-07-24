package com.book.controller.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.service.SearchBookService;

@RestController
public class SearchBookController {
	
	@Resource
	SearchBookService searchBookService;
	@GetMapping("/com/doSearchBook")
	@ResponseBody
	public Map<String,Object> doSearchBook(@RequestParam("searchDiv")String searchDiv,@RequestParam("searchKeyword")String searchKeyword
			,@RequestParam("page")String page,@RequestParam("userId") String userId,@RequestParam("update") int update) {
		
		searchBookService.insertKeywordHistory(userId, searchKeyword);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(update==0) {
			resultMap.put("top", searchBookService.selectTop());
			resultMap.put("history", searchBookService.selectHistory(userId));
		}
		URL url = null;
		HttpURLConnection conn = null;
		BufferedReader br = null;
		StringBuffer response = new StringBuffer();
		Object obj = null;
		try {
			String searchDivN="";
			
			switch(searchDiv) {
				case "title" : searchDivN="d_titl"; break; 
				case "isbn" : searchDivN="d_isbn"; break;
				case "publisher" : searchDivN="d_publ"; break;
				case "person" : searchDivN="d_auth"; break;
				default : searchDiv ="";
			}
			
			searchKeyword=setUrlEncoding(searchKeyword);
			
			String kakaoUri ="https://dapi.kakao.com/v3/search/book?page="+page+"&size=10";
			String naverUri ="https://openapi.naver.com/v1/search/book.json?page="+page+"&size=10";
			
			url = setURL("kakao", kakaoUri, searchKeyword, searchDiv);
			conn = searchBookService.searchForKaKao((HttpURLConnection)url.openConnection());
			
			int responseCode = conn.getResponseCode();
			
			if(responseCode == 200) {
				System.out.println("Success Connect KaKao");
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			}else {
				System.out.println("Fail Connect KaKao, Try Connect Naver");
				url = setURL("naver", naverUri , searchKeyword, searchDivN);
				conn = searchBookService.searchForNaver((HttpURLConnection)url.openConnection());
				
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			}
			
			String inputLine;
			
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			System.out.println("\n\n\n return value : \n\t"+response.toString());
			JSONParser parser = new JSONParser();
			obj = parser.parse(response.toString().replaceAll("'", "\""));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			
		} 
		
		resultMap.put("searchResult", (JSONObject) obj);
		return resultMap;
	}
	
	public String setUrlEncoding(String item) throws Exception {
		return URLEncoder.encode(String.format("%s", item),"UTF-8");
	}
	
	public URL setURL(String div,String uri,String query,String target) throws Exception {
		String urlStr ="";
		if("kakao".equals(div)) {
			urlStr = String.format("%s&query=%s&target=%s",uri,query,target);
		}else if("naver".equals(div)) {
			if(StringUtils.isEmpty(target)) {
				target="query";
			}
			urlStr = String.format("%s&%s=%s",uri,target,query);
		}
		URL	url = new URL(urlStr);
		return url;
	}
}
