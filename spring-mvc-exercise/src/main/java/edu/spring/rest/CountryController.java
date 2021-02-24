package edu.spring.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.domain.Country;
import edu.spring.repostory.CountryRepository;

@RestController
public class CountryController {

  private final CountryRepository repository;

  @Autowired
  public CountryController(CountryRepository repository) {
    this.repository = repository;
  }

  @RequestMapping(
      value = "/country",
      method = RequestMethod.GET
  )
  public List<CountryDto> get() {
    return repository.findAll().stream()
                     .map(CountryDto::toDto)
                     .collect(Collectors.toList());
  }

  @RequestMapping(
      value = "/country/{id}",
      method = RequestMethod.GET
  )
  public CountryDto get(
      @PathVariable("id") int id
  ) {
    Country country = repository.findById(id).orElseThrow(NotFoundException::new);
    return CountryDto.toDto(country);
  }

  @RequestMapping(
      value = "/country/",
      method = RequestMethod.POST
  )
  public @ResponseBody
  CountryDto create(
      @RequestBody CountryDto dto
  ) {
    Country country = CountryDto.toDomainObject(dto);
    Country countryWithId = repository.save(country);
    return CountryDto.toDto(countryWithId);
  }

  @DeleteMapping("/country/{id}")
  public void delete(@PathVariable("id") int id) {
    repository.deleteById(id);
  }

  @PutMapping("/country/{id}/holder")
  public void changeCountryName(
      @PathVariable("id") int id,
      @RequestParam("name") String name
  ) {
    Country country = repository.findById(id).orElseThrow(NotFoundException::new);
    country.setName(name);
    repository.save(country);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotEnoughFunds(NotFoundException ex) {
    return ResponseEntity.badRequest().body("Not found");
  }
}
