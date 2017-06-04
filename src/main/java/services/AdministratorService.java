package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import forms.AdministratorUpdateProfileForm;
import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	public AdministratorService() {
		super();
	}

	public Administrator findByPrincipal() {

		UserAccount userAccount = LoginService.getPrincipal();
		Administrator result = null;
		result = administratorRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Collection<Administrator> findAll() {

		return administratorRepository.findAll();
	}

	public Administrator findOne(int id) {

		return administratorRepository.findOne(id);
	}

	public AdministratorUpdateProfileForm construct() {
		Administrator administrator = findByPrincipal();
		AdministratorUpdateProfileForm crf = new AdministratorUpdateProfileForm();
		crf.setName(administrator.getName());
		crf.setSurname(administrator.getSurname());
		crf.setUsername(administrator.getUserAccount().getUsername());
		crf.setPassword(administrator.getUserAccount().getPassword());
		crf.setPhone(administrator.getPhone());
		crf.setId(administrator.getId());

		return crf;

	}

	public Administrator reconstructEdit(AdministratorUpdateProfileForm crf) {

		Administrator administrator = findByPrincipal();

		administrator.setName(crf.getName());
		administrator.setSurname(crf.getSurname());
		administrator.setPhone(crf.getPhone());
		administrator.getUserAccount().setUsername(crf.getUsername());
		administrator.getUserAccount().setPassword(crf.getPassword());

		return administrator;

	}

	public void update(Administrator administrator) {
		administratorRepository.save(administrator);
	}
}
