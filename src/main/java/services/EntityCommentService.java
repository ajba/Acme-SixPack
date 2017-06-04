package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.EntityComment;
import repositories.EntityCommentRepository;

@Service
@Transactional
public class EntityCommentService {

	@Autowired
	private EntityCommentRepository entityCommentRepository;

	public EntityCommentService() {
		super();
	}
	
	

	public EntityComment findOne(int id) {

		return entityCommentRepository.findOne(id);
	}
	
	public void delete(EntityComment ec){
		entityCommentRepository.delete(ec);
	}
	
	public EntityComment save(EntityComment ec){
		return entityCommentRepository.save(ec);
	}
	
	public Long findEntityCommentByCommentId(int id){
		return entityCommentRepository.findEntityCommentByCommentId(id);
	}
}
