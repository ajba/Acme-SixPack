package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query("select b from Book b, Service s where b.customer.id = ?1 and b member of s.books")
	Collection<Book> booksByCustomer(int id);
	
	@Query("select count(distinct b) from Book b where b.service.id=?1")
	Long totalNumberCustomerWhoHaveBrooked(int id);
	
	
	
	
}
