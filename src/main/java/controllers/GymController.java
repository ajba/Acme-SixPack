package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Gym;
import domain.Service;
import services.CustomerService;
import services.GymService;
import services.ServiceGymService;
import services.ServiceService;

@Controller
@RequestMapping("/gym")
public class GymController extends AbstractController {

	@Autowired
	private GymService gymService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private ServiceGymService serviceGymService;

	public GymController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Gym> g;
		Collection<Integer> n = new ArrayList<Integer>();

		g = gymService.findAll();

		for (Gym gym : g) {
			n.add(customerService.numberOfCustomersHaveBookedGym(gym.getId()));
		}

		result = new ModelAndView("gym/list");

		result.addObject("gyms", g);
		result.addObject("customerHaveBooked", n);
		result.addObject("requestURI", "gym/list.do");

		return result;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam String keyWord) {
		ModelAndView result;
		boolean mostrar = true;

		Collection<Service> services = serviceService.findServiceByNameOrDescription(keyWord);		
		Collection<Gym> servicesInGym = new ArrayList<Gym>();
		Map<Service, Collection<Gym>> search = new HashMap<Service,Collection<Gym>>();
		
		for(Service s: services){
			for(Gym g: serviceGymService.servicesOfferGym(s.getId())){		
				if(search.containsKey(s)){
					Collection<Gym> aux = new ArrayList<Gym>();
					aux = search.get(s);
					aux.add(g);
					search.put(s, clone(aux));
					aux.clear();
				}else{
					servicesInGym.add(g);
					search.put(s, clone(servicesInGym));
					servicesInGym.clear();

				}
				
			}
		
		}
		
		if(search.keySet().size()==0)
			mostrar = false;
		
		result = new ModelAndView("gym/search");
		result.addObject("search", search);
		result.addObject("wordSearch",keyWord);
		result.addObject("mostrar",mostrar);


		result.addObject("requestURI", "gym/search.do");

		return result;
	}
	
	private static Collection<Gym> clone(Collection<Gym> list){
		Collection<Gym> clone = new ArrayList<Gym>();
		for(Gym g: list){
			clone.add(g);
		}
		
		return clone;
	}
}
