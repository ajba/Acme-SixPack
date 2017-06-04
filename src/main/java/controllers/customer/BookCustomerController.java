package controllers.customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Book;
import domain.Customer;
import domain.Gym;
import domain.Service;
import services.BookService;
import services.CustomerService;
import services.GymService;
import services.ServiceService;

@Controller
@RequestMapping("/book/customer")
public class BookCustomerController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ServiceService serviceService;

	@Autowired
	private GymService gymService;

	@Autowired
	private CustomerService customerService;

	public BookCustomerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		Collection<Book> books = bookService.booksByCustomer(customerService.findByPrincipal().getId());
		Collection<Service> services = serviceService
				.serviceBookedByCustomer(customerService.findByPrincipal().getId());

		Calendar c = Calendar.getInstance();

		Collection<Boolean> expired = new ArrayList<Boolean>();

		for (Book b : books) {

			c.setTime(b.getActivateDate());
			c.add(Calendar.MINUTE, (int) b.getDuration() * 60);

			if (c.before(Calendar.getInstance())) {
				expired.add(true);
			} else {
				expired.add(false);
			}
		}

		result = new ModelAndView("book/customer/list");
		result.addObject("expired", expired);
		result.addObject("books", books);
		result.addObject("services", services);
		result.addObject("requestURI", "book/customer/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView m;

		Book b = bookService.create();

		m = createEditModelAndView(b, true);

		return m;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Book b, BindingResult binding) {

		ModelAndView m = null;

		Collection<Service> servicesInGym = serviceService.servicesInGym(b.getGym().getId());
		boolean correct = servicesInGym.contains(b.getService());

		if (correct) {
			if (binding.hasErrors()) {
				m = createEditModelAndView(b, correct);
			} else {
				try {
					bookService.save(b);
					m = new ModelAndView("redirect:list.do");
				} catch (Throwable e) {
					m = createEditModelAndView(b, "book.commit.error", correct);
				}
			}
		} else {
			m = createEditModelAndView(b, "book.commit.error", correct);
		}
		return m;
	}

	@RequestMapping(value = "/cancelBooking", method = RequestMethod.GET)
	public ModelAndView cancelBooking(@RequestParam int bookId) {

		ModelAndView m = new ModelAndView("book/customer/list");
		boolean cancelError = false;

		Book b = bookService.findOne(bookId);

		if (b != null) {
			try {
				bookService.cancelBooking(b);
			} catch (Throwable e) {
				cancelError = true;
			}
		} else {
			cancelError = true;
		}
		
		Collection<Book> books = bookService.booksByCustomer(customerService.findByPrincipal().getId());
		Collection<Service> services = serviceService
				.serviceBookedByCustomer(customerService.findByPrincipal().getId());

		Calendar c = Calendar.getInstance();

		Collection<Boolean> expired = new ArrayList<Boolean>();

		for (Book book : books) {

			c.setTime(book.getActivateDate());
			c.add(Calendar.MINUTE, (int) book.getDuration() * 60);

			if (c.before(Calendar.getInstance())) {
				expired.add(true);
			} else {
				expired.add(false);
			}
		}
		
		m.addObject("cancelError", cancelError);
		m.addObject("expired", expired);
		m.addObject("books", books);
		m.addObject("services", services);
		m.addObject("requestURI", "book/customer/list.do");
		
		return m;
	}

	/* . */
	protected ModelAndView createEditModelAndView(Book b, boolean correct) {

		ModelAndView m;

		m = createEditModelAndView(b, null, correct);

		return m;
	}

	protected ModelAndView createEditModelAndView(Book b, String message, boolean correct) {

		ModelAndView m;
		boolean noAvailableGyms = false;
		Customer c = customerService.findByPrincipal();

		// Gimnasios con cuotas activas en el momento
		Collection<Gym> gyms = gymService.gymsActivesFees(c.getId());
		Collection<Service> services = serviceService.findAll();

		if (gyms.size() == 0) {
			noAvailableGyms = true;
		}

		m = new ModelAndView("book/customer/create");
		m.addObject("requestURI", "/book/customer/create.do");
		m.addObject("book", b);
		m.addObject("correct", correct);
		m.addObject("gyms", gyms);
		m.addObject("services", services);
		m.addObject("noAvailableGyms", noAvailableGyms);
		m.addObject("message", message);

		return m;
	}
}
