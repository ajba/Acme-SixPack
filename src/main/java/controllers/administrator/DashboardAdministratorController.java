package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Customer;
import domain.Gym;
import domain.Service;
import services.ActorService;
import services.CommentService;
import services.CustomerService;
import services.GymService;
import services.MessageService;
import services.ServiceService;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController {

	@Autowired
	private GymService gymService;

	@Autowired
	private ServiceService serviceService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private CommentService commentService;

	public DashboardAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result = new ModelAndView("dashboard/administrator/list");

		Collection<Gym> mpg = gymService.mostPopularGym();
		Collection<Gym> lpg = gymService.lessPopularGym();
		Collection<Service> mps = serviceService.mostPopularService();
		Collection<Service> lps = serviceService.lessPopularService();
		Collection<Customer> pmf = customerService.paidMoreFees();
		Collection<Customer> plf = customerService.paidLessFees();
		Collection<Actor> ams = actorService.moreSpamSent();
		Double averageMessages = calcAverageMessages();
		Collection<Gym> ghmc = gymService.haveMoreComments();
		Collection<Service> shmc = serviceService.haveMoreComments();
		Double averageComments = commentService.averageNumberOfCommentsWrittenByTheActors();
		Double deviation = calcDeviation(averageComments);
		Double averageCommentsPerGym = commentService.averageCommentsPerGym();
		Double averageCommentsPerService = commentService.averageCommentsPerService();
		Collection<Customer> crmc = customerService.hasBeenRemovedMoreComments();

		result.addObject("requestURI", "dashboard/administrator/list.do");
		result.addObject("mpg", mpg);
		result.addObject("lpg", lpg);
		result.addObject("mps", mps);
		result.addObject("lps", lps);
		result.addObject("pmf", pmf);
		result.addObject("plf", plf);
		result.addObject("ams", ams);
		result.addObject("averageMessages", averageMessages);
		result.addObject("ghmc", ghmc);
		result.addObject("shmc", shmc);
		result.addObject("averageComments", averageComments);
		result.addObject("deviation", deviation);
		result.addObject("averageCommentsPerGym", averageCommentsPerGym);
		result.addObject("averageCommentsPerService", averageCommentsPerService);
		result.addObject("crmc", crmc);

		return result;
	}

	private Double calcDeviation(Double averageComments) {

		Collection<Double> auxForDeviation = commentService.auxForDeviation();
		Double desviation = 0.;

		for (Double d : auxForDeviation) {
			desviation += Math.pow((d - averageComments), 2);
		}

		desviation = desviation / auxForDeviation.size();

		return Math.sqrt(desviation);
	}

	private Double calcAverageMessages() {

		Double d = 0.;
		int auxNull = 0;

		Collection<Actor> actors = actorService.findAll();
		for (Actor a : actors) {
			if (messageService.averageMessagesActor(a.getId()) == null) {
				auxNull++;
			} else {
				d += messageService.averageMessagesActor(a.getId());
			}
		}

		return d / actors.size() - auxNull;
	}
}