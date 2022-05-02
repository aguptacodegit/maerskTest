package com.maersk.service;

import org.springframework.stereotype.Service;

import com.maersk.model.ContainerSpace;
import com.maersk.model.CustomerPayLoad;

@Service
public interface MockExternalService {
	
	public ContainerSpace checkAvailability(CustomerPayLoad customerPayLoad);

}
