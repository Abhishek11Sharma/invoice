package dacinc.dacinc.Exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dacinc.dacinc.Dtos.ApiResponseDto;

@ControllerAdvice
public class GlobalExceptionHelper {
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleNoSuchMethodArgumentException(MethodArgumentNotValidException exception) {
        List<Object> list = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName;
            try {
                fieldName = ((FieldError) error).getField();
            } catch (ClassCastException ex) {
                fieldName = error.getObjectName();
            }
            String message = error.getDefaultMessage();
            list.add(fieldName + "(" + message + ")");
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto("Parameter mistach exception !!", false, list));

    }
}
