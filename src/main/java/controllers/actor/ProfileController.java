package controllers.actor;

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

import controllers.AbstractController;
import domain.Administrator;
import domain.Customer;
import forms.AdministratorUpdateProfileForm;
import forms.CustomerRegistrationForm;
import services.AdministratorService;
import services.CustomerService;

@Controller
@RequestMapping("profile")
public class ProfileController extends AbstractController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AdministratorService administratorService;

	@RequestMapping(value = "/updateprofile", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = null;

		if (administratorService.findByPrincipal() != null) {
			result = createModelAndViewAdministrator(administratorService.construct(), "edit");

		} else if (customerService.findByPrincipal() != null) {
			result = createModelAndViewCustomer(customerService.construct(), "edit");
		}

		return result;
	}

	@RequestMapping(value = "/edit/customer", method = RequestMethod.POST, params = "save")
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
			result = createEditModelAndView(crf,null, disabled, passwordError, termsError, null);
		} else {
			try {
				Customer customer = customerService.reconstructEdit(crf);
				customerService.update(hashPassword(customer));
				result = createModelAndViewAndShowMessage();
			} catch (Throwable oops) {
				result = createEditModelAndView(crf,null, disabled, passwordError, termsError, "customer.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit/administrator", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("customerForm") @Valid AdministratorUpdateProfileForm crf,
			BindingResult binding, @RequestParam("isActived") String isActived) {
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
			result = createEditModelAndView(null,crf, disabled, passwordError, termsError, null);
		} else {
			try {
				Administrator administrator = administratorService.reconstructEdit(crf);
				administratorService.update(hashPassword(administrator));
				result = createModelAndViewAndShowMessage();
			} catch (Throwable oops) {
				result = createEditModelAndView(null,crf, disabled, passwordError, termsError, "customer.commit.error");
			}
		}

		return result;
	}

	protected ModelAndView createModelAndViewAndShowMessage() {

		ModelAndView result;

		result = new ModelAndView("update/update");
		result.addObject("show", true);

		return result;

	}

	protected ModelAndView createModelAndViewAdministrator(AdministratorUpdateProfileForm crf, String action) {

		ModelAndView result = null;
		if (action.equals("edit")) {
			result = new ModelAndView("update/update");
			result.addObject("customerForm", crf);
			result.addObject("isAdmin", true);
			result.addObject("isCustomer", false);

		}

		return result;
	}

	protected ModelAndView createModelAndViewCustomer(CustomerRegistrationForm crf, String action) {

		ModelAndView result = null;
		if (action.equals("edit")) {
			result = new ModelAndView("update/update");
			result.addObject("customerForm", crf);
			result.addObject("isCustomer", true);
			result.addObject("isAdmin", false);

		}

		return result;
	}

	protected ModelAndView createEditModelAndView(CustomerRegistrationForm crfC, AdministratorUpdateProfileForm crfA, boolean disabled, boolean passwordError,
			boolean termsError, String message) {
		ModelAndView result;

		result = new ModelAndView("update/update");
		if(crfC != null){
			result.addObject("customerForm", crfC);
			result.addObject("isCustomer", true);
			result.addObject("isAdmin", false);

		}
		if(crfA != null){
			result.addObject("customerForm", crfA);
			result.addObject("isAdmin", true);
			result.addObject("isCustomer", false);
		}

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
	
	private Administrator hashPassword(Administrator a) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String hash = encoder.encodePassword(a.getUserAccount().getPassword(), null);
		a.getUserAccount().setPassword(hash);
		return a;
	}

}
