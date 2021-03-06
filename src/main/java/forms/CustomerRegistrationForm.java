package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import domain.CreditCard;

@Access(AccessType.PROPERTY)
public class CustomerRegistrationForm {

	private int id;

	private String name;
	private String surname;
	private String phone;
	private String username;
	private String password;
	private String repeatPassword;
	private CreditCard creditCard;
	private Boolean acceptTerms;

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

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

	@Size(min = 5, max = 32)
	@Column(unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Size(min = 5, max = 32)
	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	@NotNull
	public Boolean getAcceptTerms() {
		return acceptTerms;
	}

	public void setAcceptTerms(Boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}
}
