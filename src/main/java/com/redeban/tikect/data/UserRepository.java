package com.redeban.tikect.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.redeban.tikect.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, 
											 PagingAndSortingRepository<User, Integer> {

}
