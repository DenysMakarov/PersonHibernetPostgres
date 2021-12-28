package telran.b7a.myspringhibernate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person{
    public static final long serialVersionUID= -9033114336241790853L;

    String company;
    int salary;

    public Employee(Integer id, String name, LocalDate birthDate, Address address, String company, int salary) {
        super(id, name, birthDate, address);
        this.company = company;
        this.salary = salary;
    }

    public Employee(String company, int salary) {
        this.company = company;
        this.salary = salary;
    }
}
