package com.redeban.tikect.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.redeban.tikect.data.TicketRepository;
import com.redeban.tikect.data.UserRepository;
import com.redeban.tikect.domain.Ticket;
import com.redeban.tikect.domain.User;
import com.redeban.tikect.dto.request.PaginationRequest;
import com.redeban.tikect.util.Utils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TicketService {

	private final TicketRepository ticketRepository;
	private final UserRepository userRepository;
	
	public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public Ticket bookTicket(int ticketId, int userId) {
		Ticket ticket = ticketRepository
				.findById(ticketId)
				.orElseThrow(() -> new EntityNotFoundException("Invalid ticket"));
		
		if (ticket.getUserId() != null) {
			throw new IllegalArgumentException("The ticket is reserved");
		}
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Invalid user"));
		
		ticket.setAssignmentDate(Utils.nowTimestamp());
		ticket.setUserId(user);
		ticket.setUpdateDate(Utils.nowTimestamp());
		
		return ticketRepository.save(ticket);
	}
	
	public Page<Ticket> getTickets(PaginationRequest paginationRequest) {
		return ticketRepository.findAll(PageRequest.of(paginationRequest.getFrom(), paginationRequest.getSize()));
	}
	
}
