package app.Slowly.LetterArchive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyLetterArchiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyLetterArchiveApplication.class, args);
	}

}
