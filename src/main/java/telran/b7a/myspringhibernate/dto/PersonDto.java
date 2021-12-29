package telran.b7a.myspringhibernate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, // использовать имя класса
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "child", value = ChildDto.class),
        @JsonSubTypes.Type(name = "employee", value = EmployeeDto.class),
        @JsonSubTypes.Type(name = "person", value = PersonDto.class)
})
public class PersonDto {
    Integer id;
    String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    AddressDto address;
}
