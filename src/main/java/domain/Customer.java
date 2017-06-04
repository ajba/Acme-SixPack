package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "deletedComments") })
public class Customer extends Actor {

	private CreditCard creditCard;
	private int deletedComments;

	/* RELATIONSHIPS */
	private Collection<SocialIdentity> socialIdentity;
	private Collection<FeePayment> feePayment;
	private Collection<Book> books;

	public Customer() {
		super();
	}

	@Valid
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "holderName", column = @Column(nullable = true) ),
			@AttributeOverride(name = "brand", column = @Column(nullable = true) ),
			@AttributeOverride(name = "number", column = @Column(nullable = true) ),
			@AttributeOverride(name = "CVV", column = @Column(nullable = true) ),
			@AttributeOverride(name = "expirationMonth", column = @Column(nullable = true) ),
			@AttributeOverride(name = "expirationYear", column = @Column(nullable = true) ) })
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@ElementCollection
	@OneToMany
	public Collection<SocialIdentity> getSocialIdentity() {
		return socialIdentity;
	}

	public void setSocialIdentity(Collection<SocialIdentity> socialIdentity) {
		this.socialIdentity = socialIdentity;
	}

	@ElementCollection
	@OneToMany
	public Collection<FeePayment> getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(Collection<FeePayment> feePayment) {
		this.feePayment = feePayment;
	}

	@ElementCollection
	@OneToMany(mappedBy = "customer")
	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	@Min(0)
	public int getDeletedComments() {
		return deletedComments;
	}

	public void setDeletedComments(int deletedComments) {
		this.deletedComments = deletedComments;
	}

}
