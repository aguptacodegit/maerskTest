package com.maersk.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.model.Bookings;
import com.maersk.model.ContainerBookingRef;
import com.maersk.repository.ContainerBookingRepository;
import com.maersk.service.SubmitContainerBookingService;

@Service
public class SubmitContainerBookingServiceImpl  implements SubmitContainerBookingService{

	
	@Autowired
	ContainerBookingRepository containerBookingRepository;
	
	@Override
	public ContainerBookingRef saveContainerBookingService(Bookings bookingDetails) {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
		ContainerBookingRef containerBookingRef =new ContainerBookingRef();
		String bookingRef= uuid.toString();
		bookingDetails.setBookingRef(bookingRef);
		Bookings bookingDetailsresp = containerBookingRepository.save(bookingDetails);
		containerBookingRef.setBookingRef(bookingDetailsresp.getBookingRef());
		
		return containerBookingRef;
	}

}
