package telran.b7a.myspringhibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import telran.b7a.myspringhibernate.dto.PersonDto;
import telran.b7a.myspringhibernate.model.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Stream<Person> findAllByName(String name);

    //    Stream<Person> findAllByBirthDateBetween(LocalDate minAge, LocalDate maxAge);

    Stream<Person> findAllByBirthDateBetween(Integer minAge, Integer maxAge);
//    Stream<Person> findAllByLoBetween(Integer minAge, Integer maxAge);

    Stream<Person> findAllByAddress_City(String cities);
}
