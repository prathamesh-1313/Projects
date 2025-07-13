package com.smart.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smart.Models.Store;
import com.smart.Models.User;
import com.smart.Repositories.StoreRepository;
import com.smart.Repositories.UserRepository;

@RestController
public class SearchController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@GetMapping("/searchuser/{user}")
	public ResponseEntity<List<User>> searchUser(@PathVariable("user") String user){
		
		List<User> searchUser = userRepository.getSearchUser(user);
		
		return ResponseEntity.ok(searchUser);
	}
	
	
	@GetMapping("/searchstore/{store}")
	public ResponseEntity<List<Store>> searchStore(@PathVariable("store") String store){
		
		List<Store> searchStore = storeRepository.getSearchStores(store);
		
		return ResponseEntity.ok(searchStore);
	}
	
}
