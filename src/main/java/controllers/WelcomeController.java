package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Customer;
import domain.Gym;
import domain.Service;
import services.CustomerService;
import services.GymService;
import services.ServiceService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private ServiceService serviceService;

	@Autowired
	private GymService gymService;

	@Autowired
	private CustomerService customerService;

	public WelcomeController() {
		super();
	}

	@RequestMapping(value = "/index")
	public ModelAndView index() {

		ModelAndView result = new ModelAndView("welcome/index");

		try {
			Customer c = customerService.findByPrincipal();
			boolean notAvailableOrNotActiveGym = false;
			Collection<Gym> customerGyms = gymService.gymsActivesFees(c.getId());

			if (customerGyms.isEmpty()) {

				notAvailableOrNotActiveGym = true;
			} else {

				Map<Gym, Collection<Service>> gymsServices = new HashMap<Gym, Collection<Service>>();

				Collection<Service> servicesNotBooked = new ArrayList<Service>();

				for (Gym g : customerGyms) {

					servicesNotBooked.clear();

					for (Service s : serviceService.servicesInGym(g.getId())) {
						servicesNotBooked.add(s);
					}

					for (Service s : serviceService.servicesBookedInGymByCustomer(g.getId(), c.getId())) {
						servicesNotBooked.remove(s);
					}

					gymsServices.put(g, servicesNotBooked);
				}

				int randomGym = calcRandomGym(gymsServices);
				int randomService = calcRandomService(gymsServices, randomGym);

				result.addObject("randomGym", randomGym);
				result.addObject("randomService", randomService);
				result.addObject("gymsServices", gymsServices);
			}

			result.addObject("notAvailableOrNotActiveGym", notAvailableOrNotActiveGym);
		} catch (Throwable e) {

		}

		return result;
	}

	private int calcRandomGym(Map<Gym, Collection<Service>> gymsServices) {

		Random rand = new Random();
		
		int rndGym = rand.nextInt(gymsServices.keySet().size());
		
		System.out.println(rndGym);
		return rndGym;
	}

	private int calcRandomService(Map<Gym, Collection<Service>> gymsServices, int randomGym) {

		Random rand = new Random();
		int rndService = 0;
		int aux = 0;
		
		for (Gym g : gymsServices.keySet()) {
			
			if (randomGym == aux) {
				rndService = rand.nextInt(gymsServices.get(g).size());
				break;
			}
			
			aux++;
		}
		
		System.out.println(rndService);
		return rndService;
	}
}