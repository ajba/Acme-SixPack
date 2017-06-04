package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Gym;
import services.GymService;

@Controller
@RequestMapping("/gym/administrator")
public class GymAdministratorController {

	@Autowired
	private GymService gymService;


	public  GymAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		Collection<Gym> gyms = gymService.findAll();

		result = new ModelAndView("gym/administrator/list");

		result.addObject("gyms", gyms);
		result.addObject("requestURI", "gym/administrator/list.do");

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView m;

		Gym i = gymService.create();

		m = createEditModelAndView(i);

		return m;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam String gymId) {

		ModelAndView m;
		Gym i;
		
		try {
			i = gymService.findOne(new Integer(gymId));
			Assert.notNull(i);
			m = createEditModelAndView(i);
		} catch (Throwable e) {
			m = new ModelAndView("redirect:list.do");
		}

		return m;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Gym i, BindingResult binding) {

		ModelAndView m;

		if (binding.hasErrors()) {
			m = createEditModelAndView(i);
		} else {
			try {
				gymService.save(i);
				m = new ModelAndView("redirect:list.do");
			} catch (Throwable e) {

				m = createEditModelAndView(i, "gym.commit.error");
			}
		}

		return m;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Gym g, BindingResult binding) {

		ModelAndView m;

		try {
			gymService.delete(g);
			m = new ModelAndView("redirect:list.do");
		} catch (Throwable e) {

			m = createEditModelAndView(g, "gym.commit.error");
		}

		return m;
	}

	/* . */

	protected ModelAndView createEditModelAndView(Gym g) {

		ModelAndView m;

		m = createEditModelAndView(g, null);

		return m;
	}

	protected ModelAndView createEditModelAndView(Gym g, String message) {

		ModelAndView m;

		m = new ModelAndView("gym/administrator/edit");
		m.addObject("requestURI", "/gym/administrator/create.do");

		m.addObject("gym", g);
		m.addObject("message", message);
	

		return m;
	}
	
	
	
}
