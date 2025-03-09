package com.example.busticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.busticketbooking.entity.Bookingdetails;

@Repository
public interface BookingDetailsRepository extends JpaRepository<Bookingdetails, Long> {
	
	/*@Query("SELECT bd.name, bd.email, bd.phoneno, bd.age, b.source, b.destination " +
		       "FROM bookingdetails bd " +
		       "INNER JOIN bus b ON bd.busid = b.id " +
		       "INNER JOIN UserAdmin u ON bd.userid = u.id " + // Assuming there's a user table
		       "WHERE u.id = ?1")*/
	//Bookingdetails getBookingDetails(int userid);


}
