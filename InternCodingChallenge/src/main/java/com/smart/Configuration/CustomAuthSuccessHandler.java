package com.smart.Configuration;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

	
	SimpleUrlAuthenticationSuccessHandler userSuccessHandler=new SimpleUrlAuthenticationSuccessHandler("/user/index");
	SimpleUrlAuthenticationSuccessHandler adminSuccessHandler=new SimpleUrlAuthenticationSuccessHandler("/admin/index");
	SimpleUrlAuthenticationSuccessHandler ownerSuccessHandler=new SimpleUrlAuthenticationSuccessHandler("/store-owner/index");
	
	
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for( GrantedAuthority grantedAuthority :authorities) {
			String authorityName=grantedAuthority.getAuthority();
			
			if(authorityName.equals("ROLE_ADMIN")) {
				adminSuccessHandler.onAuthenticationSuccess(request, response, authentication);
				return;
			}
			
			if(authorityName.equals("ROLE_USER")) {
			   userSuccessHandler.onAuthenticationSuccess(request, response, authentication);
			   return;
			}
			
			
			if(authorityName.equals("ROLE_OWNER")) {
				ownerSuccessHandler.onAuthenticationSuccess(request, response, authentication);{
					return;
				}
			}
		}
		
	}

}
