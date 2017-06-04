package controllers;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;
import domain.Gym;
import domain.Service;
import services.CommentService;
import services.GymService;
import services.ServiceService;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private GymService gymService;
	
	

	public CommentController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ModelAndView list(@RequestParam int id,@RequestParam String obj) {
		ModelAndView result;
		result = new ModelAndView("comment/list");
		Collection<Comment> comments = null;

		if(obj.equals("service")){
			Service s = serviceService.findOne(id);
			comments = commentService.findCommentByService(id);
			result.addObject("service", s);

		}else{
			Gym g = gymService.findOne(id);
			comments = commentService.findCommentByGym(id);
			result.addObject("gym", g);
		}

		result.addObject("comments", comments);


		result.addObject("requestURI", "comment/list.do");

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam int id,@RequestParam String obj) {
		
		Service s = new Service();
		Gym g = new Gym();
		ModelAndView m;	
		Comment c = commentService.create();
		
		if(obj.equals("service")){
			s = serviceService.findOne(id);
			m = createModelAndView(c,s,null,"create",null);

		}else{
			g = gymService.findOne(id);
			m = createModelAndView(c,null,g,"create",null);

		}
		
		
		return m;
	}
	

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Comment c,BindingResult binding,@RequestParam(required=false) String service_id,@RequestParam(required=false) String gym_id) {

		
		ModelAndView m = null;
		Date d = new Date(System.currentTimeMillis()-1);
		c.setPlacementMoment(d);
		
		if(service_id != null){

			if (binding.hasErrors()) {
				m = createModelAndView(c,null,null,"edit",null);
				//m = new ModelAndView("redirect:../service/list.do");


			} else {
				try {
					Service s = serviceService.findOne(new Integer(service_id));
					Comment comment = commentService.save(c);
					s.getComments().add(comment);
					serviceService.save(s);
	
					m = new ModelAndView("redirect:../service/list.do");
				} catch (Throwable e) {
					m = createModelAndView(c,null,null,null,"gym.commit.error");
				}
			}
		}else{
			
			if (binding.hasErrors()) {
				m = createModelAndView(c,null,null,"edit",null);
			} else {
				try {
					Gym g = gymService.findOne(new Integer(gym_id));
					Comment comment = commentService.save(c);
					g.getComments().add(comment);
					gymService.save(g);
	
					m = new ModelAndView("redirect:../gym/list.do");
				} catch (Throwable e) {
					m = createModelAndView(c,null,null,null,"gym.commit.error");
				}
			}
			
			
			
		}

		return m;	
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam int comment_id, @RequestParam(required=false) String service_id,@RequestParam(required=false) String gym_id) {

		ModelAndView m=null;

		if(service_id != null){
			try {
				Service s = serviceService.findOne(new Integer(service_id));
				Comment c = commentService.findOne(comment_id);
				s.getComments().remove(c);
				serviceService.save(s);
				commentService.delete(c);
				m = new ModelAndView("redirect:../service/list.do");
			} catch (Throwable e) {
				
				//m = createEditModelAndView(g, "gym.commit.error");
			}

		}else{
			try {
				Gym g = gymService.findOne(new Integer(gym_id));
				Comment c = commentService.findOne(comment_id);
				g.getComments().remove(c);
				gymService.save(g);
				commentService.delete(c);
				m = new ModelAndView("redirect:../service/list.do");
			} catch (Throwable e) {
				
				//m = createEditModelAndView(g, "gym.commit.error");
			}
		}
			
		return m;
	}

	
	
	protected ModelAndView createModelAndViewAndShowMessage() {

		ModelAndView result;

		result = new ModelAndView("register/register");
		result.addObject("show", true);

		return result;

	}

	protected ModelAndView createModelAndView(Comment c,Service s,Gym g,String action,String message) {

		ModelAndView result = null;
		if (action.equals("edit")) {
			result = new ModelAndView("comment/edit");
			result.addObject("message", message);
			result.addObject("comment", c);
		}else if(action.equals("create")){
			result = new ModelAndView("comment/create");
			result.addObject("comment", c);
			result.addObject("service", s);
			result.addObject("gym", g);
			result.addObject("nuevo", true);

		}

		return result;
	}
}
