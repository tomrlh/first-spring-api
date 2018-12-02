package socialbooksapi.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import socialbooksapi.domain.Book;
import socialbooksapi.repository.BooksRepository;

@RestController
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BooksRepository livrosRepository;

	@RequestMapping(method=RequestMethod.GET)
	public List<Book> getAll() {
		return livrosRepository.findAll();
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public void save(@RequestBody Book book) {
		livrosRepository.save(book);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Optional<Book> find(@PathVariable("id") Long id) {
		return livrosRepository.findById(id);
	}
	
	

	//Other way to perform a find
//	@RequestMapping(value="/{id}", method=RequestMethod.GET)
//	public ResponseEntity<Book> find(@PathVariable("id") Long id) {
//		Book book = livrosRepository.findById(id);
//		
//		if(book == null) return ResponseEntity.notFound().build();
//		
//		return ResponseEntity.status(HttpStatus.OK).body(book);
//	}	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id)  {
		livrosRepository.deleteById(id);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public void update(@RequestBody Book book, @PathVariable("id") Long id)  {
		book.setId(id);
		livrosRepository.save(book);
	}
}
