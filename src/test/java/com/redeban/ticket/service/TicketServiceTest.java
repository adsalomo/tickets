package com.redeban.ticket.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.redeban.tikect.data.TicketRepository;
import com.redeban.tikect.data.UserRepository;
import com.redeban.tikect.domain.Ticket;
import com.redeban.tikect.domain.User;
import com.redeban.tikect.service.TicketService;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

	@InjectMocks
	private TicketService ticketService;
	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void bookTicket() {		
		Ticket ticket = getTicket();
		Ticket savedTicket = getSavedTicket();
		
		User user = getUser();
		
		when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.of(ticket));
		when(ticketRepository.save(any(Ticket.class))).thenReturn(savedTicket);
		when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(user));
		
		Ticket responseTicket = ticketService.bookTicket(1, 1);
		
		assertTrue(responseTicket.getVersion() == 1);
		assertTrue(responseTicket.getUserId().getId() == 1);
		assertTrue(responseTicket.getId() == 1);
	}
	
	@Test
	public void ticketReservationReject() {		
		Ticket ticket = getReservedTicket();
		
		when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.of(ticket));
		
		IllegalArgumentException exception = 
				assertThrows(IllegalArgumentException.class, () -> ticketService.bookTicket(1, 1));
		
		assertTrue(exception.getMessage().equals("The ticket is reserved"));
	}
	
	private Ticket getSavedTicket() {
		Ticket ticket = new Ticket();
		ticket.setId(1);
		ticket.setVersion(1L);
		ticket.setUserId(getUser());
		ticket.setCreationDate("2024-12-20T20:56:10");
		ticket.setUpdateDate("2024-12-20T20:56:10");
		return ticket;
	}
	
	private Ticket getTicket() {
		Ticket ticket = new Ticket();
		ticket.setId(1);
		ticket.setCreationDate("2024-12-20 20:56:10");
		ticket.setUpdateDate("2024-12-20 20:56:10");
		return ticket;
	}
	
	private Ticket getReservedTicket() {
		Ticket ticket = new Ticket();
		ticket.setId(1);
		ticket.setVersion(1L);
		ticket.setUserId(getUser());
		ticket.setCreationDate("2024-12-20T20:56:10");
		ticket.setUpdateDate("2024-12-20T20:56:10");
		return ticket;
	}
	
	private User getUser() {
		User user = new User();
		user.setId(1);
		user.setName("Name");
		return user;
	}
	
}
