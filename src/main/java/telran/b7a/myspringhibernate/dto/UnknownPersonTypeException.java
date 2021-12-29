package telran.b7a.myspringhibernate.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnknownPersonTypeException extends RuntimeException {
    private static final long serialVersionUID = -2272143220912392850L;

}
