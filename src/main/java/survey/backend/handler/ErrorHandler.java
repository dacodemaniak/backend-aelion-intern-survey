package survey.backend.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import survey.backend.error.NoDataFoundError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundError.class)
    public ResponseEntity<NoDataFoundError> handleNoDataFoundError(
        Exception ex, WebRequest request
    ){
        // TODO: send error jsonified
        return ResponseEntity.notFound().build();
    }
}