package socialbooksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import socialbooksapi.domain.Author;

public interface AuthorsRepository extends JpaRepository<Author, Long> {

}
