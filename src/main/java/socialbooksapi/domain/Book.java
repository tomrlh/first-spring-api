package socialbooksapi.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	@NotNull(message="name is required")
	@JsonInclude(Include.NON_NULL)
	private String name;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	@NotNull(message="publicationDate is required")
	@JsonInclude(Include.NON_NULL)
	private Date publicationDate;
	
	@NotNull(message="publisher is required")
	@JsonInclude(Include.NON_NULL)
	private String publisher;

	@NotEmpty(message="resume is required")
	@Size(max=1500, message="resume exceeds the maximum size (1500 characters)")
	@JsonInclude(Include.NON_NULL)
	private String resume;
	
	@OneToMany(mappedBy="book")
	@JsonInclude(Include.NON_NULL)
	private List<Comment> comments;
	
	@ManyToOne
	@JoinColumn(name="AUTHOR_ID")
	@JsonInclude(Include.NON_NULL)
	private Author author;
	
	
	
	
	
	public Book() {}
	
	public Book(String nome) {
		this.name = nome;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
