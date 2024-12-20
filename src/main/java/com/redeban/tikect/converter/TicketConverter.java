package com.redeban.tikect.converter;

import org.springframework.stereotype.Component;

import com.redeban.tikect.domain.Ticket;
import com.redeban.tikect.dto.ticket.TicketDTO;
import com.redeban.tikect.dto.user.UserDTO;

@Component
public class TicketConverter {

	public TicketDTO convert(Ticket ticket) {
		TicketDTO ticketDTO = new TicketDTO();
		ticketDTO.setId(ticket.getId());
		
		if (ticket.getUserId() != null) {
			UserDTO userDTO = new UserDTO();
			
			userDTO.setId(ticket.getUserId().getId());
			userDTO.setName(ticket.getUserId().getName());
			
			ticketDTO.setUserId(userDTO);
		}
		
		ticketDTO.setAssignmentDate(ticket.getAssignmentDate());
		ticketDTO.setCreationDate(ticket.getCreationDate());
		ticketDTO.setUpdateDate(ticket.getUpdateDate());
		
		return ticketDTO;
	}
	
}
