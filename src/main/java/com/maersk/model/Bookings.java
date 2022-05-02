package com.maersk.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bookings {

	public enum containerType {
		DRY, REEFER
	}

	@Id
	@Column(name = "bookingRef")
	private String bookingRef;

	@Column(name = "containerSize")
	@Min(20)
	@Max(40)
	private int containerSize;

	@Column(name = "containerType")
	@NotBlank
	private containerType containerType;

	@Column(name = "origin")
	private String origin;

	@Column(name = "destination")
	private String destination;

	@Column(name = "quantity")
	@Min(1)
	@Max(100)
	private int quantity;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date timestamp;

	

}
