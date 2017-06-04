package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

	/*
	 * 11.4 - 3 The most popular service/s.
	 */
	 @Query("select s from Service s where s.books.size = (select max(s1.books.size) from Service s1)")
	 Collection<Service> mostPopulatServices();

	/*
	 * 11.4 - 4 The least popular service/s.
	 */

	@Query("select s from Service s where s.books.size = (select min(s1.books.size) from Service s1)")
	Collection<Service> leastPopularServices();

	/*
	 * 23.2 - 2 The service/s that has/have more comments.
	 */
	@Query("select s from Service s where s.comments.size = (select max(s1.comments.size) from Service s1)")
	Collection<Service> serviceHaveMoreComments();

	@Query("select s from Book b, Service s where b.customer.id = ?1 and b member of s.books")
	Collection<Service> servicesBookedByCustomer(int id);
	
	@Query("select b.service from Book b where b.customer.id = ?2 and b.gym.id = ?1 group by b.service")
	Collection<Service> servicesBookedInGymByCustomer(int gymId, int id);
	
	@Query("select sg.service from ServiceGym sg where sg.gym.id = ?1")
	Collection<domain.Service> servicesInGym(int id);
	
	@Query("select s from Service s where s.name like %?1% or s.description like %?1% ")
	Collection<Service> findServiceByNameOrDescription(String s);
	
	@Query("select s,comments from Service s where s.comments is not empty")
	Collection<Service> findCommentsFromServices();
}
