package services;

import java.util.Collection;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.KeyWords;
import repositories.KeyWordsRepository;

@Service
@Transactional
public class KeyWordsService {

	@Autowired
	private KeyWordsRepository keyWordsRepository;

	@Autowired
	private AdministratorService administratorService;

	public KeyWordsService() {
		super();
	}

	public void save(KeyWords k) {

		Assert.assertNotNull(administratorService.findByPrincipal());

		keyWordsRepository.save(k);
	}

	public Collection<KeyWords> findAll() {

		return keyWordsRepository.findAll();
	}

	public KeyWords findOne(int id) {

		return keyWordsRepository.findOne(id);
	}
}
