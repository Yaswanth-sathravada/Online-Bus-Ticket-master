package com.example.busticketbooking.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.busticketbooking.entity.Bus;

import com.example.busticketbooking.repository.BusRepository;

@Service
public class BusServiceImpl implements BusService {
	
	@Autowired
	private BusRepository busRepo;

	@Override
	public Bus addBus(Bus addBus) {
		
		Bus isBus=busRepo.findBusByBusNo(addBus.getBusno());
		if(isBus!=null) {
			return null;
		}else {
			return busRepo.save(addBus);
		}	
		
	}

	@Override
	public List<Bus> getAllBusses() {
		List<Bus> allBusses=busRepo.findAllBus();
		return allBusses;
	}

	@Override
	public boolean deletebus(String busNo) {
		busRepo.deleteByBusno(busNo);
		Bus deletebus=busRepo.findBusByBusNo(busNo);
		if(deletebus==null) {
			return true;
		}
		return  false;
	}

	@Override
	public List<Bus> Searchbus(String source, String destination, LocalDate date) {
		List<Bus> searchBusses=busRepo.findBySourceAndDestinationAndDate(source, destination, date);
		return searchBusses;
	}

	@Override
	public Bus finderBusbyId(int id) {
		Bus bus=busRepo.findByBusId(id);
		return bus;
	}

	@Override
	public Bus Editbus(Bus bus) {
		Bus isBus=busRepo.findBusByBusNo(bus.getBusno());
		if(isBus!=null) {
			return busRepo.save(bus);
		}else {
			return null;
		}
	
	}

	
	
	

}
