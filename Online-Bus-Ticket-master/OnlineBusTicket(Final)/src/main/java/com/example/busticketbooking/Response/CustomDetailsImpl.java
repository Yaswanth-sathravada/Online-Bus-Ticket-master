package com.example.busticketbooking.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.busticketbooking.entity.UserAdmin;
import com.example.busticketbooking.repository.UserAdminRepository;

@Service
public class CustomDetailsImpl implements UserDetailsService{
	
	@Autowired(required=true)
	private UserAdminRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAdmin useradmin=userRepo.findByEmail(username);
		if(useradmin==null) {
			throw new UsernameNotFoundException("User 404");
		}
		
		return new CustomUserAdminDetails(useradmin);
	}

}
