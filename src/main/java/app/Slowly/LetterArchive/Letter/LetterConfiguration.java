package app.Slowly.LetterArchive.Letter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class LetterConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(LetterRepository repository) {
        return args -> {
            LetterSummary letter1 = new LetterSummary(
                    1,
                    "Samantha",
                    "Likes Computer Science and Dogs"
            );
            LetterSummary letter2 = new LetterSummary(
                    2,
                    "Samantha",
                    "Reads Books"
            );
            repository.saveAll(List.of(letter1,letter2));
        };
    }

}

