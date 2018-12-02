package socialbooksapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import socialbooksapi.domain.Book;
import socialbooksapi.repository.BooksRepository;
import socialbooksapi.services.exceptions.BookNotFoundException;

public class BooksService {
	@Autowired
	private BooksRepository booksRepository;
	
	public List<Book> findAll() {
		return booksRepository.findAll();
	}
	
	
	
	public Book findById(Long id) {
		Optional<Book> book = booksRepository.findById(id);
		
		if(!book.isPresent()) throw new BookNotFoundException("The book was not found");
		
		return book.get();
	}
	
	
	
	public Book save(Book book) {
		book.setId(null);
		return booksRepository.save(book);
	}
	
	
	
	public void update(Book book) {
		checkIfExists(book);
		booksRepository.save(book);
	}
	
	
	
	private void checkIfExists(Book book) {
		findById(book.getId());
	}
	
	
	
	public void deleteById(Long id) {
		try {
			booksRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new BookNotFoundException("The book was not found.");
		}
	}
}
