package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer> {
	
	/*
	 * 11.4 - 1
	 * The most popular gym/s.
	 * */
	@Query("select g from Gym g where g.feePayments.size = (select max(g1.feePayments.size) from Gym g1 where g1.feePayments.size > 0)")
	Collection<Gym> mostPopularGyms();
	
	/*
	 * 11.4 - 2
	 * The least popular gyms/s.
	 * */
	@Query("select g from Gym g where g.feePayments.size = (select min(g1.feePayments.size) from Gym g1)")
	Collection<Gym> leastPopularGyms();
	
	
	/*
	 * 23.2 - 1
	 * The gyms/s that has/have more comments.
	 * */
	@Query("select g from Gym g where g.comments.size = (select max(g1.comments.size) from Gym g1)")
	Collection<Gym> gymHaveMoreComments();
	
	@Query("select distinct f.gym from FeePayment f, Customer c where (c.id = ?1) AND (f member of c.feePayment) AND (CURRENT_TIMESTAMP between f.activeDate and f.inactiveDate)")
	Collection<Gym> gymsActivesFees(int id);
	

}
