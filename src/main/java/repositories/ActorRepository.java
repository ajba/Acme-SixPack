package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	/*
	 * 18.2 - 1 The actor/s who sends/send more spam messages.
	 */

	@Query("select m.sender from Message m where (select count (m1.sender) from Message m1 where m1.sender.id = m.sender.id and m.spam = true) >= all(select count(m2.sender) from Message m2 where m2.spam = true group by m2.sender) and m.spam = true group by m.sender")
	Collection<Actor> sendsMoreSpamMessages();

}
