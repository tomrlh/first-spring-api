package socialbooksapi.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import socialbooksapi.domain.ErrorDetails;
import socialbooksapi.services.exceptions.BookNotFoundException;
import socialbooksapi.services.exceptions.ExistingAuthorException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleBookNotFoundException(
			BookNotFoundException e, HttpServletRequest request
	) {
		ErrorDetails error= new ErrorDetails();
		error.setStatus(404L);
		error.setTitle("The book was not found");
		error.setDeveloperMessage("http://errors.socialbooks.com/404");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	
	@ExceptionHandler(ExistingAuthorException.class)
	public ResponseEntity<ErrorDetails> handleBookNotFoundException(
			ExistingAuthorException e, HttpServletRequest request
	) { 
		ErrorDetails error= new ErrorDetails();
		error.setStatus(409L);
		error.setTitle("Author already exists");
		error.setDeveloperMessage("http://errors.socialbooks.com/409");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorDetails> handleBookNotFoundException(
			DataIntegrityViolationException e, HttpServletRequest request
	) { 
		ErrorDetails error= new ErrorDetails();
		error.setStatus(400L);
		error.setTitle("Invalid request");
		error.setDeveloperMessage("http://errors.socialbooks.com/400");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
}
