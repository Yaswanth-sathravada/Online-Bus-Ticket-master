package com.example.busticketbooking.service;

import java.util.List;
import java.util.Optional;

import com.example.busticketbooking.entity.Driver;

public interface DriverService {
	
	Driver addDriver(Driver addDriver);
	
	List<Driver> getAllDrivers();
	
	boolean deleteDriver(long driverid);
	
	boolean Assigndriver(Driver driver);
	
    Driver findDriverbyid(int id);

}
