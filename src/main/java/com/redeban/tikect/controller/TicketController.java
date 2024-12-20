package com.redeban.tikect.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.redeban.tikect.converter.TicketConverter;
import com.redeban.tikect.domain.Ticket;
import com.redeban.tikect.dto.request.PaginationRequest;
import com.redeban.tikect.dto.ticket.TicketDTO;
import com.redeban.tikect.service.TicketService;
import com.redeban.tikect.util.HttpUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Ticket", description = "The Ticket Api")
@RestController
@RequestMapping("/tickets")
@CrossOrigin("*")
public class TicketController {

	private final TicketService ticketService;
	private final TicketConverter ticketConverter;
	
	public TicketController(TicketService ticketService, TicketConverter ticketConverter) {
		this.ticketService = ticketService;
		this.ticketConverter = ticketConverter;
	}
	
	@Operation(
            summary = "Book ticket",
            description = "Reserve ticket per user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation")
    })
	@PostMapping("{ticketId}/reserve/{userId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void bookTicket(@PathVariable int ticketId, @PathVariable int userId) {
		ticketService.bookTicket(ticketId, userId);
	}
	
	@Operation(
            summary = "Fetch all tickets",
            description = "Fetches all tickets entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
	@GetMapping
	public List<TicketDTO> getTickets(@Valid PaginationRequest paginationRequest) {
		Page<Ticket> page = ticketService.getTickets(paginationRequest);
		
		HttpUtil.setHeaderTotalCountResponse(String.valueOf(page.getTotalElements()));
		
		return page.getContent()
				.stream().map(t -> ticketConverter.convert(t))
				.collect(Collectors.toList());
	}
	
}
