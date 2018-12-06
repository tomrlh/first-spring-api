package socialbooksapi.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import socialbooksapi.domain.Book;
import socialbooksapi.domain.Comment;
import socialbooksapi.services.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BooksService booksService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Book>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(booksService.findAll());
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Book book) {
		book = booksService.save(book);
		
		// creates the URI that refers to the resource created
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(book.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable("id") Long id) {
		Book book = booksService.findById(id);
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK)
				.cacheControl(cacheControl)
				.body(book);
	}	
	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id)  {
		booksService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Book book, @PathVariable("id") Long id)  {
		book.setId(id);
		booksService.update(book);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	@RequestMapping(value="/{id}/comments", method=RequestMethod.GET)
	public ResponseEntity<List<Comment>> findComments(@PathVariable("id") Long bookId) {
		List<Comment> comments = booksService.findComments(bookId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comments);
	}




	@RequestMapping(value="/{id}/comments", method=RequestMethod.POST)
	public ResponseEntity<Void> addComment(@PathVariable("id") Long bookId,
			@RequestBody Comment comment) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		comment.setUser(auth.getName());
		
		booksService.saveComment(bookId, comment);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
