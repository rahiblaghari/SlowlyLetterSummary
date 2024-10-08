package app.Slowly.LetterArchive.Letter;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    private final LetterRepository repository;
    @Autowired
    public Service(LetterRepository repository) {
        this.repository = repository;
    }

    public List<String> retrieveAllNames() {
        return repository.findAllUniqueUserNames();
    }

    @Transactional
    public void InsertLetter(LetterSummary currentLetter) {
        repository.save(currentLetter);
    }

    public List<LetterSummary> getLetterSummaryFromUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Transactional
    public void removeFriend(String friend) {
        repository.deleteByUserName(friend);
    }

    public List<LetterSummary> getAllLetters() {
        return repository.findAll();
    }

    public LetterSummary getLetterByID(Integer id) {
        Optional<LetterSummary> myLetter = repository.findById(id);
        if (myLetter.isPresent())
            return myLetter.get();
        throw new NullPointerException("Letter Not Found");
    }
}
