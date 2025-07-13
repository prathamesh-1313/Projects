package com.smart.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.Models.User;

public interface UserRepository extends JpaRepository<User, String> {

	@Query("Select u from User u where u.uemail=:uemail")
	public User getUserByEmail(@Param("uemail") String username);

	@Query("Select u from User u where LOWER(u.uname) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(u.urole) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	public List<User> getSearchUser(@Param("keyword") String keyword);

}
