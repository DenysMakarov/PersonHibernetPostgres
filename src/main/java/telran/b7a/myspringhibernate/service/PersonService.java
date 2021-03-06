package telran.b7a.myspringhibernate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import telran.b7a.myspringhibernate.dto.CityPopulationDto;
import telran.b7a.myspringhibernate.dto.PersonDto;
import telran.b7a.myspringhibernate.model.Address;
import telran.b7a.myspringhibernate.model.Child;

@Service
public interface PersonService {
    boolean addPerson(PersonDto personDto);

    PersonDto findPersonById(Integer id);

    PersonDto removePerson(Integer id);

    PersonDto updatePerson(Integer id, String name);

    PersonDto updateAddress(Integer id, Address address);

    Iterable<PersonDto> findPersonsByName(String name);

    Iterable<PersonDto> findPersonBetweenAges(Integer minAge, Integer maxAge);

    Iterable<PersonDto> findPersonByCities(String city);

    Iterable<CityPopulationDto> getCityPopulation();

    Iterable<PersonDto> findEmployeeBySalary(int min, int max);

    Iterable<PersonDto> getChildren();


}
