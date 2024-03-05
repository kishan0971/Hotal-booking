package com.jsp.hookup.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookdRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;
	
	@Column(name = "check_In")
	private LocalDate checkInDate;
	
	@Column(name = "check_Out")
	private LocalDate checkOutDate;
	
	@Column(name = "guest_FullName")
	private String guestFullName;
	
	@Column(name = "guest_Email")
	private String guestEmail;
	
	@Column(name = "adults")
	private int numOfAdults;
	
	@Column(name = "children")
	private int numOfChildren;
	
	@Column(name = "total_guest")
	private int totalNoOfGuest;
	
	@Column(name = "confirmation_Code")
	private String bookingConfirmationCode;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;
	
	public void calculateTotalNumberOfGuest() {
		this.totalNoOfGuest = this.numOfAdults + this.numOfChildren;
	}

	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
		calculateTotalNumberOfGuest();
	}


	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
		calculateTotalNumberOfGuest();
	}
	
	public void setBookingConfirmationCode(String bookingConfirmationCode) {
		this.bookingConfirmationCode = bookingConfirmationCode;
	}
	
	
	
	
	
	
	
	

}
