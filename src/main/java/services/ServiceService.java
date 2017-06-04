package services;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Book;
import domain.Comment;
import repositories.ServiceRepository;

@Service
@Transactional
public class ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private AdministratorService administratorService;

	public ServiceService() {
		super();
	}

	public domain.Service create() {

		domain.Service s = new domain.Service();

		s.setName("");
		s.setDescription("");
		s.setPictures(new HashSet<String>());

		// Se pasa como hidden
		s.setBooks(new HashSet<Book>());
		s.setComments(new HashSet<Comment>());

		return s;
	}

	public void save(domain.Service s) {

		Assert.assertNotNull(administratorService.findByPrincipal());

		serviceRepository.save(s);
	}

	public domain.Service findOne(int id) {

		return serviceRepository.findOne(id);
	}

	public Collection<domain.Service> findAll() {

		return serviceRepository.findAll();
	}

	public Collection<domain.Service> serviceBookedByCustomer(int id) {

		return serviceRepository.servicesBookedByCustomer(id);
	}

	public Collection<domain.Service> servicesBookedInGymByCustomer(int gymId, int id) {

		return serviceRepository.servicesBookedInGymByCustomer(gymId, id);
	}

	public Collection<domain.Service> servicesInGym(int id) {

		return serviceRepository.servicesInGym(id);
	}

	public Collection<domain.Service> findServiceByNameOrDescription(String s) {
		return serviceRepository.findServiceByNameOrDescription(s);
	}

	public Collection<domain.Service> findCommentsFromServices() {
		return serviceRepository.findCommentsFromServices();
	}

	public Collection<domain.Service> lessPopularService() {
		return serviceRepository.leastPopularServices();
	}

	public Collection<domain.Service> mostPopularService() {
		return serviceRepository.mostPopulatServices();
	}

	public Collection<domain.Service> haveMoreComments() {
		return serviceRepository.serviceHaveMoreComments();
	}
}
