package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;
import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;

@SpringBootApplication
public class BookListApplication {
	private static final Logger log = LoggerFactory.getLogger(BookListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookListApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository srepository, CategoryRepository drepository) {
		return (args) -> {
			log.info("save a couple of books");
			drepository.save(new Category("Sci-fi"));
			drepository.save(new Category("Fantasia"));
			drepository.save(new Category("Draama"));
		    
			srepository.save(new Book("Tieteisseikkailu", "Tieteisseikkailukirjailija", 2020, 20.00, drepository.findByName("Sci-fi").get(0)));
			srepository.save(new Book("Fantasiaseikkailu", "Fantasiaseikkailukirjailija", 2019, 20.00, drepository.findByName("Fantasia").get(0)));
			
			log.info("fetch all books");
			for (Book book : srepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}