package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Book;
import domain.Customer;
import domain.FeePayment;
import domain.Folder;
import domain.Message;
import forms.CustomerRegistrationForm;
import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private FolderService folderService;

	public CustomerService() {
		super();
	}

	public Customer create() {

		Customer c = new Customer();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();

		c.setName("");
		c.setSurname("");

		Collection<Folder> hola = new ArrayList<Folder>();

		c.setAdditionalFolders(hola);
		// c.setAdditionalFolders(new ArrayList<Folder>());
		c.setMessagesReceived(new HashSet<Message>());
		c.setMessagesSent(new HashSet<Message>());

		c.setBooks(new HashSet<Book>());
		c.setFeePayment(new HashSet<FeePayment>());
		c.setCreditCard(null);

		authority.setAuthority(Authority.CUSTOMER);
		userAccount.addAuthority(authority);
		c.setUserAccount(userAccount);

		return c;
	}

	public Customer reconstruct(CustomerRegistrationForm crf) {

		Customer c = create();

		c.setName(crf.getName());
		c.setSurname(crf.getSurname());
		c.setPhone(crf.getPhone());
		c.setCreditCard(crf.getCreditCard());
		c.getUserAccount().setUsername(crf.getUsername());
		c.getUserAccount().setPassword(crf.getPassword());

		return c;

	}
	
	
	public Customer reconstructEdit(CustomerRegistrationForm crf) {

		Customer c = findByPrincipal();

		c.setName(crf.getName());
		c.setSurname(crf.getSurname());
		c.setPhone(crf.getPhone());
		c.setCreditCard(crf.getCreditCard());
		c.getUserAccount().setUsername(crf.getUsername());
		c.getUserAccount().setPassword(crf.getPassword());

		return c;

	}
	
	public CustomerRegistrationForm construct(){
		Customer customer = findByPrincipal();
		CustomerRegistrationForm crf = new CustomerRegistrationForm();
		crf.setName(customer.getName());
		crf.setSurname(customer.getSurname());
		crf.setCreditCard(customer.getCreditCard());
		crf.setUsername(customer.getUserAccount().getUsername());
		crf.setPassword(customer.getUserAccount().getPassword());
		crf.setPhone(customer.getPhone());
		crf.setId(customer.getId());
		
		return crf;

		
	}

	public void save(Customer c) {

		Assert.assertNotNull(c);

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication.getPrincipal().equals("anonymousUser")) {

			c.setInbox(folderService.createForActor("Inbox", null));
			c.setOutbox(folderService.createForActor("Outbox", null));
			c.setSpambox(folderService.createForActor("SpamBox", null));
			c.setTrashbox(folderService.createForActor("Trashbox", null));

			Customer customerSaved = customerRepository.save(c);

			customerSaved.getInbox().setAdditionalFolderOwner(customerSaved);
			customerSaved.getOutbox().setAdditionalFolderOwner(customerSaved);
			customerSaved.getTrashbox().setAdditionalFolderOwner(customerSaved);

			customerRepository.save(customerSaved);
		}
	}
	
	public void update(Customer c){
		
		Assert.assertNotNull(c);
		customerRepository.save(c);

	}

	public Customer findByPrincipal() {

		UserAccount userAccount = LoginService.getPrincipal();
		Customer result = customerRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Integer numberOfCustomersHaveBookedGym(int id) {

		return customerRepository.numberOfCustomersHaveBookedGym(id);
	}

	public Collection<Customer> findAll() {

		return customerRepository.findAll();
	}

	public Customer findOne(int id) {

		return customerRepository.findOne(id);
	}

	public Collection<Customer> paidMoreFees() {
		return customerRepository.havePaidMoreFees();
	}
	
	public Collection<Customer> paidLessFees() {
		return customerRepository.havePaidLessFees();
	}

	public Collection<Customer> hasBeenRemovedMoreComments() {
		return customerRepository.haveDeletedMoreComments();
	}

}
