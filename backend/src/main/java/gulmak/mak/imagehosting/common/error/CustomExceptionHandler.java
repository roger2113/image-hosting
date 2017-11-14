package gulmak.mak.imagehosting.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<LaconicException> handleUserNotFoundException(){
        return new ResponseEntity<>(new LaconicException("User not found"), HttpStatus.NOT_FOUND);
    }

    private static class LaconicException{
        private String message;

        public LaconicException(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
