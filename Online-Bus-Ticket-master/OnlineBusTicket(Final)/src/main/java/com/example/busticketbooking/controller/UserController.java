package com.example.busticketbooking.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.busticketbooking.Response.BusResponse;
import com.example.busticketbooking.Response.UserAdminResponse;
import com.example.busticketbooking.entity.Bookingdetails;
import com.example.busticketbooking.entity.Bus;
import com.example.busticketbooking.entity.UserAdmin;
import com.example.busticketbooking.service.BookingDetailsService;
import com.example.busticketbooking.service.BusService;
import com.example.busticketbooking.service.UserAdminService;

@Controller
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private BusService busService;
	
	@Autowired 
	private UserAdminService userservice;
	
	@Autowired(required=true)
	private BookingDetailsService bookingService;

	@GetMapping("/searchbusses/{source}/{destination}/{date}")
	public ResponseEntity<BusResponse> searchBusses(@RequestHeader("Authorization") String jwt,
	        @PathVariable("source") String source, @PathVariable("destination") String destination,
	        @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
	       // @RequestParam("price") int tickets
	        ) {

	    List<Bus> searchBusses = busService.Searchbus(source, destination, date);

	    BusResponse busResponse = new BusResponse();
	    busResponse.setBusses(searchBusses);
	    busResponse.setSuccess(true);

	    return new ResponseEntity<>(busResponse, HttpStatus.OK);
	}
	
	@PostMapping("/bookticket/{busid}/{userid}")
	public ResponseEntity<String> bookTicket(@RequestHeader("Authorization") String jwt, @RequestBody Bookingdetails bookingDetails ,@PathVariable("busid") Bus busid,@PathVariable("userid")  UserAdmin userid){
		bookingDetails.setBus(busid);
		bookingDetails.setUser(userid);
		Bookingdetails details= bookingService.savebookingdetails(bookingDetails);
		if (details != null) {
	        // Booking was successful
	        return ResponseEntity.ok("Ticket booked successfully. Booking ID: " + details.getId());
	    } else {
	        // Handle the case where the booking failed
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to book ticket.");
	    }
	}
	
	@GetMapping("/bookingDetails/{userid}")
	public ResponseEntity<?> bookingDetails(@RequestHeader("Authorization") String jwt, @PathVariable("userid") int userid){
	    Bookingdetails bookDetails = bookingService.getBookingDetails(userid);
	    Map<String, Object> response = new HashMap<>();
	    
	    if(bookDetails != null) {
	        // Populate the map with booking details
	        response.put("status", "success");
	        response.put("bookingDetails", bookDetails);
	        return ResponseEntity.ok().body(response);
	    } else {
	        // Populate the map with error message
	        response.put("status", "error");
	        response.put("message", "Booking details not found for user ID: " + userid);
	        return  ((BodyBuilder) ResponseEntity.notFound()).body(response);
	    }
	}
	
	
	@PostMapping("/editUser")
	public ResponseEntity<UserAdminResponse> editUser(@RequestBody  UserAdmin userAdmin){
		
		UserAdmin user= userservice.editUser(userAdmin);
		if(user!=null) {
			UserAdminResponse userResponse =new UserAdminResponse();
			userResponse.setSuccess(true);
			userResponse.setMessage("User Edited Succesfully");
			userResponse.setUserAdmin(user);
			return new ResponseEntity<UserAdminResponse>(userResponse, HttpStatus.ACCEPTED);
		}else {
			UserAdminResponse userResponse =new UserAdminResponse();
			userResponse.setSuccess(false);
			userResponse.setMessage("User Edited Failed");
			return new ResponseEntity<UserAdminResponse>(userResponse, HttpStatus.ACCEPTED);
		}
		
		
		
		
	}

}




