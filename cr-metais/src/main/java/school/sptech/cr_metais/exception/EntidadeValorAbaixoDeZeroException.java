package school.sptech.cr_metais.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntidadeValorAbaixoDeZeroException extends RuntimeException {
    public EntidadeValorAbaixoDeZeroException(String message) {
        super(message);
    }
}
