package socialbooksapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialbooksapi.domain.Author;
import socialbooksapi.repository.AuthorsRepository;
import socialbooksapi.services.exceptions.AuthorNotFoundException;
import socialbooksapi.services.exceptions.ExistingAuthorException;

@Service
public class AuthorsService {

	@Autowired
	private AuthorsRepository authorsRepository;
	
	public List<Author> findAll() {
		return authorsRepository.findAll();
	}
	
	
	
	public Author save(Author author) {
		if(author.getId() != null) {
			Optional<Author> a = authorsRepository.findById(author.getId());
			
			if(a != null)
				throw new ExistingAuthorException("Author already exists");	
		}
		return authorsRepository.save(author);
	}
	
	
	
	public Author find(Long id) {
		Optional<Author> author = authorsRepository.findById(id);
		
		if(author == null)
			throw new AuthorNotFoundException("The author was not found");
		
		return author.get();
	}
}
