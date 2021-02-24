package edu.spring.rest;

import edu.spring.domain.Country;

public class CountryDto {

  private int id = -1;
  private String name;

  public CountryDto() {
  }

  public CountryDto(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static Country toDomainObject(CountryDto dto) {
    return new Country(dto.getId(), dto.getName());
  }

  public static CountryDto toDto(Country country) {
    return new CountryDto(country.getId(), country.getName());
  }
}
