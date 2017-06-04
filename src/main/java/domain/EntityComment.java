package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public abstract class EntityComment extends DomainEntity {

	public EntityComment() {
		super();
	}

	/* Relaciones */
	private Collection<Comment> comments;

	@OneToMany
	@ElementCollection
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

}
