package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Book;
import services.AdministratorService;
import services.BookService;

@Controller
@RequestMapping("/book/administrator")
public class BookAdministratorController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private AdministratorService adminService;

	public  BookAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		Collection<Book> books = bookService.findAll();

		result = new ModelAndView("book/administrator/list");

		result.addObject("books", books);
		result.addObject("requestURI", "book/administrator/list.do");

		return result;
	}
	
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam String bookId) {

		ModelAndView result;
		Book  b=  bookService.findOne(new Integer(bookId));
		b.setApproved(false);
		b.setAdministrator(adminService.findByPrincipal());
		bookService.saveAdmmin(b);


		result = new ModelAndView("redirect:list.do");

		return result;
	}
	
	@RequestMapping(value = "/aprove", method = RequestMethod.GET)
	public ModelAndView aprove(@RequestParam String bookId) {

		ModelAndView result;
		Book  b=  bookService.findOne(new Integer(bookId));
		b.setApproved(true);
		b.setAdministrator(adminService.findByPrincipal());
		bookService.saveAdmmin(b);


		result = new ModelAndView("redirect:list.do");

		return result;
	}
	
}
