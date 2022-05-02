package com.maersk.service;

import org.springframework.stereotype.Service;

import com.maersk.model.Bookings;
import com.maersk.model.ContainerBookingRef;

@Service
public interface SubmitContainerBookingService {
	
	public ContainerBookingRef saveContainerBookingService(Bookings bookingDetails);
		
		

}
