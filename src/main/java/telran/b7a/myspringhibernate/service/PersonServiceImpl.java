package telran.b7a.myspringhibernate.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.b7a.myspringhibernate.dao.PersonRepository;
import telran.b7a.myspringhibernate.dto.AddressDto;
import telran.b7a.myspringhibernate.dto.PersonDto;
import telran.b7a.myspringhibernate.dto.PersonNotFondException;
import telran.b7a.myspringhibernate.model.Address;
import telran.b7a.myspringhibernate.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
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
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);

        System.out.println(Period.between(person.getBirthDate(), LocalDate.now()).getYears());
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto removePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);
        if (person == null) return null;
        personRepository.deleteById(id);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto updatePerson(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);
        if (person == null) return null;
        person.setName(name);
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto updateAddress(Integer id, Address address) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);
        if (person == null) return null;
        person.setAddress(address);
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public Iterable<PersonDto> findPersonsByName(String name) {
        return personRepository.findAllByName(name)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Iterable<PersonDto> findPersonBetweenAges(Integer minAge, Integer maxAge) {
                return personRepository.findAllByBirthDateBetween(minAge, maxAge)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

//    @Override
//    @Transactional
//    public Iterable<PersonDto> findPersonBetweenAges(Integer minAge, Integer maxAge) {
//        return personRepository.findAllByBirthDate(minAge, maxAge)
//                .map(e -> modelMapper.map(e, PersonDto.class))
//                .collect(Collectors.toList());
//    }

    @Override
    @Transactional
    public Iterable<PersonDto> findPersonByCities(String city) {
        return personRepository.findAllByAddress_City(city)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

}
