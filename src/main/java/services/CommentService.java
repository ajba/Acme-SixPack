package services;

import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import domain.Comment;
import repositories.CommentRepository;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ActorService actorService;

	@Autowired
	private AdministratorService administratorService;

	public CommentService() {
		super();
	}

	public Comment create() {

		Comment c = new Comment();
		Actor a = actorService.findByPrincipal();

		c.setRank(0);
		c.setText("");

		// Siempre así al principio, pasan como hidden al formulario
		c.setActor(a);
		// Al persistir se modifica con la forma correcta
		c.setPlacementMoment(new Date(System.currentTimeMillis() - 1));

		return c;
	}

	public Comment save(Comment c) {

		Assert.assertTrue(c.getActor().equals(actorService.findByPrincipal()));
		return commentRepository.save(c);
	}

	public void delete(Comment c) {

		Assert.assertTrue(administratorService.findByPrincipal() != null);

		commentRepository.delete(c);
	}

	public Comment findOne(int id) {

		return commentRepository.findOne(id);
	}

	public Collection<Comment> findAll() {

		return commentRepository.findAll();
	}

	public Collection<Comment> findAllCommentsService() {
		return commentRepository.findAllCommentsService();
	}

	public Collection<Comment> findAllCommentsGym() {
		return commentRepository.findAllCommentsGym();
	}

	public Collection<Comment> findCommentByService(int id) {
		return commentRepository.findCommentByService(id);
	}

	public Collection<Comment> findCommentByGym(int id) {
		return commentRepository.findCommentByGym(id);
	}

	public Double averageNumberOfCommentsWrittenByTheActors() {
		return commentRepository.averageNumberOfCommentsWrittenByTheActors();
	}

	public Double averageCommentsPerGym() {
		return commentRepository.averageCommentsPerGym();
	}

	public Double averageCommentsPerService() {
		return commentRepository.averageCommentsPerService();
	}

	public Collection<Double> auxForDeviation() {
		return commentRepository.auxForDeviation();
	}
}
