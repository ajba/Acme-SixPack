package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CreditCard;
import domain.FeePayment;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Integer> {

	@Query("select f.creditCard from FeePayment f where f.creditCard.number = ?1")
	CreditCard findCreditCardByNumber(String number);

	@Query("select c.feePayment from Customer c where c.id = ?1")
	Collection<FeePayment> findByCustomer(int id);
	
	@Query("select f from FeePayment f, Customer c where (c.id = ?2) AND (f.gym.id = ?1) AND (f member of c.feePayment) AND (CURRENT_TIMESTAMP between f.activeDate and f.inactiveDate)")
	Collection<FeePayment> findActiveFeeGymCustomer(int gymId, int customerId);
}
