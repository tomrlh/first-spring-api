package socialbooksapi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import socialbooksapi.domain.Book;
import socialbooksapi.domain.Comment;
import socialbooksapi.repository.BooksRepository;
import socialbooksapi.repository.CommentsRepository;
import socialbooksapi.services.exceptions.BookNotFoundException;

@Service
public class BooksService {
	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private CommentsRepository commentsRepository;
	
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
	
	
	
	public Comment saveComment(Long bookId, Comment comment) {
		Book book = findById(bookId);
		
		comment.setBook(book);
		comment.setDate(new Date());
		
		return commentsRepository.save(comment);
	}
	
	
	
	public List<Comment> findComments(Long bookId) {
		Book book = findById(bookId);
		return book.getComments();
	}
}
