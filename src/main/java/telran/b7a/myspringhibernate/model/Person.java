package telran.b7a.myspringhibernate.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"id"})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person implements Serializable {
    private static final long serialVersionUID = 3001938261594750449L;
    @Id
    Integer id;
    String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    @Embedded
    Address address;
}

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type")

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)

//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(name = "child", value = Child.class),
//        @JsonSubTypes.Type(name = "employee", value = Employee.class)
//})