package com.example.busticketbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.busticketbooking.entity.Driver;
import com.example.busticketbooking.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService{
	
	
	@Autowired
	private DriverRepository driverRepo;

	@Override
	public Driver addDriver(Driver addDriver) {
		
		return driverRepo.save(addDriver);
	}

	@Override
	public List<Driver> getAllDrivers() {
		List<Driver> Drivers=driverRepo.findAllDrivers();
		return Drivers;
	}

	@Override
	public boolean deleteDriver(long driverid) {
		Driver driver=driverRepo.findByid(driverid);
		if(driver!=null) {
			  try {
			        driverRepo.deleteById(driverid);
			        return true;
			      
			    } catch (Exception e) {
			        e.printStackTrace();
			        return false;
			    }
			
		}else {
			return false;
		}
	
	
	}


	@Override
	public boolean Assigndriver(Driver driver) {
		Driver assigndriver= driverRepo.save(driver);
		if(assigndriver!=null) {
			return true;
		}
		return false;
	}

	@Override
	public Driver findDriverbyid(int id) {
		Driver driver=driverRepo.findByDriverId(id);
		return driver;
	}

	

	

}
