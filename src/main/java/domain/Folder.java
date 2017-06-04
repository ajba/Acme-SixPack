package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity {

	public Folder() {
		super();
	}

	private String name;

	/* Relations */

	/* messages */
	private Collection<Message> messages;

	// owner of the additionalFolder
	private Actor additionalFolderOwner;

	/* End relations */

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Valid
	@ElementCollection
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

	@Valid
	@ManyToOne(optional = true)
	public Actor getAdditionalFolderOwner() {
		return additionalFolderOwner;
	}

	public void setAdditionalFolderOwner(Actor additionalFolderOwner) {
		this.additionalFolderOwner = additionalFolderOwner;
	}
}
