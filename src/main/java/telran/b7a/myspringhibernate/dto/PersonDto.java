package telran.b7a.myspringhibernate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonDto {
//    String DateFormat = "[yyyy-MM-DD]";

    Integer id;
    String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    AddressDto address;
}
