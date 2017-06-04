package repositories;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Gym;
import domain.Service;
import domain.ServiceGym;

@Repository
public interface ServiceGymRepository extends JpaRepository<ServiceGym, Integer> {

	@Query("select sg.service from ServiceGym sg where sg.gym.id = ?1")
	Collection<Service> servicesInGym(int id);

	@Query("select sg.gym from ServiceGym sg where sg.service.id=?1")
	Collection<Gym> servicesOfferGym(int id);

	@Query("select sg.gym,sg.service from ServiceGym sg where sg.service.name like%?1% or sg.service.description like %?1%")
	ArrayList<ArrayList<Object>> findServiceByNameOrDescription2(String s);

}
