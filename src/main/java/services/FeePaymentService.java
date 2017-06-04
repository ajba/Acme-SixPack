package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Customer;
import domain.FeePayment;
import repositories.FeePaymentRepository;

@Service
@Transactional
public class FeePaymentService {

	@Autowired
	private FeePaymentRepository feePaymentRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AdministratorService administratorService;

	public FeePaymentService() {
		super();
	}

	public FeePayment create() {

		FeePayment f = new FeePayment();

		Customer c = customerService.findByPrincipal();

		if (c.getCreditCard() != null) {
			// Si el customer tenía una tarjeta de credito se le pasa
			f.setCreditCard(c.getCreditCard());
		} else {
			// sino se pone a null y se le pedirá
			f.setCreditCard(null);
		}

		f.setGym(null);
		f.setActiveDate(null);
		f.setAmount(0);

		// Se pasará como hidden
		f.setPlacementMoment(new Date(System.currentTimeMillis()));
		// Ya se calculará al persistir
		f.setInactiveDate(new Date(System.currentTimeMillis()));

		return f;
	}

	public void save(FeePayment f) {

		Assert.assertTrue(
				customerService.findByPrincipal().getCreditCard().getNumber().equals(f.getCreditCard().getNumber()));
		f.setPlacementMoment(new Date(System.currentTimeMillis() - 1));

		Calendar mas6meses = Calendar.getInstance();
		mas6meses.add(Calendar.MONTH, 6);

		Calendar activateDay = Calendar.getInstance();
		activateDay.setTime(f.getActiveDate());

		// Regla de negocio, la fecha de activacion debe ser futura y no
		// superior a 6 meses desde la actual
		Assert.assertTrue(Calendar.getInstance().before(activateDay) && activateDay.before(mas6meses));

		Calendar c = Calendar.getInstance();
		c.setTime(f.getActiveDate());
		c.add(Calendar.DATE, 30);
		f.setInactiveDate(c.getTime());

		f.setAmount(f.getGym().getFee());

		FeePayment fBD = feePaymentRepository.save(f);

		// Guardo en la lista de customer
		Customer customer = customerService.findByPrincipal();
		Collection<FeePayment> feePaymentsCustomer = customer.getFeePayment();
		feePaymentsCustomer.add(fBD);
		customer.setFeePayment(feePaymentsCustomer);
		customerService.save(customer);
	}

	public FeePayment findOne(int id) {

		return feePaymentRepository.findOne(id);
	}

	public Collection<FeePayment> findAll() {

		return feePaymentRepository.findAll();
	}

	public Collection<FeePayment> findByCustomer(int id) {

		return feePaymentRepository.findByCustomer(id);
	}

	public Collection<FeePayment> findActiveFeeGymCustomer(int gymId, int customerId) {

		return feePaymentRepository.findActiveFeeGymCustomer(gymId, customerId);
	}

	public void save(FeePayment f, Date d) {

		Assert.assertNotNull(administratorService.findByPrincipal());

		Calendar new_date = Calendar.getInstance();
		new_date.setTime(d);

		Calendar old_date = Calendar.getInstance();
		old_date.setTime(findOne(f.getId()).getInactiveDate());

		System.out.println("fecha vieja" + old_date.getTime());
		System.out.println("fecha nueva" + new_date.getTime());
		Assert.assertTrue(old_date.before(new_date) || old_date.equals(new_date));
		feePaymentRepository.save(f);
	}
}
