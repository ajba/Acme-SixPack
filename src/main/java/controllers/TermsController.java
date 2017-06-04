package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/terms")
public class TermsController extends AbstractController {

	public TermsController() {
		super();
	}

	@RequestMapping("show")
	public ModelAndView show() {

		return new ModelAndView("terms/show");
	}
}
