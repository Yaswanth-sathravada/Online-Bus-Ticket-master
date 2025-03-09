package com.example.busticketbooking.Response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.busticketbooking.entity.UserAdmin;

@SuppressWarnings("serial")
public class CustomUserAdminDetails implements UserDetails {
	
	
	private UserAdmin userAdmin;

	public CustomUserAdminDetails(UserAdmin userAdmin) {
		super();
		this.userAdmin = userAdmin;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<>();

	    // Assuming your User class has a method getRole() returning a role string
	   

	    return authorities;
	}

	/*private Object getRole() {
		// TODO Auto-generated method stub
		return userAdmin.getRole();
	}*/
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userAdmin.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userAdmin.getEmail();
				
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
