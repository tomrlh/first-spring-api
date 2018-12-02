package socialbooksapi.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import socialbooksapi.domain.Book;
import socialbooksapi.services.BooksService;
import socialbooksapi.services.exceptions.BookNotFoundException;

@RestController
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BooksService booksService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Book>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(booksService.findAll());
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Book book) {
		book = booksService.save(book);
		
		// creates the URI that refers to the resource created
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(book.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable("id") Long id) {
		Book book = null;
		try {
			book = booksService.findById(id);
		}
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id)  {
		try {
			booksService.deleteById(id);			
		}
		catch (BookNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Book book, @PathVariable("id") Long id)  {
		book.setId(id);
		try {			
			booksService.update(book);
		}
		catch (BookNotFoundException e) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.noContent().build();
	}
}
