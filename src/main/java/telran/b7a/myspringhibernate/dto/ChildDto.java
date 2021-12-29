package telran.b7a.myspringhibernate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@ToString
public class ChildDto extends PersonDto {
    String kindergarten;

    public ChildDto(Integer id, String name, LocalDate birthDate, AddressDto address, String kindergarten) {
        super(id, name, birthDate, address);
        this.kindergarten = kindergarten;
    }
}
