package com.maersk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.maersk.model.Bookings;

@Service
@Repository
public interface ContainerBookingRepository extends JpaRepository<Bookings, String>{
	
		
}
