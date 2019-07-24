package com.book.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchBookControllerTest {
	
	
	@Test
	public void testSearch() {
		/*
		기본   https://openapi.naver.com/v1/search/book.json?query=
		상세	get /v1/search/book_adv
			HOST: openapi.naver.com
	        Content-Type: plain/text
			X-Naver-Client-Id: Ap0vAnBF405AEOYqc9mw
			X-Naver-Client-Secret: uiEdOgH7fS
			title	string	검색 결과 문서의 제목을 나타낸다. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
			
			d_titl	string	N	-	책 제목 검색	상세 검색만 해당
			d_auth	string	N	-	저자명 검색	상세 검색만 해당
			d_isbn	string	N	-	isbn 검색	상세 검색만 해당
			d_publ	string	N	-	출판사 검색	상세 검색만 해당
		 */
		String auth= "KakaoAK "+"50ec4effd25cf0e8ae7f8fc4bf355430";
		String strUrl = "https://dapi.kakao.com/v3/search/book?query=";
		try {
			String param=URLEncoder.encode(String.format("%s", "수리부엉이"),"UTF-8");
			URL url = new URL(strUrl+param);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", auth);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			int resCode = conn.getResponseCode();
			BufferedReader br;
			if(resCode==200) {
				System.out.println("Success!!!!!");
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			}else {
				System.out.println("error!!!!!");
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(response.toString().replaceAll("'", "\""));
			JSONObject jsonObj = (JSONObject) obj;
			JSONObject metaJson = (JSONObject)jsonObj.get("meta");
			JSONArray documentsJsonArr = (JSONArray)jsonObj.get("documents");
			System.out.println(metaJson.get("total_count"));
			JSONObject tt = (JSONObject)documentsJsonArr.get(0);
			System.out.println(tt.get("contents"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
