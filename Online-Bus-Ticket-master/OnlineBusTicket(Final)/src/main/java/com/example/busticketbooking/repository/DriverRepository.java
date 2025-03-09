package com.example.busticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.busticketbooking.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    
    @Query("SELECT d FROM Driver d WHERE d.id = ?1")
    Driver findByDriverId(int driverId);
    
    @Query("SELECT d FROM Driver d")
    List<Driver> findAllDrivers();
	
	Driver findByid(long id);
	

	//@Query("DELETE FROM Driver d WHERE d.id = ?1")
    //void deleteByid(int id);
	
	@Query("SELECT d FROM Driver d WHERE d.email = ?1")
	Driver findByDrivermail(String mail);

	
}