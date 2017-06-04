package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Customer;
import forms.CustomerRegistrationForm;
import services.CustomerService;

@Controller
@RequestMapping("/register")
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerService customerService;

	public CustomerController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		CustomerRegistrationForm crf = new CustomerRegistrationForm();
		String action = "edit";
		result = createModelAndView(crf, action);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("customerForm") @Valid CustomerRegistrationForm crf, BindingResult binding,
			@RequestParam("isActived") String isActived) {
		ModelAndView result = null;
		boolean disabled;
		boolean passwordError = false;
		boolean termsError = false;
		
		if (isActived.equals("true")) {
			disabled = true;
		} else {
			disabled = false;
		}
		
		if (!crf.getPassword().equals(crf.getRepeatPassword())) {
			passwordError = true;
		}
		
		if (!crf.getAcceptTerms()) {
			termsError = true;
		}

		if (binding.hasErrors() || passwordError || termsError) {
			result = createEditModelAndView(crf, disabled, passwordError, termsError, null);
		} else {
			try {
				Customer customer = customerService.reconstruct(crf);
				customerService.save(hashPassword(customer));
				result = createModelAndViewAndShowMessage();
			} catch (Throwable oops) {
				result = createEditModelAndView(crf, disabled, passwordError, termsError, "customer.commit.error");
			}
		}

		return result;
	}

	protected ModelAndView createModelAndViewAndShowMessage() {

		ModelAndView result;

		result = new ModelAndView("register/register");
		result.addObject("show", true);

		return result;

	}

	protected ModelAndView createModelAndView(CustomerRegistrationForm crf, String action) {

		ModelAndView result = null;
		if (action.equals("edit")) {
			result = new ModelAndView("register/register");
			result.addObject("customerForm", crf);
			result.addObject("disabled", true);
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(CustomerRegistrationForm crf, boolean disabled, boolean passwordError, boolean termsError, String message) {
		ModelAndView result;

		result = new ModelAndView("register/register");
		result.addObject("customerForm", crf);
		result.addObject("message", message);
		result.addObject("disabled", disabled);
		result.addObject("passwordError", passwordError);
		result.addObject("termsError", termsError);

		return result;
	}

	private Customer hashPassword(Customer c) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String hash = encoder.encodePassword(c.getUserAccount().getPassword(), null);
		c.getUserAccount().setPassword(hash);
		return c;
	}

}
