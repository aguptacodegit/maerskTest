package com.maersk.serviceImpl;

import org.springframework.stereotype.Service;

import com.maersk.model.ContainerSpace;
import com.maersk.model.CustomerPayLoad;
import com.maersk.service.MockExternalService;


@Service
public class MockExternalServiceImpl implements MockExternalService{

	@Override
	public ContainerSpace checkAvailability(CustomerPayLoad customerPayLoad) {
		// TODO Auto-generated method stub
		
		// Added this condition for mock data it will return same space whatever conatiner size will be passed in Customer payload
		// this we can change based on our buisness logic 
		 
		ContainerSpace containerSpace = new ContainerSpace();
		containerSpace.setAvailableSpace(customerPayLoad.getContainerSize());
		
		return containerSpace;
	}

}
