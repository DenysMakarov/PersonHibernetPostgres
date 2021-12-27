package telran.b7a.myspringhibernate.model;


import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Embeddable
public class Address implements Serializable {
    private static final long serialVersionUID = 7335303006915188803L;
    String city;
    String street;
    int building;
}
