package com.maersk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.maersk.model.Bookings;
import com.maersk.model.CheckAvailable;
import com.maersk.model.ContainerBookingRef;
import com.maersk.model.ContainerSpace;
import com.maersk.model.CustomerPayLoad;
import com.maersk.service.MockExternalService;
import com.maersk.service.SubmitContainerBookingService;

@RestController
@RequestMapping("/api/bookings")
public class ConatainerBookingController {

	@Autowired
	MockExternalService mockExternalService;

	@Autowired
	SubmitContainerBookingService submitContainerBookingService;

	@Autowired
	RestTemplate restTemplate;

	@Value("${maersk.container.booking.endpoint}")
	private String maerskEndPoint;

	@PostMapping(path = "/availability")
	public ResponseEntity<CheckAvailable> getBooking(@RequestBody @Valid CustomerPayLoad customerPayLoad) {
		
		System.out.println(maerskEndPoint);
		
		ContainerSpace containerSpace = restTemplate.postForObject(maerskEndPoint, customerPayLoad,
				ContainerSpace.class);

		CheckAvailable checkAvailable = new CheckAvailable();
		if (containerSpace != null) {

			if (containerSpace.getAvailableSpace() != 0) {
				checkAvailable.setAvailable(true);
				return new ResponseEntity<CheckAvailable>(checkAvailable, HttpStatus.ACCEPTED);
			} else {
				checkAvailable.setAvailable(false);
				return new ResponseEntity<CheckAvailable>(checkAvailable, HttpStatus.ACCEPTED);
			}

		}
		return new ResponseEntity<CheckAvailable>(checkAvailable, HttpStatus.BAD_REQUEST);

	}

	// added for Mock data to check the availble space it will return the conatiner
	// size which is passed in CustomerPayLoad containerSize value
	@PostMapping(path = "/checkAvailable")
	public ResponseEntity<ContainerSpace> checkAvailable(@RequestBody CustomerPayLoad customerPayLoad) {

		ContainerSpace containerSpace = mockExternalService.checkAvailability(customerPayLoad);
		if (containerSpace != null) {

			return new ResponseEntity<ContainerSpace>(containerSpace, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<ContainerSpace>(containerSpace, HttpStatus.NOT_FOUND);
		}

	}

	
	// data will be submitted to H2 database for quick testing this we can change to cassandra or sql or mongo db any DB 
	@PostMapping(path = "/submitBooking")
	public ResponseEntity<ContainerBookingRef> submitBooking(@RequestBody @Valid Bookings booking) {

		ContainerBookingRef containerBookingRef = submitContainerBookingService.saveContainerBookingService(booking);

		if (containerBookingRef != null) {

			return new ResponseEntity<ContainerBookingRef>(containerBookingRef, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<ContainerBookingRef>(containerBookingRef, HttpStatus.NOT_FOUND);
		}

	}

}
