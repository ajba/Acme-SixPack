package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Service;
import services.ServiceGymService;

@Controller
@RequestMapping("/serviceGym")
public class ServiceGymController extends AbstractController {

	@Autowired
	private ServiceGymService serviceGymService;

	public ServiceGymController() {
		super();
	}

	// Solo lista los servicios por gimnasio
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int gymId) {
		ModelAndView result;

		Collection<Service> s = serviceGymService.servicesInGym(gymId);

		result = new ModelAndView("serviceGym/list");

		result.addObject("services", s);
		result.addObject("requestURI", "serviceGym/list.do");

		return result;
	}
}
