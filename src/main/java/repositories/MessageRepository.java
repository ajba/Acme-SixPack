package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	/*
	 * 18.2 - 2 The average number of messages in an actor's message boxes. La
	 * suma de los mensajes que hay en todas las carpetas entre el número de las
	 * carpetas
	 */
	@Query("select sum(f.messages.size)/count(f)*1.0 from Folder f where f.additionalFolderOwner.id=?1")
	Double averageNumberMessagesInMessageBoxes(int id);
}
