package com.jsp.hookup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.hookup.exception.InvalidBookingRequestException;
import com.jsp.hookup.exception.ResourceNotFoundException;
import com.jsp.hookup.model.BookdRoom;
import com.jsp.hookup.model.Room;
import com.jsp.hookup.response.BookingResponse;
import com.jsp.hookup.response.RoomResponse;
import com.jsp.hookup.service.BookingService;
import com.jsp.hookup.service.RoomService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
	
	private final BookingService bookingService;
	
	private final RoomService roomService;
	
	
	@GetMapping("/all-bookings")	
	public ResponseEntity<List<BookingResponse>> getAllBookings(){
		List<BookdRoom> bookings = bookingService.getAllBookings();
		
		List<BookingResponse> bookingResponses = new ArrayList<>();

		for (BookdRoom booking : bookings) {
			BookingResponse bookingResponse = getBookingResponse(booking);
			bookingResponses.add(bookingResponse);
		}
		return ResponseEntity.ok(bookingResponses);
		
		
	}
	
	
	
	@GetMapping("/confirmation/{confirmationCode}")
	public ResponseEntity<?> getBookingByConfimationCode(@PathVariable String confirmationCode){
		try {
			BookdRoom booking = bookingService.findByBookingConfirmationCode(confirmationCode);
			BookingResponse bookingResponse = getBookingResponse(booking);
			return ResponseEntity.ok(bookingResponse);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	
	@PostMapping("/room/{roomId}/booking")
	public ResponseEntity<?> saveBooking(@PathVariable Long roomId,@RequestBody BookdRoom bookingRequest){
		
		try {
			String confirmationCode = bookingService.saveBooking(roomId, bookingRequest);
			
			
			return ResponseEntity.ok("Room Booked Successfully ! Your Booking Confirmation code is : "+ confirmationCode);
		} catch (InvalidBookingRequestException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
	
	@DeleteMapping("/booking/{bookingId}/delete")
	public void cancelBooking(@PathVariable Long bookingId) {
		bookingService.cancelBooking(bookingId);
	}
	
	private BookingResponse getBookingResponse(BookdRoom booking) {
		Room theRoom = roomService.getRoomById(booking.getRoom().getId()).get();
		RoomResponse room = new RoomResponse(theRoom.getId(), theRoom.getRoomType(), theRoom.getRoomPrice());
		return new BookingResponse(booking.getBookingId(),booking.getCheckInDate(), booking.getCheckOutDate(),booking.getGuestFullName(),booking.getGuestEmail(), booking.getNumberOfAdults(), booking.getNumberOfChildren(), booking.getTotalNoOfGuest(),booking.getBookingConfirmationCode(), room);
	}

}
