package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	public Actor() {
		super();
	}

	private String name;
	private String surname;
	private String phone;

	/* Relations */

	// inbox
	private Folder inbox;
	// outbox
	private Folder outbox;
	// trashbox
	private Folder trashbox;
	// spambox
	private Folder spambox;

	// additional folders
	private Collection<Folder> additionalFolders;

	// messages receives
	private Collection<Message> messagesReceived;
	// messages sent
	private Collection<Message> messagesSent;

	// usserAccount
	private UserAccount userAccount;

	/* End relations */

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Pattern(regexp = "^^[67][0-9]{8}")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Valid
	@OneToOne(optional = false)
	public Folder getInbox() {
		return inbox;
	}

	public void setInbox(Folder inbox) {
		this.inbox = inbox;
	}

	@Valid
	@OneToOne(optional = false)
	public Folder getOutbox() {
		return outbox;
	}

	public void setOutbox(Folder outbox) {
		this.outbox = outbox;
	}

	@Valid
	@OneToOne(optional = false)
	public Folder getTrashbox() {
		return trashbox;
	}

	public void setTrashbox(Folder trashbox) {
		this.trashbox = trashbox;
	}

	@Valid
	@OneToOne(optional = false)
	public Folder getSpambox() {
		return spambox;
	}

	public void setSpambox(Folder spambox) {
		this.spambox = spambox;
	}

	@Valid
	@ElementCollection
	@OneToMany(mappedBy = "recipient")
	public Collection<Message> getMessagesReceived() {
		return messagesReceived;
	}

	public void setMessagesReceived(Collection<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	@Valid
	@ElementCollection
	@OneToMany(mappedBy = "sender")
	public Collection<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(Collection<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Valid
	@ElementCollection
	@OneToMany(mappedBy = "additionalFolderOwner")
	public Collection<Folder> getAdditionalFolders() {
		return additionalFolders;
	}

	public void setAdditionalFolders(Collection<Folder> additionalFolders) {
		this.additionalFolders = additionalFolders;
	}
}