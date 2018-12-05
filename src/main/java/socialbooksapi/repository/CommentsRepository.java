package socialbooksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import socialbooksapi.domain.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

}
