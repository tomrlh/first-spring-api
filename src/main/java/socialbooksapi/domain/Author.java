package socialbooksapi.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Name is required")
	@JsonInclude(Include.NON_NULL)
	private String name;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@NotNull(message="dateBirth is required")
	@JsonInclude(Include.NON_NULL)
	private String dateBirth;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message="nationality is required")
	private String nationality;
	@OneToMany(mappedBy="author")
	@JsonIgnore
	private List<Book> books;
	

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
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	public String getDateBirth() {
		return dateBirth;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd")
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
