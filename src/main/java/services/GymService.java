package services;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.FeePayment;
import domain.Gym;
import repositories.GymRepository;

@Service
@Transactional
public class GymService {

	@Autowired
	private GymRepository gymRepository;

	@Autowired
	private AdministratorService administratorService;

	public GymService() {
		super();
	}

	public Gym create() {

		Gym g = new Gym();

		g.setName("");
		g.setDescription("");
		g.setAddress("");
		g.setPhone("");
		g.setFee(0.);
		g.setPicture("");

		// Se pasa como hidden
		g.setFeePayments(new HashSet<FeePayment>());

		return g;
	}

	public void save(Gym g) {

		Assert.assertNotNull(administratorService.findByPrincipal());

		gymRepository.save(g);
	}

	public Gym findOne(int id) {

		return gymRepository.findOne(id);
	}

	public Collection<Gym> findAll() {

		return gymRepository.findAll();
	}

	public Collection<Gym> gymsActivesFees(int id) {

		return gymRepository.gymsActivesFees(id);
	}

	public Collection<Gym> mostPopularGym() {

		return gymRepository.mostPopularGyms();
	}
	
	public Collection<Gym> lessPopularGym() {

		return gymRepository.leastPopularGyms();
	}

	
	public void delete(Gym g) {
		gymRepository.delete(g);

	}

	public Collection<Gym> haveMoreComments() {
		return gymRepository.gymHaveMoreComments();
	}

}
