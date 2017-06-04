package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.EntityComment;

@Repository
public interface EntityCommentRepository extends JpaRepository<EntityComment, Integer> {
	
	@Query("select ec.id from EntityComment ec,Comment c where c.id=?1 and c member of ec.comments")
	Long findEntityCommentByCommentId(int id);
	
	
	

}
