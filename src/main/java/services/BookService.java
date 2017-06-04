package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Book;
import repositories.BookRepository;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CustomerService customerService;
	
	

	public BookService() {
		super();
	}

	public Book create() {

		Book b = new Book();

		b.setActivateDate(new Date(System.currentTimeMillis()));
		b.setDuration(0.);

		// Siempre es asi, estas se pasaran como hidden en el formulario
		b.setCustomer(customerService.findByPrincipal());
		b.setApproved(false);
		// Se inicializa pero al guardarla se modifica con la fecha correcta
		b.setPlacementMoment(new Date(System.currentTimeMillis()));

		return b;
	}

	public void save(Book b) {

		Assert.assertTrue(b.getCustomer().equals(customerService.findByPrincipal()));
		Calendar now = Calendar.getInstance();
		Calendar activateDay = Calendar.getInstance();
		activateDay.setTime(b.getActivateDate());
		
		// La fecha de activación sea superior a la fecha de activación
		// insertada
		Assert.assertTrue(now.before(activateDay));

		b.setPlacementMoment(new Date(System.currentTimeMillis() - 1));
		bookRepository.save(b);
	}
	
	public void saveAdmmin(Book b) {
		
		bookRepository.save(b);
	
	}

	public void delete(Book b) {

		Assert.assertTrue(b.getCustomer().equals(customerService.findByPrincipal()) && b.getAdministrator() == null);

		bookRepository.delete(b);
	}

	public Book findOne(int id) {

		return bookRepository.findOne(id);
	}

	public Collection<Book> findAll() {

		return bookRepository.findAll();
	}

	public Collection<Book> booksByCustomer(int id) {

		return bookRepository.booksByCustomer(id);
	}
	
	public Long totalNumberCustomerWhoHaveBrooked(int id){
		return bookRepository.totalNumberCustomerWhoHaveBrooked(id);
	}

	public void cancelBooking(Book b) {
		
		Assert.assertTrue(customerService.findByPrincipal().getId() == b.getCustomer().getId());
		Assert.assertTrue(b.getAdministrator() == null && !b.getApproved());

		boolean expired = false;
		Calendar c = Calendar.getInstance();
		c.setTime(b.getActivateDate());
		c.add(Calendar.MINUTE, (int) b.getDuration() * 60);

		if (c.before(Calendar.getInstance())) {
			System.out.println("Expirada?");
			expired = true;
		}
		
		Assert.assertTrue(!expired);

		bookRepository.delete(b);
	}
}
