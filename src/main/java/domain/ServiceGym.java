package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class ServiceGym extends DomainEntity{

	private Gym gym;
	private Service service;

	public ServiceGym() {
		super();
	}
	
	@Valid
	@ManyToOne(optional=false)
	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	@Valid
	@ManyToOne(optional=false)
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}
