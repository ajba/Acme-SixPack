package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	/*
	 * 11.4 - 5 The customer/s who has/have paid more fees.
	 */
	@Query("select c from Customer c where c.feePayment.size = (select max(c1.feePayment.size) from Customer c1)")
	Collection<Customer> havePaidMoreFees();

	/*
	 * 11.4 - 6 The customer/s who has/have paid less fees.
	 */
	@Query("select c from Customer c where c.feePayment.size = (select min(c1.feePayment.size) from Customer c1)")
	Collection<Customer> havePaidLessFees();

	/*
	 * The customer/s who has/have been removed more comments.
	 */
	@Query("select c from Customer c where c.deletedComments=(select max(c1.deletedComments) from Customer c1)")
	Collection<Customer> haveDeletedMoreComments();

	@Query("select count(distinct c) from Customer c, Book b where b.gym.id = ?1 and b.customer.id = c.id")
	Integer numberOfCustomersHaveBookedGym(int id);

	@Query("select c from Customer c where c.userAccount.id = ?1")
	Customer findByUserAccountId(int id);
}
