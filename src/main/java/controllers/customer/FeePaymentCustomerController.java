package controllers.customer;

import java.util.Calendar;
import java.util.Collection;

import javax.validation.Valid;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Customer;
import domain.FeePayment;
import domain.Gym;
import services.CustomerService;
import services.FeePaymentService;
import services.GymService;

@Controller
@RequestMapping("/feePayment/customer")
public class FeePaymentCustomerController extends AbstractController {

	@Autowired
	private FeePaymentService feePaymentService;

	@Autowired
	private GymService gymService;

	@Autowired
	private CustomerService customerService;

	public FeePaymentCustomerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int gymId) {

		ModelAndView result;

		Collection<FeePayment> f = feePaymentService.findActiveFeeGymCustomer(gymId,
				customerService.findByPrincipal().getId());
		
		result = new ModelAndView("feePayment/customer/list");
		
		result.addObject("feePayments", f);
		result.addObject("requestURI", "feePayment/customer/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView m;

		boolean expired = true;
		boolean requiredCreditCard = false;

		FeePayment f = feePaymentService.create();

		if (f.getCreditCard() != null) {
			int monthCC = f.getCreditCard().getExpirationMonth();
			int yearCC = f.getCreditCard().getExpirationYear();
			int month = Calendar.getInstance().get(Calendar.MONTH);
			int year = Calendar.getInstance().get(Calendar.YEAR);

			if (yearCC >= year && monthCC >= month + 1) {
				expired = false;
			}
		} else {
			requiredCreditCard = true;
		}

		m = createEditModelAndView(f, requiredCreditCard, expired);

		return m;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FeePayment f, BindingResult binding) {

		ModelAndView m = null;
		boolean expired = false;
		boolean requiredCreditcard = false;

		Customer c = customerService.findByPrincipal();

		for (FieldError i : binding.getFieldErrors()) {
			if (i.getField().equals("creditCard.number") || i.getField().equals("creditCard.expirationMonth")
					|| i.getField().equals("creditCard.CVV") || i.getField().equals("creditCard.expirationYear")) {
				requiredCreditcard = true;
			}
		}

		try {
			int monthForm = f.getCreditCard().getExpirationMonth();
			int yearForm = f.getCreditCard().getExpirationYear();
			int month = Calendar.getInstance().get(Calendar.MONTH);
			int year = Calendar.getInstance().get(Calendar.YEAR);

			Assert.assertTrue(yearForm >= year);
			Assert.assertTrue(monthForm >= month + 1);
		} catch (Throwable e) {
			expired = true;
			m = createEditModelAndView(f, "feePayment.commit.error", requiredCreditcard, expired);
		}

		if (!expired) {
			if (binding.hasErrors()) {
				m = createEditModelAndView(f, requiredCreditcard, expired);
			} else {
				try {
					c.setCreditCard(f.getCreditCard());
					customerService.save(c);
					feePaymentService.save(f);
					m = new ModelAndView("redirect:../../gym/customer/list.do");
				} catch (Throwable e) {
					m = createEditModelAndView(f, "feePayment.commit.error", requiredCreditcard, expired);
				}
			}
		}

		return m;
	}

	/* . */
	protected ModelAndView createEditModelAndView(FeePayment f, boolean requiredCreditCard, boolean expired) {

		ModelAndView m;

		m = createEditModelAndView(f, null, requiredCreditCard, expired);

		return m;
	}

	protected ModelAndView createEditModelAndView(FeePayment f, String message, boolean requiredCreditCard,
			boolean expired) {

		ModelAndView m;

		Collection<Gym> gyms = gymService.findAll();

		m = new ModelAndView("feePayment/customer/create");
		m.addObject("requestURI", "/feePayment/customer/create.do");
		m.addObject("feePayment", f);
		m.addObject("expired", expired);
		m.addObject("gyms", gyms);
		m.addObject("requiredCreditCard", requiredCreditCard);
		m.addObject("message", message);

		return m;
	}
}