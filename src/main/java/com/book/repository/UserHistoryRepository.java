package com.book.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.builer.UserHistory;


public interface UserHistoryRepository extends JpaRepository<UserHistory,Long>{
	@Query(value="SELECT keyword,FORMATDATETIME(reg_date,'yyyy-MM-dd hh:mm:ss') as reg_date FROM USER_HISTORY WHERE user_id = :user_id ORDER BY reg_date DESC",nativeQuery=true)
    List<Map<String,Object>> findByUserId(@Param("user_id")String user_id);
}
