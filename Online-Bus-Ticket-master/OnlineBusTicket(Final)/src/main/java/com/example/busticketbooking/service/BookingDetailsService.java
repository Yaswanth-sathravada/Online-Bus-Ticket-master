package com.example.busticketbooking.service;

import com.example.busticketbooking.entity.Bookingdetails;

public interface BookingDetailsService {
	
	Bookingdetails savebookingdetails(Bookingdetails bookingdetails);
	
	Bookingdetails getBookingDetails(int userid);

}
