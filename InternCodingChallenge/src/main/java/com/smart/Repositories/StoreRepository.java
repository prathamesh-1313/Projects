package com.smart.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.Models.Store;

public interface StoreRepository extends JpaRepository<Store, String> {

	@Query("Select s from Store s where LOWER(s.name) LIKE CONCAT('%', :keyword, '%') OR LOWER(s.owner.uname) LIKE CONCAT('%', :keyword, '%')")
	public List<Store> getSearchStores(@Param("keyword") String keyword);
	
	
	@Query("Select s from Store s where s.owner.uid=:uid")
	public List<Store>getUserStore(@Param("uid") String uid); 
	
}
