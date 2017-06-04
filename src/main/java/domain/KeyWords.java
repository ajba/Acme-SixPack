package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class KeyWords extends DomainEntity {

	private Collection<String> spamWords;

	public KeyWords() {
		super();
		spamWords = new ArrayList<String>();
	}

	@ElementCollection
	public Collection<String> getSpamWords() {
		return spamWords;
	}

	public void setSpamWords(Collection<String> spamWords) {
		this.spamWords = spamWords;
	}
}
