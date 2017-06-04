package controllers.administrator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.FeePayment;
import services.FeePaymentService;

@Controller
@RequestMapping("/feePayment/administrator")
public class FeeAdministratorController {

	@Autowired
	private FeePaymentService feeService;


	public  FeeAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		Collection<FeePayment> fees = feeService.findAll();

		result = new ModelAndView("feePayment/administrator/list");

		result.addObject("fees", fees);
		result.addObject("requestURI", "feePayment/administrator/list.do");

		return result;
	}
	
	

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int feeId, @RequestParam String date) {
		System.out.println("Esntramos en la vista");
		ModelAndView result;
		FeePayment fp = feeService.findOne(feeId);
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd"); 
		System.out.println("Vamos por aqui");
		System.out.println(df);
		Date d;
		try {
			d = df.parse(date);
			fp.setInactiveDate(d);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		

		feeService.save(fp);


		result = new ModelAndView("redirect:list.do");

		return result;
	}
	
	
	
}
