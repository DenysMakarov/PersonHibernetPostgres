package telran.b7a.myspringhibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import telran.b7a.myspringhibernate.dao.PersonRepository;
import telran.b7a.myspringhibernate.model.Address;
import telran.b7a.myspringhibernate.model.Child;
import telran.b7a.myspringhibernate.model.Employee;

import java.time.LocalDate;

@SpringBootApplication
public class SpringHibernateApplication implements CommandLineRunner {

    PersonRepository repository;

    @Autowired
    public SpringHibernateApplication(PersonRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        if (repository.count()!=0){
//            return;
//        }
//        Employee p1 = new Employee(1000, "John", LocalDate.of(1990, 5, 12),
//                new Address("Rehovod", "Herzl", 18), "Motorola", 20000);
//        Employee p2 = new Employee(2000, "Marry", LocalDate.of(1994, 5, 12),
//                new Address("Lod", "Laskov", 13), "IBM", 21000);
//        Child p3 = new Child(3000, "Peter", LocalDate.of(1994, 5, 12),
//                new Address("Ashkelon", "Bar Kohra", 117), "Shalom");
//
//        repository.save(p1);
//        repository.save(p2);
//        repository.save(p3);
    }
}
