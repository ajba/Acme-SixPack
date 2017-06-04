package services;

import java.util.Collection;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.SocialIdentity;
import repositories.SocialIdentityRepository;

@Service
@Transactional
public class SocialIdentityService {

	@Autowired
	private SocialIdentityRepository socialIdentityRepository;

	@Autowired
	private CustomerService customerService;

	public SocialIdentityService() {
		super();
	}

	public SocialIdentity create() {

		SocialIdentity s = new SocialIdentity();

		s.setNick("");
		s.setSocialNetworkName("");
		s.setHomePage("");
		s.setPicture("");

		return s;
	}

	public void save(SocialIdentity s) {

		Assert.assertNotNull(customerService.findByPrincipal());

		socialIdentityRepository.save(s);
	}
	
	public void delete(SocialIdentity s){
		
		Assert.assertTrue(customerService.findByPrincipal().getSocialIdentity().contains(s));

		socialIdentityRepository.delete(s);
	}

	public SocialIdentity findOne(int id) {

		return socialIdentityRepository.findOne(id);
	}

	public Collection<SocialIdentity> findAll() {

		return socialIdentityRepository.findAll();
	}
}
