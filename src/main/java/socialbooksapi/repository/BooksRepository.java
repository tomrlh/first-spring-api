package socialbooksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import socialbooksapi.domain.Book;

public interface BooksRepository extends JpaRepository<Book, Long> {
	
}
