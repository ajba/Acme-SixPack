package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import domain.Administrator;
import domain.Customer;
import repositories.ActorRepository;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AdministratorService adminService;

	public ActorService() {
		super();
	}

	public Actor findByPrincipal() {

		Administrator admin = adminService.findByPrincipal();
		Customer customer = customerService.findByPrincipal();
		Actor a = null;

		if (customer != null) {

			a = customer;
		} else if (admin != null) {

			a = admin;
		}

		return a;
	}

	public List<Actor> findAll() {

		List<Actor> actors = new ArrayList<Actor>();

		actors.addAll(adminService.findAll());

		actors.addAll(customerService.findAll());

		return actors;
	}

	public Actor findOne(int id) {

		Administrator admin = adminService.findOne(id);
		Customer customer = customerService.findOne(id);
		Actor a = null;

		if (customer != null) {

			a = customer;
		} else if (admin != null) {

			a = admin;
		}

		return a;
	}

	public Actor asignarCarpetas(Actor a) {

		Assert.assertNotNull(a);

		a.getInbox().setAdditionalFolderOwner(a);
		a.getOutbox().setAdditionalFolderOwner(a);
		a.getTrashbox().setAdditionalFolderOwner(a);
		a.getSpambox().setAdditionalFolderOwner(a);
		return actorRepository.save(a);
	}

	public Collection<Actor> moreSpamSent() {

		return actorRepository.sendsMoreSpamMessages();
	}
}
