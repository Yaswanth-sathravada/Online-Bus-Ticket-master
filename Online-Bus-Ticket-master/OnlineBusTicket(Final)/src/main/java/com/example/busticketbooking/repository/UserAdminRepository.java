package com.example.busticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.busticketbooking.entity.UserAdmin;

public interface UserAdminRepository extends JpaRepository<UserAdmin, Long> {

	UserAdmin findByEmail(String email);

	@Query("SELECT u FROM UserAdmin u ")
	List<UserAdmin> findAllUsers();

	@Transactional
	int deleteUserByEmail(String eMail);
	
	
	



}
