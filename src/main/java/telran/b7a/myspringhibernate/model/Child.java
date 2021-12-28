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
public class Child extends Person {
    public static final long serialVersionUID= 328798430937247504L;

    String kindergarten;

    public Child(Integer id, String name, LocalDate birthDate, Address address, String kindergarten) {
        super(id, name, birthDate, address);
        this.kindergarten = kindergarten;
    }

    public Child(String kindergarten) {
        this.kindergarten = kindergarten;
    }
}
