package com.example.busticketbooking.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.busticketbooking.entity.Bus;

public interface BusService {

	Bus addBus(Bus addBus);
	
	List<Bus> getAllBusses();
	
	boolean deletebus(String busNo);

	List<Bus> Searchbus(String source, String destination, LocalDate date);
	
	Bus finderBusbyId(int id);
	
	Bus Editbus(Bus bus);
	

}
