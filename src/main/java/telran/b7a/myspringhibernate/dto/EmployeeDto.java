package telran.b7a.myspringhibernate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto extends PersonDto{
    String company;
    Integer salary;

    public EmployeeDto(Integer id, String name, LocalDate birthDate, AddressDto address, String company, Integer salary) {
        super(id, name, birthDate, address);
        this.company = company;
        this.salary = salary;
    }

    public EmployeeDto(String company, Integer salary) {
        this.company = company;
        this.salary = salary;
    }
}
