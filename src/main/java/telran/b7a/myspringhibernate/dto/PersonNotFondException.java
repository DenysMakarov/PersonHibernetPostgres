package telran.b7a.myspringhibernate.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "person not found")
public class PersonNotFondException extends RuntimeException{
    private static final long serialVersionUID = 7973749845311046131L;
}
