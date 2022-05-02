package com.maersk.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPayLoad {
	
	public  enum containerType{
		DRY, REEFER
	}
	
	@Min(20)
	@Max(40) 
	private int containerSize;
	
	private containerType containerType;
	
	@NotNull
	private String origin;
	private String destination;
	
	@Min(1)
	@Max(100)
	private int quantity;
	
	
	
	
	

}
