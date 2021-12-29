package telran.b7a.myspringhibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.b7a.myspringhibernate.dto.CityPopulationDto;
import telran.b7a.myspringhibernate.model.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {
//    @Query(value = "SELECT * FROM persons WHERE name=?1", nativeQuery = true) // в SQL аргументы начинаются с 1
    // JPQL
    @Query("select p from Person p where p.name=?1")
    Stream<Person> findAllByName(String name);

    Stream<Person> findAllByBirthDateBetween(LocalDate minAge, LocalDate maxAge);

    @Query("select p from Person p where p.address.city=:cities")
    Stream<Person> findAllByAddressCity(@Param("cities") String cities);

    @Query("select new telran.b7a.myspringhibernate.dto.CityPopulationDto(p.address.city, count(p)) from Person p group by p.address.city order by count(p) desc")
    List<CityPopulationDto> getCityPopulation();

    @Query("select p from Employee p where p.salary > ?1 and p.salary < ?2")
    Stream<Person> findAllBySalary(@Param("min") int min, @Param("max") int max);

    @Query("select p from Child p")
    Stream<Person> findAllChild();

//    Stream<Person> findByKindergartenNotNull();

}
