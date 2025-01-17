package com.jsp.hookup.service;

import java.util.List;

import com.jsp.hookup.model.BookdRoom;

public interface BookingService {

	void cancelBooking(Long bookingId);

	String saveBooking(Long roomId, BookdRoom bookingRequest);

	BookdRoom findByBookingConfirmationCode(String confirmationCode);

	List<BookdRoom> getAllBookings();

	List<BookdRoom> getAllBookingsByRoomId(long roomId);

}
