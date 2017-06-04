package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Gym;
import services.CustomerService;
import services.GymService;

@Controller
@RequestMapping("/gym/customer")
public class GymCustomerController {

	@Autowired
	private GymService gymService;

	@Autowired
	private CustomerService customerService;

	public GymCustomerController() {
		super();
	}

	// Gimnasios con cuotas activas
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		Collection<Gym> gyms = gymService.gymsActivesFees(customerService.findByPrincipal().getId());

		result = new ModelAndView("gym/customer/list");

		result.addObject("gyms", gyms);
		result.addObject("requestURI", "gym/customer/list.do");

		return result;
	}
}
