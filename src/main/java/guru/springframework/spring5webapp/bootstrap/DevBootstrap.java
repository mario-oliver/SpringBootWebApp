package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){
        Publisher publisher = new Publisher("Collins LLC", "NYC");
        publisherRepository.save(publisher);
        Publisher publisher2 = new Publisher("Harper LLC", "DC");
        publisherRepository.save(publisher2);

        //Eric
        Author Eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", publisher);
        Eric.getBooks().add(ddd);
        ddd.getAuthors().add(Eric);

        authorRepository.save(Eric);
        bookRepository.save(ddd);

        Author Mo = new Author("M", "O");
        Book book2 = new Book("eyp", "1235", publisher2);
        Mo.getBooks().add(book2);

        authorRepository.save(Mo);
        bookRepository.save(book2);
    }
}
