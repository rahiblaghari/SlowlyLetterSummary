package app.Slowly.LetterArchive.Letter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterRepository extends JpaRepository<LetterSummary, Integer> {

    // Get all letters corresponding to a specific userName
    List<LetterSummary> findByUserName(String userName);

    // Get all unique user names
    @Query("SELECT DISTINCT u.userName FROM LetterSummary u")
    List<String> findAllUniqueUserNames();

    void deleteByUserName(String friend);
}
