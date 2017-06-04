package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.KeyWords;

@Repository
public interface KeyWordsRepository extends JpaRepository<KeyWords, Integer> {

}
