package edu.spring.repostory;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.spring.domain.Country;

public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

  List<Country> findAll();
}
