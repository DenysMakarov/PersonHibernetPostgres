package telran.b7a.myspringhibernate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.b7a.myspringhibernate.dto.*;
import telran.b7a.myspringhibernate.model.Address;
import telran.b7a.myspringhibernate.model.Child;
import telran.b7a.myspringhibernate.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public boolean addPerson(@RequestBody PersonDto personDto){
        return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto findPerson(@PathVariable Integer id){
        return personService.findPersonById(id);
    }

    @DeleteMapping ("/{id}")
    public PersonDto removePerson(@PathVariable Integer id){
        return personService.removePerson(id);
    }

    @PutMapping ("/{id}")
    public PersonDto updatePerson(@PathVariable Integer id , @RequestBody UpdateDto updateDto){
        return personService.updatePerson(id, updateDto.getName());
    }

    @PutMapping ("/address/{id}")
    public PersonDto updateAddress(@PathVariable Integer id , @RequestBody Address address){
        return personService.updateAddress(id, address);
    }

    @GetMapping  ("/persons/{name}")
    public Iterable<PersonDto> findPersonByName(@PathVariable String name){
        return personService.findPersonsByName(name);
    }

    @GetMapping  ("/address/{cities}")
    public Iterable<PersonDto> findPersonByCities(@PathVariable String cities){
        return personService.findPersonByCities(cities);
    }

    @GetMapping  ("/age")
    public Iterable<PersonDto> findPersonBetweenAges(@RequestBody AgeDto ageDto){
        return personService.findPersonBetweenAges(ageDto.getMinAge(), ageDto.getMaxAge());
    }

    @GetMapping("/population/city")
    public Iterable<CityPopulationDto> getCityPopulation(){
        return personService.getCityPopulation();
    }

    @GetMapping("/salary")
    public Iterable<PersonDto> getAllBySalary(@RequestBody SalaryDto salaryDto){
        return personService.findEmployeeBySalary(salaryDto.getMin(), salaryDto.getMax());
    }

    @GetMapping("/children")
    public Iterable<PersonDto> getAllChildren(){
        return personService.getChildren();
    }
}

