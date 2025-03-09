package com.example.busticketbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.busticketbooking.entity.UserAdmin;
import com.example.busticketbooking.repository.UserAdminRepository;

@Service
public class UserAdminServiceimpl implements UserAdminService {

	@Autowired
	private UserAdminRepository userRepo;

	@Override
	public List<UserAdmin> getAllUsers() {
		List<UserAdmin> users = userRepo.findAllUsers();
		return users;
	}

	@Override
	public int deleteUser(String eMail) {
		UserAdmin userAdmin = userRepo.findByEmail(eMail);
		if (userAdmin != null) {
			int deleteuser = userRepo.deleteUserByEmail(eMail);
			return deleteuser;
		}else {
			return -1;
		}

	}

	@Override
	public UserAdmin editUser(UserAdmin useradmin) {
		Optional<UserAdmin> user = userRepo.findById(useradmin.getId());
		if (user != null) {
			UserAdmin userAdmin = userRepo.save(useradmin);
			return userAdmin;
		} else {
			return null;
		}

	}

}
