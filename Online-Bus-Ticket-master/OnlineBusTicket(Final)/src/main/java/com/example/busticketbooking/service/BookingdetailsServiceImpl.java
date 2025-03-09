package com.example.busticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.busticketbooking.entity.Bookingdetails;
import com.example.busticketbooking.repository.BookingDetailsRepository;


@Service
public class BookingdetailsServiceImpl implements BookingDetailsService {
	
	@Autowired
	private BookingDetailsRepository bookRepo;

	@Override
	public Bookingdetails savebookingdetails(Bookingdetails bookingdetails) {
		
		Bookingdetails booking=bookRepo.save(bookingdetails);
	
		return booking;
	}

	@Override
	public Bookingdetails getBookingDetails(int userid) {
		/*Bookingdetails bookingDetails=bookRepo.getBookingDetails(userid);
		if(bookingDetails!=null) {
			return bookingDetails;
		}*/
		return null;
	}


}
