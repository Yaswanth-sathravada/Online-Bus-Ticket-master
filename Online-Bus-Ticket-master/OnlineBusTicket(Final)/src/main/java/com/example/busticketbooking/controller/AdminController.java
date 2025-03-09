package com.example.busticketbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.busticketbooking.Response.BusResponse;
import com.example.busticketbooking.Response.DriverResponse;
import com.example.busticketbooking.Response.UserAdminResponse;
import com.example.busticketbooking.entity.Bus;
import com.example.busticketbooking.entity.Driver;
import com.example.busticketbooking.entity.UserAdmin;
import com.example.busticketbooking.repository.BusRepository;
import com.example.busticketbooking.repository.DriverRepository;
import com.example.busticketbooking.service.BusService;
import com.example.busticketbooking.service.DriverService;
import com.example.busticketbooking.service.UserAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired(required = true)
	private BusService busService;

	@Autowired(required = true)
	private DriverService driverService;

	@Autowired(required = true)
	private UserAdminService userAdminService;

	@Autowired
	private BusRepository BusRepo;

	@Autowired
	private DriverRepository DriverRepo;

	@PostMapping("/addbus")
	public ResponseEntity<BusResponse> addBus(@RequestBody Bus addBus) {

		Bus isBus = BusRepo.findBusByBusNo(addBus.getBusno());

		if (isBus != null) {
			BusResponse busResponse = new BusResponse();
			busResponse.setSuccess(false);
			busResponse.setMessage("bus with num" + addBus.getBusno() + "already found");
			return new ResponseEntity<BusResponse>(busResponse, HttpStatus.BAD_REQUEST);
		}

		Bus bus = busService.addBus(addBus);
		if (bus != null) {
			BusResponse busResponse = new BusResponse();
			busResponse.setSuccess(true);
			busResponse.setMessage("Bus added successfully");
			return new ResponseEntity<BusResponse>(busResponse, HttpStatus.ACCEPTED);
		} else {
			BusResponse busResponse = new BusResponse();
			busResponse.setSuccess(false);
			busResponse.setMessage("Failed to add bus");
			return new ResponseEntity<BusResponse>(busResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/editBus")
	public ResponseEntity<BusResponse> editBus(@RequestBody Bus addBus) {

		Bus isBus = BusRepo.findBusByBusNo(addBus.getBusno());
		if (isBus == null) {
			BusResponse busResponse = new BusResponse();
			busResponse.setSuccess(false);
			busResponse.setMessage("bus with num" + addBus.getBusno() + "not found");
			return new ResponseEntity<BusResponse>(busResponse, HttpStatus.BAD_REQUEST);
		} else {
			Bus bus = busService.Editbus(addBus);
			if (bus != null) {
				BusResponse busResponse = new BusResponse();
				busResponse.setSuccess(true);
				busResponse.setMessage("Bus edited successfully");
				return new ResponseEntity<BusResponse>(busResponse, HttpStatus.ACCEPTED);
			} else {
				BusResponse busResponse = new BusResponse();
				busResponse.setSuccess(false);
				busResponse.setMessage("Failed to edit Bus ");
				return new ResponseEntity<BusResponse>(busResponse, HttpStatus.BAD_REQUEST);
			}
		}

	}

	@PostMapping("/deletebus/{busNo}")
	public ResponseEntity<BusResponse> deleteBus(
			@PathVariable("busNo") String busNo) {
		boolean deletedBus = busService.deletebus(busNo);

		BusResponse busResponse = new BusResponse();

		if (deletedBus) {
			busResponse.setSuccess(true);
			busResponse.setMessage("Bus with number " + busNo + " deleted successfully");
			return new ResponseEntity<>(busResponse, HttpStatus.OK);
		} else {
			busResponse.setSuccess(false);
			busResponse.setMessage("Bus with number " + busNo + " not found or could not be deleted");
			return new ResponseEntity<>(busResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/allbusses")
	public ResponseEntity<BusResponse> getAllBUsses() {
		List<Bus> allBusses = busService.getAllBusses();
		BusResponse busResponse = new BusResponse();
		busResponse.setBusses(allBusses);
		busResponse.setSuccess(true);
		return new ResponseEntity<BusResponse>(busResponse, HttpStatus.FOUND);

	}

	// Driverid validation
	public static boolean isValidDriverId(String driverId) {
		String regex = "\\d{4}";
		return driverId.matches(regex);
	}

	@PostMapping("/addDriver")
	public ResponseEntity<DriverResponse> addDriver(
			@RequestBody Driver addDriver) {

		/*
		 * boolean isIdvalid=isValidDriverId(Integer.toString(addDriver.getdriverid()));
		 * 
		 * if(isIdvalid) { Driver
		 * isDriver=addDriverRepo.findByDriverId(addDriver.getdriverid());
		 * if(isDriver!=null) { return ResponseEntity.status(HttpStatus.CREATED).
		 * body("Driver with id already exists"); }
		 */

		Driver driver = DriverRepo.findByDrivermail(addDriver.getEmail());
		if (driver != null) {
			DriverResponse driverResponse = new DriverResponse();
			driverResponse.setSuccess(false);
			driverResponse.setMessage("driver with email already exists");
			return new ResponseEntity<DriverResponse>(driverResponse, HttpStatus.FOUND);
		} else {
			Driver Driver = driverService.addDriver(addDriver);
			if (Driver != null) {
				DriverResponse driverResponse = new DriverResponse();
				driverResponse.setSuccess(true);
				driverResponse.setMessage("Driver added successfully");
				return new ResponseEntity<DriverResponse>(driverResponse, HttpStatus.FOUND);
			} else {
				DriverResponse driverResponse = new DriverResponse();
				driverResponse.setSuccess(false);
				driverResponse.setMessage("Failed to add Driver");
				return new ResponseEntity<DriverResponse>(driverResponse, HttpStatus.FOUND);
			}

		}

	}

	@PostMapping("/editDriver")
	public ResponseEntity<DriverResponse> editDriver(
			@RequestBody Driver updatedDriver) {

		Driver existingDriver = DriverRepo.findByDriverId(updatedDriver.getId().intValue());

		if (existingDriver == null) {
			DriverResponse driverResponse = new DriverResponse();
			driverResponse.setSuccess(false);
			driverResponse.setMessage("Driver with the specified ID not found");
			return new ResponseEntity<>(driverResponse, HttpStatus.NOT_FOUND);
		} else {
			// Assuming DriverService.updateDriver(updatedDriver) updates the existing
			// driver
			Driver editedDriver = driverService.addDriver(updatedDriver);

			if (editedDriver != null) {
				DriverResponse driverResponse = new DriverResponse();
				driverResponse.setSuccess(true);
				driverResponse.setMessage("Driver edited successfully");
				return new ResponseEntity<>(driverResponse, HttpStatus.OK);
			} else {
				DriverResponse driverResponse = new DriverResponse();
				driverResponse.setSuccess(false);
				driverResponse.setMessage("Failed to edit driver");
				return new ResponseEntity<>(driverResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@PostMapping("/deletedriver/{id}")
	public ResponseEntity<DriverResponse> deleteDriver(
			@PathVariable("id") long id) {
		boolean deletedDriver = driverService.deleteDriver(id);

		DriverResponse driverResponse = new DriverResponse();

		if (deletedDriver) {
			driverResponse.setSuccess(true);
			driverResponse.setMessage("Driver with ID " + id + " deleted successfully");
			return new ResponseEntity<>(driverResponse, HttpStatus.OK);
		} else {
			driverResponse.setSuccess(false);
			driverResponse.setMessage("Driver with ID " + id + " not deleted successfully or not found");
			return new ResponseEntity<>(driverResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/alldrivers")
	public ResponseEntity<DriverResponse> getAllDriver() {
		List<Driver> Drivers = driverService.getAllDrivers();
		DriverResponse driverResponse = new DriverResponse();
		driverResponse.setDrivers(Drivers);
		driverResponse.setSuccess(true);

		return new ResponseEntity<DriverResponse>(driverResponse, HttpStatus.FOUND);

	}

	@GetMapping("/allusers")
	public ResponseEntity<UserAdminResponse> getallUsers() {

		List<UserAdmin> allUsers = userAdminService.getAllUsers();
		UserAdminResponse userReponse = new UserAdminResponse();
		userReponse.setUsers(allUsers);

		return new ResponseEntity<UserAdminResponse>(userReponse, HttpStatus.FOUND);
	}

	@PostMapping("/deleteUser/{email}")
    public  ResponseEntity<UserAdminResponse> deleteUser(@PathVariable("email") String eMail){
		
    	int deleteUser=userAdminService.deleteUser(eMail);
    	if(deleteUser>=1) {
    		UserAdminResponse userResponse= new UserAdminResponse();
    		userResponse.setSuccess(true);
    		userResponse.setMessage("user with"+ eMail+" deleted succesfully");
    		return new ResponseEntity<UserAdminResponse>(userResponse, HttpStatus.OK );
    	}else {
    		UserAdminResponse userResponse= new UserAdminResponse();
    		userResponse.setSuccess(false);
    		userResponse.setMessage("user with"+ eMail+" not deleted succesfully");
    		return new ResponseEntity<UserAdminResponse>(userResponse, HttpStatus.OK );
    	}
    	

    	  
    	
    	
    }
	
	@PostMapping("/assinDriver/{busid}/{driverid}")
	public ResponseEntity<DriverResponse> assinDriver(@PathVariable("busid") String busid, @PathVariable("driverid") String driverid) {
	    try {
	        int busId = Integer.parseInt(busid);
	        int driverId = Integer.parseInt(driverid);

	        Driver driver = driverService.findDriverbyid(driverId);

	        if (driver != null) {
	            Bus bus = busService.finderBusbyId(busId);
	            driver.setBus(bus);

	            Driver updatedDriver = driverService.addDriver(driver);

	            if (updatedDriver != null) {
	                DriverResponse driverResponse = new DriverResponse();
	                driverResponse.setSuccess(true);
	                driverResponse.setMessage("Driver assigned successfully to bus " + bus.getBusno());
	                driverResponse.setDriver(updatedDriver);
	                return new ResponseEntity<>(driverResponse, HttpStatus.OK);
	            } else {
	            	DriverResponse driverResponse = new DriverResponse();
	                driverResponse.setSuccess(false);
	                driverResponse.setMessage("Driver not assigned successfully to bus " + bus.getBusno());
	                return new ResponseEntity<>(driverResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        } else {
	        	DriverResponse driverResponse = new DriverResponse();
                driverResponse.setSuccess(false);
                driverResponse.setMessage("Driver with "+ driverid +" Not found");
                return new ResponseEntity<>(driverResponse, HttpStatus.NOT_FOUND);
	        }
	    } catch (NumberFormatException e) {
	        // Handle the case where the path variables could not be parsed as integers
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}


}
