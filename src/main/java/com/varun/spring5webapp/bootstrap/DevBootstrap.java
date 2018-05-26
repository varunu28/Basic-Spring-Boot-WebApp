package com.varun.spring5webapp.bootstrap;

import com.varun.spring5webapp.model.Author;
import com.varun.spring5webapp.model.Book;
import com.varun.spring5webapp.model.Publisher;
import com.varun.spring5webapp.repositories.AuthorRepository;
import com.varun.spring5webapp.repositories.BookRepository;
import com.varun.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {

        Publisher hc = new Publisher("Harper Collins", "New York");
        Publisher worx = new Publisher("Worx", "Chicago");

        publisherRepository.save(hc);
        publisherRepository.save(worx);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", hc);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development Without EJB", "2344", worx);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
