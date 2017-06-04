package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {
		
	
	private Collection<Book> book;
	
	
	public Administrator() {
		super();
	}
	
	

	@ElementCollection
	@OneToMany(mappedBy="administrator")
	public Collection<Book> getBook() {
		return book;
	}

	public void setBook(Collection<Book> book) {
		this.book = book;
	}
	
	
	
	
}
