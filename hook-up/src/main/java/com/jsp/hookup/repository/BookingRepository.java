package com.jsp.hookup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.hookup.model.BookdRoom;

public interface BookingRepository extends JpaRepository<BookdRoom, Long> {
	
	BookdRoom findByBookingConfirmationCode(String confirmationCode);
	
	List<BookdRoom> findByRoomId(Long roomId);

}
