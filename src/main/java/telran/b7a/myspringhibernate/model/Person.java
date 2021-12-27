package telran.b7a.myspringhibernate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "persons")
public class Person implements Serializable {
    private static final long serialVersionUID = 3001938261594750449L;
    @Id
    Integer id;
    String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
//        @Embedded
    Address address;

}