package telran.b7a.myspringhibernate.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.b7a.myspringhibernate.dao.PersonRepository;
import telran.b7a.myspringhibernate.dto.CityPopulationDto;
import telran.b7a.myspringhibernate.dto.PersonDto;
import telran.b7a.myspringhibernate.dto.PersonNotFondException;
import telran.b7a.myspringhibernate.dto.UnknownPersonTypeException;
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
        personRepository.save(modelMapper.map(personDto, getModeClass(personDto)));
        return true;
    }

    private Class<? extends Person>  getModeClass(PersonDto personDto) {
        String modelClassName = personDto.getClass().getSimpleName();
        modelClassName = modelClassName.substring(0, modelClassName.length()-3);

        try {
            Class<? extends Person> clazz = (Class<? extends Person>) Class.forName("telran.b7a.myspringhibernate.model." + modelClassName);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new UnknownPersonTypeException();
        }
    }


    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);
        return modelMapper.map(person, getDtoClass(person));
    }

    private Class<? extends PersonDto>  getDtoClass(Person person) {
        String modelClassName = person.getClass().getSimpleName();
        modelClassName = modelClassName + "Dto";
        try {
            Class<? extends PersonDto> clazz = (Class<? extends PersonDto>) Class.forName("telran.b7a.myspringhibernate.dto." + modelClassName);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new UnknownPersonTypeException();
        }
    }

    @Override
    @Transactional
    public PersonDto removePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);
        personRepository.deleteById(id);
        return modelMapper.map(person, getDtoClass(person));
    }

    @Override
    @Transactional
    public PersonDto updatePerson(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFondException::new);
        person.setName(name);
//        personRepository.save(person);
        return modelMapper.map(person, getDtoClass(person));
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
                .map(e -> modelMapper.map(e, getDtoClass(e)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<PersonDto> findPersonBetweenAges(Integer minAge, Integer maxAge) {
        return personRepository.findAllByBirthDateBetween(LocalDate.now().minusYears(maxAge), LocalDate.now().minusYears(minAge))
                .map(e -> modelMapper.map(e, getDtoClass(e)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<PersonDto> findPersonByCities(String city) {
        return personRepository.findAllByAddressCity(city)
                .map(e -> modelMapper.map(e, getDtoClass(e)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<CityPopulationDto> getCityPopulation() {
        return personRepository.getCityPopulation();
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<PersonDto> findEmployeeBySalary(int min, int max) {
        return personRepository.findAllBySalary(min, max)
                .map(e -> modelMapper.map(e, getDtoClass(e)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<PersonDto> getChildren() {
        return personRepository.findAllChild()
                .map(e -> modelMapper.map(e, getDtoClass(e)))
                .collect(Collectors.toList());
    }
}
