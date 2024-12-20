package com.redeban.tikect.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.redeban.tikect.domain.Ticket;

import jakarta.persistence.LockModeType;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer>, 
											 PagingAndSortingRepository<Ticket, Integer> {

	@Lock(LockModeType.OPTIMISTIC)
	public Optional<Ticket> findById(Integer id);
	
}
