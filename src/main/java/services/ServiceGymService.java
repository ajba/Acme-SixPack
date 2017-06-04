package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Gym;
import domain.ServiceGym;
import repositories.ServiceGymRepository;

@Service
@Transactional
public class ServiceGymService {

	@Autowired
	private ServiceGymRepository serviceGymRepository;

	@Autowired
	private AdministratorService administratorService;

	public ServiceGymService() {
		super();
	}

	public ServiceGym create() {

		ServiceGym sg = new ServiceGym();

		sg.setGym(null);
		sg.setService(null);

		return sg;
	}

	public void save(ServiceGym s) {

		Assert.assertNotNull(administratorService.findByPrincipal());

		serviceGymRepository.save(s);
	}

	public void delete(ServiceGym s) {

		Assert.assertNotNull(administratorService.findByPrincipal());

		serviceGymRepository.delete(s);
	}

	public ServiceGym findOne(int id) {

		return serviceGymRepository.findOne(id);
	}

	public Collection<ServiceGym> findAll() {

		return serviceGymRepository.findAll();
	}

	public Collection<domain.Service> servicesInGym(int id) {

		return serviceGymRepository.servicesInGym(id);
	}

	public Collection<Gym> servicesOfferGym(int id) {
		return serviceGymRepository.servicesOfferGym(id);
	}

	public ArrayList<ArrayList<Object>> findServiceByNameOrDescription2(String s) {
		return serviceGymRepository.findServiceByNameOrDescription2(s);
	}
}
