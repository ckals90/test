package com.book.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.book.builer.SearchKeyword;


public interface SearchKeywordRepository extends JpaRepository<SearchKeyword,Long>{
	@Query(value="SELECT cnt FROM Search_Keyword WHERE keyword = ?1", nativeQuery = true)
    String findByCount(String keyword);
	
	@Modifying
	@Transactional
	@Query("UPDATE SearchKeyword A SET A.cnt = A.cnt+1 WHERE A.keyword=:keyword")
	void updateCount(@Param("keyword") String keyword);
	
	@Query(value="SELECT keyword,cnt FROM SEARCH_KEYWORD ORDER BY cnt DESC Limit 0,10 ",nativeQuery=true)
	List<Map<String,Object>> selectTop();
}
