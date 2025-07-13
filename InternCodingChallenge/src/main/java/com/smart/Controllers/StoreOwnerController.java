package com.smart.Controllers;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.Models.Store;
import com.smart.Models.User;
import com.smart.Repositories.StoreRepository;
import com.smart.Repositories.UserRepository;
import com.smart.helper.Message;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/store-owner")
public class StoreOwnerController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	
	@ModelAttribute
	public void addCommonData(Principal principal, Model model) {
		String username = principal.getName();

		User user = userRepository.getUserByEmail(username);

		model.addAttribute("user", user);
	}
	
	
	@RequestMapping("/index")
	public String storeOwner() {
		
		return "StoreOwner/Store";
	}
	
	
	
	@RequestMapping("/addstore")
	public String addStore(Model model) {
		
		model.addAttribute("store", new Store());
		
		return "StoreOwner/AddStore";
	}
	
	
	@RequestMapping("/addStoreInfo")
	public String addStoreInfo(@Valid @ModelAttribute Store store, BindingResult result, Model model, Principal principal){
		
	
		
		if(result.hasErrors()) {
          
			model.addAttribute("nameErr", result.getFieldError("name"));
			model.addAttribute("emailErr", result.getFieldError("email"));
			model.addAttribute("addressErr", result.getFieldError("address"));
			return "StoreOwner/AddStore";
		}
		
		
	     
	     
	     User storeOwner = userRepository.getUserByEmail(principal.getName());
	     
	     store.setId(UUID.randomUUID().toString());
	     
	     store.setOwner(storeOwner);
	     
	     
	     storeOwner.getStore().add(store);
		 
	     userRepository.save(storeOwner);
	     
	     model.addAttribute("storeMsg", new Message("Store Registered Successfully!!","alert-success"));
		
		
		return "StoreOwner/AddStore";
	}
	
	
	@RequestMapping("/profile")
	public String profile(Principal principal,Model model) {
		
		User owner = userRepository.getUserByEmail(principal.getName());
		
		model.addAttribute("owner", owner);
		
		return "StoreOwner/Profile";
	}
	
	
	
	@RequestMapping("/viewstores")
	public String viewStores(Principal principal,Model model) {
		
		User storeOwner = userRepository.getUserByEmail(principal.getName());
		
		List<Store> stores = storeRepository.getUserStore(storeOwner.getUid());
		
		model.addAttribute("stores", stores);
		
		return "StoreOwner/ViewStore";
	}
	
	
	
	@RequestMapping("/updateProfile/{id}")
	public String update(@PathVariable("id") String id, Principal principal, Model model) {
		
		
	    User owner=userRepository.getById(id);
	    
	    
	    model.addAttribute("owner", owner);
		
		return "StoreOwner/UpdateProfile";
	}
	
	
	@RequestMapping("/updateInfo")
	public String updateInfo(@ModelAttribute User owner,Principal principal) {
		
		User oldOwner = userRepository.getUserByEmail(principal.getName());
		
		oldOwner.setUname(owner.getUname());
		oldOwner.setUemail(owner.getUemail());
		oldOwner.setUaddress(owner.getUaddress());
		
		userRepository.save(oldOwner);
		
		
		return "redirect:/store-owner/index";
	}
	
}
