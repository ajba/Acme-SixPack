package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Gym;
import domain.Service;
import services.BookService;
import services.ServiceGymService;
import services.ServiceService;

@Controller
@RequestMapping("service")
public class ServiceController extends AbstractController {
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private ServiceGymService serviceGymService;
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		ArrayList<Long> totalCustomer = new ArrayList<Long>();
		Collection<Service> services = serviceService.findAll();
		
		
		for(Service s: services){
			totalCustomer.add(bookService.totalNumberCustomerWhoHaveBrooked(s.getId()));
		}

		result = new ModelAndView("service/list");

		result.addObject("services", services);
		result.addObject("totalCustomer", totalCustomer);
		result.addObject("count", 0);


		
		result.addObject("requestURI", "service/list.do");

		return result;
	}
	
	@RequestMapping(value = "/watchOfferService", method = RequestMethod.GET)
	public ModelAndView listGym(int idGym) {
		ModelAndView result;

		Collection<Gym> gyms = serviceGymService.servicesOfferGym(idGym);

		result = new ModelAndView("service/watchOfferService");

		result.addObject("gyms", gyms);
		
		result.addObject("requestURI", "service/listGym.do");

		return result;
	}


}
