package com.example.busticketbooking.service;

import java.util.List;

import com.example.busticketbooking.entity.UserAdmin;

public interface UserAdminService {
	
	public List<UserAdmin> getAllUsers();

	public int deleteUser(String eMail);
	
	public UserAdmin editUser(UserAdmin useradmin);

	

}
