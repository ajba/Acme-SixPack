package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Gym extends EntityComment {

	private String name;
	private String description;
	private String address;
	private String phone;
	private double fee;
	private String picture;

	/* Relaciones */
	private Collection<FeePayment> feePayments;
	private Collection<Book> books;

	public Gym() {
		super();
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Pattern(regexp = "^^[67][0-9]{8}")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Min(0)
	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@ElementCollection
	@OneToMany(mappedBy = "gym")
	public Collection<FeePayment> getFeePayments() {
		return feePayments;
	}

	public void setFeePayments(Collection<FeePayment> feePayments) {
		this.feePayments = feePayments;
	}

	@ElementCollection
	@OneToMany(mappedBy = "gym")
	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}
}
