package telran.b7a.myspringhibernate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalDate;


@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Child extends Person {
    public static final long serialVersionUID= 328798430937247504L;
    String kindergarten;

    public Child(Integer id, String name, LocalDate birthDate, Address address, String kindergarten) {
        super(id, name, birthDate, address);
        this.kindergarten = kindergarten;
    }
}
