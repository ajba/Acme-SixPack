package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	/*
	 * 23.2 - 3 The average number of comments written by the actors, including
	 * the standard deviation(el maximo menos la media).
	 */
	@Query("select count(c)/(select count(distinct c1.actor)*1.0 from Comment c1) from Comment c")
	Double averageNumberOfCommentsWrittenByTheActors();
	
	@Query("select distinct count(c)*1.0 from Comment c group by c.actor having count(c) > 1")
	Collection<Double> auxForDeviation();

	/*
	 * 23.2 - 4 The average number of comments per gym.
	 */
	@Query("select (sum(g.comments.size)/count(g))*1.0 from Gym g")
	Double averageCommentsPerGym();

	/*
	 * 23.2 - 5 The average number of comments per service.
	 */
	@Query("select (sum(s.comments.size)/count(s))*1.0 from Service s")
	Double averageCommentsPerService();

	@Query("select s.comments from Service s")
	Collection<Comment> findAllCommentsService();

	@Query("select g.comments from Gym g")
	Collection<Comment> findAllCommentsGym();

	@Query("select s.comments from Service s where s.id=?1")
	Collection<Comment> findCommentByService(int id);

	@Query("select g.comments from Gym g where g.id=?1")
	Collection<Comment> findCommentByGym(int id);

}
// select count(c)/(select count(distinct c1.actor)*1.0 from Comment c1),
// (select distinct count(c2) from Comment c2 group by c2.actor having count(c2)
// > 1)-(select count(c3)/(select count(distinct c4.actor)*1.0 from Comment c4)
// from Comment c3) from Comment c;