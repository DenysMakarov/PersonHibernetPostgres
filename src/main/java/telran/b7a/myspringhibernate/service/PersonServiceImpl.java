package telran.b7a.myspringhibernate.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.b7a.myspringhibernate.dao.PersonRepository;
import telran.b7a.myspringhibernate.dto.CityPopulationDto;
import telran.b7a.myspringhibernate.dto.PersonDto;
import telran.b7a.myspringhibernate.dto.PersonNotFondException;
import telran.b7a.myspringhibernate.model.Address;
import telran.b7a.myspringhibernate.model.Person;

import java.time.LocalDate;
import java.util.stream.Collectors;


@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepository;
    ModelMapper modelMapper;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) return false;
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto removePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);
        personRepository.deleteById(id);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto updatePerson(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);
        person.setName(name);
//        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional // так же используется save()
    public PersonDto updateAddress(Integer id, Address address) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);
        person.setAddress(address);
//        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional(readOnly = true) // для того, что бы не блокировать путь для для остальных кто хочет также искать
    public Iterable<PersonDto> findPersonsByName(String name) {
        return personRepository.findAllByName(name)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<PersonDto> findPersonBetweenAges(Integer minAge, Integer maxAge) {
        return personRepository.findAllByBirthDateBetween(LocalDate.now().minusYears(maxAge), LocalDate.now().minusYears(minAge))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<PersonDto> findPersonByCities(String city) {
        return personRepository.findAllByAddressCity(city)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<CityPopulationDto> getCityPopulation() {
        return personRepository.getCityPopulation();
    }

}
